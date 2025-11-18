package main.excecoes.midia;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class MidiaInvalidaException extends MidiaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor que cria a exceção, aceitando uma mensagem detalhada que explica o motivo da invalidez da Mídia.
	 * @param message: A mensagem de erro detalhada sobre o que torna a Mídia inválida.
	 */

	public MidiaInvalidaException(String message) {
        super(message);
    }
}
