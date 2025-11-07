package main.gerenciador;

import main.model.midias.Categoria;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorCategoria {
    List<Categoria> categoriasFilme;
    List<Categoria> categoriasMusica;
    List<Categoria> categoriasLivro;

    public GerenciadorCategoria() {
        this.categoriasFilme = new ArrayList<>();
        this.categoriasMusica = new ArrayList<>();
        this.categoriasLivro = new ArrayList<>();
    }

    public boolean criarCategoriaPorTipo(Categoria categoria, char tipo) {
        char tipoUpper = Character.toUpperCase(tipo);
        boolean deuCerto;

        switch (tipoUpper) {
            case 'F':
                deuCerto = criarCategoria(this.categoriasFilme, categoria, "Filme");
                break;
            case 'M':
                deuCerto = criarCategoria(this.categoriasMusica, categoria, "Música");
                break;
            case 'L':
                deuCerto = criarCategoria(this.categoriasLivro, categoria, "Livro");
                break;
            default:
                return false;
        }
        return deuCerto;
    }

    public Categoria encontrarCategoriaPorTipo(String categoria, char tipo) {
        char tipoUpper = Character.toUpperCase(tipo);

        Categoria categoriaEncontrada;

        switch (tipoUpper) {
            case 'F':
                categoriaEncontrada = encontrarCategoriaPorNome(this.categoriasFilme, categoria);
                break;
            case 'M':
                categoriaEncontrada = encontrarCategoriaPorNome(this.categoriasMusica, categoria);
                break;
            case 'L':
                categoriaEncontrada = encontrarCategoriaPorNome(this.categoriasLivro, categoria);
                break;
            default:
                return null;
        }

        return categoriaEncontrada;
    }

    public List<Categoria> encontrarCategorias (char tipo) {
        char tipoUpper = Character.toUpperCase(tipo);
        List<Categoria> categoriasEncontrada;

        switch (tipoUpper) {
            case 'F':
                categoriasEncontrada = getCategoriasFilme();
                break;
            case 'M':
                categoriasEncontrada = getCategoriasMusica();
                break;
            case 'L':
                categoriasEncontrada = getCategoriasLivro();
                break;
            default:
                return null;
        }
        return categoriasEncontrada;
    }

    public boolean removerCategoriaPorTipo(String categoria, char tipo) {
        char tipoUpper = Character.toUpperCase(tipo);
        boolean deuCerto;

        switch (tipoUpper) {
            case 'F':
                deuCerto = removerCategoria(this.categoriasFilme, categoria);
                break;
            case 'M':
                deuCerto = removerCategoria(this.categoriasMusica, categoria);
                break;
            case 'L':
                deuCerto = removerCategoria(this.categoriasLivro, categoria);
                break;
            default:
                return false;
        }
        return deuCerto;
    }

    public boolean atualizarCategoriaPorTipo(String categoria, char tipo, Categoria categoriaNova) {
        char tipoUpper = Character.toUpperCase(tipo);
        boolean deuCerto;
        switch (tipoUpper) {
            case 'F':
                deuCerto = atualizarCategoria(this.categoriasFilme, categoria, categoriaNova);
                break;
            case 'M':
                deuCerto = atualizarCategoria(this.categoriasMusica, categoria, categoriaNova);
                break;
            case 'L':
                deuCerto = atualizarCategoria(this.categoriasLivro, categoria, categoriaNova);
                break;
            default:
                return false;
        }
        return deuCerto;
    }

    public boolean removerTudoCategoria(char tipo) {
        char tipoUpper = Character.toUpperCase(tipo);

        switch (tipoUpper) {
            case 'F':
                removerTudo(this.categoriasFilme);
                break;
            case 'M':
                removerTudo(this.categoriasMusica);
                break;
            case 'L':
                removerTudo(this.categoriasLivro);
                break;
            default:
                return false;
        }
        return true;
    }

    private boolean criarCategoria(List<Categoria> categorias, Categoria categoria, String tipo)  {
        if (categorias.contains(categoria)) {
            return false;
        }

        for (Categoria c : categorias) {
            if (c.getCategoria().equalsIgnoreCase(categoria.getCategoria())) {
                return false;
            }
        }

        categorias.add(categoria);
        return true;
    }

    private Categoria encontrarCategoriaPorNome(List<Categoria> categorias, String nome) {
        Categoria categoriaEncontrada = null;
        for (Categoria categoria : categorias) {
            if (categoria.getCategoria().equalsIgnoreCase(nome)) {
                categoriaEncontrada = categoria;
            }
        }
        return categoriaEncontrada;
    }

    private boolean atualizarCategoria(List<Categoria> categorias, String nome, Categoria categoriaNova) {
        Categoria categoriaAtualizar = encontrarCategoriaPorNome(categorias, nome);
        if (categoriaAtualizar == null) {
            return false;
        }

        categoriaAtualizar.setCategoria(categoriaNova.getCategoria());
        return true;
    }

    private boolean removerCategoria(List<Categoria> categorias, String nome) {
        Categoria categoriaRemover = encontrarCategoriaPorNome(categorias, nome);
        if (categoriaRemover == null) {
            return false;
        }

        categorias.remove(categoriaRemover);
        return true;
    }

    private void removerTudo(List<Categoria> categorias) {
        categorias.removeAll(categorias);
    }

    private List<Categoria> getCategoriasFilme() {
        return categoriasFilme;
    }

    private List<Categoria> getCategoriasMusica() {
        return categoriasMusica;
    }

    private List<Categoria> getCategoriasLivro() {
        return categoriasLivro;
    }
}
