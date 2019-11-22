package com.cts.exception;

/**
 * 
 * Custom Exception
 *
 */
public class DuplicateEmailException extends RuntimeException{

	public DuplicateEmailException(String mesage) {
        super(mesage);
    }
}
