package org.unibl.etf.master.crypto.wallet.exception;

public class NotEnoughFundsException extends RuntimeException {

    public NotEnoughFundsException(String message) {
        super(message);
    }

    public NotEnoughFundsException(Class<?> clazz, long id) {
        super(getFormattedMessage(clazz.getSimpleName(), String.valueOf(id)));
    }

    public NotEnoughFundsException(Class<?> clazz, String id) {
        super(getFormattedMessage(clazz.getSimpleName(), id));
    }

    private static String getFormattedMessage(String entity, String id) {
        return String.format("%s with hash %s doesn't have enough funds.", entity, id);
    }
}

