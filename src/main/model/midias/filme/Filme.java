package main.model.midias.filme;

import java.io.Serializable;
import main.model.idioma.Idioma;
import main.model.midias.Categoria;
import main.model.midias.Midia;

/**
 * * @author Yasmin Darlen Schneider e Laura
 */
public class Filme extends Midia implements Serializable {

    private static final long serialVersionUID = 1L;
    private Idioma idioma;

    /**
     * Construtor da classe Filme. Inicializa os atributos herdados de Midia e o atributo específico idioma.
     * @param id: O identificador único da mídia.
     * @param local: O caminho para o arquivo no sistema operacional.
     * @param tamanhoEmDisco: O tamanho do arquivo em disco (em MB, por exemplo).
     * @param titulo: O título do filme.
     * @param duracao: A duração do filme em minutos.
     * @param categoria: A categoria do filme.
     * @param idioma: O idioma de áudio do filme.
     */
    public Filme(int id, String local, double tamanhoEmDisco, String titulo, int duracao, Categoria categoria, Idioma idioma) {
        super(id, local, tamanhoEmDisco, titulo, duracao, categoria);
        setIdioma(idioma);
    }

    /**
     * Sobrescreve o método da classe pai para incluir o atributo específico do idioma na exibição dos atributos.
     * @return Uma String contendo todos os atributos do Filme formatados para exibição.
     */
    @Override
    public String exibirAtributos() {
        return super.exibirAtributos() + "Idioma: " + getIdioma().exibirIdioma() + "\n";
    }

    /**
     * Retorna o objeto Idioma do filme.
     * @return o Idioma do filme.
     */
    public Idioma getIdioma() {
        return idioma;
    }

    /**
     * Define o objeto Idioma do filme.
     * @param idioma: o Idioma a ser definido.
     * @throws IllegalArgumentException Se o idioma for nulo.
     */
    public void setIdioma(Idioma idioma) throws IllegalArgumentException {
        if (idioma == null) {
            throw new IllegalArgumentException("Idioma não pode ser nulo.");
        }
        this.idioma = idioma;
    }
}