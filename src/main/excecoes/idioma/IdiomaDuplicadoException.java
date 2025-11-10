package main.excecoes.idioma;

public class IdiomaDuplicadoException extends IdiomaException {
  public IdiomaDuplicadoException(String nome) {
    super("Idioma " + nome + " já está cadastrado na lista.");
  }
}
