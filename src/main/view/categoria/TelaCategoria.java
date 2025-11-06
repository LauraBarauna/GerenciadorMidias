package main.view.categoria;

import main.controller.CategoriaController;
import main.gerenciador.GerenciadorCategoria;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCategoria {
    private JPanel jPanelPrincipal;
    private JComboBox<String> acoesCategoria;
    private JPanel jPanelAcaoCategoria;

    private CategoriaController controller;

    private TelaCadastroCategoria cadastro;

    public TelaCategoria(List<String> acoes, GerenciadorCategoria gerenciador) {
        this.controller = new CategoriaController(gerenciador);
        this.cadastro = new TelaCadastroCategoria(this.controller);

        adicionarLayout();
        adicionarAcoes(acoes);
        trocarTela();
    }

    private void adicionarLayout() {
        this.jPanelAcaoCategoria.setLayout(new CardLayout());
    }

    private void adicionarAcoes(List<String> acoes) {
        for (String a : acoes) {
            if (acoesCategoria == null){
                break;
            }
            this.acoesCategoria.addItem(a);
        }
    }

    private void trocarTela() {
        String acao = this.acoesCategoria.getSelectedItem().toString().toUpperCase();

        switch (acao) {
            case "ADICIONAR":
                jPanelAcaoCategoria.removeAll();
                jPanelAcaoCategoria.add(cadastro.getjPanelPrincipal());
                jPanelAcaoCategoria.revalidate();
                jPanelAcaoCategoria.repaint();
                break;
        }

    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
