package main.excecoes.midia;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class MidiaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor padrão que aceita uma mensagem detalhada sobre o erro ocorrido.
	 * * @param message: A mensagem de erro que descreve a exceção da Mídia.
	 */

	public MidiaException(String message) {
        super(message);
    }
}
