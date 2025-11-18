package main.excecoes.idioma;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class IdiomaInvalidoException extends IdiomaException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor que cria a exceção, aceitando uma mensagem detalhada que explica o motivo da invalidez.
	 * @param message: A mensagem de erro detalhada sobre o que torna o Idioma inválido.
	 */

	public IdiomaInvalidoException(String message) {
        super(message);
    }
}
