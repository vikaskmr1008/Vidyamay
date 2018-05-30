/**
 * 
 */
package com.vidyamay.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.ObjectError;

import com.google.common.base.Throwables;

/**
 * @author vikas.kumar3
 *
 */
@Component
public class PlatformExceptionTranslator {

	/**
     * Instantiates a new platform exception translator util.
     */
    private PlatformExceptionTranslator() {
        super();
    }

    /**
     * Creates the platform error.
     *
     * @param errorCodes
     *            the error codes
     * @param args
     *            the args
     * @param errorLabelKey
     *            the error label key
     * @return the platform error
     */
    public static PlatformError createPlatformError(
            PlatformErrorCodes errorCodes, Object[] args,
            String... errorLabelKey) {

        String customErrorKey = null;
        if (!ObjectUtils.isEmpty(errorLabelKey)) {
            customErrorKey = errorLabelKey[0];
        }
        final PlatformError platformError = new PlatformError();

        platformError.setStatus(errorCodes.value());

        platformError.setCode(errorCodes.getErrorCode());

        platformError.setMessage(message(errorCodes, args, customErrorKey));

        platformError.setDeveloperMessage(
                developerMessage(errorCodes, args, customErrorKey));

        platformError.setMoreInfo(moreInfo(errorCodes));

        return platformError;
    }

    /**
     * @param errorCodes
     * @param error
     * @return
     */
    public static PlatformError createPlatformError(PlatformErrorCodes errorCodes, ObjectError error) {

      final PlatformError platformError = new PlatformError();

      platformError.setStatus(errorCodes.value());

      platformError.setCode(errorCodes.getErrorCode());

      platformError.setMessage(message(errorCodes, null, null));

      platformError.setDeveloperMessage(message(error));

      platformError.setMoreInfo(moreInfo(errorCodes));

      return platformError;
    }


    /**
     * Creates the and throws.
     *
     * @param platformErrorCodes
     *            the platform error codes
     * @param args
     *            the args
     * @param errorLabelKey
     *            the error label key
     * @return the platform exception
     */
    public static PlatformException createAndThrows(
            PlatformErrorCodes platformErrorCodes, Object[] args,
            String... errorLabelKey) {

        String customErrorKey = null;
        if (!ObjectUtils.isEmpty(errorLabelKey))
            customErrorKey = errorLabelKey[0];

        final PlatformError platformError = new PlatformError();

        platformError.setStatus(platformErrorCodes.value());

        platformError.setCode(platformErrorCodes.getErrorCode());

        platformError
                .setMessage(message(platformErrorCodes, args, customErrorKey));

        platformError.setDeveloperMessage(
                developerMessage(platformErrorCodes, args, customErrorKey));

        platformError.setMoreInfo(moreInfo(platformErrorCodes));

        return wrapException(PlatformErrorCodes.FAILED_DEPENDENCY.value(),
                Arrays.asList(platformError));
    }

    /**
     * More info.
     *
     * @param errorCodes
     *            the error codes
     * @return the string
     */
    private static String moreInfo(PlatformErrorCodes errorCodes) {
        return new StringBuffer(
                PropertyHolderUtil.getStringProperty("server.contextPath"))
                        .append("/").append("errors").append("/")
                        .append(errorCodes.getErrorCode()).toString();
    }

    /**
     * Deleloper message.
     *
     * @param errorCodes
     *            the error codes
     * @param args
     *            the args
     * @param customErrorKey
     *            the custom error key
     * @return the string
     */
    private static String developerMessage(PlatformErrorCodes errorCodes,
            Object[] args, String customErrorKey) {

        String key = customErrorKey;
        if (key == null) {
            key = errorCodes.getErrorCode();
        }
        return BeanUtil.getBean(Springi18nUtils.class)
                .getMessage(key + "." + "developer.message", args);
    }

    /**
     * Message.
     *
     * @param errorCodes
     *            the error codes
     * @param args
     *            the args
     * @param customErrorKey
     *            the custom error key
     * @return the string
     */
    private static String message(PlatformErrorCodes errorCodes, Object[] args,
            String customErrorKey) {

        return BeanUtil.getBean(Springi18nUtils.class)
                .getMessage(errorCodes.getErrorCode() + "." + "message", args);
    }

    /**
     * Utility method to wrap throwable to platform exception.
     *
     * @param errorCode
     *            the error code
     * @param args
     *            the args
     * @param errorKey
     *            the error key
     * @return INTERNAL_SERVER_ERROR and error code
     */
    public static PlatformException wrapException(PlatformErrorCodes errorCode,
            Object[] args, String... errorKey) {

        final PlatformError error = createPlatformError(errorCode, args,
                errorKey);

        return new PlatformException(errorCode.value(), error);
    }

    /**
     * Utility method to wrap throwable to platform exception.
     *
     * @param exception
     *            the exception
     * @param errorCode
     *            the error code
     * @param errorKey
     *            the error key
     * @return INTERNAL_SERVER_ERROR and error code
     */
    public static PlatformException wrapException(Throwable exception,
            PlatformErrorCodes errorCode, String... errorKey) {

        final PlatformError platformError = createPlatformError(
                PlatformErrorCodes.INTERNAL_SERVER_ERROR,
                new Object[] { Throwables.getStackTraceAsString(exception) },
                errorKey);

        return new PlatformException(errorCode.value(), platformError);
    }

    /**
     * Utility method to wrap throwable to platform exception.
     *
     * @param httpStatus
     *            the http status
     * @param errors
     *            the errors
     * @return INTERNAL_SERVER_ERROR and error code
     */
    public static PlatformException wrapException(int httpStatus,
            List<PlatformError> errors) {
        return new PlatformException(httpStatus, errors);
    }

    /**
     * Wrap exception.
     *
     * @param status
     *            the status
     * @param code
     *            the code
     * @param message
     *            the message
     * @return the platform exception
     */
    public static PlatformException wrapException(final int status,
            final String code, String message) {
        PlatformError error = new PlatformError(status, code, message);
        return new PlatformException(status, error);
    }

    /**
     * Utility method to wrap throwable to platform exception.
     *
     * @param httpStatus
     *            the http status
     * @param errors
     *            the errors
     * @return the platform exception
     */
    public static PlatformException raiseException(int httpStatus,
            List<PlatformError> errors) {
        return new PlatformException(httpStatus, errors);
    }

    /**
     * Throw if error exist.
     *
     * @param httpStatus
     *            the http status
     * @param errors
     *            the errors
     * @throws PlatformException
     *             the platform exception
     */
    public static void throwIfErrorExist(int httpStatus,
            List<PlatformError> errors) throws PlatformException {
        if (!errors.isEmpty()) {
            throw PlatformExceptionTranslator.raiseException(httpStatus,
                    errors);
        }
    }
    
	/**
	 * @param error
	 * @return
	 */
	private static String message(ObjectError error) {
		if (error != null) {
			return BeanUtil.getBean(Springi18nUtils.class).getMessage(error);
		}
		return StringUtils.EMPTY;
	}

}
