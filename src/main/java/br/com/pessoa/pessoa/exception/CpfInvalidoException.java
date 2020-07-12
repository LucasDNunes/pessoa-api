package br.com.pessoa.pessoa.exception;

public class CpfInvalidoException extends IllegalArgumentException {

  public CpfInvalidoException(String s) {
    super(s + " CPF inválido");
  }
}
