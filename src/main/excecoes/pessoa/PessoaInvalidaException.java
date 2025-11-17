package main.excecoes.pessoa;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */
public class PessoaInvalidaException extends PessoaException {

	private static final long serialVersionUID = 1L;

    /**
     * Construtor da exceção. Cria a exceção com uma mensagem de detalhe específica.
     * @param message: A mensagem de erro que descreve a causa da exceção.
     */
	public PessoaInvalidaException(String message) {
        super(message);
    }
}