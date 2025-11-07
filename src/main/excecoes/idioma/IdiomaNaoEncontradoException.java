package main.excecoes.idioma;

public class IdiomaNaoEncontradoException extends IdiomaException {
    public IdiomaNaoEncontradoException(String nome) {
        super("Idioma com o nome: " + nome + " não foi  encontrado na lista.");
    }
}
