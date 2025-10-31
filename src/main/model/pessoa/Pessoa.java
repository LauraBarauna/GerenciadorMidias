package main.model.pessoa;

import main.excecoes.NomeInvalido;

public class Pessoa {
    private String nome;

    public Pessoa(String nome){
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws NomeInvalido {
        if (nome == null || nome.isBlank()) {
            throw new NomeInvalido("Nome não pode ser vazio ou nulo.");
        }
        this.nome = nome;
    }
}
