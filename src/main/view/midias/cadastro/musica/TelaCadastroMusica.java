package main.view.midias.cadastro.musica;

import main.controller.PessoaController;

import javax.swing.*;
import java.util.List;

public class TelaCadastroMusica {
    private JPanel jPanelPrincipal;
    private JComboBox<String> artistas;
    private JButton cadastrarMusicaButton;

    private PessoaController pessoaController;

    public TelaCadastroMusica(PessoaController pessoaController) {
        this.pessoaController = pessoaController;
        atualizarListaArtistas();
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
}
