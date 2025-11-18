package main.excecoes.pessoa;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class PessoaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor padrão que aceita uma mensagem detalhada sobre o erro ocorrido.
	 * * @param message: A mensagem de erro que descreve a exceção da Pessoa.
	 */

	public PessoaException(String message) {
        super(message);
    }
}
