package com.xziying.gobang.exception;

public class WhereConflictException extends RuntimeException{
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public WhereConflictException() {
        super("当前位置不可下！");
    }
}
