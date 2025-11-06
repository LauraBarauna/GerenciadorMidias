package main.model.midias.livro;

import main.excecoes.autor.AutorDuplicadoException;
import main.excecoes.autor.AutorNaoEncontradoException;
import main.model.midias.Categoria;
import main.model.midias.Midia;
import main.model.pessoa.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class Livro extends Midia {

    // Um livro pode ter vários autores
    private List<Pessoa> autores;

    //==============================
    // CONSTRUTOR
    //==============================
    public Livro(int id, String local, double tamanhoEmDisco,
                 String titulo, int duracao, Categoria categoria) {
        // Inicializa atributos herdados da superclasse Midia
        super(id, local, tamanhoEmDisco, titulo, duracao, categoria);
        // Cria uma lista vazia de autores
        this.autores = new ArrayList<>();
    }

    //==============================
    // MÉTODOS DE NEGÓCIO
    //==============================

    /**
     * Adiciona um autor à lista, impedindo duplicatas.
     * @param autor Pessoa a ser adicionada.
     * @throws AutorDuplicadoException caso o autor já esteja cadastrado no livro.
     */
    public void incluirAutor(Pessoa autor) throws AutorDuplicadoException {
        if (this.autores.contains(autor)) {
            throw new AutorDuplicadoException(autor.getNome(), super.getTitulo());
        }
        this.autores.add(autor);
    }

    /**
     * Busca um autor pelo nome, ignorando maiúsculas/minúsculas.
     * @param nomeAutor Nome do autor a procurar.
     * @return Pessoa correspondente.
     * @throws AutorNaoEncontradoException caso o nome não exista.
     */
    public Pessoa getAutor(String nomeAutor) {
        Pessoa autor = null;

        for (Pessoa a : getAutores()) {
            if (a.getNome().equalsIgnoreCase(nomeAutor)) {
                autor = a;
            }
        }

        if (autor == null) {
            throw new AutorNaoEncontradoException(nomeAutor);
        }

        return autor;
    }

    /**
     * Remove um autor com base no nome.
     * Se não existir, a exceção de AutorNaoEncontradoException será lançada.
     */
    public void removerAutor(String nomeAutor) {
        Pessoa autorRemover = getAutor(nomeAutor);
        getAutores().remove(autorRemover);
    }

    /**
     * Retorna a lista de autores.
     * Caso a lista esteja vazia, lança uma exceção informando que o livro não possui autores.
     */
    public List<Pessoa> getAutores() {
        if (this.autores.isEmpty()) {
            throw new RuntimeException("O livro " + super.getTitulo() + " não possuí autores.");
        }
        return this.autores;
    }

    //==============================
    // MÉTODOS SOBRESCRITOS
    //==============================

    /**
     * Retorna os atributos formatados do livro.
     */
    @Override
    public String exibirAtributos() {
        String atributos = "";
        atributos += "Autores: " + getAutores() + "\n";
        return atributos;
    }

    public void setDuracao(int duracao) throws IllegalArgumentException {
        super.setDuracao(duracao);
    }
}