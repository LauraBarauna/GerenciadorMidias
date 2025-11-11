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
    }

    public void atualizarListaArtista() {
        List<String> artistas = pessoaController.listarPessoas();
        adicionarListaArtistas(artistas);
    }

    private void adicionarListaArtistas(List<String> artistasList) {
        artistas.removeAllItems(); // limpa o combo antes de adicionar novos itens

        if (!artistasList.isEmpty()) {
            for (String artista : artistasList) {
                artistas.addItem(artista); // adiciona ao JComboBox
            }
        } else {
            artistas.addItem("Não existe nenhum artista cadastrado. Cadastre um no menu de Pessoa acima.");
        }
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
