package br.com.pessoa.pessoa.exception;

public class EmailInvalidoException extends IllegalArgumentException {

  public EmailInvalidoException(String s) {
    super(s + " email inválido");
  }
}
