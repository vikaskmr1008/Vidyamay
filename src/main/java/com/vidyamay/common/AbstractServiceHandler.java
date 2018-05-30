package com.vidyamay.common;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vidyamay.utils.PlatformError;
import com.vidyamay.utils.PlatformErrorCodes;
import com.vidyamay.utils.PlatformException;
import com.vidyamay.utils.PlatformExceptionTranslator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author vikas.kumar3
 *
 */
public abstract class AbstractServiceHandler {
	
	public PlatformException raiseNotFoundException(final Object... args) {

	    final PlatformError pltformError = PlatformExceptionTranslator
	        .createPlatformError(PlatformErrorCodes.NOT_FOUND, args);
	    return PlatformExceptionTranslator.wrapException(PlatformErrorCodes.NOT_FOUND.value(),
	        Arrays.asList(pltformError));
	  }
	
	/**
	   * Raise not implemented exception.
	   *
	   * @param methodName
	   *          the method name
	   * @return the platform exception
	   */
	  public PlatformException raiseNotImplementedException(final String methodName) {
	    final PlatformError platformError = PlatformExceptionTranslator
	        .createPlatformError(PlatformErrorCodes.NOT_IMPLEMENTED, new Object[] { "Not Supported" });
	    return PlatformExceptionTranslator.wrapException(PlatformErrorCodes.NOT_IMPLEMENTED.value(),
	        Arrays.asList(platformError));
	  }
	  
	  /**
	   * Handle response.
	 * @param <T>
	   *
	   * @param payload
	   *          the payload
	   * @return the abstract response
	   */
	  public <T> AbstractResponse handleResponse(final Mono<T> payload) {
	    return new AbstractResponse(HttpStatus.OK.value(), null, new ResponseEntity<Mono<T>>(payload, HttpStatus.OK));
	  }
	  
	  /**
	   * Handle response.
	 * @param <T>
	   *
	   * @param payload
	   *          the payload
	   * @return the abstract response
	   */
	  public <T> AbstractResponse handleResponse(final Flux<T> payload) {
	    return new AbstractResponse(HttpStatus.OK.value(), null, payload);
	  }
	  
	  /**
	   * Handle response.
	   *
	   * @param payload
	   *          the payload
	   * @param status
	   *          the status
	   * @return the abstract response
	   */
	  public AbstractResponse handleResponse(Object payload, int status) {
	    return new AbstractResponse(status, null, payload);
	  }
	  
	  /**
	   * Handle response with error.
	   *
	   * @param payload
	   *          the payload
	   * @param errors
	   *          the errors
	   * @param status
	   *          the status
	   * @return the abstract response
	   */
	  public AbstractResponse handleResponseWithError(Object payload, List<PlatformError> errors,
	      int status) {
	    return new AbstractResponse(status, errors, payload);
	  }


}
