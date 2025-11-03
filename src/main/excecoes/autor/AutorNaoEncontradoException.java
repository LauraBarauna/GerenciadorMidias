package main.excecoes.autor;

public class AutorNaoEncontradoException extends AutorException {
    public AutorNaoEncontradoException(String nome) {
        super("Autor com o nome: " + nome + " não foi encontrado.");
    }
}
