package main.excecoes.pessoa;

import main.model.pessoa.Pessoa;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class PessoaNaoEncontradaException extends PessoaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor que cria a exceção com uma mensagem detalhada, incluindo o nome da pessoa que não foi encontrada.
	 * @param nome: O nome da Pessoa que foi procurada e não existe no sistema.
	 */

	public PessoaNaoEncontradaException(String nome) {
        super("Pessoa " + nome + " não foi  encontrada na lista.");
    }
}
