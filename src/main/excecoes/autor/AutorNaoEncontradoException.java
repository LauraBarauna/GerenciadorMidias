package main.excecoes.autor;

/**
 * @author Laura
 */
public class AutorNaoEncontradoException extends AutorException {
    
	private static final long serialVersionUID = 1L;

	/**
     * Construtor da exceção. Cria uma mensagem de erro padronizada que informa qual autor estava sendo procurado.
     * @param nome: O nome do autor que estava sendo procurado e não foi encontrado.
     */
    public AutorNaoEncontradoException(String nome) {
        super("Autor com o nome: " + nome + " não foi encontrado.");
    }
}