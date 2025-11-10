package main.model.idioma;

public class Idioma {
    private String idioma;

    public Idioma(String idioma) {
        setIdioma(idioma);
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) throws IllegalArgumentException {
        if (idioma == null || idioma.isBlank()) {
            throw new IllegalArgumentException("Idioma não pode ser vazio ou nulo.");
        }
        this.idioma = idioma;
    }

    public String exibirIdioma() {
        return getIdioma();
    }
    
    @Override
    public String toString() {
        return idioma;
    }
}
