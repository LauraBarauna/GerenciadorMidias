package main.gerenciador;

import main.model.midias.Categoria;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorCategoria {
    List<Categoria> categorias;

    public GerenciadorCategoria() {
        this.categorias = new ArrayList<Categoria>();
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }
}
