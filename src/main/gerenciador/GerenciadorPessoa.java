package main.gerenciador;

import main.model.pessoa.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorPessoa {
    private List<Pessoa> pessoas;

    public GerenciadorPessoa() {
        pessoas = new ArrayList<Pessoa>();
    }

    public boolean adicionarPessoa(Pessoa p) {
        if (pessoas.contains(p)) {
            return false;
        }

        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().equalsIgnoreCase(p.getNome())) {
                return false;
            }
        }
        pessoas.add(p);
        return true;
    }

    private Pessoa buscarPessoa(String nome) {
        if (nome == null || nome.isBlank()) {
            return null;
        }

        Pessoa pessoa = null;

        for (Pessoa p : pessoas) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                pessoa = p;
            }
        }
        return pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public boolean removerPessoa(String nome) {
        Pessoa p = buscarPessoa(nome);
        if (p == null) {
            return false;
        }
        pessoas.remove(p);
        return true;
    }

    public boolean removerTodasPessoas() {
        if (pessoas.isEmpty()) {
            return false;
        }
        pessoas.clear();
        return true;
    }
}
