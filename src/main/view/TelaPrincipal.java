package main.view;

import main.view.midias.TelaMidia;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private JPanel jPanelPrincipal;

    private JTabbedPane menu;

    private JPanel midias;
    private JPanel categorias;
    private JPanel idiomas;
    private JPanel pessoa;
    private JPanel inicio;

    private TelaMidia telaMidia;

    public TelaPrincipal(TelaMidia telaMidia) {
        this.telaMidia = telaMidia;
        configurarTela();
        adicionarLayouts();

        selecionarMenu();
    }

    public void adicionarLayouts() {
        this.midias.setLayout(new CardLayout());
        this.categorias.setLayout(new CardLayout());
        this.idiomas.setLayout(new CardLayout());
        this.pessoa.setLayout(new CardLayout());
    }

    public void configurarTela() {
        setTitle("Gerenciador de Midias");
        setContentPane(this.jPanelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void selecionarMenu() {
        menu.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                int index = menu.getSelectedIndex();
                String aba = menu.getTitleAt(index).toUpperCase();


                switch (aba) {
                    case "MÍDIAS":
                        atualizarMidia(telaMidia);
                        break;
                    case "CATEGORIA":

                        break;
                    case "IDIOMA":
                        break;
                    case "PESSOA":
                        break;
                }
            }
        });
    }

    public void atualizarMidia(TelaMidia telaMidia) {
        midias.removeAll();
        midias.add(telaMidia.getjPanelPrincipal());
        midias.revalidate();
        midias.repaint();
    }

    public static void main(String[] args) {
        new TelaGerenciador();
    }

}
