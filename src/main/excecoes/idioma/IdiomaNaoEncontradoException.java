package main.excecoes.idioma;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class IdiomaNaoEncontradoException extends IdiomaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor que cria a exceção com uma mensagem detalhada, incluindo o nome do idioma que não foi encontrado.
	 * * @param nome: O nome do Idioma que foi procurado e não existe no sistema.
	 */

	public IdiomaNaoEncontradoException(String nome) {
        super("Idioma " + nome + " não foi  encontrado na lista.");
    }
}
