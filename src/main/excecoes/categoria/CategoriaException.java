package main.excecoes.categoria;

/**
 * @author Laura Barauna
 */
public class CategoriaException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	/**
     * Construtor da exceção. Cria a exceção com uma mensagem de detalhe específica. 
     * @param message: A mensagem de erro que descreve a causa da exceção.
     */
    public CategoriaException(String message) {
        super(message);
    }
}