package main.excecoes.categoria;

public class CategoriaNaoEncontradaException extends CategoriaException {
    public CategoriaNaoEncontradaException(String nome, String tipoLista) {
        super("Categoria com o nome: " + nome + " não foi encontrada na lista de " + tipoLista + ".");
    }
}
