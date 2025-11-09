package main.controller;

import main.excecoes.arquivo.ExtensaoInvalidaException;
import main.excecoes.categoria.CategoriaDuplicadaException;
import main.excecoes.categoria.CategoriaNaoEncontradaException;
import main.gerenciador.GerenciadorCategoria;
import main.model.midias.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaController {
    private GerenciadorCategoria gerenciador;

    public CategoriaController(GerenciadorCategoria gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void adicionarCategoria (String nomeCategoria, String tipoCategoria) throws CategoriaDuplicadaException {
        boolean adiconou = getGerenciador().criarCategoriaPorTipo(new Categoria(nomeCategoria),
                Character.toUpperCase(tipoCategoria.charAt(0)));

        if (!adiconou) {
            throw new CategoriaDuplicadaException(nomeCategoria, tipoCategoria);
        }
    }

    public void listarCategoriaPorNome(String nomeCategoria, String tipoCategoria) throws CategoriaNaoEncontradaException {
        Categoria categoriaLista = getGerenciador().encontrarCategoriaPorTipo(nomeCategoria,
                Character.toUpperCase(tipoCategoria.charAt(0)));

        if (categoriaLista == null) {
            throw new CategoriaNaoEncontradaException(nomeCategoria, tipoCategoria);
        }

    }

    public void removerCategoria (String nomeCategoria, String tipoCategoria) throws CategoriaNaoEncontradaException {
        boolean removeu = getGerenciador().removerCategoriaPorTipo(nomeCategoria,
                Character.toUpperCase(tipoCategoria.charAt(0)));

        if (!removeu) {
            throw new CategoriaNaoEncontradaException(nomeCategoria, tipoCategoria);
        }
    }

    public void removerTudo(String tipoCategoria) throws RuntimeException {
        boolean removeuTudo = getGerenciador().removerTudoCategoria(
                Character.toUpperCase(tipoCategoria.charAt(0)));

        if (!removeuTudo) {
            throw new RuntimeException("Tipo de mídia não existe");
        }
    }

    public void atualziarCategoria (String nomeCategoria, Categoria categoriaNova, String tipoCategoria) throws CategoriaNaoEncontradaException {
        boolean atualizou = getGerenciador().atualizarCategoriaPorTipo(nomeCategoria,
                Character.toUpperCase(tipoCategoria.charAt(0)),
                categoriaNova);
        if (!atualizou) {
            throw new CategoriaNaoEncontradaException(nomeCategoria, tipoCategoria);
        }
    }

    public List<String> listarCategoriasString(String tipoCategoria)  {
        List<String> categorias = new ArrayList<>();

        for (Categoria c : gerenciador.encontrarCategorias(
                Character.toUpperCase(tipoCategoria.charAt(0))
        )) {
            categorias.add(c.getCategoria());
        }
        return categorias;
    }

    public List<String> listarCategoriasExtensaoString(String extensao) throws ExtensaoInvalidaException {
        List<Categoria> categorias = gerenciador.encontrarCategoriasPorExtensao(extensao);

        if (categorias == null) {
            throw new ExtensaoInvalidaException(extensao);
        }

        List<String> categoriasString = new ArrayList<>();


        for (Categoria c : categorias) {
            categoriasString.add(c.getCategoria());
        }
        return categoriasString;
    }

    public List<Categoria> listarCategorias(String tipoCategoria)  {
        return getGerenciador().encontrarCategorias(
                Character.toUpperCase(tipoCategoria.charAt(0))
        );
    }

    public GerenciadorCategoria getGerenciador() {
        return gerenciador;
    }
}
