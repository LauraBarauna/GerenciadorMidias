package main.view.midias.principal;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.MidiaController;
import main.controller.PessoaController;
import main.view.midias.cadastro.midia.TelaCadastroMidia;
import main.view.midias.listar.telaListar.TelaListarMidia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaMidia {
    private JPanel jPanelPrincipal;
    private JComboBox<String> acoesMidia;
    private JPanel jPanelAcaoMidia;

    private TelaCadastroMidia cadastro;
    private TelaListarMidia listar;

    public TelaMidia(List<String> acoes, CategoriaController controller, IdiomaController idiomaController,
                     PessoaController pessoaController, MidiaController midiaController) {
        this.cadastro = new TelaCadastroMidia(controller, idiomaController, pessoaController, midiaController);
        this.listar = new TelaListarMidia(midiaController, pessoaController, controller);
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

        acoesMidia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String acaoSelecionada = acoesMidia.getSelectedItem().toString().toUpperCase();
                switch (acaoSelecionada) {
                    case "ADICIONAR":
                        cadastro.telaMidia();
                        jPanelAcaoMidia.removeAll();
                        jPanelAcaoMidia.add(cadastro.getjPanelPrincipal());
                        jPanelAcaoMidia.revalidate();
                        jPanelAcaoMidia.repaint();
                        break;
                    case "LISTAR":
                        jPanelAcaoMidia.removeAll();
                        jPanelAcaoMidia.add(listar.getjPanelPrincipal());
                        jPanelAcaoMidia.revalidate();
                        jPanelAcaoMidia.repaint();
                        break;

                }
            }
        });


    }

    public TelaCadastroMidia getCadastro() {
        return cadastro;
    }
}
