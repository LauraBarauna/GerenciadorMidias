package main.excecoes.categoria;

public class CategoriaDuplicadaException extends CategoriaException {
  public CategoriaDuplicadaException(String nome, String tipoLista) {
    super("Categoria " + nome + " já está cadastrada na lista " + tipoLista + ".");
  }
}
