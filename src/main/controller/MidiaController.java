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

import java.util.ArrayList;
import java.util.List;

public class MidiaController {

    private GerenciadorMidia gerenciador;
    private GeradorId geradorId;

    public MidiaController() {
        this.gerenciador = new GerenciadorMidia();
        this.geradorId = new GeradorId();
    }

    public void cadastrarLivro(String local, double tamanhoEmDisco, String titulo, int duracao,
                               String categoria) throws MidiaDuplicadaException {

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

    public List<Midia> listarMidias() {
       return this.gerenciador.listarTodasMidias();
    }

    public List<Filme> listarFilmes() {
        List<Filme> fimes = new ArrayList<>();

        for (Midia midia : this.gerenciador.listarTodasMidias()) {
            if (midia instanceof Filme) {
                fimes.add((Filme) midia);
            }
        }
        return fimes;
    }

    public List<Musica> listarMusicas() {
        List<Musica> musicas = new ArrayList<>();
        for (Midia midia : this.gerenciador.listarTodasMidias()) {
            if (midia instanceof Musica) {
                musicas.add((Musica) midia);
            }
        }
        return musicas;
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        for (Midia midia : this.gerenciador.listarTodasMidias()) {
            if (midia instanceof Livro) {
                livros.add((Livro) midia);
            }
        }
        return livros;
    }

    public void atualizarMidia(Midia midia) throws RuntimeException {
        boolean atualizou = this.gerenciador.atualizarMidia(midia);

        if (!atualizou) {
            throw new RuntimeException("Não existe nenhuma midia cadastrada!");
        }
    }
}
