package main.excecoes.midia;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */
public class MidiaInvalidaException extends MidiaException {

	private static final long serialVersionUID = 1L;

    /**
     * Construtor da exceção. Cria a exceção com uma mensagem de detalhe específica que descreve o motivo da invalidez da mídia.
     * @param message: A mensagem de erro que descreve a causa da exceção.
     */
	public MidiaInvalidaException(String message) {
        super(message);
    }
}