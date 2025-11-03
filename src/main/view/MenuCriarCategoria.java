package main.view;

import main.controller.categoriaController.CategoriaController;
import main.model.midias.Categoria;

import javax.swing.*;

public class MenuCriarCategoria {
    private JPanel painelPrincipal;
    private CategoriaController categoriaController;
    private JLabel lbHeader;
    private JTextField textField1;


    public MenuCriarCategoria(String tipo) {
        categoriaController = new CategoriaController();
        criarCategoria(tipo);
    }

    public void criarCategoria(String tipo) {
        Categoria categoria = new Categoria(textField1.getText());
        categoriaController.cadastrarCategoria(categoria, tipo);
    }

    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }
}
