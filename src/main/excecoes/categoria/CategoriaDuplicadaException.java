package main.excecoes.categoria;

/**
 * @author Laura
 */
public class CategoriaDuplicadaException extends CategoriaException {
    
	private static final long serialVersionUID = 1L;

	/**
     * Construtor da exceção. Cria uma mensagem de erro padronizada que informa qual categoria e em qual lista/contexto ocorreu a duplicidade.
     * @param nome: O nome da categoria que foi tentada ser duplicada.
     * @param tipoLista: O nome da lista ou contexto onde a duplicidade foi detectada.
     */
    public CategoriaDuplicadaException(String nome, String tipoLista) {
        super("Categoria com o nome: " + nome + " já está cadastrada na lista: " + tipoLista + ".");
    }
}