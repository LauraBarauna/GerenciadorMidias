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
     * Construtor da classe controladora.
     * Inicializa a instância do GerenciadorIdioma.
     */

    public IdiomaController() {
        this.gerenciador = new GerenciadorIdioma();
    }
    
    /**
     * Tenta cadastrar um novo idioma no sistema.
     * @param nome: O nome do idioma a ser cadastrado.
     * @throws IdiomaDuplicadoException Se já existir um idioma com o mesmo nome.
     */

    public void cadastrarIdioma(String nome) {
        boolean adicionou = getGerenciador().adicionarIdioma(new Idioma(nome));

        if (!adicionou) {
            throw new IdiomaDuplicadoException(nome);
        }
    }
    
    /**
     * Lista os nomes de todos os idiomas cadastrados no sistema.
     * @return Uma lista de {@code String} contendo os nomes dos idiomas.
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
     * Tenta remover um idioma específico do sistema pelo nome.
     * @param nome: O nome do idioma a ser removido.
     * @throws IdiomaNaoEncontradoException Se o idioma com o nome fornecido não for encontrado.
     */

    public void removerIdioma(String nome) {
        boolean removeu = getGerenciador().removerIdioma(nome);

        if (!removeu) {
            throw new IdiomaNaoEncontradoException(nome);
        }
    }
    
    /**
     * Remove todos os idiomas cadastrados no sistema.
     * @return true se a remoção de todos for bem-sucedida ou se a lista já estiver vazia, false caso contrário.
     */

    public boolean removerTudo() {
        return getGerenciador().removerTodosIdiomas();
    }
    
    /**
     * Lista todos os objetos Idioma cadastrados no sistema.
     * @return Uma lista de objetos Idioma.
     */

    public List<Idioma> listarIdiomas() {
        return getGerenciador().getIdiomas();
    }
    
    /**
     * Retorna a instância do gerenciador de idiomas.
     * @return O GerenciadorIdioma em uso.
     */

    public GerenciadorIdioma getGerenciador() {
        return gerenciador;
    }
}
