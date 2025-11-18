package main.excecoes.arquivo;

/**
 * @author Laura
 */
public class ArquivoException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	/**
     * Construtor da exceção. Cria a exceção com uma mensagem de detalhe específica.
     * @param message: A mensagem de erro que descreve a causa da exceção.
     */
    public ArquivoException(String message) {
        super(message);
    }
}