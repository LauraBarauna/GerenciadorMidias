package main.view.TelaPrincipal;

import main.view.MenuAdicionarMidia.MenuAdicionarMidia;
import main.view.MenuCriarCategoria;
import main.view.MenuPrincipal.MenuPrincipal;
import main.view.menuCategoria.MenuCategoriaMidia;

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
        MenuAdicionarMidia menuAdicionarMidia = new MenuAdicionarMidia(this);
        MenuCategoriaMidia menuCategoriaMidia = new MenuCategoriaMidia(menuAdicionarMidia.getMidiaController().getExtensaoMidia());
        MenuCriarCategoria menuCriarCategoria = new MenuCriarCategoria(menuAdicionarMidia.getMidiaController().getExtensaoMidia());

        painelPrincipal.add(menuPrincipal.getPainelPrincipal(), "menu");
        painelPrincipal.add(menuAdicionarMidia.getPainelPrincipal(), "adicionar");
        painelPrincipal.add(menuCategoriaMidia.getPainelPrincipal(), "categoria");

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
