package main.excecoes.pessoa;

import main.model.pessoa.Pessoa;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */
public class PessoaNaoEncontradaException extends PessoaException {

	private static final long serialVersionUID = 1L;

    /**
     * Construtor da exceção. Cria uma mensagem de erro padronizada que informa qual pessoa estava sendo procurada.
     * @param nome: O nome da pessoa que estava sendo procurada e não foi encontrada.
     */
	public PessoaNaoEncontradaException(String nome) {
        super("Pessoa " + nome + " não foi  encontrada na lista.");
    }
}