package main.model.midias.testes;

import main.excecoes.autor.AutorDuplicadoException;
import main.excecoes.autor.AutorNaoEncontradoException;
import main.model.midias.Categoria;
import main.model.midias.livro.Livro;
import main.model.pessoa.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * * @author Luiz Felipe Batista Serpa de Maria
 */

public class TesteLivro {

    private Livro livro;
    private Categoria categoria;

    /**
     * Testa o comportamento de preparar um livro antes de cada execução.
     * Deve garantir que cada teste tenha objetos limpos e independentes.
     */
    @BeforeEach
    void setup() {
        categoria = new Categoria("Terror Gótico");

        livro = new Livro(
                1,
                "C:/livros/frankenstein.pdf",
                4.5,
                "Frankenstein",
                280,
                categoria
        );
    }

    /**
     * Testa o comportamento do construtor do livro.
     * Deve retornar um objeto corretamente inicializado.
     */
    @Test
    void deveCriarLivroCorretamente() {
        assertEquals(1, livro.getId());
        assertEquals("C:/livros/frankenstein.pdf", livro.getLocal());
        assertEquals(4.5, livro.getTamanhoEmDisco());
        assertEquals("Frankenstein", livro.getTitulo());
        assertEquals(280, livro.getDuracao());
        assertEquals("Terror Gótico", livro.getCategoria().getCategoria());
    }

    /**
     * Testa o comportamento de adicionar um autor ao livro.
     * Deve retornar sucesso ao incluir um autor válido.
     */
    @Test
    void deveAdicionarAutorCorretamente() {
        Pessoa autor = new Pessoa("Mary Shelley");

        livro.incluirAutor(autor);

        assertEquals(1, livro.getAutores().size());
        assertEquals("Mary Shelley", livro.getAutores().get(0).getNome());
    }

    /**
     * Testa a tentativa de adicionar o mesmo autor duas vezes.
     * Deve retornar exceção de autor duplicado.
     */
    @Test
    void deveLancarExcecaoAoAdicionarAutorDuplicado() {

        livro.incluirAutor(new Pessoa("Mary Shelley"));

        AutorDuplicadoException e = assertThrows(
                AutorDuplicadoException.class,
                () -> livro.incluirAutor(new Pessoa("Mary Shelley"))
        );

        assertTrue(e.getMessage().contains("Mary Shelley"));
        assertTrue(e.getMessage().contains("Frankenstein"));
    }

    /**
     * Testa a busca de um autor existente pelo nome.
     * Deve retornar o autor encontrado.
     */
    @Test
    void deveEncontrarAutor() {
        livro.incluirAutor(new Pessoa("Bram Stoker"));

        Pessoa encontrado = livro.getAutor("Bram Stoker");

        assertEquals("Bram Stoker", encontrado.getNome());
    }

    /**
     * Testa a busca de um autor inexistente.
     * Deve retornar exceção informando que o autor não foi encontrado.
     */
    @Test
    void deveLancarExcecaoAoBuscarAutorInexistente() {

        AutorNaoEncontradoException e = assertThrows(
                AutorNaoEncontradoException.class,
                () -> livro.getAutor("Desconhecido")
        );

        assertTrue(e.getMessage().contains("Desconhecido"));
    }

    /**
     * Testa a remoção de um autor existente.
     * Deve retornar sucesso ao remover e a lista fica vazia.
     */
    @Test
    void deveRemoverAutorCorretamente() {
        livro.incluirAutor(new Pessoa("Robert Louis Stevenson"));

        livro.removerAutor("Robert Louis Stevenson");

        assertThrows(RuntimeException.class, () -> livro.getAutores());
    }

    /**
     * Testa a tentativa de remover um autor inexistente.
     * Deve retornar exceção de autor não encontrado.
     */
    @Test
    void deveLancarExcecaoAoRemoverAutorInexistente() {

        assertThrows(AutorNaoEncontradoException.class, () ->
                livro.removerAutor("Ninguém")
        );
    }

    /**
     * Testa a obtenção da lista de autores quando ela está vazia.
     * Deve retornar exceção indicando que não há autores.
     */
    @Test
    void deveLancarExcecaoQuandoNaoHaAutores() {

        RuntimeException e = assertThrows(
                RuntimeException.class,
                () -> livro.getAutores()
        );

        assertTrue(e.getMessage().contains("não possui autores"));
    }

    /**
     * Testa a exibição de atributos quando há autores cadastrados.
     * Deve retornar string contendo a lista de autores.
     */
    @Test
    void deveExibirAtributosComAutores() {

        livro.incluirAutor(new Pessoa("Mary Shelley"));
        livro.incluirAutor(new Pessoa("Bram Stoker"));

        String atributos = livro.exibirAtributos();

        assertTrue(atributos.contains("Mary Shelley"));
        assertTrue(atributos.contains("Bram Stoker"));
        assertTrue(atributos.contains("Autores:"));
    }

    /**
     * Testa a exibição de atributos quando não há autores cadastrados.
     * Deve retornar mensagem informando ausência de autores.
     */
    @Test
    void deveExibirAtributosSemAutores() {

        String atributos = livro.exibirAtributos();

        assertTrue(atributos.contains("Nenhum autor cadastrado"));
    }
}
