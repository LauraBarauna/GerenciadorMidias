package main.excecoes.midia;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class MidiaDuplicadaException extends MidiaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor que cria a exceção com uma mensagem detalhada, especificando o ID da mídia que causou a duplicidade.
	 * @param id: O identificador único (ID) da Mídia cuja tentativa de cadastro resultou em duplicidade.
	 */

	public MidiaDuplicadaException(int id) {
        super("Midia com o id " + id + " já está cadastrada na lista.");
    }
}
