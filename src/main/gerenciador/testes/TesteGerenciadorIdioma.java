package main.gerenciador.testes;

import main.gerenciador.GerenciadorIdioma;
import main.model.idioma.Idioma;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TesteGerenciadorIdioma {

    /**
     * Testa a adição de um idioma válido.
     * O método deve retornar true e o objeto deve ser inserido na lista interna.
     */
    @Test
    public void testAdicionarIdioma() {

        GerenciadorIdioma gi = new GerenciadorIdioma();

        Idioma idioma = new Idioma("Português");

        boolean ok = gi.adicionarIdioma(idioma);

        assertTrue(ok);
        assertEquals(1, gi.getIdiomas().size());
    }

    /**
     * Testa a tentativa de adicionar um idioma nulo.
     * Deve retornar false e a lista não deve ser alterada.
     */
    @Test
    public void testAdicionarIdiomaNull() {

        GerenciadorIdioma gi = new GerenciadorIdioma();

        boolean ok = gi.adicionarIdioma(null);

        assertFalse(ok);
        assertEquals(0, gi.getIdiomas().size());
    }

    /**
     * Testa se o gerenciador impede a adição de idiomas duplicados.
     * Dois objetos diferentes com o mesmo nome devem ser rejeitados.
     */
    @Test
    void testAdicionarIdiomaDuplicado() {

        GerenciadorIdioma gi = new GerenciadorIdioma();

        Idioma i1 = new Idioma("Inglês");
        Idioma i2 = new Idioma("Inglês");

        gi.adicionarIdioma(i1);
        boolean ok = gi.adicionarIdioma(i2);

        assertFalse(ok);
        assertEquals(1, gi.getIdiomas().size());
    }

    /**
     * Testa a detecção de duplicatas ignorando diferenças de maiúsculas/minúsculas.
     * Nomes equivalentes com letras diferentes devem ser considerados duplicados.
     */
    @Test
    void testAdicionarIdiomaCaseInsensitive() {

        GerenciadorIdioma gi = new GerenciadorIdioma();

        Idioma i1 = new Idioma("Espanhol");
        Idioma i2 = new Idioma("esPanHol");

        gi.adicionarIdioma(i1);
        boolean ok = gi.adicionarIdioma(i2);

        assertFalse(ok);
        assertEquals(1, gi.getIdiomas().size());
    }

    /**
     * Testa a remoção de um idioma existente pelo nome.
     * A operação deve retornar true e remover o idioma da lista.
     */
    @Test
    void testRemoverIdioma() {

        GerenciadorIdioma gi = new GerenciadorIdioma();

        Idioma i = new Idioma("Alemão");

        gi.adicionarIdioma(i);
        boolean removido = gi.removerIdioma("Alemão");

        assertTrue(removido);
        assertEquals(0, gi.getIdiomas().size());
    }

    /**
     * Testa a tentativa de remover um idioma não cadastrado.
     * Deve retornar false.
     */
    @Test
    public void testRemoverIdiomaInexistente() {

        GerenciadorIdioma gi = new GerenciadorIdioma();

        boolean removido = gi.removerIdioma("Russo");

        assertFalse(removido);
    }

    /**
     * Testa a remoção passando nomes inválidos como entrada (nulo, vazio ou só espaços).
     * Todos os casos devem resultar em false.
     */
    @Test
    public void testRemoverIdiomaStringInvalida() {

        GerenciadorIdioma gi = new GerenciadorIdioma();

        gi.adicionarIdioma(new Idioma("Italiano"));

        assertFalse(gi.removerIdioma(""));
        assertFalse(gi.removerIdioma("   "));
        assertFalse(gi.removerIdioma(null));
    }

    /**
     * Testa a remoção de todos os idiomas quando existem itens na lista.
     * Deve retornar true e a lista deve ser completamente vazia.
     */
    @Test
    public void testRemoverTodosIdiomas() {

        GerenciadorIdioma gi = new GerenciadorIdioma();

        gi.adicionarIdioma(new Idioma("Coreano"));
        gi.adicionarIdioma(new Idioma("Japonês"));

        boolean ok = gi.removerTodosIdiomas();

        assertTrue(ok);
        assertEquals(0, gi.getIdiomas().size());
    }

    /**
     * Testa o comportamento ao tentar remover todos os idiomas quando a lista está vazia.
     * Deve retornar false, pois não há nada para remover.
     */
    @Test
    public void testRemoverTodosIdiomasListaVazia() {

        GerenciadorIdioma gi = new GerenciadorIdioma();

        boolean ok = gi.removerTodosIdiomas();

        assertFalse(ok);
    }

    /**
     * Testa se o método getIdiomas retorna corretamente a lista atual de idiomas.
     * Verifica a quantidade e os nomes armazenados.
     */
    @Test
    public void testGetIdiomas() {

        GerenciadorIdioma gi = new GerenciadorIdioma();

        gi.adicionarIdioma(new Idioma("Francês"));
        gi.adicionarIdioma(new Idioma("Mandarim"));

        List<Idioma> idiomas = gi.getIdiomas();

        assertEquals(2, idiomas.size());
        assertEquals("Francês", idiomas.get(0).exibirIdioma());
        assertEquals("Mandarim", idiomas.get(1).exibirIdioma());
    }
}
