package main.view.midias;

import main.controller.CategoriaController;
import main.gerenciador.GerenciadorCategoria;
import main.view.midias.cadastro.midia.TelaCadastroMidia;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaMidia {
    private JPanel jPanelPrincipal;
    private JComboBox<String> acoesMidia;
    private JPanel jPanelAcaoMidia;

    private TelaCadastroMidia cadastro;

    public TelaMidia(List<String> acoes, CategoriaController controller) {
        this.cadastro = new TelaCadastroMidia(controller);
        carregarComboBoxMidias(acoes);
        adicionarLayouts();
        atualizarPainel();
    }

    public void adicionarLayouts() {
        this.jPanelAcaoMidia.setLayout(new CardLayout());
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    private void carregarComboBoxMidias(List<String> acoes) {
        for (String a : acoes) {
            if (acoesMidia == null) {
                break;
            }
            acoesMidia.addItem(a);
        }
    }

    private void atualizarPainel() {
        String acaoSelecionada = acoesMidia.getSelectedItem().toString().toUpperCase();

        switch (acaoSelecionada) {
            case "ADICIONAR":
                jPanelAcaoMidia.removeAll();
                jPanelAcaoMidia.add(cadastro.getjPanelPrincipal());
                jPanelAcaoMidia.revalidate();
                jPanelAcaoMidia.repaint();
                break;
        }
    }

    public TelaCadastroMidia getCadastro() {
        return cadastro;
    }
}
