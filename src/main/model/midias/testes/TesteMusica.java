package main.model.midias.testes;

import main.model.midias.Categoria;
import main.model.midias.musica.Musica;
import main.model.pessoa.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteMusica {

    private Musica musica;
    private Categoria categoria;
    private Pessoa artista;

    /**
     * Testa o comportamento de preparar novos objetos antes de cada teste.
     * Deve garantir que cada execução comece em um estado limpo e independente.
     */
    @BeforeEach
    void setup() {
        categoria = new Categoria("Rock");
        artista = new Pessoa("Linkin Park");

        musica = new Musica(
                1,
                "C:/musicas/lp.mp3",
                3.4,
                "In the End",
                220,
                categoria,
                artista
        );
    }

    /**
     * Testa o comportamento do construtor da classe Musica.
     * Deve retornar um objeto corretamente inicializado com todos os atributos válidos.
     */
    @Test
    void deveCriarMusicaCorretamente() {
        assertEquals(1, musica.getId());
        assertEquals("C:/musicas/lp.mp3", musica.getLocal());
        assertEquals(3.4, musica.getTamanhoEmDisco());
        assertEquals("In the End", musica.getTitulo());
        assertEquals(220, musica.getDuracao());
        assertEquals("Rock", musica.getCategoria().getCategoria());
        assertEquals("Linkin Park", musica.getArtista().getNome());
    }

    /**
     * Testa o comportamento do método setArtista quando recebe um artista válido.
     * Deve atualizar corretamente o artista da música.
     */
    @Test
    void deveAlterarArtistaComSucesso() {
        Pessoa novoArtista = new Pessoa("Imagine Dragons");

        musica.setArtista(novoArtista);

        assertEquals("Imagine Dragons", musica.getArtista().getNome());
    }

    /**
     * Testa o comportamento do método setArtista quando recebe nulo.
     * Deve lançar IllegalArgumentException com mensagem adequada.
     */
    @Test
    void deveLancarExcecaoAoDefinirArtistaNulo() {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> musica.setArtista(null)
        );

        assertTrue(e.getMessage().contains("Pessoa (Artista) não pode ser nula."));
    }

    /**
     * Testa o comportamento do método setDuracao quando recebe um valor válido.
     * Deve atualizar a duração corretamente.
     */
    @Test
    void deveAlterarDuracaoCorretamente() {
        musica.setDuracao(300);

        assertEquals(300, musica.getDuracao());
    }

    /**
     * Testa o comportamento do método setDuracao quando recebe um valor inválido.
     * Deve lançar IllegalArgumentException.
     */
    @Test
    void deveLancarExcecaoParaDuracaoInvalida() {
        assertThrows(
                IllegalArgumentException.class,
                () -> musica.setDuracao(0)
        );
    }

    /**
     * Testa o comportamento do método exibirAtributos.
     * Deve retornar uma string contendo todas as informações relevantes da música.
     */
    @Test
    void deveExibirAtributosCorretamente() {
        String atributos = musica.exibirAtributos();

        assertTrue(atributos.contains("In the End"));
        assertTrue(atributos.contains("220"));
        assertTrue(atributos.contains("Rock"));
        assertTrue(atributos.contains("Artista: Linkin Park"));
    }
}
