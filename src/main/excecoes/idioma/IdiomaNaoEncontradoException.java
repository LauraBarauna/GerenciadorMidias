package main.excecoes.idioma;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */
public class IdiomaNaoEncontradoException extends IdiomaException {

	private static final long serialVersionUID = 1L;

    /**
     * Construtor da exceção. Cria uma mensagem de erro padronizada que informa qual idioma estava sendo procurado.
     * @param nome: O nome do idioma que estava sendo procurado e não foi encontrado.
     */
	public IdiomaNaoEncontradoException(String nome) {
        super("Idioma " + nome + " não foi  encontrado na lista.");
    }
}