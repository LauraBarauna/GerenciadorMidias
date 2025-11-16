package main.model.midias.testes;

import main.model.midias.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteCategoria {

    private Categoria categoria;

    /**
     * Executa antes de cada teste.
     * Aqui sempre criamos uma categoria válida para evitar repetição e garantir que cada método teste comece em um estado conhecido.
     */
    @BeforeEach
    public void setup() {
        categoria = new Categoria("Ação");
    }

    /**
     * Testa se o construtor inicializa corretamente o objeto Categoria.
     * Deve armazenar exatamente o nome informado.
     */
    @Test
    public void deveCriarCategoriaCorretamente() {
        assertEquals("Ação", categoria.getCategoria());
    }

    /**
     * Testa a alteração do nome da categoria com um valor válido.
     * Isso garante que o setter esteja funcionando e validando corretamente.
     */
    @Test
    public void deveAlterarCategoriaComSucesso() {
        categoria.setCategoria("Comédia");

        assertEquals("Comédia", categoria.getCategoria());
    }

    /**
     * O construtor NÃO deve aceitar valores nulos.
     * Aqui verificamos se a exceção é lançada corretamente e se a mensagem contém a justificativa adequada.
     */
    @Test
    public void deveLancarExcecaoSeConstrutorReceberNulo() {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Categoria(null)
        );

        assertTrue(e.getMessage().contains("não pode ser vazia ou nula"));
    }

    /**
     * Verifica se o construtor rejeita valores vazios ou somente com espaços.
     * Esses valores são considerados inválidos e devem gerar exceção.
     */
    @Test
    public void deveLancarExcecaoSeConstrutorReceberStringVazia() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Categoria("   ")
        );
    }

    /**
     * O método setCategoria também deve validar nulos.
     * Aqui validamos que a exceção é lançada corretamente.
     */
    @Test
    public void deveLancarExcecaoAoAlterarParaNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> categoria.setCategoria(null)
        );
    }

    /**
     * Valores vazios não podem ser atribuídos ao nome da categoria.
     * O teste garante consistência entre validação do construtor e do setter.
     */
    @Test
    public void deveLancarExcecaoAoAlterarParaVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> categoria.setCategoria("  ")
        );
    }

    /**
     * Testa o toString(), que deve retornar apenas o nome da categoria.
     */
    @Test
    public void deveRetornarToStringCorretamente() {
        assertEquals("Ação", categoria.toString());
    }
}
