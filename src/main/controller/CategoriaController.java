package main.controller;

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
    public CategoriaController(GerenciadorCategoria gerenciador) {
        this.gerenciador = gerenciador;
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
     * Atualiza o nome de uma categoria. A categoria é localizada pelo nomeCategoria antigo e seu nome é substituído pelo nome contido em categoriaNova.
     * @param nomeCategoria: O nome antigo da categoria a ser atualizada.
     * @param categoriaNova: O objeto Categoria contendo o novo nome.
     * @param tipoCategoria: O tipo de mídia associado ('F', 'M' ou 'L').
     * @throws CategoriaNaoEncontradaException Se a categoria original não for encontrada para atualização.
     */
    public void atualziarCategoria (String nomeCategoria, Categoria categoriaNova, String tipoCategoria) throws CategoriaNaoEncontradaException {
        boolean atualizou = getGerenciador().atualizarCategoriaPorTipo(nomeCategoria,
                Character.toUpperCase(tipoCategoria.charAt(0)),
                categoriaNova);
        if (!atualizou) {
            throw new CategoriaNaoEncontradaException(nomeCategoria, tipoCategoria);
        }
    }

    /**
     * Lista todas as categorias cadastradas para um tipo de mídia específico. Retorna apenas os nomes das categorias (String) para a camada de apresentação.
     * @param tipoCategoria: O tipo de mídia associado ('F', 'M' ou 'L').
     * @return Uma lista de strings contendo os nomes das categorias.
     * @throws RuntimeException Se o tipo de categoria for inválido e o gerenciador retornar nulo.
     */
    public List<String> listarCategorias (String tipoCategoria) throws RuntimeException {
        List<String> categorias = new ArrayList<>();
        
        List<Categoria> categoriasEncontradas = gerenciador.encontrarCategorias(Character.toUpperCase(tipoCategoria.charAt(0)));
        
        if (categoriasEncontradas == null) {
            // Lógica para tratar tipo inválido, se necessário, ou assumir que o RuntimeException é suficiente.
             throw new RuntimeException("Tipo de categoria inválido: " + tipoCategoria);
        }

        for (Categoria c : categoriasEncontradas) {
            categorias.add(c.getCategoria());
        }
        return categorias;
    }

    /**
     * Retorna a instância do GerenciadorCategoria.
     * @return O GerenciadorCategoria em uso.
     */
    public GerenciadorCategoria getGerenciador() {
        return gerenciador;
    }
}