package main.model.pessoa;

/**
 * Representa uma pessoa, usada como autor em mídias como livros.
 * Contém apenas o nome, com validação para evitar valores inválidos.
 */
public class Pessoa {

    // Atributo privado para armazenar o nome da pessoa
    private String nome;

    /**
     * Construtor que inicializa o objeto Pessoa com um nome válido.
     * @param nome Nome da pessoa.
     * @throws IllegalArgumentException se o nome for nulo ou vazio.
     */
    public Pessoa(String nome) {
        setNome(nome); // Usa o setter para garantir a validação
    }

    /**
     * Retorna o nome da pessoa.
     * @return nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa com validação.
     * @param nome Novo nome da pessoa.
     * @throws IllegalArgumentException se o nome for nulo ou vazio.
     */
    public void setNome(String nome) throws IllegalArgumentException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
        }
        this.nome = nome;
    }

    /**
     * Retorna o nome da pessoa como representação textual.
     * Isso é útil quando a pessoa é exibida em listas ou impressa no console.
     * @return nome da pessoa.
     */
    @Override
    public String toString() {
        return nome;
    }
}