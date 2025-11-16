package main.excecoes.pessoa;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */
public class PessoaDuplicadaException extends PessoaException {

	private static final long serialVersionUID = 1L;

    /**
     * Construtor da exceção. Cria uma mensagem de erro padronizada que informa qual pessoa já está cadastrada.
     * @param nome: O nome da pessoa que foi tentada ser duplicada.
     */
	public PessoaDuplicadaException(String nome) {
        super("Pessoa " + nome + " já está cadastrada na lista.");
    }
}