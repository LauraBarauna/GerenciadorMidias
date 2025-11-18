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

/**
 * @author Laura Barauna
 */

public class MidiaController {

    private GerenciadorMidia gerenciador;
    private GeradorId geradorId;
    
    /**
     * Construtor do Controller de Mídia. Inicializa o GerenciadorMidia e o GeradorId.
     */

    public MidiaController() {
        this.gerenciador = new GerenciadorMidia();
        this.geradorId = new GeradorId();
    }
    
    /**
     * Cadastra um novo Livro no sistema. Gera um ID único e tenta incluir a mídia no Gerenciador.
     * @param local: O caminho do arquivo do livro.
     * @param tamanhoEmDisco: O tamanho do arquivo em disco (double).
     * @param titulo: O título do livro.
     * @param duracao: O número de páginas (duração usada genericamente para int).
     * @param categoria: A categoria do livro.
     * @return O objeto Livro cadastrado.
     * @throws MidiaDuplicadaException Se o ID gerado já estiver em uso (o que é improvável se o GeradorId funcionar corretamente).
     */

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
    
    /**
     * Cadastra uma nova {@code Musica} no sistema. Gera um ID único e tenta incluir a mídia no Gerenciador.
     * @param local: O caminho do arquivo da música.
     * @param tamanhoEmDisco: O tamanho do arquivo em disco (double).
     * @param titulo: O título da música.
     * @param duracao: A duração da música em segundos/minutos.
     * @param categoria: A categoria da música.
     * @param artista: O nome do artista (Pessoa).
     * @throws MidiaDuplicadaException Se o ID gerado já estiver em uso.
     */

    public void cadastrarMusica(String local, double tamanhoEmDisco,
                                String titulo, int duracao, String categoria, String artista) throws MidiaDuplicadaException {

        int id = geradorId.getId();

        boolean adicionou = this.gerenciador.incluirMidia(new Musica(id, local, tamanhoEmDisco,
                titulo, duracao, new Categoria(categoria), new Pessoa(artista)));

        if (!adicionou) {
            throw new MidiaDuplicadaException(id);
        }
    }
    
    /**
     * Cadastra um novo {@code Filme} no sistema. Gera um ID único e tenta incluir a mídia no Gerenciador.
     * @param local: O caminho do arquivo do filme.
     * @param tamanhoEmDisco: O tamanho do arquivo em disco (double).
     * @param titulo: O título do filme.
     * @param duracao: A duração do filme em minutos.
     * @param categoria: A categoria do filme.
     * @param idioma: O idioma do filme.
     * @throws MidiaDuplicadaException Se o ID gerado já estiver em uso.
     */

    public void cadastrarFilme(String local, double tamanhoEmDisco, String titulo,
                               int duracao, String categoria, String idioma) throws MidiaDuplicadaException {

        int id = geradorId.getId();

        boolean adicionou = this.gerenciador.incluirMidia(new Filme(id, local, tamanhoEmDisco, titulo,
                duracao, new Categoria(categoria), new Idioma(idioma)));

        if (!adicionou) {
            throw new MidiaDuplicadaException(id);
        }
    }
    
    /**
     * Lista todas as Mídias cadastradas no sistema, independentemente do tipo.
     * @return Uma List<Midia> contendo todas as mídias.
     */

    public List<Midia> listarMidias() {
       return this.gerenciador.listarTodasMidias();
    }
    
    /**
     * Lista apenas os objetos que são instâncias de Filme.
     * @return Uma List<Midia> contendo apenas Filmes.
     */

    public List<Midia> listarFilmes() {
        List<Midia> fimes = new ArrayList<>();

        for (Midia midia : this.gerenciador.listarTodasMidias()) {
            if (midia instanceof Filme) {
                fimes.add(midia);
            }
        }
        return fimes;
    }
    
    /**
     * Lista apenas os objetos que são instâncias de Musica.
     * @return Uma List<Midia> contendo apenas Músicas.
     */

    public List<Midia> listarMusicas() {
        List<Midia> musicas = new ArrayList<>();
        for (Midia midia : this.gerenciador.listarTodasMidias()) {
            if (midia instanceof Musica) {
                musicas.add(midia);
            }
        }
        return musicas;
    }
    
    /**
     * Lista todas as Mídias que pertencem à categoria fornecida.
     * @param categoria: O nome da Categoria para filtrar.
     * @return Uma List<Midia> contendo as mídias da categoria.
     * @throws MidiaNaoEncontradaException Se nenhuma mídia for encontrada para a categoria.
     */

    public List<Midia> listarPorCategoria(String categoria) throws MidiaNaoEncontradaException {
        List<Midia> midias = this.gerenciador.listarPorCategoria(categoria);

        if (midias.isEmpty()) {
            throw new MidiaNaoEncontradaException(categoria);
        }

        return midias;
    }
    
    /**
     * Retorna todas as Mídias ordenadas alfabeticamente pelo título.
     * @return Uma List<Midia> ordenada.
     */

    public List<Midia> ordenarPorTitulo() {
        return this.gerenciador.ordenarPorTitulo();
    }
    
    /**
     * Retorna todas as Mídias ordenadas pela duração, ou número de páginas, dependendo do tipo.
     * @return Uma List<Midia> ordenada.
     */

    public List<Midia> ordernarPorDuracao() {
        return this.gerenciador.ordenarPorDuracao();
    }
    
    /**
     * Lista apenas os objetos que são instâncias de Livro.
     * @return Uma List<Midia> contendo apenas Livros.
     */

    public List<Midia> listarLivros() {
        List<Midia> livros = new ArrayList<>();
        for (Midia midia : this.gerenciador.listarTodasMidias()) {
            if (midia instanceof Livro) {
                livros.add(midia);
            }
        }
        return livros;
    }
    
    /**
     * Busca uma Mídia pelo título.
     * @param titulo: O título da Mídia a ser buscada.
     * @return O objeto Midia encontrado.
     * @throws MidiaNaoEncontradaException Se a Mídia com o título não for encontrada.
     */

    public Midia buscarMidiaPorTitulo(String titulo) throws MidiaNaoEncontradaException {
        Midia buscou = this.gerenciador.buscarMidiaPorTitulo(titulo);

        if (buscou == null) {
            throw new MidiaNaoEncontradaException(titulo, true);
        }
        return buscou;
    }
    
    /**
     * Busca uma Mídia pelo ID único.
     * @param id: O ID da Mídia a ser buscada.
     * @return O objeto Midia encontrado.
     * @throws MidiaNaoEncontradaException Se a Mídia com o ID não for encontrada.
     */

    public Midia buscarMidiaPorId(int id) throws MidiaNaoEncontradaException {
        Midia buscou = this.gerenciador.buscarPorId(id);

        if (buscou == null) {
            throw new MidiaNaoEncontradaException(id);
        }
        return buscou;
    }
    
    /**
     * Remove uma Mídia do sistema pelo ID.
     * @param id O ID da Mídia a ser removida.
     */

    public void removerMidia(int id) {
        boolean removeu = this.gerenciador.removerMidia(id);
    }
    
    /**
     * Tenta atualizar uma Mídia existente. O método lança uma RuntimeException genérica se a atualização falhar,
     * o que implica que a mídia não foi encontrada.
     * @param midia: O objeto Midia com os dados atualizados.
     * @throws RuntimeException Se a mídia a ser atualizada não for encontrada.
     */

    public void atualizarMidia(Midia midia) throws RuntimeException {
        boolean atualizou = this.gerenciador.atualizarMidia(midia);

        if (!atualizou) {
            throw new RuntimeException("Não existe nenhuma midia cadastrada!");
        }
    }
}
