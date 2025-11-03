package main.view.MenuAdicionarMidia;

import main.controller.midiaController.MidiaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdicionarMidia {
    private MidiaController midiaController;
    private JPanel painelPrincipal;
    private JLabel lbHeadear;
    private JTextField textField1;
    private JLabel lbTitulo;
    private JButton btnContinuar;

    public MenuAdicionarMidia() {
        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    midiaController = new MidiaController();
                }
                catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }

            }
        });
    }

    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }
}
