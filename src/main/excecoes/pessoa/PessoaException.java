package main.excecoes.pessoa;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */
public class PessoaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    /**
     * Construtor da exceção. Cria a exceção com uma mensagem de detalhe específica.
     * @param message: A mensagem de erro que descreve a causa da exceção.
     */
	public PessoaException(String message) {
        super(message);
    }
}