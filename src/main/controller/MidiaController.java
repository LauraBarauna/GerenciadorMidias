package main.controller;

import main.gerenciador.GerenciadorMidia;
import main.model.geradorId.GeradorId;
import main.model.idioma.Idioma;
import main.model.midias.Categoria;
import main.model.midias.Midia;
import main.model.midias.filme.Filme;
import main.model.midias.livro.Livro;
import main.model.midias.musica.Musica;
import main.model.pessoa.Pessoa;

public class MidiaController {

    private GerenciadorMidia gerenciador;
    private GeradorId geradorId;

    public MidiaController() {
        this.gerenciador = new GerenciadorMidia();
        this.geradorId = new GeradorId();
    }

    public void cadastrarLivro(String local, double tamanhoEmDisco, String titulo, int duracao, String categoria) {

        boolean adicinou = this.gerenciador.incluirMidia(new Livro(geradorId.getId(), local, tamanhoEmDisco,
                titulo, duracao, new Categoria(categoria)));
    }


    public void cadastrarMusica(String local, double tamanhoEmDisco,
                                String titulo, int duracao, String categoria, String artista) {

        boolean adicionou = this.gerenciador.incluirMidia(new Musica(geradorId.getId(), local, tamanhoEmDisco,
                titulo, duracao, new Categoria(categoria), new Pessoa(artista)));

    }

    public void cadastrarFilme(String local, double tamanhoEmDisco, String titulo,
                               int duracao, String categoria, String idioma) {

        boolean adicinou = this.gerenciador.incluirMidia(new Filme(geradorId.getId(), local, tamanhoEmDisco, titulo,
                duracao, new Categoria(categoria), new Idioma(idioma)));

    }
}
