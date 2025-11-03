package main.view.MenuPrincipal;

import main.view.TelaPrincipal.TelaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal {
    private JPanel PainelPrincipal;
    private JLabel lbHeader;
    private JButton btnAddMidia;
    private JButton btnListarMidia;


    public MenuPrincipal(TelaPrincipal telaPrincipal) {
        btnAddMidia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaPrincipal.mostarTela("adicionar");
            }
        });
    }

    public JPanel getPainelPrincipal() {
        return PainelPrincipal;
    }
}
