package main.view.categoria;

import main.controller.CategoriaController;
import main.excecoes.categoria.CategoriaException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaListarCategoria {

    private CategoriaController controller;

    private JPanel jPanelPrincipal;
    private JTextArea textArea;
    private JComboBox tipoMidia;

    public TelaListarCategoria(CategoriaController controller) {
        this.controller = controller;
        listarCategorias();
        textArea.setEditable(false);
    }

    public void listarCategorias() {

        try {

            tipoMidia.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String tipo = tipoMidia.getSelectedItem().toString();
                    String categoria = controller.listarCategorias(tipo);

                    if (categoria != null) {
                        textArea.setText(categoria);
                    } else {
                        textArea.setText("Não existe nenhuma categoria cadastrada para " + tipo + ".");
                    }


                }
            });

        } catch (CategoriaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
