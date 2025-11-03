package main.view.TelaPrincipal;

import main.view.MenuAdicionarMidia.MenuAdicionarMidia;
import main.view.MenuPrincipal.MenuPrincipal;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private CardLayout layout;
    private JPanel painelPrincipal;

    public TelaPrincipal() {
        setTitle("Gerenciador de Mídias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        layout = new CardLayout();
        painelPrincipal = new JPanel(layout);

        MenuPrincipal menuPrincipal = new MenuPrincipal(this);
        MenuAdicionarMidia menuAdicionarMidia = new MenuAdicionarMidia();

        painelPrincipal.add(menuPrincipal.getPainelPrincipal(), "menu");
        painelPrincipal.add(menuAdicionarMidia.getPainelPrincipal(), "adicionar");

        add(painelPrincipal);
        mostarTela("menu");
    }

    public void mostarTela(String tela) {
        layout.show(painelPrincipal, tela);
    }

    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
