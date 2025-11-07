package main.excecoes.idioma;

public class IdiomaDuplicadoException extends IdiomaException {
  public IdiomaDuplicadoException(String nome) {
    super("Categoria com o nome: " + nome + " já está cadastrada na lista.");
  }
}
