package main.excecoes.idioma;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */
public class IdiomaDuplicadoException extends IdiomaException {

	private static final long serialVersionUID = 1L;

    /**
     * Construtor da exceção. Cria uma mensagem de erro padronizada que informa qual idioma já está cadastrado.
     * @param nome: O nome do idioma que foi tentado ser duplicado.
     */
  public IdiomaDuplicadoException(String nome) {
    super("Idioma " + nome + " já está cadastrado na lista.");
  }
}