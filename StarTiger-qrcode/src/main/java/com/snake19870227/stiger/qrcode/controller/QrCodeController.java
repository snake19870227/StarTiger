package com.snake19870227.stiger.qrcode.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;
import com.snake19870227.stiger.qrcode.entity.bo.QrCodeBo;
import com.snake19870227.stiger.qrcode.entity.dto.CreateQrCodeResponse;
import com.snake19870227.stiger.qrcode.entity.dto.ReadQrCodeResponse;
import com.snake19870227.stiger.web.exception.RestRequestException;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/13
 */
@Controller
@RequestMapping(path = "/qrcode")
public class QrCodeController {

    private static final Logger logger = LoggerFactory.getLogger(QrCodeController.class);

    private final QRCodeWriter qrCodeWriter;

    public QrCodeController() {
        qrCodeWriter = new QRCodeWriter();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CreateQrCodeResponse create(@RequestParam(name = "content") String content,
                                       @RequestParam(name = "errorLevel", defaultValue = "L") String errorLevel,
                                       @RequestParam(name = "imageType", defaultValue = "png") String imageType,
                                       @RequestParam(name = "imageWidth", defaultValue = "200") int imageWidth,
                                       @RequestParam(name = "imageHeight", defaultValue = "200") int imageHeight) {
        try {
            byte[] imageBytes = createImageBytes(content, errorLevel, imageType, imageWidth, imageHeight);
            QrCodeBo qrCodeBo = createQrCodeBo(content, errorLevel, imageType, imageWidth, imageHeight, imageBytes);
            return RestResponseBuilder.createSuccessRestResp(qrCodeBo, CreateQrCodeResponse.class);
        } catch (WriterException | IOException e) {
            throw new RestRequestException("1001", e);
        }
    }

    @GetMapping
    public void create(@RequestParam(name = "content") String content,
                       @RequestParam(name = "errorLevel", defaultValue = "L") String errorLevel,
                       @RequestParam(name = "imageType", defaultValue = "png") String imageType,
                       @RequestParam(name = "imageWidth", defaultValue = "200") int imageWidth,
                       @RequestParam(name = "imageHeight", defaultValue = "200") int imageHeight,
                       HttpServletResponse response) {
        try {
            byte[] imageBytes = createImageBytes(content, errorLevel, imageType, imageWidth, imageHeight);
            ServletUtil.write(response, new ByteArrayInputStream(imageBytes), "image/" + imageType);
        } catch (WriterException | IOException e) {
            throw new RestRequestException("1001", e);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReadQrCodeResponse read(@RequestParam(name = "file") MultipartFile file) {
        try {
            String content = readQrCode(file);
            return RestResponseBuilder.createSuccessRestResp(content, ReadQrCodeResponse.class);
        } catch (IOException | NotFoundException | ChecksumException | FormatException e) {
            throw new RestRequestException("1002", e);
        }
    }

    @PostMapping
    public void read(@RequestParam(name = "file") MultipartFile file,
                                   HttpServletResponse response) {
        try {
            String content = readQrCode(file);
            if (StrUtil.startWithIgnoreCase(content, "http://")
                    || StrUtil.startWithIgnoreCase(content, "https://")) {
                response.sendRedirect(content);
            } else {
                ServletUtil.write(response, content, MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8");
            }
        } catch (IOException | NotFoundException | ChecksumException | FormatException e) {
            throw new RestRequestException("1002", e);
        }
    }

    private byte[] createImageBytes(String content, String errorLevel, String imageType, int imageWidth, int imageHeight) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>(2);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.valueOf(errorLevel));
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, imageWidth, imageHeight, hints);
        ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, imageType, imageOutputStream);
        return imageOutputStream.toByteArray();
    }

    private QrCodeBo createQrCodeBo(String content, String errorLevel, String imageType, int imageWidth, int imageHeight, byte[] imageBytes) {
        QrCodeBo qrCodeBo = new QrCodeBo();
        qrCodeBo.setContent(content);
        qrCodeBo.setErrorLevel(errorLevel);
        qrCodeBo.setImageType(imageType);
        qrCodeBo.setWidth(imageWidth);
        qrCodeBo.setHeight(imageHeight);
        qrCodeBo.setBase64Image(Base64.encode(imageBytes));
        return qrCodeBo;
    }

    private String readQrCode(MultipartFile file) throws FormatException, ChecksumException, NotFoundException, IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(bitmap);
        return result.getText();
    }
}
