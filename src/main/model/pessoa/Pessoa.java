package main.model.pessoa;

public class Pessoa {
    private String nome;

    public Pessoa(String nome) {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws IllegalArgumentException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
        }
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}
