package main.gerenciador;

import main.model.pessoa.Pessoa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Laura Barauna 
 */
public class GerenciadorPessoa {
    
    private List<Pessoa> pessoas;

    /**
     * Construtor da classe GerenciadorPessoa. Inicializa a lista interna de pessoas como um ArrayList vazio.
     */
    public GerenciadorPessoa() {
        pessoas = new ArrayList<Pessoa>();
    }

    /**
     * Adiciona uma nova pessoa à coleção. Verifica se a pessoa já existe na lista, tanto pelo objeto em si .contains quanto pelo nome (ignorando maiúsculas/minúsculas - .equalsIgnoreCase).
     * @param p O objeto Pessoa a ser adicionado.
     * @return true se a pessoa foi adicionada com sucesso, false se já existe duplicada.
     */
    public boolean adicionarPessoa(Pessoa p) {
        // Embora .contains(p) possa funcionar, a verificação por nome é mais robusta
        // para garantir que dois objetos Pessoa com o mesmo nome não sejam adicionados.
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().equalsIgnoreCase(p.getNome())) {
                return false;
            }
        }
        pessoas.add(p);
        return true;
    }

    /**
     * Busca uma pessoa na coleção pelo nome. A busca é feita ignorando maiúsculas/minúsculas.
     * @param nome O nome da pessoa a ser buscada.
     * @return O objeto Pessoa encontrado, ou null se não for encontrado ou se o nome for inválido.
     */
    private Pessoa buscarPessoa(String nome) {
        if (nome == null || nome.isBlank()) {
            return null;
        }

        Pessoa pessoa = null;

        for (Pessoa p : pessoas) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                pessoa = p;
                break;
            }
        }
        return pessoa;
    }

    /**
     * Retorna a lista completa de pessoas cadastradas.
     * @return A List<Pessoa> contendo todas as pessoas.
     */
    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    /**
     * Remove uma pessoa da coleção pelo nome. Utiliza o método buscarPessoa(String nome) para encontrar o objeto a ser removido.
     * @param nome: O nome da pessoa a ser removida.
     * @return true se a pessoa foi encontrada e removida, false caso contrário.
     */
    public boolean removerPessoa(String nome) {
        Pessoa p = buscarPessoa(nome);
        if (p == null) {
            return false;
        }
        pessoas.remove(p);
        return true;
    }

    /**
     * Remove todas as pessoas da coleção.
     * @return true se a lista foi limpa (ou se já estava vazia, embora o código retorne false se estiver vazia).
     * Nota: O método atual retorna false se a lista estiver vazia antes da remoção, indicando que nenhuma ação foi realizada.
     */
    public boolean removerTodasPessoas() {
        if (pessoas.isEmpty()) {
            return false;
        }
        pessoas.clear();
        return true;
    }
}