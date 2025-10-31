package main.model.midias.filme;

import main.model.arquivo.Arquivo;
import main.model.midias.Categoria;
import main.model.midias.Midia;

public class Filme extends Midia {

    private Idioma idioma;

    public Filme(int id, Arquivo arquivo, String titulo, int duracao,Categoria categoria, Idioma idioma) {
        super(id, arquivo, titulo, duracao, categoria);
        setIdioma(idioma);
    }

    public Idioma getIdioma() {
        return idioma;
    }

    @Override
    public void setDuracao(int duracao) throws IllegalArgumentException {
        super.setDuracao(duracao);
    }

    public void setIdioma(Idioma idioma) throws IllegalArgumentException {
        if (idioma == null) {
            throw new IllegalArgumentException("Idioma não pode ser nulo.");
        }
        this.idioma = idioma;
    }
}
