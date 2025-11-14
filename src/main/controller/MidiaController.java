package main.controller;

import main.excecoes.midia.MidiaDuplicadaException;
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

    public void cadastrarLivro(String local, double tamanhoEmDisco, String titulo, int duracao, String categoria) throws MidiaDuplicadaException {

        int id = geradorId.getId();

        boolean adicionou = this.gerenciador.incluirMidia(new Livro(id, local, tamanhoEmDisco,
                titulo, duracao, new Categoria(categoria)));

        if (!adicionou) {
            throw new MidiaDuplicadaException(id);
        }
    }


    public void cadastrarMusica(String local, double tamanhoEmDisco,
                                String titulo, int duracao, String categoria, String artista) throws MidiaDuplicadaException {

        int id = geradorId.getId();

        boolean adicionou = this.gerenciador.incluirMidia(new Musica(id, local, tamanhoEmDisco,
                titulo, duracao, new Categoria(categoria), new Pessoa(artista)));

        if (!adicionou) {
            throw new MidiaDuplicadaException(id);
        }

    }

    public void cadastrarFilme(String local, double tamanhoEmDisco, String titulo,
                               int duracao, String categoria, String idioma) throws MidiaDuplicadaException {

        int id = geradorId.getId();

        boolean adicionou = this.gerenciador.incluirMidia(new Filme(id, local, tamanhoEmDisco, titulo,
                duracao, new Categoria(categoria), new Idioma(idioma)));

        if (!adicionou) {
            throw new MidiaDuplicadaException(id);
        }

    }
}
