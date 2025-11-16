package main.controller;

import main.excecoes.pessoa.PessoaDuplicadaException;
import main.excecoes.pessoa.PessoaNaoEncontradaException;
import main.gerenciador.GerenciadorPessoa;
import main.model.pessoa.Pessoa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */
public class PessoaController {
    
    private GerenciadorPessoa pessoa;

    /**
     * Construtor do Controller. Inicializa uma nova instância de {@code GerenciadorPessoa}.
     */
    public PessoaController() {
        this.pessoa = new GerenciadorPessoa();
    }

    /**
     * Cadastra uma nova pessoa no sistema. Valida se o nome é nulo ou vazio e delega a criação para o gerenciador.
     * @param nome: O nome da pessoa a ser cadastrada.
     * @throws IllegalArgumentException Se o nome for nulo ou em branco.
     * @throws PessoaDuplicadaException Se uma pessoa com o mesmo nome já estiver cadastrada.
     */
    public void cadastrarPessoa(String nome) throws PessoaDuplicadaException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Campo nome deve ser preenchido.");
        }
        
        boolean adicionou = pessoa.adicionarPessoa(new Pessoa(nome));
        if (!adicionou) {
            throw new PessoaDuplicadaException(nome);
        }
    }

    /**
     * Remove uma pessoa do sistema pelo nome. Lança exceção se o nome for inválido ou se a pessoa não for encontrada.
     * @param nome: O nome da pessoa a ser removida.
     * @throws IllegalArgumentException Se o nome for nulo ou em branco.
     * @throws PessoaNaoEncontradaException Se a pessoa não for encontrada para remoção.
     */
    public void removerPessoa(String nome) throws PessoaNaoEncontradaException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        
        boolean removido = pessoa.removerPessoa(nome);
        if (!removido) {
            throw new PessoaNaoEncontradaException(nome);
        }
    }

    /**
     * Lista os nomes de todas as pessoas cadastradas.
     * @return Uma List<String> contendo apenas os nomes das pessoas.
     */
    public List<String> listarPessoas() {
        List<String> pessoasString = new ArrayList<>();
        List<Pessoa> pessoas = pessoa.getPessoas();

        if (pessoas.isEmpty()) {
            return pessoasString;
        }

        for (Pessoa p : pessoas) {
            pessoasString.add(p.getNome());
        }
        return pessoasString;
    }

    /**
     * Remove todas as pessoas cadastradas do gerenciador.
     * @return true se a operação foi realizada e a lista foi limpa, false se a lista já estava vazia.
     */
    public boolean removerTodasPessoas() {
        return pessoa.removerTodasPessoas();
    }
    
    /**
     * Retorna a instância do GerenciadorPessoa.
     * @return O GerenciadorPessoa em uso.
     */
    public GerenciadorPessoa getGerenciador() {
        return pessoa;
    }
}