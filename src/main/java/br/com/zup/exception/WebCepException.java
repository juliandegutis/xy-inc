package br.com.zup.exception;

/**
 * Custom Exception para o serviço
 * 
 * @author juliang
 *
 */

public class WebCepException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4338175671611854431L;

	/**
	 * Default constructor for class
	 */
	public WebCepException() {

	}

	/**
	 * Constructor for class with exception message
	 * 
	 * @param msg
	 */
	public WebCepException( String msg ) {

		super( msg );
	}

	/**
	 * Constructor for class with exception message and throwable param
	 * 
	 * @param msg
	 * @param th
	 */
	public WebCepException( String msg, Throwable th ) {

		super( msg, th );
	}
}
