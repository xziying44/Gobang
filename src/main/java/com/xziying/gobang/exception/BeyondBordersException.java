package com.xziying.gobang.exception;

public class BeyondBordersException extends RuntimeException{
    public BeyondBordersException() {
        super("棋子超出棋盘边界！");
    }
}
