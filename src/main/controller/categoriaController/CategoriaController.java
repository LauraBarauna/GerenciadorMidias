package main.controller.categoriaController;

import main.gerenciador.GerenciadorCategoria;
import main.model.midias.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaController {
    private GerenciadorCategoria gerenciadorCategoria;

    public CategoriaController() {
       gerenciadorCategoria = new GerenciadorCategoria();
    }

    public void cadastrarCategoria(Categoria categoria, String tipo) {
        gerenciadorCategoria.cadastrarCategoria(categoria, tipo);
    }

    public List<Categoria> listarCategorias(String tipo) {
        return gerenciadorCategoria.getCategoriasFilme(tipo);
    }



}
