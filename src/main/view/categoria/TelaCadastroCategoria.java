package main.view.categoria;

import main.controller.CategoriaController;
import main.excecoes.categoria.CategoriaException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroCategoria {

    private CategoriaController controller;

    private JComboBox comboBoxTipo;
    private JPanel jPanelPrincipal;
    private JTextField textFieldNome;
    private JButton btnAdicionar;
    private JLabel nomeCategoria;
    private JLabel tipoMidia;

    public TelaCadastroCategoria(CategoriaController controller) {
        this.controller = controller;
        adicionarCategoria();
    }

    public void adicionarCategoria() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldNome.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "O campo nome deve ser preenchido.");
                }

                try {
                    controller.adicionarCategoria(textFieldNome.getText(), comboBoxTipo.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null, "Categoria adicionada com sucesso.");
                } catch (CategoriaException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }



            }
        });
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
