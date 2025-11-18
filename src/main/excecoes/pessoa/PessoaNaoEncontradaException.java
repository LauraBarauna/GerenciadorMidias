package main.excecoes.pessoa;

import main.model.pessoa.Pessoa;

public class PessoaNaoEncontradaException extends PessoaException {
    public PessoaNaoEncontradaException(String nome) {
        super("Pessoa " + nome + " não foi  encontrada na lista.");
    }
}
