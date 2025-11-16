package main.excecoes.midia;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class MidiaNaoEncontradaException extends MidiaException {

	private static final long serialVersionUID = 1L;

    /**
     * Construtor da exceção. Cria uma mensagem de erro padronizada que informa qual o ID da mídia que estava sendo procurada.
     * @param id: O identificador único (ID) da mídia que estava sendo procurada e não foi encontrada.
     */
	public MidiaNaoEncontradaException(int id) {
        super("Midia com id " + id + " não foi  encontrada na lista.");
    }
}