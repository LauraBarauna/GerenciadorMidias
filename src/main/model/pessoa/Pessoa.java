package main.model.pessoa;

/**
 * @author Laura
 */
public class Pessoa {
    
    private String nome;

    /**
     * Construtor da classe Pessoa. Inicializa a pessoa, garantindo que o nome não seja nulo ou vazio.
     * @param nome O nome da pessoa.
     */
    public Pessoa(String nome) {
        setNome(nome);
    }

    /**
     * @return O nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     * @param nome: O novo nome.
     * @throws IllegalArgumentException Se o nome for nulo ou uma string vazia (em branco).
     */
    public void setNome(String nome) throws IllegalArgumentException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
        }
        this.nome = nome;
    }
    
    /**
     * Sobrescreve o método toString() para retornar diretamente o nome. Útil para exibir o objeto Pessoa em logs ou na interface sem chamar explicitamente  método getNome().
     * @return O nome da pessoa.
     */
    @Override
    public String toString() {
        return nome;
    }
}