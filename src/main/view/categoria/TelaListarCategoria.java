package main.view.categoria;

import main.controller.CategoriaController;
import main.excecoes.categoria.CategoriaException;
import main.model.midias.Categoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaListarCategoria {

    private CategoriaController controller;

    private JPanel jPanelPrincipal;
    private JComboBox tipoMidia;
    private JPanel jPanelCategorias;

    public TelaListarCategoria(CategoriaController controller) {
        this.controller = controller;
        jPanelCategorias.setLayout(new BoxLayout(jPanelCategorias, BoxLayout.Y_AXIS));
        jPanelCategorias.setAlignmentX(Component.CENTER_ALIGNMENT);
        pegarTipoMidia();
    }

    private void pegarTipoMidia() {
        tipoMidia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = tipoMidia.getSelectedItem().toString();
                listarCategorias(tipo);
                pegarTipoMidia();
            }
        });
    }

    public void listarCategorias(String tipo) {
        List<String> categorias = new ArrayList<>();
        categorias = controller.listarCategorias(tipo);

        jPanelCategorias.removeAll();

        if (!categorias.isEmpty()) {
            for (String c : categorias) {
                JPanel linha = new JPanel(new BorderLayout());
                linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
                JLabel nome = new JLabel(c, JLabel.LEFT);

                JButton btnRemover = new JButton("Remover");

                btnRemover.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.removerCategoria(c, tipo);
                        JOptionPane.showMessageDialog(jPanelPrincipal, "Categoria "  + c +" removida com sucesso!");
                        listarCategorias(tipo);
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
            linha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            jPanelCategorias.add(linha);
            jPanelCategorias.revalidate();
            jPanelCategorias.repaint();
        }


    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
