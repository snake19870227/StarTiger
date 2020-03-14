package com.snake19870227.stiger.qrcode.entity.bo;

import cn.hutool.core.codec.Base64;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/13
 */
public class QrCodeBo {

    private String content;

    private String errorLevel;

    private String imageType;

    private int width;

    private int height;

    private String base64Image;

    public QrCodeBo() {
    }

    public QrCodeBo(String content, String errorLevel, String imageType, int width, int height) {
        this.content = content;
        this.errorLevel = errorLevel;
        this.imageType = imageType;
        this.width = width;
        this.height = height;
    }

    public QrCodeBo(String content, String errorLevel, String imageType, int width, int height, String base64Image) {
        this.content = content;
        this.errorLevel = errorLevel;
        this.imageType = imageType;
        this.width = width;
        this.height = height;
        this.base64Image = base64Image;
    }

    public QrCodeBo(String content, String errorLevel, String imageType, int width, int height, byte[] imageBytes) {
        this.content = content;
        this.errorLevel = errorLevel;
        this.imageType = imageType;
        this.width = width;
        this.height = height;
        this.base64Image = Base64.encode(imageBytes);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(String errorLevel) {
        this.errorLevel = errorLevel;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
