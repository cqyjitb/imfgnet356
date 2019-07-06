package com.hand.hap.attachment.exception;

import com.hand.hap.core.exception.BaseException;

/**
 * @author njq.niu@hand-china.com
 */
public class AttachmentException extends BaseException {

    public AttachmentException(String code, String descriptionKey, Object[] parameters) {
        super(code, descriptionKey, parameters);
    }
}
