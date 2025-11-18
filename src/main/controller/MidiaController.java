package main.controller;

import main.excecoes.midia.MidiaDuplicadaException;
import main.excecoes.midia.MidiaNaoEncontradaException;
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

    public Livro cadastrarLivro(String local, double tamanhoEmDisco, String titulo, int duracao,
                               String categoria) throws MidiaDuplicadaException {

        int id = geradorId.getId();

        boolean adicionou = this.gerenciador.incluirMidia(new Livro(id, local, tamanhoEmDisco,
                titulo, duracao, new Categoria(categoria)));

        if (!adicionou) {
            throw new MidiaDuplicadaException(id);
        }
        return (Livro) gerenciador.buscarMidiaPorTitulo(titulo);
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

    public List<Midia> listarFilmes() {
        List<Midia> fimes = new ArrayList<>();

        for (Midia midia : this.gerenciador.listarTodasMidias()) {
            if (midia instanceof Filme) {
                fimes.add(midia);
            }
        }
        return fimes;
    }

    public List<Midia> listarMusicas() {
        List<Midia> musicas = new ArrayList<>();
        for (Midia midia : this.gerenciador.listarTodasMidias()) {
            if (midia instanceof Musica) {
                musicas.add(midia);
            }
        }
        return musicas;
    }

    public List<Midia> listarPorCategoria(String categoria) throws MidiaNaoEncontradaException {
        List<Midia> midias = this.gerenciador.listarPorCategoria(categoria);

        if (midias.isEmpty()) {
            throw new MidiaNaoEncontradaException(categoria);
        }

        return midias;
    }

    public List<Midia> ordenarPorTitulo() {
        return this.gerenciador.ordenarPorTitulo();
    }

    public List<Midia> ordernarPorDuracao() {
        return this.gerenciador.ordenarPorDuracao();
    }

    public List<Midia> listarLivros() {
        List<Midia> livros = new ArrayList<>();
        for (Midia midia : this.gerenciador.listarTodasMidias()) {
            if (midia instanceof Livro) {
                livros.add(midia);
            }
        }
        return livros;
    }

    public Midia buscarMidiaPorTitulo(String titulo) throws MidiaNaoEncontradaException {
        Midia buscou = this.gerenciador.buscarMidiaPorTitulo(titulo);

        if (buscou == null) {
            throw new MidiaNaoEncontradaException(titulo, true);
        }
        return buscou;
    }

    public Midia buscarMidiaPorId(int id) throws MidiaNaoEncontradaException {
        Midia buscou = this.gerenciador.buscarPorId(id);

        if (buscou == null) {
            throw new MidiaNaoEncontradaException(id);
        }
        return buscou;
    }

    public void removerMidia(int id) {
        boolean removeu = this.gerenciador.removerMidia(id);
    }

    public void atualizarMidia(Midia midia) throws RuntimeException {
        boolean atualizou = this.gerenciador.atualizarMidia(midia);

        if (!atualizou) {
            throw new RuntimeException("Não existe nenhuma midia cadastrada!");
        }
    }
}
