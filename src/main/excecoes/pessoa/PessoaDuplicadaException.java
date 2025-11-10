package main.excecoes.pessoa;

public class PessoaDuplicadaException extends PessoaException {
    public PessoaDuplicadaException(String nome) {
        super("Pessoa " + nome + " já está cadastrada na lista.");
    }
}
