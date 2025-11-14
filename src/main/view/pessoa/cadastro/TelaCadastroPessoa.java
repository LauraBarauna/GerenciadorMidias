package main.view.pessoa.cadastro;

import main.controller.PessoaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaCadastroPessoa {
    private JTextField textFieldNome;
    private JPanel jPanelPrincipal;
    private JButton btnAdicionar;
    private JLabel nome;

    private PessoaController controller;

    public TelaCadastroPessoa(PessoaController controller) {
        this.controller = controller;
        adicionarPessoa();
        enterPessoa();
    }

    private void enterPessoa() {
        textFieldNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnAdicionar.doClick();
                }
            }
        });
    }

    private void adicionarPessoa() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                try {
                    controller.cadastrarPessoa(nome);
                    JOptionPane.showMessageDialog(null, "Pessoa " + nome + " cadastrada com sucesso.");
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }

            }
        });
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
