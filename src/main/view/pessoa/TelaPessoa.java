package main.view.pessoa;

import main.controller.PessoaController;
import main.gerenciador.GerenciadorPessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaPessoa {
    private JPanel jPanelPrincipal;
    private JComboBox<String> acoesPessoas;
    private JPanel jPanelAcoesPessoa;

    private PessoaController controller;

    private TelaCadastroPessoa cadastro;

    public TelaPessoa(List<String> acoes, GerenciadorPessoa gerenciador) {
        this.controller = new PessoaController(gerenciador);
        this.cadastro = new TelaCadastroPessoa(controller);
        adicionarLayout();
        adicionarAcoes(acoes);
        trocarTela();
    }

    private void trocarTela() {
        this.acoesPessoas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String acao = acoesPessoas.getSelectedItem().toString().toUpperCase();

                switch (acao) {
                    case "ADICIONAR":
                        jPanelAcoesPessoa.removeAll();
                        jPanelAcoesPessoa.add(cadastro.getjPanelPrincipal());
                        jPanelAcoesPessoa.revalidate();
                        jPanelAcoesPessoa.repaint();
                        break;
                    case "LISTAR":
                }

            }
        });
    }

    private void adicionarLayout() {
        this.jPanelAcoesPessoa.setLayout(new CardLayout());
    }

    private void adicionarAcoes(List<String> acoes) {
        for (String a : acoes) {
            acoesPessoas.addItem(a);
        }
    }

    public JPanel getJPanelPrincipal() {
        return jPanelPrincipal;
    }
}
