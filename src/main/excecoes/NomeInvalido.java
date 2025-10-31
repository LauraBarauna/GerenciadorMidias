package main.excecoes;

public class NomeInvalido extends RuntimeException {
  public NomeInvalido(String message) {
    super(message);
  }
}
