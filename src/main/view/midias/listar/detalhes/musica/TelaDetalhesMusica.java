package main.view.midias.listar.detalhes.musica;

import main.controller.PessoaController;
import main.model.midias.Midia;
import main.model.midias.musica.Musica;
import main.model.pessoa.Pessoa;

import javax.swing.*;
import java.util.List;

public class TelaDetalhesMusica {
    private JPanel jPanelPrincipal;
    private JComboBox<String> artistas;

    private PessoaController pessoaController;

    public TelaDetalhesMusica(PessoaController pessoaController) {
        this.pessoaController = pessoaController;
    }

    public void atualizarLista(Midia midia) {
        List<String> artistas = this.pessoaController.listarPessoas();

        Musica musica = (Musica) midia;
        listarArtistas(artistas, musica);
    }

    private void listarArtistas(List<String> artistas, Musica musica) {
        this.artistas.removeAllItems();

        for (String artista : artistas) {
            this.artistas.addItem(artista);
        }
        this.artistas.setSelectedItem(musica.getArtista().getNome());
    }

    public Pessoa getArtista() {
        return new Pessoa(artistas.getSelectedItem().toString());
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
