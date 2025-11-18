package main.gerenciador;

import main.model.idioma.Idioma;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class GerenciadorIdioma {
    List<Idioma> idiomas;
    
    /**
     * Construtor do Gerenciador de Idioma. Inicializa a lista de Idiomas como uma nova instância de ArrayList.
     */

    public GerenciadorIdioma() {
        this.idiomas = new ArrayList<>();
    }
    
    /**
     * Adiciona um novo objeto Idioma à lista gerenciada. Garante que o idioma não seja adicionado se o nome dele já existir (case-insensitive)
     * ou se o objeto for nulo.
     * @param idioma: O objeto Idioma a ser adicionado.
     * @return true se o idioma foi adicionado com sucesso, não era duplicata, e false caso contrário.
     */

    public boolean adicionarIdioma(Idioma idioma) {
        if (idioma == null) {
            return false;
        }

        if (this.idiomas.contains(idioma)) {
            return false;
        }

        for (Idioma i : this.idiomas) {
            if (i.exibirIdioma().equalsIgnoreCase(idioma.exibirIdioma())) {
                return false;
            }
        }

        this.idiomas.add(idioma);
        return true;
    }
    
    /**
     * Busca um objeto Idioma na lista pelo seu nome (String), ignorando maiúsculas e minúsculas. Método auxiliar interno, usado 
     * para realizar remoções e outras verificações.
     * @param idioma O nome (String) do idioma a ser buscado.
     * @return O objeto Idioma encontrado, ou null se não for encontrado ou ainda se o nome for inválido.
     */

    private Idioma buscarIdioma(String idioma) {
        if (idioma == null || idioma.isBlank()) {
            return null;
        }

        Idioma idiomaBuscar = null;

        for (Idioma i : this.idiomas) {
            if (i.exibirIdioma().equalsIgnoreCase(idioma)) {
                idiomaBuscar = i;
            }
        }
        return idiomaBuscar;
    }
    
    /**
     * Remove um objeto Idioma da lista pelo seu nome.
     * @param idioma: O nome (String) do idioma a ser removido.
     * @return true se o idioma foi encontrado e removido, false caso contrário.
     */

    public boolean removerIdioma(String idioma) {
        Idioma idiomaRemover = buscarIdioma(idioma);
        if (idiomaRemover == null) {
            return false;
        }
        this.idiomas.remove(idiomaRemover);
        return true;
    }
    
    /**
     * Remove todos os objetos Idioma da lista.
     * @return true se a lista não estava vazia e foi limpa, false se a lista já estava vazia.
     */

    public boolean removerTodosIdiomas() {
        if (getIdiomas().isEmpty()) {
            return false;
        }
        this.idiomas.clear();
        return true;
    }
    
    /**
     * Retorna a lista completa de todos os Idiomas gerenciados.
     * @return A List de objetos Idioma.
     */

    public List<Idioma> getIdiomas() {
        return this.idiomas;
    }
}
