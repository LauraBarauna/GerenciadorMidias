package main.excecoes.autor;

/**
 * @author Laura Barauna
 */
public class AutorInvalidoException extends AutorException {
    
	private static final long serialVersionUID = 1L;

	/**
     * Construtor da exceção. Cria a exceção com uma mensagem de detalhe específica.
     * @param message: A mensagem de erro que descreve a causa da exceção.
     */
    public AutorInvalidoException(String message) {
        super(message);
    }
}