package main.view.midias;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaMidia {
    private JPanel jPanelPrincipal;
    private JComboBox<String> acoesMidia;
    private JPanel jPanelAcaoMidia;

    private TelaCadastroMidia cadastro;

    public TelaMidia(List<String> acoes) {
        this.cadastro = new TelaCadastroMidia();
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

    public JComboBox<String> getComboBoxMidia() {
        return acoesMidia;
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
        }
    }
}
