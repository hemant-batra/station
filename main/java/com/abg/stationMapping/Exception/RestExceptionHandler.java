package com.abg.stationMapping.Exception;

import java.net.BindException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.abg.stationMapping.response.dto.ErrorDetails;
import com.abg.stationMapping.response.dto.ErrorResponse;
import com.abg.stationMapping.response.dto.ErrorStatus;
import com.abg.stationMapping.util.Consts;

@RestControllerAdvice
@EnableWebMvc
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static Logger logger = LogManager.getLogger(RestExceptionHandler.class);

	/*
	 * Below method override ResponseEntityExceptionHandler class method to
	 * customize the API error
	 */

	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handleHttpRequestMethodNotSupported : " + ex.getMessage());
		return buildResponseEntity(Consts.METHOD_NOT_ALLOWED_HTTP_CODE, Consts.BAD_REQUEST, Consts.METHOD_NOT_ALLOWED,
				ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
	}

	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handleHttpMediaTypeNotSupported : " + ex.getMessage());
		return buildResponseEntity(Consts.MEDIA_TYPE_NOT_SUPPORTED_HTTP_CODE, Consts.BAD_REQUEST,
				Consts.MEDIA_TYPE_NOT_SUPPORTED, ex.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handleHttpMediaTypeNotAcceptable : " + ex.getMessage());
		return buildResponseEntity(Consts.MEDIA_TYPE_NOT_ACCEPTABLE_HTTP_CODE, Consts.BAD_REQUEST,
				Consts.MEDIA_TYPE_NOT_ACCEPTABLE, ex.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.error("handleMissingPathVariable : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST, Consts.MISSING_PATH_VARIABLE,
				ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handleMissingServletRequestParameter : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST,
				Consts.MISSING_SERVLET_REQUEST_PARAMETER, ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handleServletRequestBindingException : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST,
				Consts.SERVLET_REQUEST_BINDING_ERROR, ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handleConversionNotSupported : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST, Consts.CONVERSION_NOT_SUPPORTED,
				ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.error("handleTypeMismatch : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST, Consts.TYPE_MISMATCH,
				ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handleHttpMessageNotReadable : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST, Consts.HTTP_MESSAGE_NOT_READABLE,
				ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handleHttpMessageNotWritable : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST, Consts.HTTP_MESSAGE_NOT_WRITABLE,
				ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handleMethodArgumentNotValid : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST, Consts.METHOD_ARGUMENT_NOT_VALID,
				ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handleMissingServletRequestPart : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST,
				Consts.MISSING_SERVLET_REQUEST_PART, ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		logger.error("handleBindException : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST, Consts.BIND_ERROR, ex.getMessage(),
				HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.error("handleNoHandlerFoundException : " + ex.getMessage());
		return buildResponseEntity(Consts.RESOURCES_NOT_FOUND_HTTP_CODE, Consts.BAD_REQUEST, Consts.NO_HANDLER_FOUND,
				ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handleAsyncRequestTimeoutException : " + ex.getMessage());
		return buildResponseEntity(Consts.INTERNAL_SERVER_ERROR_HTTP_CODE, Consts.BAD_REQUEST,
				Consts.ASYNC_REQUEST_TIMEOUT, ex.getMessage(), HttpStatus.REQUEST_TIMEOUT);
	}

	/*
	 * Custom Exception Handler
	 */

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
		logger.error("handleEntityNotFoundException : " + ex.getMessage());
		return buildResponseEntity(Consts.INTERNAL_SERVER_ERROR_HTTP_CODE, Consts.BAD_REQUEST, Consts.ENTITY_NOT_FOUND,
				ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	// @Validate For Validating Path Variables and Request Parameters
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		logger.error("handleConstraintViolationException : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST, Consts.CONSTRAINT_VIOLATION_ERROR,
				ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(Exception ex) {
		logger.error("handleConflict : " + ex.getMessage());
		return buildResponseEntity(Consts.BAD_REQUEST_HTTP_CODE, Consts.BAD_REQUEST, Consts.INVALID_REQUEST,
				ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Throwable.class)
	protected ResponseEntity<Object> handleAllExceptions(Throwable ex) {
		logger.error("handleAllExceptions : " + ex.getMessage());
		return buildResponseEntity(Consts.INTERNAL_SERVER_ERROR_HTTP_CODE, Consts.BAD_REQUEST, Consts.GENERAL_ERROR,
				ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Error Response
	 * 
	 * @param code
	 * @param message
	 * @param reason
	 * @param details
	 * @param httpStatus
	 * @return
	 */
	private ResponseEntity<Object> buildResponseEntity(String code, String message, String reason, String details,
			HttpStatus httpStatus) {

		List<ErrorDetails> errorDetailsList = new ArrayList<ErrorDetails>();
		ErrorDetails errorDetails = ErrorDetails.builder().code(code).message(message).reason(reason).details(details)
				.build();
		errorDetailsList.add(errorDetails);

		ErrorStatus errorStatus = ErrorStatus.builder()
				.request_time((LocalDateTime.now()).format(DateTimeFormatter.ofPattern(Consts.DATE_PATTERN)))
				.request_errors(errorDetailsList.size()).error(errorDetails).build();

		ErrorResponse errorResponse = ErrorResponse.builder().status(errorStatus).build();

		return new ResponseEntity<>(errorResponse, httpStatus);
	}

}
