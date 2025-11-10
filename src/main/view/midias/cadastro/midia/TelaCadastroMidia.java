package main.view.midias.cadastro.midia;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.excecoes.arquivo.ExtensaoInvalidaException;
import main.view.midias.cadastro.filme.TelaCadastroFilme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCadastroMidia {
    private JPanel jPanelPrincipal;

    private JLabel titulo;
    private JTextField textFieldTitulo;

    private JLabel caminho;
    private JTextField textFieldCaminho;
    private JButton btnBuscar;

    private JLabel categoria;
    private JComboBox comboBoxCategoria;

    private JButton btnAdicionar;

    private String caminhoArquivo;
    private String extensaoArquivo;

    private CategoriaController categoriaController;

    private TelaCadastroFilme filme;

    public TelaCadastroMidia(CategoriaController categoriaController, IdiomaController idiomaController) {
        buscarCaminhoArquivo();
        this.categoriaController = categoriaController;
        this.filme = new TelaCadastroFilme(idiomaController);
        //jPanelPrincipal.setLayout(new CardLayout());
        continuar();
    }

    private void continuar() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avancarTela();
            }
        });
    }

    private void buscarCaminhoArquivo() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int resposta = fileChooser.showOpenDialog(jPanelPrincipal);

                if (resposta == JFileChooser.APPROVE_OPTION) {
                    caminhoArquivo = fileChooser.getSelectedFile().getAbsolutePath();
                    textFieldCaminho.setText(caminhoArquivo);
                    extensaoArquivo = fileChooser.getSelectedFile().getAbsolutePath().toString().substring(caminhoArquivo.length() - 3);
                    listarCategorias(extensaoArquivo);
                }

            }
        });
    }

    public void listarCategorias(String extensao) {
        comboBoxCategoria.removeAllItems();

        try {
            List<String> categorias = categoriaController.listarCategoriasExtensaoString(extensao);

            if (categorias.isEmpty()) {
                comboBoxCategoria.addItem("Não existe nenhuma categoria para a extensão " + extensao + ". Adicione uma no menu Categoria.");
            } else {
                for (String categoria : categorias) {
                    comboBoxCategoria.addItem(categoria);
                }
            }
        } catch (ExtensaoInvalidaException e) {
            JOptionPane.showMessageDialog(jPanelPrincipal, e.getMessage());
        }

    }

    private void avancarTela() {
        switch (this.extensaoArquivo.toUpperCase()) {
            case "MP4":
            case "MKV":
                jPanelPrincipal.removeAll();
                jPanelPrincipal.add(filme.getjPanelPrincipal());
                jPanelPrincipal.revalidate();
                jPanelPrincipal.repaint();
                break;

        }
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    public String getExtensaoArquivo() {
        return extensaoArquivo;
    }
}
