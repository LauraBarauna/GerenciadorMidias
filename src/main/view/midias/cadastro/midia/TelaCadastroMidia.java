package main.view.midias.cadastro.midia;

import main.controller.CategoriaController;
import main.excecoes.arquivo.ExtensaoInvalidaException;

import javax.swing.*;
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

    public TelaCadastroMidia(CategoriaController categoriaController) {
        buscarCaminhoArquivo();
        this.categoriaController = categoriaController;
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

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    public String getExtensaoArquivo() {
        return extensaoArquivo;
    }
}
