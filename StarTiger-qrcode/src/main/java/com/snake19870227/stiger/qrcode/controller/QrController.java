package com.snake19870227.stiger.qrcode.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;
import com.snake19870227.stiger.qrcode.entity.bo.QrCodeBo;
import com.snake19870227.stiger.qrcode.entity.dto.CreateQrCodeResponse;
import com.snake19870227.stiger.web.exception.RestRequestException;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/14
 */
@Controller
@RequestMapping(path = "/qr")
public class QrController {

    private static final Logger logger = LoggerFactory.getLogger(QrController.class);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CreateQrCodeResponse create(@RequestParam(name = "content") String content,
                                       @RequestParam(name = "errorLevel", defaultValue = "M") String errorLevel,
                                       @RequestParam(name = "imageType", defaultValue = "png") String imageType,
                                       @RequestParam(name = "imageWidth", defaultValue = "300") int imageWidth,
                                       @RequestParam(name = "imageHeight", defaultValue = "300") int imageHeight) {
        try {
            byte[] imageBytes = generate(
                    content, ErrorCorrectionLevel.valueOf(errorLevel),
                    imageType, imageWidth, imageHeight, null
            );
            QrCodeBo qrCodeBo = new QrCodeBo(content, errorLevel, imageType, imageWidth, imageHeight, imageBytes);
            return RestResponseBuilder.createSuccessRestResp(qrCodeBo, CreateQrCodeResponse.class);
        } catch (Exception e) {
            throw new RestRequestException("1001", e);
        }
    }

    @GetMapping
    public void create(@RequestParam(name = "content") String content,
                       @RequestParam(name = "errorLevel", defaultValue = "M") String errorLevel,
                       @RequestParam(name = "imageType", defaultValue = "png") String imageType,
                       @RequestParam(name = "imageWidth", defaultValue = "300") int imageWidth,
                       @RequestParam(name = "imageHeight", defaultValue = "300") int imageHeight,
                       HttpServletResponse response) {
        try {
            byte[] imageBytes = generate(
                    content, ErrorCorrectionLevel.valueOf(errorLevel),
                    imageType, imageWidth, imageHeight, null
            );
            ServletUtil.write(response, new ByteArrayInputStream(imageBytes), "image/" + imageType);
        } catch (Exception e) {
            throw new RestRequestException("1001", e);
        }
    }

