package main.excecoes.pessoa;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class PessoaDuplicadaException extends PessoaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor que cria a exceção com uma mensagem detalhada, especificando o nome da pessoa que causou a duplicidade.
	 * @param nome: O nome da Pessoa cuja tentativa de cadastro resultou em duplicidade.
	 */

	public PessoaDuplicadaException(String nome) {
        super("Pessoa " + nome + " já está cadastrada na lista.");
    }
}
