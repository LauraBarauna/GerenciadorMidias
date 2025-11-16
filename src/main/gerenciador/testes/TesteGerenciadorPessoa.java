package main.gerenciador.testes;

import main.gerenciador.GerenciadorPessoa;
import main.model.pessoa.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TesteGerenciadorPessoa {

    private GerenciadorPessoa gp;

    /**
     * Antes de cada teste, um novo GerenciadorPessoa é criado.
     * Isso garante que Yasmin, Luiz e Laura sempre começam com uma lista totalmente vazia.
     */
    @BeforeEach
    void setup() {
        gp = new GerenciadorPessoa();
    }

    /**
     * Testa se uma pessoa (ex.: Ana) é adicionada com sucesso.
     * A operação deve retornar true e a lista deve conter exatamente 1 pessoa.
     */
    @Test
    void deveAdicionarPessoa() {
        Pessoa p = new Pessoa("Ana");

        boolean adicionou = gp.adicionarPessoa(p);

        assertTrue(adicionou);
        assertEquals(1, gp.getPessoas().size());
    }

    /**
     * Testa se uma pessoa NÃO pode ser adicionada duas vezes quando se trata
     * do mesmo objeto. A segunda inserção deve falhar.
     */
    @Test
    void naoDeveAdicionarPessoaDuplicadaPorObjeto() {
        Pessoa p = new Pessoa("Luiz");

        gp.adicionarPessoa(p);

        assertFalse(gp.adicionarPessoa(p)); // O mesmo objeto não pode entrar duas vezes
    }

    /**
     * Testa se uma pessoa com o mesmo nome, mas objetos diferentes,
     * não pode ser adicionada novamente. A comparação deve ignorar maiúsculas/minúsculas.
     */
    @Test
    void naoDeveAdicionarPessoaDuplicadaPorNome() {
        gp.adicionarPessoa(new Pessoa("Laura"));

        boolean resultado = gp.adicionarPessoa(new Pessoa("laura"));

        assertFalse(resultado); // Nomes iguais devem ser bloqueados
    }

    /**
     * Testa se o método removerPessoa encontra e remove corretamente uma pessoa, mesmo com variação de maiúsculas/minúsculas.
     */
    @Test
    void deveEncontrarPessoaAoRemover() {
        gp.adicionarPessoa(new Pessoa("João"));

        boolean removido = gp.removerPessoa("joão");

        assertTrue(removido);
        assertEquals(0, gp.getPessoas().size());
    }

    /**
     * Testa o comportamento quando tentar remover uma pessoa que não existe.
     Deve retornar false.
     */
    @Test
    void naoDeveRemoverPessoaInexistente() {
        assertFalse(gp.removerPessoa("Yasmin"));
    }

    /**
     * Garante que nomes nulos ou vazios não causam remoção indevida.
     * A operação deve falhar e retornar false.
     */
    @Test
    void naoDeveRemoverPessoaComNomeNuloOuVazio() {
        assertFalse(gp.removerPessoa(null));
        assertFalse(gp.removerPessoa(""));
    }

    /**
     * Testa a remoção completa da lista.
     * Deve retornar true e a lista deve ficar vazia.
     */
    @Test
    void deveRemoverTodasPessoas() {
        gp.adicionarPessoa(new Pessoa("Luiz"));
        gp.adicionarPessoa(new Pessoa("Yasmin"));
        gp.adicionarPessoa(new Pessoa("Laura"));

        boolean removido = gp.removerTodasPessoas();

        assertTrue(removido);
        assertTrue(gp.getPessoas().isEmpty());
    }

    /**
     * Testa o comportamento ao tentar remover todas as pessoas enquanto a lista já está vazia.
     * Deve retornar false, pois nada pode ser removido.
     */
    @Test
    public void naoDeveRemoverTodasQuandoListaJaEstaVazia() {
        assertFalse(gp.removerTodasPessoas());
    }
}
