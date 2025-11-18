package main.gerenciador;

import main.model.midias.Categoria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Laura Barauna
 */
public class GerenciadorCategoria {
    
    List<Categoria> categoriasFilme;
    List<Categoria> categoriasMusica;
    List<Categoria> categoriasLivro;

    /**
     * Construtor da classe GerenciadorCategoria. Inicializa as três listas de categorias como ArrayList vazias.
     */
    public GerenciadorCategoria() {
        this.categoriasFilme = new ArrayList<>();
        this.categoriasMusica = new ArrayList<>();
        this.categoriasLivro = new ArrayList<>();
    }

    /**
     * Cria uma nova categoria e a adiciona à lista correspondente ao tipo de mídia. Garante que a categoria não seja duplicada dentro de sua respectiva lista.
     * @param categoria: O objeto Categoria a ser criado.
     * @param tipo: O tipo de mídia: 'F' para Filme, 'M' para Música, 'L' para Livro.
     * @return true se a categoria foi criada com sucesso, false se o tipo for inválido ou a categoria for duplicada.
     */
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

    /**
     * Encontra e retorna uma categoria pelo nome dentro da lista do tipo de mídia especificado.
     * @param categoria: O nome da categoria a ser encontrada.
     * @param tipo: O tipo de mídia: 'F' para Filme, 'M' para Música, 'L' para Livro.
     * @return O objeto Categoria encontrado, ou null se não for encontrado ou se o tipo for inválido.
     */
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

    /**
     * Retorna a lista completa de categorias para um determinado tipo de mídia.
     * @param tipo: O tipo de mídia: 'F' para Filme, 'M' para Música, 'L' para Livro.
     * @return A List<Categoria> do tipo especificado, ou null se o tipo for inválido.
     */
    public List<Categoria> encontrarCategoriasPorExtensao(String extensao) {
        List<Categoria> categorias = new ArrayList<>();
        switch (extensao.toUpperCase()) {
            case "MP4":
            case "MKV":
                categorias = getCategoriasFilme();
            break;
            case "MP3":
                categorias = getCategoriasMusica();
            break;
            case "PDF":
            case "PUB":
                categorias = getCategoriasLivro();
                break;
            default:
                return null;
        }
        return categorias;
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

    /**
     * Remove uma categoria pelo nome da lista correspondente ao tipo de mídia.
     * @param categoria: O nome da categoria a ser removida.
     * @param tipo: O tipo de mídia: 'F' para Filme, 'M' para Música, 'L' para Livro.
     * @return true se a categoria foi removida com sucesso, false se não foi encontrada ou se o tipo for inválido.
     */
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

    /**
     * Lógica central para adicionar uma categoria a uma lista específica. Verifica duplicidade pelo nome da categoria, ignorando letras maiúsculas/minúsculas.
     * @param categorias: A lista de categorias onde a nova categoria será adicionada.
     * @param categoria: O objeto {@code Categoria} a ser adicionado.
     * @param tipo: O tipo da lista (usado apenas para fins de debug/log, não no código).
     * @return Se true será adicionada, e false se duplicada.
     */
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

    /**
     * Lógica central para encontrar uma categoria pelo nome em uma lista.
     * @param categorias A lista de categorias a ser pesquisada.
     * @param nome O nome da categoria a ser procurada.
     * @return O objeto Categoria encontrado, ou null se não for encontrado.
     */
    private Categoria encontrarCategoriaPorNome(List<Categoria> categorias, String nome) {
        Categoria categoriaEncontrada = null;
        for (Categoria categoria : categorias) {
            if (categoria.getCategoria().equalsIgnoreCase(nome)) {
                categoriaEncontrada = categoria;
                break;
            }
        }
        return categoriaEncontrada;
    }

    /**
     * Lógica central para remover uma categoria pelo nome.
     * @param categorias: A lista de categorias onde a categoria deve ser removida.
     * @param nome: O nome da categoria a ser removida.
     * @return Se true é removida, se false não encontrada.
     */
    private boolean removerCategoria(List<Categoria> categorias, String nome) {
        Categoria categoriaRemover = encontrarCategoriaPorNome(categorias, nome);
        if (categoriaRemover == null) {
            return false;
        }

        categorias.remove(categoriaRemover);
        return true;
    }

    /**
     * @return List<Categoria> para Filmes.
     */
    private void removerTudo(List<Categoria> categorias) {
        categorias.removeAll(categorias);
    }

    private List<Categoria> getCategoriasFilme() {
        return categoriasFilme;
    }

    /**
     * @return List<Categoria> para Músicas.
     */
    private List<Categoria> getCategoriasMusica() {
        return categoriasMusica;
    }

    /**
     * @return List<Categoria> para Livros.
     */
    private List<Categoria> getCategoriasLivro() {
        return categoriasLivro;
    }
}