package main.model.midias.musica;

import java.io.Serializable;

import main.model.midias.Categoria;
import main.model.midias.Midia;
import main.model.pessoa.Pessoa;

public class Musica extends Midia implements Serializable {
	private static final long serialVersionUID = 1L;
	private Pessoa artista;

    public Musica(int id, String local, double tamanhoEmDisco, 
                  String titulo, int duracao, Categoria categoria, Pessoa artista) {
        super(id, local, tamanhoEmDisco, titulo, duracao, categoria);
        setArtista(artista);
    }
    
    @Override
    public String exibirAtributos() {
        String atributos = "";
        atributos += "Pessoa: " + getArtista() + "\n";
        return atributos;
    }

    @Override
    public void setDuracao(int duracao) throws IllegalArgumentException {
        super.setDuracao(duracao);
    }
    
    public Pessoa getArtista() {
        return artista;
    }

    public void setArtista(Pessoa artista) throws IllegalArgumentException{
        if(artista == null) {
            throw new IllegalArgumentException("Pessoa não pode ser nula.");
        }
        this.artista = artista;
    }
}
