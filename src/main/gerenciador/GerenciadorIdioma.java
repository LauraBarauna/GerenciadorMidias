package main.gerenciador;

import main.model.idioma.Idioma;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Laura Barauna (Adaptado para Javadoc)
 */

public class GerenciadorIdioma {
    
    List<Idioma> idiomas;

    /**
     * Construtor da classe GerenciadorIdioma. Inicializa a lista interna de idiomas como um {@code ArrayList} vazio.
     */
    public GerenciadorIdioma() {
        this.idiomas = new ArrayList<>();
    }

    /**
     * Adiciona um novo idioma à coleção. Verifica se o idioma é nulo ou se já existe na lista, comparando pelo nome do idioma, ignorando maiúsculas/minúsculas.
     * @param idioma: O objeto Idioma a ser adicionado.
     * @return true se o idioma foi adicionado com sucesso, false se for nulo ou duplicado.
     */
    public boolean adicionarIdioma(Idioma idioma) {
        if (idioma == null) {
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
     * Busca um idioma na coleção pelo nome. A busca é feita ignorando maiúsculas/minúsculas.
     * @param idioma: O nome do idioma a ser buscado.
     * @return O objeto Idioma encontrado, ou  null se não for encontrado ou se o nome for inválido.
     */
    private Idioma buscarIdioma(String idioma) {
        if (idioma == null || idioma.isBlank()) {
            return null;
        }

        Idioma idiomaBuscar = null;

        for (Idioma i : this.idiomas) {
            if (i.exibirIdioma().equalsIgnoreCase(idioma)) {
                idiomaBuscar = i;
                break;
            }
        }
        return idiomaBuscar;
    }

    /**
     * Remove um idioma da coleção pelo nome. Utiliza o método buscarIdioma(String idioma) para encontrar o objeto a ser removido.
     * @param idioma: O nome do idioma a ser removido.
     * @return  true se o idioma foi encontrado e removido, false caso contrário.
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
     * Remove todos os idiomas da coleção.
     * @return true se a lista foi limpa, false se a lista já estava vazia.
     */
    public boolean removerTodosIdiomas() {
        if (getIdiomas().isEmpty()) {
            return false;
        }
        this.idiomas.clear();
        return true;
    }

    /**
     * Retorna a lista completa de idiomas cadastrados.
     * @return A List<Idioma> contendo todos os idiomas.
     */
    public List<Idioma> getIdiomas() {
        return this.idiomas;
    }
}