package main.view.midias.cadastro.midia;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.PessoaController;
import main.excecoes.arquivo.ExtensaoInvalidaException;
import main.view.midias.cadastro.filme.TelaCadastroFilme;
import main.view.midias.cadastro.livro.TelaCadastroLivro;
import main.view.midias.cadastro.musica.TelaCadastroMusica;

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
    private String tituloArquivo;
    private String categoriaArquivo;

    private CategoriaController categoriaController;

    private TelaCadastroFilme filme;
    private TelaCadastroMusica musica;
    private TelaCadastroLivro livro;

    private CardLayout layout;

    public TelaCadastroMidia(CategoriaController categoriaController, IdiomaController idiomaController,
                             PessoaController pessoaController) {
        buscarCaminhoArquivo();
        this.categoriaController = categoriaController;
        this.filme = new TelaCadastroFilme(idiomaController);
        this.musica = new TelaCadastroMusica(pessoaController);
        this.livro = new TelaCadastroLivro(pessoaController);

        layout = new CardLayout();
        adicionarPainel();
        continuar();
    }

    private void adicionarPainel() {
        jPanelPrincipal.setLayout(layout);

        jPanelPrincipal.add(jPanelMidia, "midia");
        jPanelPrincipal.add(filme.getjPanelPrincipal(), "filme");
        jPanelPrincipal.add(musica.getjPanelPrincipal(), "musica");
        jPanelPrincipal.add(livro.getjPanelPrincipal(), "livro");
    }

    private void continuar() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String semCategoria = comboBoxCategoria.getSelectedItem().toString().substring(0, 3);
                tituloArquivo = textFieldTitulo.getText();

                if (caminhoArquivo == null || caminhoArquivo.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Um arquivo precisa ser selecionado.");
                } else if (semCategoria.equalsIgnoreCase("não") || semCategoria.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Uma categoria precisa ser selecionada.");
                } else if (tituloArquivo.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Campo título precisa ser preenchido.");
                } else {
                    categoriaArquivo = comboBoxCategoria.getSelectedItem().toString();
                    avancarTela();
                }
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
                comboBoxCategoria.addItem("Não existe nenhuma categoria para a extensão ." + extensao + ". Adicione uma no menu Categoria.");
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
        comboBoxCategoria.addItem("Importe um arquivo para ver as categorias.");
        layout.show(jPanelPrincipal, "midia");
    }

    private void avancarTela() {
        switch (this.extensaoArquivo.toUpperCase()) {
            case "MP4":
            case "MKV":
                layout.show(jPanelPrincipal, "filme");
                break;
            case "MP3":
                layout.show(jPanelPrincipal, "musica");
                break;
            case "PDF":
                layout.show(jPanelPrincipal, "livro");
                break;
        }
    }

    public String getCategoriaArquivo() {
        return categoriaArquivo;
    }

    public String getTituloArquivo() {
        return tituloArquivo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
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

    public TelaCadastroFilme getFilme() {
        return filme;
    }

    public TelaCadastroMusica getMusica() {
        return musica;
    }

    public TelaCadastroLivro getLivro() {
        return livro;
    }
}
