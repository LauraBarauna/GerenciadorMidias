package main.model.midias.filme;

import java.io.Serializable;
import main.model.idioma.Idioma;
import main.model.midias.Categoria;
import main.model.midias.Midia;

public class Filme extends Midia implements Serializable {
    private static final long serialVersionUID = 1L;

    private Idioma idioma;

    public Filme(int id, String local, double tamanhoEmDisco, String titulo, int duracao, Categoria categoria, Idioma idioma) {
        super(id, local, tamanhoEmDisco, titulo, duracao, categoria);
        setIdioma(idioma);
    }

    @Override
    public String exibirAtributos() {
        return super.exibirAtributos() + "Idioma: " + getIdioma() + "\n";
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) throws IllegalArgumentException {
        if (idioma == null) {
            throw new IllegalArgumentException("Idioma não pode ser nulo.");
        }
        this.idioma = idioma;
    }
}