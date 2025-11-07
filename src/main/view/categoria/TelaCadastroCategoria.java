package main.view.categoria;

import main.controller.CategoriaController;
import main.excecoes.categoria.CategoriaException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        adicionarCategoriaEnter();


    }

    public void adicionarCategoriaEnter() {
        textFieldNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnAdicionar.doClick(); // simula o clique no botão
                }
            }
        });
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
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }



            }
        });
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
