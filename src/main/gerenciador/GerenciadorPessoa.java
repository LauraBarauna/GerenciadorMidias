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
     * Construtor do Gerenciador de Pessoa. Inicializa a lista de Pessoas como uma nova instância de ArrayList.
     */

    public GerenciadorPessoa() {
        pessoas = new ArrayList<Pessoa>();
    }
    
    /**
     * Adiciona uma nova Pessoa à lista gerenciada. Garante que a pessoa não seja adicionada se o nome dela já existir,
     * ignorando maiúsculas e minúsculas na comparação.
     * @param p: O objeto Pessoa a ser adicionado.
     * @return true se a pessoa foi adicionada com sucesso, se não era duplicata, e false caso contrário.
     */

    public boolean adicionarPessoa(Pessoa p) {
        if (pessoas.contains(p)) {
            return false;
        }

        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().equalsIgnoreCase(p.getNome())) {
                return false;
            }
        }
        pessoas.add(p);
        return true;
    }
    
    /**
    * Busca uma Pessoa na lista pelo nome, ignorando maiúsculas e minúsculas. Método auxiliar interno, usado para realizar remoções e verificações.
    * @param nome: O nome da Pessoa a ser buscada.
    * @return O objeto Pessoa encontrado, ou null se não for encontrado ou ainda se o nome for inválido.
    */

    private Pessoa buscarPessoa(String nome) {
        if (nome == null || nome.isBlank()) {
            return null;
        }

        Pessoa pessoa = null;

        for (Pessoa p : pessoas) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                pessoa = p;
            }
        }
        return pessoa;
    }
    
    /**
     * Retorna a lista completa de todas as Pessoas gerenciadas.
     * @return A List de objetos Pessoa.
     */

    public List<Pessoa> getPessoas() {
        return pessoas;
    }
    
    /**
     * Remove uma {@code Pessoa} da lista pelo nome. Utiliza o método buscarPessoa para localizar a pessoa a ser removida.
     * @param nome: O nome da Pessoa a ser removida.
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
     * Remove todas as Pessoa da lista.
     * @return true se a lista não estava vazia e foi limpa, false se a lista já estava vazia.
     */

    public boolean removerTodasPessoas() {
        if (pessoas.isEmpty()) {
            return false;
        }
        pessoas.clear();
        return true;
    }
}
