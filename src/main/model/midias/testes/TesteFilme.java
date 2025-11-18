package main.model.midias.testes;

import main.model.idioma.Idioma;
import main.model.midias.Categoria;
import main.model.midias.filme.Filme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * * @author Luiz Felipe Batista Serpa de Maria
 */
public class TesteFilme {

    private Categoria categoria;
    private Idioma idioma;

    /**
     * Executado antes de cada teste.
     * Cria uma categoria e um idioma válidos que serão usados repetidamente.
     */
    @BeforeEach
    void setup() {
        categoria = new Categoria("Ação");
        idioma = new Idioma("Português");
    }

    /**
     * Testa a criação correta de um filme usando valores válidos.
     */
    @Test
    void deveCriarFilmeCorretamente() {

        Filme filme = new Filme(
                1,
                "C:/filmes/harrypotter.mp4",
                150.0,
                "Harry Potter e a Pedra Filosofal",
                152,
                categoria,
                idioma
        );

        assertEquals(1, filme.getId());
        assertEquals("C:/filmes/harrypotter.mp4", filme.getLocal());
        assertEquals(150.0, filme.getTamanhoEmDisco());
        assertEquals("Harry Potter e a Pedra Filosofal", filme.getTitulo());
        assertEquals(152, filme.getDuracao());
        assertEquals(categoria, filme.getCategoria());
        assertEquals(idioma, filme.getIdioma());
    }

    /**
     * Testa se o construtor lança exceção quando o idioma é nulo.
     */
    @Test
    void deveLancarExcecaoQuandoIdiomaForNulo() {

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Filme(
                        1,
                        "C:/filmes/senhoraneis.mp4",
                        200.0,
                        "O Senhor dos Anéis: A Sociedade do Anel",
                        178,
                        categoria,
                        null // inválido
                )
        );

        assertEquals("Idioma não pode ser nulo.", e.getMessage());
    }

    /**
     * Testa se o método setIdioma atualiza o idioma corretamente.
     * Exemplo: mudar "Harry Potter" para idioma Inglês.
     */
    @Test
    void deveDefinirIdiomaCorretamente() {

        Filme filme = new Filme(
                2,
                "C:/filmes/harrypotter2.mp4",
                160.0,
                "Harry Potter e a Câmara Secreta",
                161,
                categoria,
                idioma
        );

        Idioma novoIdioma = new Idioma("Inglês");
        filme.setIdioma(novoIdioma);

        assertEquals(novoIdioma, filme.getIdioma());
    }

    /**
     * Testa se setIdioma lança exceção ao receber null.
     */
    @Test
    void deveLancarExcecaoNoSetIdiomaComNulo() {

        Filme filme = new Filme(
                3,
                "C:/filmes/titanic.mp4",
                200.0,
                "Titanic",
                195,
                categoria,
                idioma
        );

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                filme.setIdioma(null)
        );

        assertEquals("Idioma não pode ser nulo.", e.getMessage());
    }

    /**
     * Testa o método exibirAtributos usando o filme "Nárnia".
     * Verifica se todos os atributos aparecem na string final.
     */
    @Test
    void deveExibirTodosAtributosCorretamente() {

        Filme filme = new Filme(
                4,
                "C:/filmes/narnia.mp4",
                180.0,
                "As Crônicas de Nárnia",
                143,
                categoria,
                idioma
        );

        String atributos = filme.exibirAtributos();

        assertTrue(atributos.contains("Id: 4"));
        assertTrue(atributos.contains("Local do arquivo: C:/filmes/narnia.mp4"));
        assertTrue(atributos.contains("Tamanho do arquivo: 180.0"));
        assertTrue(atributos.contains("Título: As Crônicas de Nárnia"));
        assertTrue(atributos.contains("Duração: 143"));
        assertTrue(atributos.contains("Categoria: Ação"));
        assertTrue(atributos.contains("Idioma: Português"));
    }
}
