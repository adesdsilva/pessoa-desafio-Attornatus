package br.com.setecolinas.pessoasmanager.pessoa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String msg) {
		super(msg);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}

	public BadRequestException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
