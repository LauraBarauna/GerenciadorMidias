package main.controller;

import main.excecoes.idioma.IdiomaDuplicadoException;
import main.excecoes.idioma.IdiomaNaoEncontradoException;
import main.gerenciador.GerenciadorIdioma;
import main.model.idioma.Idioma;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Laura Barauna
 */
public class IdiomaController {
    
    private GerenciadorIdioma gerenciador;

    /**
     * Construtor do Controller. Inicializa uma nova instância de GerenciadorIdioma}.
     */
    public IdiomaController() {
        this.gerenciador = new GerenciadorIdioma();
    }

    /**
     * Cadastra um novo idioma no sistema. Cria um objeto Idioma com o nome fornecido e delega a adição ao gerenciador. Lança exceção de duplicidade se a adição falhar.
     * @param nome: O nome do idioma a ser cadastrado.
     * @throws IdiomaDuplicadoException Se um idioma com o mesmo nome já estiver cadastrado.
     * @throws IllegalArgumentException Se o nome for nulo ou vazio (validação feita no construtor de Idioma).
     */
    public void cadastrarIdioma(String nome) {
        boolean adicionou = getGerenciador().adicionarIdioma(new Idioma(nome));
        if (!adicionou) {
            throw new IdiomaDuplicadoException(nome);
        }
    }

    /**
     * Lista apenas os nomes de todos os idiomas cadastrados.
     * @return Uma List<String> contendo os nomes dos idiomas. Retorna uma lista vazia se não houver idiomas.
     */
    public List<String> listarNomeIdioma () {
        List<Idioma> idiomas = getGerenciador().getIdiomas();
        List<String> nomes = new ArrayList<>();

        if (idiomas.isEmpty()) {
            return nomes;
        }
        for (Idioma idioma : idiomas) {
            nomes.add(idioma.getIdioma());
        }
        return nomes;
    }

    /**
     * Remove um idioma do sistema pelo nome.
     * @param nome: O nome do idioma a ser removido.
     * @throws IdiomaNaoEncontradoException Se o idioma não for encontrado para remoção.
     */
    public void removerIdioma(String nome) {
        boolean removeu = getGerenciador().removerIdioma(nome);

        if (!removeu) {
            throw new IdiomaNaoEncontradoException(nome);
        }
    }

    /**
     * Remove todos os idiomas cadastrados do gerenciador.
     * @return true se a operação foi realizada e a lista foi limpa, false se a lista já estava vazia.
     */
    public boolean removerTudo() {
        return getGerenciador().removerTodosIdiomas();
    }

    /**
     * Lista todos os objetos Idioma cadastrados.
     * @return Uma List<Idioma> contendo todos os objetos idioma.
     */
    public List<Idioma> listarIdiomas() {
        return getGerenciador().getIdiomas();
    }

    /**
     * Retorna a instância do GerenciadorIdioma.
     * @return O GerenciadorIdioma em uso.
     */
    public GerenciadorIdioma getGerenciador() {
        return gerenciador;
    }
}