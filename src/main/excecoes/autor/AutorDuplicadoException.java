package main.excecoes.autor;

public class AutorDuplicadoException extends AutorException {
    public AutorDuplicadoException(String nome, String nomeLivro) {
        super("Autor com o nome: " + nome + " já está cadastro no livro: " + nomeLivro + ".");
    }
}
