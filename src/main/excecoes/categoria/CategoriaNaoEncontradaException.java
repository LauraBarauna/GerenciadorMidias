package main.excecoes.categoria;

/**
 * @author Laura Barauna
 */
public class CategoriaNaoEncontradaException extends CategoriaException {
    
	private static final long serialVersionUID = 1L;

	/**
     * Construtor da exceção. Cria uma mensagem de erro padronizada que informa qual categoria e em qual lista/contexto a categoria não foi encontrada.
     * @param nome: O nome da categoria que estava sendo procurada.
     * @param tipoLista: O nome da lista ou contexto onde a busca foi realizada (ex: "categorias cadastradas").
     */
    public CategoriaNaoEncontradaException(String nome, String tipoLista) {
        super("Categoria com o nome: " + nome + " não foi encontrada na lista de " + tipoLista + ".");
    }
}