package org.unibl.etf.master.crypto.wallet.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(Class<?> clazz) {
        super(getFormattedMessage(clazz.getSimpleName()));
    }

    private static String getFormattedMessage(String entity) {
        return String.format("Given %s already exists.", entity);
    }
}