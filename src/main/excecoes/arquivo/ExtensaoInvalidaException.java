package main.excecoes.arquivo;

/**
 * @author Laura
 */
public class ExtensaoInvalidaException extends ArquivoException {
  
	private static final long serialVersionUID = 1L;

  /**
   * Construtor da exceção. Cria uma mensagem de erro padronizada que informa qual extensão é a não suportada.
   * @param extensao: A extensão de arquivo (sem o ponto) que não é suportada.
   */
  public ExtensaoInvalidaException(String extensao) {
    super("A extensão de arquivo ." + extensao + " não é suportada no nosso programa.");
  }
}