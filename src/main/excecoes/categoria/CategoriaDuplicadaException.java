package main.excecoes.categoria;

public class CategoriaDuplicadaException extends CategoriaException {
  public CategoriaDuplicadaException(String nome, String tipoLista) {
    super("Categoria com o nome: " + nome + " já está cadastrada na lista: " + tipoLista + ".");
  }
}
