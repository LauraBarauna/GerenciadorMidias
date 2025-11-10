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
    private JPanel jPanelMidia;


    private JTextField textFieldTitulo;

    private JTextField textFieldCaminho;
    private JButton btnBuscar;
    private JLabel titulo;
    private JLabel caminho;
    private JLabel categoria;

    private JComboBox comboBoxCategoria;

    private JButton btnAdicionar;

    private String caminhoArquivo;
    private String extensaoArquivo;

    private CategoriaController categoriaController;

    private TelaCadastroFilme filme;

    private CardLayout layout;

    public TelaCadastroMidia(CategoriaController categoriaController, IdiomaController idiomaController) {
        buscarCaminhoArquivo();
        this.categoriaController = categoriaController;
        this.filme = new TelaCadastroFilme(idiomaController);

        layout = new CardLayout();
        jPanelPrincipal.setLayout(layout);

        jPanelPrincipal.add(jPanelMidia, "midia");
        jPanelPrincipal.add(filme.getjPanelPrincipal(), "filme");

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

    public void telaMidia() {
        layout.show(jPanelPrincipal, "midia");
    }

    private void avancarTela() {
        System.out.println("oi");
        switch (this.extensaoArquivo.toUpperCase()) {
            case "MP4":
            case "MKV":
                System.out.println("FIlme");
                layout.show(jPanelPrincipal, "filme");
                break;
        }
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    public String getExtensaoArquivo() {
        return extensaoArquivo;
    }

    public JPanel getjPanelMidia() {
        return jPanelMidia;
    }
}
