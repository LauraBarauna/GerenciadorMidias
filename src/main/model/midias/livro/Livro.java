package main.model.midias.livro;

import main.excecoes.autor.AutorDuplicadoException;
import main.excecoes.autor.AutorNaoEncontradoException;
import main.model.midias.Categoria;
import main.model.midias.Midia;
import main.model.pessoa.Pessoa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * * @author Yasmin Darlen Schneider e Laura
 */
public class Livro extends Midia implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Pessoa> autores;

    /**
     * Construtor da classe Livro. Inicializa os atributos herdados de Midia e a lista de autores.
     * @param id: O identificador único da mídia.
     * @param local: O caminho para o arquivo no sistema operacional.
     * @param tamanhoEmDisco: O tamanho do arquivo em disco (em MB, por exemplo).
     * @param titulo: O título do livro.
     * @param duracao: A duração do livro em páginas.
     * @param categoria: A categoria do livro.
     */
    public Livro(int id, String local, double tamanhoEmDisco, String titulo, int duracao, Categoria categoria) {
        super(id, local, tamanhoEmDisco, titulo, duracao, categoria);
        this.autores = new ArrayList<>();
    }

    /**
     * Adiciona um novo autor à lista, verificando se ele já não existe através do nome. Lança uma exceção se houver uma tentativa de incluir um autor com o mesmo nome.
     * @param autor O objeto {@code Pessoa} a ser incluído como autor.
     * @throws AutorDuplicadoException Se um autor com o mesmo nome já estiver cadastrado.
     */
    public void incluirAutor(Pessoa autor) {
        for (Pessoa a : autores) {
            if (a.getNome().equalsIgnoreCase(autor.getNome())) {
                throw new AutorDuplicadoException(autor.getNome(), getTitulo());
            }
        }
        this.autores.add(autor);
    }

    /**
     * Busca e retorna um autor pelo nome na lista de autores do livro.
     * @param nomeAutor: O nome do autor a ser buscado.
     * @return O objeto Pessoa encontrado.
     * @throws AutorNaoEncontradoException Se nenhum autor com o nome fornecido for encontrado.
     */
    public Pessoa getAutor(String nomeAutor) {
        for (Pessoa a : autores) {
            if (a.getNome().equalsIgnoreCase(nomeAutor)) {
                return a;
            }
        }
        throw new AutorNaoEncontradoException(nomeAutor);
    }

    /**
     * Remove um autor da lista pelo nome. Depende do método getAutor para encontrar e validar a existência do autor.
     * @param nomeAutor: O nome do autor a ser removido.
     * @throws AutorNaoEncontradoException Se o autor não for encontrado.
     */
    public void removerAutor(String nomeAutor) {
        Pessoa autorRemover = getAutor(nomeAutor);
        autores.remove(autorRemover);
    }

    /**
     * Retorna a lista de todos os autores do livro.
     * @return Uma List<Pessoa> contendo todos os autores.
     * @throws RuntimeException Se a lista de autores estiver vazia.
     */
    public List<Pessoa> getAutores() {
        if (this.autores.isEmpty()) {
            throw new RuntimeException("O livro " + getTitulo() + " não possui autores.");
        }
        return this.autores;
    }

    /**
     * Sobrescreve o método da classe pai para incluir a lista de autores formatada na exibição dos atributos.
     * @return Uma String contendo todos os atributos do Livro formatados para exibição.
     */
    @Override
    public String exibirAtributos() {
        String resultado = super.exibirAtributos();
        resultado = resultado + "Autores: ";
        
        if (autores.isEmpty()) {
            resultado = resultado + "Nenhum autor cadastrado\n";
        } else {
            // Formata a lista de autores separados por vírgula
            for (int i = 0; i < autores.size(); i++) {
                resultado += autores.get(i).getNome();
                if (i < autores.size() - 1) {
                    resultado = resultado + ", ";
                }
            }
            resultado = resultado + "\n";
        }
        
        return resultado;
    }
}