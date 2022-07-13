package com.xziying.gobang.exception;

public class NotToRegretException extends RuntimeException{
    public NotToRegretException() {
        super("当前不可悔棋！");
    }
}
