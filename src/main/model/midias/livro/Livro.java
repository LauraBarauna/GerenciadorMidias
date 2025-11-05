package main.model.midias.livro;

import main.excecoes.autor.AutorDuplicadoException;
import main.excecoes.autor.AutorNaoEncontradoException;
import main.model.midias.Categoria;
import main.model.midias.Midia;
import main.model.pessoa.Pessoa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Livro extends Midia implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Pessoa> autores;

    public Livro(int id, String local, double tamanhoEmDisco, String titulo, int duracao, Categoria categoria) {
        super(id, local, tamanhoEmDisco, titulo, duracao, categoria);
        this.autores = new ArrayList<>();
    }

    public void incluirAutor(Pessoa autor) {
        for (Pessoa a : autores) {
            if (a.getNome().equalsIgnoreCase(autor.getNome())) {
                throw new AutorDuplicadoException(autor.getNome(), getTitulo());
            }
        }
        this.autores.add(autor);
    }

    public Pessoa getAutor(String nomeAutor) {
        for (Pessoa a : autores) {
            if (a.getNome().equalsIgnoreCase(nomeAutor)) {
                return a;
            }
        }
        throw new AutorNaoEncontradoException(nomeAutor);
    }

    public void removerAutor(String nomeAutor) {
        Pessoa autorRemover = getAutor(nomeAutor);
        autores.remove(autorRemover);
    }

    public List<Pessoa> getAutores() {
        if (this.autores.isEmpty()) {
            throw new RuntimeException("O livro " + getTitulo() + " não possui autores.");
        }
        return this.autores;
    }

    @Override
    public String exibirAtributos() {
        String resultado = super.exibirAtributos();
        resultado = resultado + "Autores: ";
        
        if (autores.isEmpty()) {
            resultado = resultado + "Nenhum autor cadastrado\n";
        } else {
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
