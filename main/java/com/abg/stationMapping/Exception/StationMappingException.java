package com.abg.stationMapping.Exception;

public class StationMappingException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public StationMappingException() {
	}

	public StationMappingException(String message) {
		super(message);
	}

	public StationMappingException(Throwable cause) {
		super(cause);
	}

	public StationMappingException(String message, Throwable cause) {
		super(message, cause);
	}
}
