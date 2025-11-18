package main.model.midias;

import java.io.Serializable;

/**
 * @author Laura
 */
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    private String categoria;

    /**
     * Construtor da classe Categoria. Inicializa o objeto, garantindo que o nome da categoria seja válido.
     * @param categoria O nome da categoria.
     */
    public Categoria(String categoria) {
        setCategoria(categoria);
    }

    /**
     * @return O nome da categoria.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define o nome da categoria.
     * @param categoria: O novo nome da categoria.
     * @throws IllegalArgumentException Se o nome da categoria for nulo ou uma string vazia (em branco).
     */
    public void setCategoria(String categoria) throws IllegalArgumentException {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("Nome da categoria não pode ser vazia ou nula.");
        }
        this.categoria = categoria;
    }
    
    /**
     * Sobrescreve o método toString()} para retornar diretamente o nome da categoria.
     * @return O nome da categoria.
     */
    @Override
    public String toString() {
        return categoria;
    }
}