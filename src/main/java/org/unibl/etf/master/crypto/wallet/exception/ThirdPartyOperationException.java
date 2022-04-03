package org.unibl.etf.master.crypto.wallet.exception;

public class ThirdPartyOperationException extends RuntimeException {

    public ThirdPartyOperationException(String message) {
        super(message);
    }

    public ThirdPartyOperationException(Class<?> clazz, long id) {
        super(getFormattedMessage(clazz.getSimpleName(), String.valueOf(id)));
    }

    public ThirdPartyOperationException(Class<?> clazz, String id) {
        super(getFormattedMessage(clazz.getSimpleName(), id));
    }

    private static String getFormattedMessage(String entity, String reason) {
        return String.format("%s operation failed with reason: %s.", entity, reason);
    }
}