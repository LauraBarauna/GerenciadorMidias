package main.excecoes.idioma;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class IdiomaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor padrão que aceita uma mensagem detalhada sobre o erro ocorrido.
	 * @param message: A mensagem de erro que descreve a exceção do Idioma.
	 */

	public IdiomaException(String message) {
        super(message);
    }
}
