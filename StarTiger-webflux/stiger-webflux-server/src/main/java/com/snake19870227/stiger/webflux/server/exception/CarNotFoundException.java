package com.snake19870227.stiger.webflux.server.exception;

/**
 * @author Bu HuaYang
 */
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException() {
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}
