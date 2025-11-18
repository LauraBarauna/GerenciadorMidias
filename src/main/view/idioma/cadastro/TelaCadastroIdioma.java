package main.view.idioma.cadastro;

import main.controller.IdiomaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaCadastroIdioma {
    private JTextField textFieldIdioma;
    private JPanel jPanelPrincipal;
    private JButton btnAdicionar;
    private JLabel idioma;

    private IdiomaController controller;

    public TelaCadastroIdioma(IdiomaController controller) {
        this.controller = controller;
        cadastrarIdioma();
        enterIdioma();
    }

    private void enterIdioma() {
        textFieldIdioma.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnAdicionar.doClick();
                }
            }
        });
    }

    private void cadastrarIdioma() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idioma = textFieldIdioma.getText();
                try {
                    controller.cadastrarIdioma(idioma);
                    JOptionPane.showMessageDialog(null, "Idioma " + idioma + " cadastrado com sucesso!");
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
