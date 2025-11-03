package main.view.menuCategoria;

import main.controller.categoriaController.CategoriaController;
import main.model.midias.Categoria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class MenuCategoriaMidia {
    private JPanel painelPrincipal;
    private CategoriaController categoriaController;
    private JLabel lbHeader;
    private JLabel lbCategoria;
    private JComboBox<Categoria> comboCategoria;
    private JButton btnCriarCategoria;

    public MenuCategoriaMidia(String tipo) {
        this.categoriaController = new CategoriaController();
        carregarCategorias(categoriaController.listarCategorias(tipo));

        btnCriarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void carregarCategorias(List<Categoria> categorias) {
        DefaultComboBoxModel<Categoria> model = new DefaultComboBoxModel<>();
        for (Categoria categoria : categorias) {
            model.addElement(categoria);
        }
        comboCategoria.setModel(model);
    }

    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }
}
