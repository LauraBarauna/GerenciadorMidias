package main.excecoes.midia;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */
public class MidiaDuplicadaException extends MidiaException {

	private static final long serialVersionUID = 1L;

    /**
     * Construtor da exceção. Cria uma mensagem de erro padronizada que informa qual o ID da mídia que já está cadastrada.
     * @param id: O identificador único (ID) da mídia que foi tentada ser duplicada.
     */
	public MidiaDuplicadaException(int id) {
        super("Midia com o id " + id + " já está cadastrada na lista.");
    }
}