package main.model.midias.livro;

import main.excecoes.autores.InclusaoDeAutorInvalida;
import main.excecoes.autores.ListagemDeAutoresInvalida;
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

    public void incluirAutor(Pessoa autor) throws InclusaoDeAutorInvalida {
        if (this.autores.contains(autor)) {
            throw new InclusaoDeAutorInvalida("Você não pode adicionar o autor " + autor.getNome()
            + " porque ele já foi adicionado.");
        }
        this.autores.add(autor);
    }

    public Pessoa getAutor(String nomeAutor) throws ListagemDeAutoresInvalida {
        Pessoa autor = null;

        for (Pessoa a : getAutores()) {
            if (a.getNome().equalsIgnoreCase(nomeAutor)) {
                autor = a;
            }
        }

        if (autor == null) {
            throw new ListagemDeAutoresInvalida("O livro " + super.getTitulo() + " não possúi o autor " + nomeAutor);
        }

        return autor;
    }

    public void removerAutor(String nomeAutor) throws ListagemDeAutoresInvalida {
        Pessoa autorRemover = getAutor(nomeAutor);
        getAutores().remove(autorRemover);
    }

    public List<Pessoa> getAutores() throws ListagemDeAutoresInvalida {
        if (this.autores.isEmpty()) {
            throw new ListagemDeAutoresInvalida("O livro " + super.getTitulo() + " não possuí autores.");
        }
        return this.autores;
    }

    @Override
    public String exibirAtributos() throws ListagemDeAutoresInvalida {
        String atributos = "";
        atributos += "Autores: " + getAutores() + "\n";
        return atributos;
    }

    @Override
    public void setDuracao(int duracao) throws IllegalArgumentException {
        super.setDuracao(duracao);
    }
}
