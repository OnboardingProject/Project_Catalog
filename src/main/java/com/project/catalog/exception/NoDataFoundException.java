package com.project.catalog.exception;

public class NoDataFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	String msg;

	public NoDataFoundException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
