package main.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Arrays;
import java.util.List;

public class TelaPrincipal {
    private JTabbedPane menu;

    private JPanel midias;
    private JPanel categorias;
    private JPanel idiomas;

    private JComboBox<String> acoesMidias;
    private JComboBox<String> acoesCategoria;
    private JComboBox<String> acoesIdioma;
    private JComboBox<String> acoesPessoas;

    private List<JComboBox> boxes;

    private JPanel jPanelAcaoMidia;
    private JPanel jPanelPrincipal;
    private JPanel jPanelAcaoCategoria;
    private JPanel jPanelAcaoIdioma;
    private JPanel pessoa;
    private JPanel jPanelAcaoPessoa;

    private TelaCadastroMidia cadastroMidia;
    private TelaCadastroCategoria cadastroCategoria;

    public TelaPrincipal() {
        cadastroMidia = new TelaCadastroMidia();
        cadastroCategoria = new TelaCadastroCategoria();

        boxes = Arrays.asList(acoesMidias, acoesCategoria, acoesIdioma, acoesPessoas);

        jPanelAcaoMidia.setLayout(new CardLayout());
        jPanelAcaoCategoria.setLayout(new CardLayout());

        carregarComboBoxMidias();
        listenerComboBoxMidias();
        listenerComboBoxCategorias();
    }

    public void carregarComboBoxMidias() {
        List<String> acoes = Arrays.asList(
                "Adicionar",
                "Listar Todos",
                "Listar um",
                "Atualizar",
                "Remover"
        );
        for (JComboBox box : boxes) {
            for (String a : acoes) {
                box.addItem(a);
            }
        }
    }

    public void listenerComboBoxMidias() {
        acoesMidias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarPainelMidias();
            }
        });
    }

    public void listenerComboBoxCategorias() {
        acoesCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarPainelCategorias();
            }
        });
    }

    private void atualizarPainelMidias() {
        String acaoSelecionada = acoesMidias.getSelectedItem().toString().toUpperCase();

        switch (acaoSelecionada) {
            case "ADICIONAR":
                jPanelAcaoMidia.removeAll();
                jPanelAcaoMidia.add(cadastroMidia.getjPanelPrincipal());
                jPanelAcaoMidia.revalidate();
                jPanelAcaoMidia.repaint();
        }

    }

    private void atualizarPainelCategorias() {
        String acaoSelecionada = acoesMidias.getSelectedItem().toString().toUpperCase();

        switch (acaoSelecionada) {
            case "ADICIONAR":
                jPanelAcaoCategoria.removeAll();
                jPanelAcaoCategoria.add(cadastroCategoria.getjPanelPrincipal());
                jPanelAcaoCategoria.revalidate();
                jPanelAcaoCategoria.repaint();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TelaPrincipal");
        TelaPrincipal tela = new TelaPrincipal();
        frame.setContentPane(tela.jPanelPrincipal);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
