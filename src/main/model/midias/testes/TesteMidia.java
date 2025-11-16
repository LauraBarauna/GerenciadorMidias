package main.model.midias.testes;

import main.model.midias.Categoria;
import main.model.midias.Midia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteMidia {

    private Midia midia;
    private Categoria categoria;

    /**
     * Testa o comportamento de preparar uma nova mídia antes de cada teste.
     * Deve garantir que cada execução comece com objetos limpos e independentes.
     */
    @BeforeEach
    void setup() {
        categoria = new Categoria("Ação");

        midia = new Midia(
                1,
                "C:/midias/video.mp4",
                100.5,
                "Meu Vídeo",
                120,
                categoria
        );
    }

    /**
     * Testa o comportamento do construtor de Midia.
     * Deve retornar um objeto corretamente inicializado com todos os atributos válidos.
     */
    @Test
    void deveCriarMidiaCorretamente() {
        assertEquals(1, midia.getId());
        assertEquals("C:/midias/video.mp4", midia.getLocal());
        assertEquals(100.5, midia.getTamanhoEmDisco());
        assertEquals("Meu Vídeo", midia.getTitulo());
        assertEquals(120, midia.getDuracao());
        assertEquals("Ação", midia.getCategoria().getCategoria());
    }

    /**
     * Testa o comportamento de alterar o ID da mídia.
     * Deve atualizar o valor corretamente quando um ID válido é informado.
     */
    @Test
    void deveAlterarIdComSucesso() {
        midia.setId(10);
        assertEquals(10, midia.getId());
    }

    /**
     * Testa o comportamento de alterar o caminho do arquivo.
     * Deve armazenar corretamente o novo local.
     */
    @Test
    void deveAlterarLocalComSucesso() {
        midia.setLocal("D:/outro/local.mp4");
        assertEquals("D:/outro/local.mp4", midia.getLocal());
    }

    /**
     * Testa o comportamento de alterar o tamanho em disco.
     * Deve atualizar o valor quando informado um tamanho válido.
     */
    @Test
    void deveAlterarTamanhoComSucesso() {
        midia.setTamanhoEmDisco(55.0);
        assertEquals(55.0, midia.getTamanhoEmDisco());
    }

    /**
     * Testa o comportamento do método setTitulo.
     * Deve atualizar o título corretamente quando receber um valor válido.
     */
    @Test
    void deveAlterarTituloComSucesso() {
        midia.setTitulo("Senhor dos Anéis");
        assertEquals("Senhor dos Anéis", midia.getTitulo());
    }

    /**
     * Testa o comportamento de alterar a duração da mídia.
     * Deve armazenar o novo valor corretamente quando válido.
     */
    @Test
    void deveAlterarDuracaoComSucesso() {
        midia.setDuracao(300);
        assertEquals(300, midia.getDuracao());
    }

    /**
     * Testa o comportamento de alterar a categoria da mídia.
     * Deve substituir pela nova categoria enviada.
     */
    @Test
    void deveAlterarCategoriaComSucesso() {
        Categoria nova = new Categoria("Terror");
        midia.setCategoria(nova);
        assertEquals("Terror", midia.getCategoria().getCategoria());
    }

    // ------------------- TESTES DE EXCEÇÕES -------------------

    /**
     * Testa o comportamento do método setId quando recebe um valor inválido.
     * Deve lançar IllegalArgumentException.
     */
    @Test
    void deveLancarExcecaoIdInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> midia.setId(0)
        );
    }

    /**
     * Testa o comportamento do método setLocal quando recebe uma string inválida.
     * Deve lançar IllegalArgumentException.
     */
    @Test
    void deveLancarExcecaoLocalInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> midia.setLocal(" ")
        );
    }

    /**
     * Testa o comportamento do método setTamanhoEmDisco com valor inválido.
     * Deve retornar exceção.
     */
    @Test
    void deveLancarExcecaoTamanhoInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> midia.setTamanhoEmDisco(0)
        );
    }

    /**
     * Testa o comportamento do método setTitulo quando recebe um valor inválido.
     * Deve lançar IllegalArgumentException.
     */
    @Test
    void deveLancarExcecaoTituloInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> midia.setTitulo("")
        );
    }

    /**
     * Testa o comportamento de setDuracao com um valor inválido.
     * Deve lançar IllegalArgumentException.
     */
    @Test
    void deveLancarExcecaoDuracaoInvalida() {
        assertThrows(
                IllegalArgumentException.class,
                () -> midia.setDuracao(-10)
        );
    }

    /**
     * Testa o comportamento do método setCategoria ao receber nulo.
     * Deve lançar IllegalArgumentException.
     */
    @Test
    void deveLancarExcecaoCategoriaNula() {
        assertThrows(
                IllegalArgumentException.class,
                () -> midia.setCategoria(null)
        );
    }

    /**
     * Testa o comportamento do método exibirAtributos.
     * Deve retornar uma string contendo todas as informações da mídia.
     */
    @Test
    void deveExibirAtributosCorretamente() {
        String atributos = midia.exibirAtributos();

        assertTrue(atributos.contains("Id: 1"));
        assertTrue(atributos.contains("Local do arquivo: C:/midias/video.mp4"));
        assertTrue(atributos.contains("Tamanho do arquivo: 100.5"));
        assertTrue(atributos.contains("Título: Meu Vídeo"));
        assertTrue(atributos.contains("Duração: 120"));
        assertTrue(atributos.contains("Categoria: Ação"));
    }
}
