package com.vidyamay.utils;

/**
*
* The PlatformError message format
*
* { "status": 404, "code": 40483, "message": "Oops! It looks like that file
* does not exist.", "developerMessage": "File resource for path
* /uploads/foobar.txt does not exist. Please wait 10 minutes until the upload
* batch completes before checking again.", "moreInfo":
* "http://www.mycompany.com/errors/40483" }
*
* Http Status Code Convention
*
* 1 1xx Informational. 2 2xx Success. 3 3xx Redirection. 4 4xx Client Error. 5
* 5xx Server Error. 6 Unofficial codes. 6.1 Internet Information Services. 6.2
* nginx. 6.3 Cloudflare. 7 See also. 8 Notes.
*
*/
public class PlatformError {
	
	/** Http Status *. */
    private int status;

    /**
     * The code property is an error code specific to your particular REST API.
     */
    private String code;

    /**
     * The message property is a nice human readable error message that can
     * potentially be shown directly to an application end user (not a
     * developer).
     */
    private String message;

    /**
     * The developerMessage property conveys any and all technical information
     * that a developer calling your REST API might find useful. This is where
     * you might include exception messages, stack traces, or anything else that
     * you think will help a developer.
     */

    private String developerMessage;

    /**
     * The moreInfo property specifies a URL that anyone seeing the error
     * message can click (or copy and paste) in a browser. The target web page
     * should describe the error condition fully, as well as potential solutions
     * to help them resolve the error condition.
     */
    private String moreInfo;

    /**
     * Instantiates a new platform error.
     */
    public PlatformError() {
        // Default constructor
    }

    /*
     * public PlatformError(int status, String code, String message, String
     * developerMessage, String moreInfo) { this.status = status; this.code =
     * code; this.message = message; this.developerMessage = developerMessage;
     * this.moreInfo = moreInfo; }
     *
     * public PlatformError(SystemErrorCodes SystemErrorCodes, String
     * message, String developerMessage, String moreInfo) { this.status =
     * SystemErrorCodes.value(); this.code =
     * SystemErrorCodes.getErrorCode(); this.message = message;
     * this.developerMessage = developerMessage; this.moreInfo = moreInfo; }
     *
     *
     * public PlatformError(int status, String code, String message) {
     * this.status = status; this.code = code; this.message = message; }
     */
    
    /**
     * Instantiates a new platform error.
     */
    public PlatformError(int status, String code, String message) {
    	this.status = status;
    	this.code = code;
    	this.message = message;
     }

	/**
     * Gets the status.
     *
     * @return the status
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Sets the status.
     *
     * @param status
     *            the new status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the code.
     *
     * @param code
     *            the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message.
     *
     * @param message
     *            the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the developer message.
     *
     * @return the developer message
     */
    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    /**
     * Sets the developer message.
     *
     * @param developerMessage
     *            the new developer message
     */
    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    /**
     * Gets the more info.
     *
     * @return the more info
     */
    public String getMoreInfo() {
        return this.moreInfo;
    }

    /**
     * Sets the more info.
     *
     * @param moreInfo
     *            the new more info
     */
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PlatformError [status=" + this.status + ", code=" + this.code
                + ", message=" + this.message + ", developerMessage="
                + this.developerMessage + ", moreInfo=" + this.moreInfo + "]";
    }

}
