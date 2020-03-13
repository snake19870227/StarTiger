package com.snake19870227.stiger.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/3/13
 */
public class QrCodeCreateTest {

    private static final Logger logger = LoggerFactory.getLogger(QrCodeCreateTest.class);

    private String globalContent = "https://github.com/snake19870227";

    private File testImageFile = new File(this.getClass().getResource("/test.png").getFile());

    @Test
    public void test1() {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix
                    = qrCodeWriter.encode(globalContent, BarcodeFormat.QR_CODE, 250, 250);
            MatrixToImageWriter.writeToPath(bitMatrix, "png", Paths.get(testImageFile.toURI()));
        } catch (WriterException | IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void test2() throws IOException, FormatException, ChecksumException, NotFoundException {
        BufferedImage image = ImageIO.read(this.testImageFile);
        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(bitmap);
        String text = result.getText();
        logger.info(text);
        Assert.assertEquals(text, globalContent);
    }
}
