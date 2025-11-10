package main.view.categoria.principal;

import main.controller.CategoriaController;
import main.gerenciador.GerenciadorCategoria;
import main.view.categoria.cadastro.TelaCadastroCategoria;
import main.view.categoria.listar.TelaListarCategoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCategoria {
    private JPanel jPanelPrincipal;
    private JComboBox<String> acoesCategoria;
    private JPanel jPanelAcaoCategoria;

    private TelaCadastroCategoria cadastro;
    private TelaListarCategoria listarCategoria;

    public TelaCategoria(List<String> acoes, CategoriaController controller) {
        this.cadastro = new TelaCadastroCategoria(controller);
        this.listarCategoria = new TelaListarCategoria(controller);

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
        acoesCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String acao = acoesCategoria.getSelectedItem().toString().toUpperCase();
                switch (acao) {
                    case "ADICIONAR":
                        jPanelAcaoCategoria.removeAll();
                        jPanelAcaoCategoria.add(cadastro.getjPanelPrincipal());
                        jPanelAcaoCategoria.revalidate();
                        jPanelAcaoCategoria.repaint();
                        break;
                    case "LISTAR":
                        listarCategoria.atualizarLista(); // recarrega categorias
                        jPanelAcaoCategoria.removeAll();
                        jPanelAcaoCategoria.add(listarCategoria.getjPanelPrincipal());
                        jPanelAcaoCategoria.revalidate();
                        jPanelAcaoCategoria.repaint();
                        break;
                }
                trocarTela();
            }
        });



    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
