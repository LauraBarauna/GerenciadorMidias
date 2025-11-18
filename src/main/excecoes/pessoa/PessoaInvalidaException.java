package main.excecoes.pessoa;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class PessoaInvalidaException extends PessoaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor que cria a exceção, aceitando uma mensagem detalhada que explica o motivo da invalidez.
	 * @param message: A mensagem de erro detalhada sobre o que torna a Pessoa inválida.
	 */

	public PessoaInvalidaException(String message) {
        super(message);
    }
}
