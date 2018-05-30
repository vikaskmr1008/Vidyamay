/**
 * 
 */
package com.vidyamay.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vidyamay.utils.PlatformError;

/**
 * @author vikas.kumar3
 *
 */
public class AbstractResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1555610929715648531L;
	
	 /** The data. */
    private Object data;
    
    /** The errors. */
    private List<PlatformError> errors = new ArrayList<>();

    /** The status. */
    private int status;

    /** The metadata. */
    private HashMap<String, Object> metadata = new HashMap<>();


    /**
     * Instantiates a new client response.
     */
    public AbstractResponse() {
        super();
    }

    /**
     * constructor with parameter.
     *
     * @param status
     *            the status
     * @param errors
     *            the errors
     * @param data
     *            the data
     */
    public AbstractResponse(int status, List<PlatformError> errors, Object data) {
    	this.status = status;
        this.setErrors(errors);
        this.data = data;
    }

    /**
     * Instantiates a new client response.
     *
     * @param status
     *            the status
     * @param errors
     *            the errors
     * @param data
     *            the data
     * @param metadata
     *            the metadata
     * @param baseUri
     *            the base uri
     */
    public AbstractResponse(int status, List<PlatformError> errors, Object data,
            HashMap<String, Object> metadata, String baseUri) {
        this.status = status;
        this.setErrors(errors);
        this.metadata = metadata;
        this.metadata.put("baseUri", baseUri);
        this.data = data;
    }
    
    /**
     * Gets the errors.
     *
     * @return the errors
     */
    public final List<PlatformError> getErrors() {
        return this.errors;
    }
    
    /**
     * Sets the errors.
     *
     * @param errors
     *            the new errors
     */
    public void setErrors(List<PlatformError> errors) {
        if (errors == null) {
            this.errors = new ArrayList<>();
        } else {
            final List<PlatformError> errorsCopy = new ArrayList<>(errors);
            this.errors = errorsCopy;
        }
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public final Object getData() {
        return this.data;
    }

    /**
     * Sets the data.
     *
     * @param payload
     *            the new data
     */
    public final void setData(Object payload) {
        this.data = payload;
    }
    
    /**
     * Gets the metadata.
     *
     * @return the metadata
     */
    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    /**
     * Sets the metadata.
     *
     * @param metadata
     *            the metadata
     */
    public final void setMetadata(HashMap<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    /**
     * Gets the status.
     *
     * @return the status
     */
    public final int getStatus() {
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AbstractResponse [data=" + this.data + ", getMetadata()="
                + getMetadata() + ", getErrors()=" + getErrors()
                + ", getStatus()=" + getStatus() + "]";
    }


}
