package main.controller;

import main.excecoes.pessoa.PessoaDuplicadaException;
import main.excecoes.pessoa.PessoaNaoEncontradaException;
import main.gerenciador.GerenciadorPessoa;
import main.model.pessoa.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaController {
    private GerenciadorPessoa pessoa;

    public PessoaController(GerenciadorPessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void cadastrarPessoa(String nome) throws PessoaDuplicadaException {
        if (nome == null && nome.isBlank()) {
            throw new IllegalArgumentException("Campo nome deve ser preenchido.");
        }
        boolean adicionou = pessoa.adicionarPessoa(new Pessoa(nome));
        if (!adicionou) {
            throw new PessoaDuplicadaException(nome);
        }
    }

    public void removerPessoa(String nome) throws PessoaNaoEncontradaException {
        if (nome == null && nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        boolean removido = pessoa.removerPessoa(nome);
        if (!removido) {
            throw new PessoaNaoEncontradaException(nome);
        }
    }

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

    public boolean removerTodasPessoas() {
        return pessoa.removerTodasPessoas();
    }
}
