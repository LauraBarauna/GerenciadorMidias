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

/**
 * @author Laura Barauna
 */
public class MidiaController {

    private GerenciadorMidia gerenciador;
    private GeradorId geradorId;

    /**
     * Construtor do Controller. Inicializa o gerenciador de mídias e o gerador de IDs.
     */
    public MidiaController() {
        this.gerenciador = new GerenciadorMidia();
        this.geradorId = new GeradorId();
    }

    /**
     * Cadastra um novo Livro no sistema. Gera um novo ID e cria um objeto Livro com os dados fornecidos, incluindo uma nova Categoria.
     * @param local: O caminho para o arquivo no sistema operacional.
     * @param tamanhoEmDisco: O tamanho do arquivo em disco (em MB, por exemplo).
     * @param titulo: O título do livro.
     * @param duracao: A duração do livro em páginas.
     * @param categoria: O nome da categoria do livro.
     * @throws MidiaDuplicadaException Se uma mídia com o ID gerado (o que é improvável se o {@code GeradorId} estiver funcionando) já existir.
     */
    public void cadastrarLivro(String local, double tamanhoEmDisco, String titulo, int duracao, String categoria) throws MidiaDuplicadaException {
        int id = geradorId.getId();
        boolean adicionou = this.gerenciador.incluirMidia(new Livro(id, local, tamanhoEmDisco, titulo, duracao, new Categoria(categoria)));
        if (!adicionou) {
            throw new MidiaDuplicadaException(id);
        }
    }


    /**
     * Cadastra uma nova Música no sistema. Gera um novo ID e cria um objeto Musica com os dados fornecidos, incluindo uma nova Categoria e um novo Artista (Pessoa).
     * @param local: O caminho para o arquivo no sistema operacional.
     * @param tamanhoEmDisco: O tamanho do arquivo em disco (em MB, por exemplo).
     * @param titulo: O título da música.
     * @param duracao: A duração da música em segundos.
     * @param categoria: O nome da categoria da música.
     * @param artista: O nome do artista da música.
     * @throws MidiaDuplicadaException Se uma mídia com o ID gerado já existir.
     */
    public void cadastrarMusica(String local, double tamanhoEmDisco, String titulo, int duracao, String categoria, String artista) throws MidiaDuplicadaException {
        int id = geradorId.getId();
        boolean adicionou = this.gerenciador.incluirMidia(new Musica(id, local, tamanhoEmDisco, titulo, duracao, new Categoria(categoria), new Pessoa(artista)));
        if (!adicionou) {
            throw new MidiaDuplicadaException(id);
        }

    }

    /**
     * Cadastra um novo Filme no sistema. Gera um novo ID e cria um objeto Filme com os dados fornecidos, incluindo uma nova Categoria e um novo Idioma.
     * @param local: O caminho para o arquivo no sistema operacional.
     * @param tamanhoEmDisco: O tamanho do arquivo em disco (em MB, por exemplo).
     * @param titulo: O título do filme.
     * @param duracao: A duração do filme em minutos.
     * @param categoria: O nome da categoria do filme.
     * @param idioma: O nome do idioma do áudio do filme.
     * @throws MidiaDuplicadaException Se uma mídia com o ID gerado já existir.
     */
    public void cadastrarFilme(String local, double tamanhoEmDisco, String titulo, int duracao, String categoria, String idioma) throws MidiaDuplicadaException {
        int id = geradorId.getId();
        boolean adicionou = this.gerenciador.incluirMidia(new Filme(id, local, tamanhoEmDisco, titulo, duracao, new Categoria(categoria), new Idioma(idioma)));
        if (!adicionou) {
            throw new MidiaDuplicadaException(id);
        }

    }
}