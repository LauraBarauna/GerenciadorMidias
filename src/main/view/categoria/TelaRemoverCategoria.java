package main.view.categoria;

import main.controller.CategoriaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaRemoverCategoria {
    private JPanel jPanelPrincipal;
    private JComboBox comboBox1;
    private JPanel jPanelCategorias;
    private JScrollPane jPanelScroll;

    private CategoriaController controller;

    public TelaRemoverCategoria(CategoriaController controller) {
        this.jPanelCategorias.setLayout(new BoxLayout(jPanelCategorias, BoxLayout.Y_AXIS));
        this.controller = controller;
        pegarTipoMidia();
    }

    private void pegarTipoMidia() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = comboBox1.getSelectedItem().toString();
                atualizarList(tipo);
            }
        });
    }

    private void atualizarList(String tipo) {
        jPanelCategorias.removeAll();

        List<String> categorias = controller.listarCategorias(tipo);

        if (!categorias.isEmpty()) {
            for (String c : categorias) {
                JPanel linha = new JPanel(new BorderLayout());
                linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
                JLabel nome = new JLabel(c, JLabel.CENTER);
                JButton btnRemover = new JButton("Remover");

                btnRemover.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.removerCategoria(c, tipo);
                        JOptionPane.showMessageDialog(jPanelPrincipal, "Categoria "  + c +" removida com sucesso!");
                        atualizarList(tipo);
                    }
                });

                linha.add(nome, BorderLayout.CENTER);
                linha.add(btnRemover, BorderLayout.EAST);
                linha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                jPanelCategorias.add(linha);
            }
            jPanelCategorias.revalidate();
            jPanelCategorias.repaint();
        } else {
            JPanel linha = new JPanel(new BorderLayout());
            JLabel semCategoria = new JLabel(tipo + " não tem nenhuma categoria cadastrada!", JLabel.CENTER);
            linha.add(semCategoria, BorderLayout.CENTER);
            linha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            jPanelCategorias.add(linha);
            jPanelCategorias.revalidate();
            jPanelCategorias.repaint();
        }

    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
