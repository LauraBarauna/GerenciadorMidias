package main.gerenciador;

import main.model.idioma.Idioma;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorIdioma {
    List<Idioma> idiomas;

    public GerenciadorIdioma() {
        this.idiomas = new ArrayList<>();
    }

    public boolean adicionarIdioma(Idioma idioma) {
        if (idioma == null) {
            return false;
        }

        if (this.idiomas.contains(idioma)) {
            return false;
        }

        for (Idioma i : this.idiomas) {
            if (i.exibirIdioma().equalsIgnoreCase(idioma.exibirIdioma())) {
                return false;
            }
        }

        this.idiomas.add(idioma);
        return true;
    }

    private Idioma buscarIdioma(String idioma) {
        if (idioma == null || idioma.isBlank()) {
            return null;
        }

        Idioma idiomaBuscar = null;

        for (Idioma i : this.idiomas) {
            if (i.exibirIdioma().equalsIgnoreCase(idioma)) {
                idiomaBuscar = i;
            }
        }
        return idiomaBuscar;
    }

    public boolean removerIdioma(String idioma) {
        Idioma idiomaRemover = buscarIdioma(idioma);
        if (idiomaRemover == null) {
            return false;
        }
        this.idiomas.remove(idiomaRemover);
        return true;
    }

    public boolean removerTodosIdiomas() {
        if (getIdiomas().isEmpty()) {
            return false;
        }
        this.idiomas.clear();
        return true;
    }

    public List<Idioma> getIdiomas() {
        return this.idiomas;
    }
}
