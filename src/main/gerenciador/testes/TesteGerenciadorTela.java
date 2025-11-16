package main.gerenciador.testes;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.MidiaController;
import main.controller.PessoaController;
import main.gerenciador.GerenciadorTela;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TesteGerenciadorTela {

    /**
     * Testa se o objeto GerenciadorTela é construído corretamente.
     * Aqui usamos controllers reais porque o construtor apenas os repassa
     * para as telas internas, sem executar lógica adicional.
     */
    @Test
    public void deveConstruirGerenciadorTela() {

        CategoriaController categoriaController = new CategoriaController();
        IdiomaController idiomaController = new IdiomaController();
        PessoaController pessoaController = new PessoaController();
        MidiaController midiaController = new MidiaController();

        GerenciadorTela gerenciadorTela =
                new GerenciadorTela(categoriaController, idiomaController, pessoaController, midiaController);

        assertNotNull(gerenciadorTela);
    }

    /**
     * Testa a ação interna usada para configurar botões da tela de mídias.
     * O método privado adicionarAcoesMidia() deve retornar EXATAMENTE 5 ações:
     *  Adicionar
     *  Listar Todos
     *  Listar um
     *  Atualizar
     *  Remover
     *
     * Usamos reflexão porque o método é privado. Isso garante que possam validar o comportamento sem expor a implementação.
     */
    @Test
    public void deveRetornarAcoesMidia() throws Exception {

        GerenciadorTela gt = new GerenciadorTela(
                new CategoriaController(),
                new IdiomaController(),
                new PessoaController(),
                new MidiaController()
        );

        var metodo = GerenciadorTela.class.getDeclaredMethod("adicionarAcoesMidia");
        metodo.setAccessible(true);

        List<String> lista = (List<String>) metodo.invoke(gt);

        assertEquals(5, lista.size());
        assertTrue(lista.contains("Adicionar"));
        assertTrue(lista.contains("Listar Todos"));
        assertTrue(lista.contains("Listar um"));
        assertTrue(lista.contains("Atualizar"));
        assertTrue(lista.contains("Remover"));
    }

    /**
     * Testa o método privado adicionarAcoes(), responsável por montar
     * o conjunto padrão de ações simples exibidas nas telas de categorias, idiomas e pessoas.
     * Esse método deve sempre retornar APENAS duas ações:
     *  Adicionar
     *  Listar
     * Usamos reflexão para validar o comportamento interno sem modificar a visibilidade original do código do projeto.
     */
    @Test
    public void deveRetornarAcoesSimples() throws Exception {

        GerenciadorTela gt = new GerenciadorTela(
                new CategoriaController(),
                new IdiomaController(),
                new PessoaController(),
                new MidiaController()
        );

        var metodo = GerenciadorTela.class.getDeclaredMethod("adicionarAcoes");
        metodo.setAccessible(true);

        List<String> lista = (List<String>) metodo.invoke(gt);

        assertEquals(2, lista.size());
        assertTrue(lista.contains("Adicionar"));
        assertTrue(lista.contains("Listar"));
    }
}
