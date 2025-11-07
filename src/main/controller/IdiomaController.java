package main.controller;

import main.excecoes.idioma.IdiomaDuplicadoException;
import main.excecoes.idioma.IdiomaNaoEncontradoException;
import main.gerenciador.GerenciadorIdioma;
import main.model.idioma.Idioma;

import java.util.ArrayList;
import java.util.List;

public class IdiomaController {
    private GerenciadorIdioma gerenciador;

    public IdiomaController(GerenciadorIdioma gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void cadastrarIdioma(String nome) {
        boolean adicionou = getGerenciador().adicionarIdioma(new Idioma(nome));

        if (!adicionou) {
            throw new IdiomaDuplicadoException(nome);
        }
    }

    public List<String> listarNomeIdioma () {
        List<Idioma> idiomas = getGerenciador().getIdiomas();
        List<String> nomes = new ArrayList<>();

        if (idiomas.isEmpty()) {
            return nomes;
        }

        for (Idioma idioma : idiomas) {
            nomes.add(idioma.getIdioma());
        }
        return nomes;
    }

    public void removerIdioma(String nome) {
        boolean removeu = getGerenciador().removerIdioma(nome);

        if (!removeu) {
            throw new IdiomaNaoEncontradoException(nome);
        }
    }

    public boolean removerTudo() {
        return getGerenciador().removerTodosIdiomas();
    }

    public List<Idioma> listarIdiomas() {
        return getGerenciador().getIdiomas();
    }

    public GerenciadorIdioma getGerenciador() {
        return gerenciador;
    }
}
