package org.ds.msgboard.exception;

public class MsgException extends RuntimeException {

    public MsgException(String message) {
        super(message);
    }

    public MsgException(String message, Throwable cause) {
        super(message, cause);
    }
}