    @PostMapping(params = "content", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CreateQrCodeResponse create(@RequestParam(name = "content") String content,
                                       @RequestParam(name = "errorLevel", defaultValue = "M") String errorLevel,
                                       @RequestParam(name = "imageType", defaultValue = "png") String imageType,
                                       @RequestParam(name = "imageWidth", defaultValue = "300") int imageWidth,
                                       @RequestParam(name = "imageHeight", defaultValue = "300") int imageHeight,
                                       @RequestParam(name = "logoFile") MultipartFile logoFile) {
        try {
            byte[] imageBytes = generate(
                    content, ErrorCorrectionLevel.valueOf(errorLevel),
                    imageType, imageWidth, imageHeight,
                    ImgUtil.toImage(logoFile.getBytes())
            );
            QrCodeBo qrCodeBo = new QrCodeBo(content, errorLevel, imageType, imageWidth, imageHeight, imageBytes);
            return RestResponseBuilder.createSuccessRestResp(qrCodeBo, CreateQrCodeResponse.class);
        } catch (Exception e) {
            throw new RestRequestException("1001", e);
        }
    }

    @GetMapping(params = {"content", "logoFileUrl" }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CreateQrCodeResponse create(@RequestParam(name = "content") String content,
                                       @RequestParam(name = "errorLevel", defaultValue = "M") String errorLevel,
                                       @RequestParam(name = "imageType", defaultValue = "png") String imageType,
                                       @RequestParam(name = "imageWidth", defaultValue = "300") int imageWidth,
                                       @RequestParam(name = "imageHeight", defaultValue = "300") int imageHeight,
                                       @RequestParam(name = "logoFileUrl") String logoFileUrl) {
        try {
            logoFileUrl = URLUtil.normalize(logoFileUrl);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            HttpUtil.download(logoFileUrl, outputStream, true);
            byte[] imageBytes = generate(
                    content, ErrorCorrectionLevel.valueOf(errorLevel),
                    imageType, imageWidth, imageHeight,
                    ImgUtil.toImage(outputStream.toByteArray())
            );
            QrCodeBo qrCodeBo = new QrCodeBo(content, errorLevel, imageType, imageWidth, imageHeight, imageBytes);
            return RestResponseBuilder.createSuccessRestResp(qrCodeBo, CreateQrCodeResponse.class);
        } catch (Exception e) {
            throw new RestRequestException("1001", e);
        }
    }

    @GetMapping(params = {"content", "logoFileUrl" })
    public void create(@RequestParam(name = "content") String content,
                       @RequestParam(name = "errorLevel", defaultValue = "M") String errorLevel,
                       @RequestParam(name = "imageType", defaultValue = "png") String imageType,
                       @RequestParam(name = "imageWidth", defaultValue = "300") int imageWidth,
                       @RequestParam(name = "imageHeight", defaultValue = "300") int imageHeight,
                       @RequestParam(name = "logoFileUrl") String logoFileUrl,
                       HttpServletResponse response) {
        try {
            logoFileUrl = URLUtil.normalize(logoFileUrl);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            HttpUtil.download(logoFileUrl, outputStream, true);
            byte[] imageBytes = generate(
                    content, ErrorCorrectionLevel.valueOf(errorLevel),
                    imageType, imageWidth, imageHeight,
                    ImgUtil.toImage(outputStream.toByteArray())
            );
            ServletUtil.write(response, new ByteArrayInputStream(imageBytes), "image/" + imageType);
        } catch (Exception e) {
            throw new RestRequestException("1001", e);
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void read(@RequestParam(name = "file") MultipartFile file,
                     @RequestParam(name = "autoRedirect", defaultValue = "true") boolean autoRedirect,
                     HttpServletResponse response) {
        try {
            String content = QrCodeUtil.decode(file.getInputStream());
            boolean canRedirect = StrUtil.startWithIgnoreCase(content, "http://")
                    || StrUtil.startWithIgnoreCase(content, "https://");
            if (autoRedirect && canRedirect) {
                response.sendRedirect(content);
            } else {
                ServletUtil.write(response, content, MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8");
            }
        } catch (Exception e) {
            throw new RestRequestException("1002", e);
        }
    }

    @GetMapping(params = "fileUrl")
    public void read(@RequestParam(name = "fileUrl") String fileUrl,
                     @RequestParam(name = "autoRedirect", defaultValue = "true") boolean autoRedirect,
                     HttpServletResponse response) {
        try {
            fileUrl = URLUtil.normalize(fileUrl);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            HttpUtil.download(fileUrl, outputStream, true);
            String content = QrCodeUtil.decode(ImgUtil.toImage(outputStream.toByteArray()));
            boolean canRedirect = StrUtil.startWithIgnoreCase(content, "http://")
                    || StrUtil.startWithIgnoreCase(content, "https://");
            if (autoRedirect && canRedirect) {
                response.sendRedirect(content);
            } else {
                ServletUtil.write(response, content, MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8");
            }
        } catch (Exception e) {
            throw new RestRequestException("1002", e);
        }
    }

    private byte[] generate(String content,
                            ErrorCorrectionLevel errorLevel,
                            String imageType,
                            int imageWidth,
                            int imageHeight,
                            Image logoImage) {
        QrConfig config = createQrConfig(imageWidth, imageHeight, errorLevel);
        if (logoImage != null) {
            config.setImg(logoImage);
        }
        return generate(content, imageType, config);
    }

    private byte[] generate(String content, String imageType, QrConfig config) {
        ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream();
        QrCodeUtil.generate(content, config, imageType, imageOutputStream);
        return imageOutputStream.toByteArray();
    }

    private QrConfig createQrConfig(int imageWidth, int imageHeight, ErrorCorrectionLevel errorLevel) {
        return QrConfig.create()
                .setWidth(imageWidth)
                .setHeight(imageHeight)
                .setErrorCorrection(errorLevel);
    }
}
