package main.model.idioma;

import java.io.Serializable;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */
public class Idioma implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idioma;

    /**
     * Construtor da classe Idioma. Inicializa o objeto com o nome do idioma.
     * @param idioma: O nome do idioma.
     */
    public Idioma(String idioma) {
        setIdioma(idioma);
    }

    /**
     * @return O nome do idioma.
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Define o nome do idioma.
     * @param idioma: O novo nome do idioma.
     * @throws IllegalArgumentException Se o idioma for nulo ou uma string vazia (em branco).
     */
    public void setIdioma(String idioma) throws IllegalArgumentException {
        if (idioma == null || idioma.isBlank()) {
            throw new IllegalArgumentException("Idioma não pode ser vazio ou nulo.");
        }
        this.idioma = idioma;
    }

    /**
     * Retorna o nome do idioma. Este método é apenas um alias para o método getIdioma().
     * @return O nome do idioma.
     */
    public String exibirIdioma() {
        return getIdioma();
    }
    
    /**
     * Sobrescreve o método toString() para retornar diretamente o nome do idioma.
     * @return O nome do idioma.
     */
    @Override
    public String toString() {
        return idioma;
    }
}