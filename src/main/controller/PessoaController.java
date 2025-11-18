package main.controller;

import main.excecoes.pessoa.PessoaDuplicadaException;
import main.excecoes.pessoa.PessoaNaoEncontradaException;
import main.gerenciador.GerenciadorPessoa;
import main.model.pessoa.Pessoa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class PessoaController {
    private GerenciadorPessoa pessoa;
    
    /**
     * Construtor do Controller de Pessoa. Inicializa o GerenciadorPessoa.
     */

    public PessoaController() {
        this.pessoa = new GerenciadorPessoa();
    }
    
    /**
     * Tenta cadastrar uma nova Pessoa com o nome fornecido. Realiza a validação de nulidade/vazio e delega a criação ao Gerenciador.
     * Lança exceções específicas em caso de erro de validação ou duplicidade.
     * @param nome: O nome da Pessoa a ser cadastrada.
     * @throws IllegalArgumentException Se o nome for nulo ou vazio/em branco.
     * @throws PessoaDuplicadaException Se já existir uma Pessoa com o mesmo nome.
     */

    public void cadastrarPessoa(String nome) throws PessoaDuplicadaException {
        if (nome == null && nome.isBlank()) {
            throw new IllegalArgumentException("Campo nome deve ser preenchido.");
        }
        boolean adicionou = pessoa.adicionarPessoa(new Pessoa(nome));
        if (!adicionou) {
            throw new PessoaDuplicadaException(nome);
        }
    }
    
    /**
     * Tenta remover uma Pessoa da lista pelo nome.
     * @param nome: O nome da Pessoa a ser removida.
     * @throws IllegalArgumentException Se o nome for nulo ou vazio/em branco.
     * @throws PessoaNaoEncontradaException Se a Pessoa com o nome fornecido não for encontrada.
     */

    public void removerPessoa(String nome) throws PessoaNaoEncontradaException {
        if (nome == null && nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        boolean removido = pessoa.removerPessoa(nome);
        if (!removido) {
            throw new PessoaNaoEncontradaException(nome);
        }
    }
    
    /**
     * Lista o nome de todas as Pessoas cadastradas no sistema.
     * @return Uma List<String> contendo apenas os nomes das Pessoas. Retorna uma lista vazia se não houver Pessoas.
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
     * Retorna a lista completa de objetos Pessoa.
     * @return Uma List<Pessoa> contendo todos os objetos Pessoa.
     */

    public List<Pessoa> getPessoas() {
        return pessoa.getPessoas();
    }
    
    /**
     * Remove todas as Pessoas cadastradas no sistema.
     * @return true se havia Pessoas para remover, false caso contrário.
     */

    public List<Pessoa> getPessoas() {
        return pessoa.getPessoas();
    }

    public boolean removerTodasPessoas() {
        return pessoa.removerTodasPessoas();
    }
}
