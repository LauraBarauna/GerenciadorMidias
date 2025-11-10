package main.view.midias.principal;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.view.midias.cadastro.midia.TelaCadastroMidia;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaMidia {
    private JPanel jPanelPrincipal;
    private JComboBox<String> acoesMidia;
    private JPanel jPanelAcaoMidia;

    private TelaCadastroMidia cadastro;

    public TelaMidia(List<String> acoes, CategoriaController controller, IdiomaController idiomaController) {
        this.cadastro = new TelaCadastroMidia(controller, idiomaController);
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
