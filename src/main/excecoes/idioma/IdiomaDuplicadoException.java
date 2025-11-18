package main.excecoes.idioma;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class IdiomaDuplicadoException extends IdiomaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor que cria a exceção com uma mensagem detalhada, especificando o nome do idioma que causou a duplicidade.
	 * @param nome: O nome do Idioma cuja tentativa de cadastro resultou em duplicidade.
	 */

  public IdiomaDuplicadoException(String nome) {
    super("Idioma " + nome + " já está cadastrado na lista.");
  }
}
