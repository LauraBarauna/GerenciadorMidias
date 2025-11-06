package main.excecoes.arquivo;

public class ExtensaoInvalidaException extends ArquivoException {
  public ExtensaoInvalidaException(String extensao) {
    super("A extensão de arquivo ." + extensao + " não é suportada no nosso programa.");
  }
}
