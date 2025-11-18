package main.excecoes.autor;

/**
 * @author Laura
 */
public class AutorDuplicadoException extends AutorException {
    
	private static final long serialVersionUID = 1L;

	/**
     * Construtor da exceção. Cria uma mensagem de erro padronizada que informa qual autor e em qual livro ocorreu a duplicidade.
     * @param nome: O nome do autor que foi tentado ser duplicado.
     * @param nomeLivro: O título do livro onde a duplicidade foi detectada.
     */
    public AutorDuplicadoException(String nome, String nomeLivro) {
        super("Autor com o nome: " + nome + " já está cadastro no livro: " + nomeLivro + ".");
    }
}