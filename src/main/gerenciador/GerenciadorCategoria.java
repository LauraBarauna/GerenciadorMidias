package main.gerenciador;

import main.model.midias.Categoria;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorCategoria {
    private List<Categoria> categoriasFilme;
    private List<Categoria> categoriasMusica;
    private List<Categoria> categoriasLivro;

    public GerenciadorCategoria() {
        this.categoriasFilme = new ArrayList<Categoria>();
        this.categoriasMusica = new ArrayList<>();
        this.categoriasLivro = new ArrayList<>();
    }

    public void cadastrarCategoria(Categoria categoria, String tipo) {
        String tipoUpperCase = tipo.toUpperCase();
        switch (tipoUpperCase) {
            case "MUSICA":
                this.categoriasMusica.add(categoria);
            case "MP3":
                this.categoriasFilme.add(categoria);
                break;
            case "LIVRO":
                this.categoriasLivro.add(categoria);
            case "PDF":
                this.categoriasLivro.add(categoria);
            case "EPUB":
                this.categoriasLivro.add(categoria);
                break;
            case "FILME":
                this.categoriasFilme.add(categoria);
            case "MP4":
                this.categoriasFilme.add(categoria);
            case "MKV":
                this.categoriasFilme.add(categoria);
                break;
        }
    }

    public List<Categoria> getCategoriasFilme(String tipo) {
        if (tipo.equalsIgnoreCase("MUSICA") || tipo.equalsIgnoreCase("MP3")) {
            return this.categoriasMusica;
        } else if (tipo.equalsIgnoreCase("LIVRO") || tipo.equalsIgnoreCase("PDF") || tipo.equalsIgnoreCase("EPUB")) {
            return this.categoriasLivro;
        } else {
            return this.categoriasFilme;
        }
    }
}
