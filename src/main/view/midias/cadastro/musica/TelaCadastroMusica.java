package main.view.midias.cadastro.musica;

import main.controller.MidiaController;
import main.controller.PessoaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCadastroMusica {
    private JPanel jPanelPrincipal;
    private JComboBox<String> artistas;
    private JButton cadastrarMusicaButton;
    private JTextField textFieldSegundos;

    private PessoaController pessoaController;
    private MidiaController midiaController;

    private String caminhoArquivo;
    private String extensaoArquivo;
    private String tituloArquivo;
    private String categoriaArquivo;
    private double tamanhoArquivo;

    public TelaCadastroMusica(PessoaController pessoaController, MidiaController midiaController) {
        this.pessoaController = pessoaController;
        this.midiaController = midiaController;
        atualizarListaArtistas();
        cadastrarMusica();

    }

    private void cadastrarMusica() {
        cadastrarMusicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldSegundos.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Digite a duração da música em segundos!");
                    return;
                }

                int segundos = Integer.parseInt(textFieldSegundos.getText());

                if (segundos <= 0) {
                    JOptionPane.showMessageDialog(null, "Duração da música não pode ser menor ou igual a 0!");
                    return;
                }

                midiaController.cadastrarMusica(caminhoArquivo, tamanhoArquivo,
                        tituloArquivo, segundos, categoriaArquivo, artistas.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null, "Música cadastrada com sucesso!");
            }
        });
    }

    public void atualizarListaArtistas() {
        List<String> artistas = pessoaController.listarPessoas();
        adicionarArtistas(artistas);
    }

    private void adicionarArtistas(List<String> artistas) {
        this.artistas.removeAllItems();

        if (!artistas.isEmpty()) {
            for (String a : artistas) {
                this.artistas.addItem(a);
            }
        } else {
            this.artistas.addItem("Não existe nenhuma pessoa cadastrada. Cadastre um no menu de Pessoa acima.");
        }
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public void setCategoriaArquivo(String categoriaArquivo) {
        this.categoriaArquivo = categoriaArquivo;
    }

    public void setTituloArquivo(String tituloArquivo) {
        this.tituloArquivo = tituloArquivo;
    }

    public void setExtensaoArquivo(String extensaoArquivo) {
        this.extensaoArquivo = extensaoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }
}
