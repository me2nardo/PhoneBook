package org.rbo.exception.web;

import org.rbo.exception.AppException;

/**
 * Created by vitalii.levash on 18.04.2016.
 */
public class NotFoundException extends AppException {
    public NotFoundException(final int entityId) {
        super(String.format("could not find '%d'.", entityId));
    }

    public NotFoundException(final int entityId, final String entityName) {
        super(String.format("could not find %s by '%d'.", entityName, entityId));
    }

    public NotFoundException(final String msg) {
        super(msg);
    }

}
