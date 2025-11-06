package main.model.midias.filme;

import main.model.idioma.Idioma;
import main.model.midias.Categoria;
import main.model.midias.Midia;

public class Filme extends Midia {

    private Idioma idioma; // Composição: cada Filme "tem um" Idioma

    //==============================
    // CONSTRUTOR
    //==============================
    public Filme(int id, String local, double tamanhoEmDisco,
                 String titulo, int duracao, Categoria categoria, Idioma idioma) {
        // Chama o construtor da superclasse (Midia)
        super(id, local, tamanhoEmDisco, titulo, duracao, categoria);
        setIdioma(idioma); // Aplica validação de idioma
    }

    //==============================
    // EXIBIÇÃO DE ATRIBUTOS
    //==============================
    @Override
    public String exibirAtributos() {
        String atributos = "";
        atributos += "Idioma: " + getIdioma().exibirIdioma() + "\n";
        return atributos;
    }

    //==============================
    // GETTERS e SETTERS
    //==============================
    public Idioma getIdioma() {
        return idioma;
    }

    @Override
    public void setDuracao(int duracao) throws IllegalArgumentException {
        // Reaproveita a validação da superclasse
        super.setDuracao(duracao);
    }

    public void setIdioma(Idioma idioma) throws IllegalArgumentException {
        if (idioma == null) {
            throw new IllegalArgumentException("Idioma não pode ser nulo.");
        }
        this.idioma = idioma;
    }
}