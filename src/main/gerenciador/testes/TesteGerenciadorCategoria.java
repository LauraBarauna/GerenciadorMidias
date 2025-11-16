package main.gerenciador.testes;

import main.gerenciador.GerenciadorCategoria;
import main.model.midias.Categoria;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TesteGerenciadorCategoria {

    /**
     * Testa a criação de uma categoria associada ao tipo FILME ('F').
     * O método deve retornar true e a categoria deve ser adicionada à lista correspondente.
     */
    @Test
    public void testCriarCategoriaPorTipoFilme() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        Categoria c = new Categoria("Ação");

        boolean criado = gc.criarCategoriaPorTipo(c, 'F');
        assertTrue(criado);

        assertEquals(1, gc.encontrarCategorias('F').size());
    }

    /**
     * Testa o comportamento ao tentar adicionar uma categoria duplicada para o mesmo tipo.
     * A primeira inserção deve funcionar, mas a segunda deve retornar false e não alterar a lista.
     */
    @Test
    public void testCriarCategoriaDuplicada() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        Categoria c1 = new Categoria("Terror");
        Categoria c2 = new Categoria("Terror");

        gc.criarCategoriaPorTipo(c1, 'F');

        boolean criado2 = gc.criarCategoriaPorTipo(c2, 'F');

        assertFalse(criado2);
        assertEquals(1, gc.encontrarCategorias('F').size());
    }

    /**
     * Testa a tentativa de criar uma categoria usando um tipo inválido.
     * Deve retornar false, pois o tipo fornecido não é reconhecido pelo gerenciador.
     */
    @Test
    public void testCriarCategoriaTipoInvalido() {

        GerenciadorCategoria gc = new GerenciadorCategoria();
        Categoria c = new Categoria("Ação");

        boolean criado = gc.criarCategoriaPorTipo(c, 'X');

        assertFalse(criado);
    }

    /**
     * Testa a busca de uma categoria existente pelo seu nome e tipo.
     * Deve retornar o objeto Categoria correspondente.
     */
    @Test
    public void testEncontrarCategoriaPorTipo() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        Categoria c = new Categoria("Pop");
        gc.criarCategoriaPorTipo(c, 'M');

        Categoria encontrada = gc.encontrarCategoriaPorTipo("Pop", 'M');

        assertNotNull(encontrada);
        assertEquals("Pop", encontrada.getCategoria());
    }

    /**
     * Testa a busca de uma categoria que não existe no tipo informado.
     * O método deve retornar null.
     */
    @Test
    public void testEncontrarCategoriaPorTipoNaoExistente() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        Categoria encontrada = gc.encontrarCategoriaPorTipo("Nada", 'L');

        assertNull(encontrada);
    }

    /**
     * Testa a busca de uma categoria utilizando um tipo inválido.
     * Como o tipo não existe, o método deve retornar null.
     */
    @Test
    public void testEncontrarCategoriaTipoInvalido() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        Categoria encontrada = gc.encontrarCategoriaPorTipo("Ação", 'Z');

        assertNull(encontrada);
    }

    /**
     * Testa a recuperação das categorias associadas à extensão de arquivos de vídeo (ex.: MP4).
     * Como a extensão corresponde ao tipo FILME, o método deve retornar todas as categorias desse tipo.
     */
    @Test
    public void testEncontrarCategoriasPorExtensaoFilme() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        gc.criarCategoriaPorTipo(new Categoria("Drama"), 'F');
        gc.criarCategoriaPorTipo(new Categoria("Ação"), 'F');

        List<Categoria> categorias = gc.encontrarCategoriasPorExtensao("MP4");

        assertEquals(2, categorias.size());
    }

    /**
     * Testa a tentativa de buscar categorias usando uma extensão inválida.
     * Deve retornar null, pois a extensão não está associada a nenhum tipo.
     */
    @Test
    void testEncontrarCategoriasPorExtensaoInvalida() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        List<Categoria> categorias = gc.encontrarCategoriasPorExtensao("EXE");

        assertNull(categorias);
    }

    /**
     * Testa a listagem de todas as categorias associadas ao tipo MÚSICA ('M').
     * Deve retornar a lista com exatamente as categorias cadastradas.
     */
    @Test
    void testEncontrarCategorias() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        gc.criarCategoriaPorTipo(new Categoria("Rock"), 'M');
        gc.criarCategoriaPorTipo(new Categoria("Pop"), 'M');

        List<Categoria> categorias = gc.encontrarCategorias('M');

        assertEquals(2, categorias.size());
    }

    /**
     * Testa a listagem de categorias usando um tipo inválido.
     * Deve retornar null, pois o tipo não pertence a nenhum grupo conhecido.
     */
    @Test
    public void testEncontrarCategoriasTipoInvalido() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        List<Categoria> categorias = gc.encontrarCategorias('Z');

        assertNull(categorias);
    }

    /**
     * Testa a remoção de uma categoria existente no tipo informado.
     * Deve retornar true e a categoria deve ser removida corretamente.
     */
    @Test
    public void testRemoverCategoriaPorTipo() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        gc.criarCategoriaPorTipo(new Categoria("Ficção"), 'L');

        boolean removida = gc.removerCategoriaPorTipo("Ficção", 'L');

        assertTrue(removida);
        assertEquals(0, gc.encontrarCategorias('L').size());
    }

    /**
     * Testa a tentativa de remover uma categoria que não existe.
     * O método deve retornar false, pois não há categoria correspondente para remover.
     */
    @Test
    public void testRemoverCategoriaInexistente() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        boolean removida = gc.removerCategoriaPorTipo("Nada", 'M');

        assertFalse(removida);
    }

    /**
     * Testa a remoção de uma categoria utilizando um tipo inválido.
     * Deve retornar false, pois o tipo não é reconhecido.
     */
    @Test
    public void testRemoverCategoriaTipoInvalido() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        boolean removida = gc.removerCategoriaPorTipo("Ação", 'X');

        assertFalse(removida);
    }

    /**
     * Testa a atualização de uma categoria existente para uma nova categoria.
     * O método deve retornar true e substituir corretamente o valor anterior.
     */
    @Test
    public void testAtualizarCategoriaPorTipo() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        gc.criarCategoriaPorTipo(new Categoria("Antigo"), 'F');

        Categoria nova = new Categoria("Novo");

        boolean atualizado = gc.atualizarCategoriaPorTipo("Antigo", 'F', nova);

        assertTrue(atualizado);
        assertEquals("Novo", gc.encontrarCategoriaPorTipo("Novo", 'F').getCategoria());
    }

    /**
     * Testa a tentativa de atualizar uma categoria que não existe.
     * O método deve retornar false.
     */
    @Test
    public void testAtualizarCategoriaNaoExistente() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        Categoria nova = new Categoria("Novo");

        boolean atualizado = gc.atualizarCategoriaPorTipo("Nada", 'F', nova);

        assertFalse(atualizado);
    }

    /**
     * Testa a tentativa de atualizar uma categoria utilizando um tipo inválido.
     * Deve retornar false, pois o tipo não é reconhecido.
     */
    @Test
    public void testAtualizarCategoriaTipoInvalido() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        Categoria nova = new Categoria("Novo");

        boolean atualizado = gc.atualizarCategoriaPorTipo("Teste", 'X', nova);

        assertFalse(atualizado);
    }

    /**
     * Testa a remoção completa de todas as categorias de um tipo.
     * Deve retornar true e esvaziar a lista correspondente.
     */
    @Test
    public void testRemoverTudoCategoria() {

        GerenciadorCategoria gc = new GerenciadorCategoria();

        gc.criarCategoriaPorTipo(new Categoria("Latina"), 'M');
        gc.criarCategoriaPorTipo(new Categoria("Pop"), 'M');

        boolean ok = gc.removerTudoCategoria('M');

        assertTrue(ok);
        assertEquals(0, gc.encontrarCategorias('M').size());
    }

    /**
     * Testa o comportamento ao tentar remover todas as categorias de um tipo inválido.
     * Deve retornar false, pois o tipo não é reconhecido.
     */
    @Test
    public void testRemoverTudoCategoriaTipoInvalido() {
        GerenciadorCategoria gc = new GerenciadorCategoria();
        boolean ok = gc.removerTudoCategoria('X');
        assertFalse(ok);
    }
}
