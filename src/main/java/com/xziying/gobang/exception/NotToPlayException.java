package com.xziying.gobang.exception;

public class NotToPlayException extends RuntimeException{
    public NotToPlayException() {
        super("当前无法下棋！");
    }
}
