package main.view.midias.listar.detalhes.filme;

import main.controller.IdiomaController;
import main.model.idioma.Idioma;
import main.model.midias.Midia;
import main.model.midias.filme.Filme;

import javax.swing.*;
import java.util.List;

public class TelaDetalhesFilme {
    private JPanel jPanelPrincipal;
    private JComboBox<String> idiomas;

    private IdiomaController idiomaController;

    public TelaDetalhesFilme(IdiomaController idiomaController) {
        this.idiomaController = idiomaController;
    }

    public void atualizarLista(Midia midia) {
        List<String> idiomas = idiomaController.listarNomeIdioma();

        Filme filme = (Filme) midia;

        this.idiomas.setSelectedItem(filme.getIdioma().exibirIdioma());
        listarIdiomas(idiomas);
    }

    private void listarIdiomas(List<String> idiomas) {
        this.idiomas.removeAll();
        for (String idioma : idiomas) {
            this.idiomas.addItem(idioma);
        }
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    public Idioma getIdioma() {
        return new Idioma((String) this.idiomas.getSelectedItem());
    }
}
