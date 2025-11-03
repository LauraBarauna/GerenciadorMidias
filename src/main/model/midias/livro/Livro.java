package main.model.midias.livro;

import main.excecoes.autor.AutorDuplicadoException;
import main.excecoes.autor.AutorNaoEncontradoException;
import main.model.midias.Categoria;
import main.model.midias.Midia;
import main.model.pessoa.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class Livro extends Midia {
    private List<Pessoa> autores;

    public Livro(int id, String local, double tamanhoEmDisco,
                 String titulo, int duracao, Categoria categoria) {
        super(id, local, tamanhoEmDisco, titulo, duracao, categoria);
        this.autores = new ArrayList<>();
    }

    public void incluirAutor(Pessoa autor) throws AutorDuplicadoException {
        if (this.autores.contains(autor)) {
            throw new AutorDuplicadoException(autor.getNome(), super.getTitulo());
        }
        this.autores.add(autor);
    }

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

    public void removerAutor(String nomeAutor) {
        Pessoa autorRemover = getAutor(nomeAutor);
        getAutores().remove(autorRemover);
    }

    public List<Pessoa> getAutores() {
        if (this.autores.isEmpty()) {
            throw new RuntimeException("O livro " + super.getTitulo() + " não possuí autores.");
        }
        return this.autores;
    }

    @Override
    public String exibirAtributos()   {
        String atributos = "";
        atributos += "Autores: " + getAutores() + "\n";
        return atributos;
    }

    @Override
    public void setDuracao(int duracao) throws IllegalArgumentException {
        super.setDuracao(duracao);
    }
}
