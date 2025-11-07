package main.view.idioma;

import main.controller.IdiomaController;
import main.gerenciador.GerenciadorIdioma;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaIdioma {
    private JComboBox<String> acoesIdioma;
    private JPanel jPanelPrincipal;
    private JPanel jPanelAcoesIdioma;

    private IdiomaController controller;

    private TelaCadastroIdioma cadastro;
    private TelaListarIdioma listar;

    public TelaIdioma(List<String> acoes, GerenciadorIdioma gerenciadorIdioma) {
        this.controller = new IdiomaController(gerenciadorIdioma);
        this.cadastro = new TelaCadastroIdioma(this.controller);
        this.listar = new TelaListarIdioma(this.controller);
        adicionarLayout();
        adicionarAcoesIdioma(acoes);
        trocarTela();
    }

    private void adicionarLayout() {
        this.jPanelAcoesIdioma.setLayout(new CardLayout());
    }

    private void adicionarAcoesIdioma(List<String> acoes) {
        for (String a : acoes) {
            this.acoesIdioma.addItem(a);
        }
    }

    private void trocarTela() {
        this.acoesIdioma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String acao = acoesIdioma.getSelectedItem().toString().toUpperCase();

                switch (acao) {
                    case "ADICIONAR":
                        jPanelAcoesIdioma.removeAll();
                        jPanelAcoesIdioma.add(cadastro.getjPanelPrincipal());
                        jPanelAcoesIdioma.revalidate();
                        jPanelAcoesIdioma.repaint();
                        break;
                    case "LISTAR":
                        listar.atualizarLista();
                        jPanelAcoesIdioma.removeAll();
                        jPanelAcoesIdioma.add(listar.getjPanelPrincipal());
                        jPanelAcoesIdioma.revalidate();
                        jPanelAcoesIdioma.repaint();
                    break;
                }

            }
        });
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
