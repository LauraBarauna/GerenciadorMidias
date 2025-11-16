package main.controller;

import main.excecoes.arquivo.ExtensaoInvalidaException;
import main.excecoes.categoria.CategoriaDuplicadaException;
import main.excecoes.categoria.CategoriaNaoEncontradaException;
import main.gerenciador.GerenciadorCategoria;
import main.model.midias.Categoria;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Laura
 */
public class CategoriaController {
    
    private GerenciadorCategoria gerenciador;

    /**
     * Construtor do Controller.
     * @param gerenciador: A instância do gerenciador a ser utilizada.
     */
    public CategoriaController() {
        this.gerenciador = new GerenciadorCategoria();
    }

    /**
     * Adiciona uma nova categoria ao sistema. Recebe o nome e o tipo como String, cria o objeto Categoria e delega a criação para o gerenciador. Lança exceção de duplicidade se falhar.
     * @param nomeCategoria: O nome da categoria a ser adicionada.
     * @param tipoCategoria: O tipo de mídia associado ('F', 'M' ou 'L').
     * @throws CategoriaDuplicadaException Se a categoria já existir para o tipo especificado.
     */
    public void adicionarCategoria (String nomeCategoria, String tipoCategoria) throws CategoriaDuplicadaException {
        Categoria novaCategoria = new Categoria(nomeCategoria);
        
        boolean adiconou = getGerenciador().criarCategoriaPorTipo(novaCategoria,
                Character.toUpperCase(tipoCategoria.charAt(0)));

        if (!adiconou) {
            throw new CategoriaDuplicadaException(nomeCategoria, tipoCategoria);
        }
    }

    /**
     * Verifica se uma categoria existe para um dado tipo de mídia. Se a categoria não for encontrada, lança a exceção.
     * @param nomeCategoria: O nome da categoria a ser listada/verificada.
     * @param tipoCategoria: O tipo de mídia associado ('F', 'M' ou 'L').
     * @throws CategoriaNaoEncontradaException Se a categoria não for encontrada.
     */
    public void listarCategoriaPorNome(String nomeCategoria, String tipoCategoria) throws CategoriaNaoEncontradaException {
        Categoria categoriaLista = getGerenciador().encontrarCategoriaPorTipo(nomeCategoria,
                Character.toUpperCase(tipoCategoria.charAt(0)));

        if (categoriaLista == null) {
            throw new CategoriaNaoEncontradaException(nomeCategoria, tipoCategoria);
        }
    }

    /**
     * Remove uma categoria existente do sistema. Lança exceção se a remoção falhar, geralmente se a categoria não ter sido encontrada.
     * @param nomeCategoria: O nome da categoria a ser removida.
     * @param tipoCategoria: O tipo de mídia associado ('F', 'M' ou 'L').
     * @throws CategoriaNaoEncontradaException Se a categoria não for encontrada para remoção.
     */
    public void removerCategoria (String nomeCategoria, String tipoCategoria) throws CategoriaNaoEncontradaException {
        boolean removeu = getGerenciador().removerCategoriaPorTipo(nomeCategoria,
                Character.toUpperCase(tipoCategoria.charAt(0)));

        if (!removeu) {
            throw new CategoriaNaoEncontradaException(nomeCategoria, tipoCategoria);
        }
    }

    /**
     * Remove todas as categorias associadas a um determinado tipo de mídia.
     *
     * @param tipoCategoria O tipo de mídia cujas categorias devem ser removidas.
     *                      Deve ser 'F', 'M' ou 'L'.
     * @throws RuntimeException Se o tipo de mídia fornecido não existir.
     */
    public void removerTudo(String tipoCategoria) throws RuntimeException {
        boolean removeuTudo = getGerenciador().removerTudoCategoria(
                Character.toUpperCase(tipoCategoria.charAt(0)));

        if (!removeuTudo) {
            throw new RuntimeException("Tipo de mídia não existe");
        }
    }
    

    /**
     * Lista todas as categorias cadastradas para um tipo de mídia específico. Retorna apenas os nomes das categorias (String) para a camada de apresentação.
     * @param tipoCategoria: O tipo de mídia associado ('F', 'M' ou 'L').
     * @return Uma lista de strings contendo os nomes das categorias.
     * @throws RuntimeException Se o tipo de categoria for inválido e o gerenciador retornar nulo.
     */
    public List<String> listarCategoriasString(String tipoCategoria)  {
        List<String> categorias = new ArrayList<>();

        for (Categoria c : gerenciador.encontrarCategorias(
                Character.toUpperCase(tipoCategoria.charAt(0))
        )) {
            categorias.add(c.getCategoria());
        }
        return categorias;
    }

    /**
     * Retorna a instância do GerenciadorCategoria.
     * @return O GerenciadorCategoria em uso.
     */
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