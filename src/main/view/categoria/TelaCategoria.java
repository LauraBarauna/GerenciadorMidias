package main.view.categoria;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCategoria {
    private JPanel jPanelPrincipal;
    private JComboBox<String> acoesCategoria;
    private JPanel jPanelAcaoCategoria;

    private TelaCadastroCategoria cadastro;

    public TelaCategoria(List<String> acoes) {
        this.cadastro = new TelaCadastroCategoria();
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
