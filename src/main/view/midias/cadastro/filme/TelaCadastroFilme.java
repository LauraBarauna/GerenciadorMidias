package main.view.midias.cadastro.filme;

import main.controller.IdiomaController;

import javax.swing.*;
import java.util.List;

public class TelaCadastroFilme {
    private JPanel jPanelPrincipal;
    private JComboBox<String> idiomas;
    private JButton cadastrarFilmeButton;

    private IdiomaController idiomaController;

    public TelaCadastroFilme(IdiomaController idiomaController) {
        this.idiomaController = idiomaController;
    }

    public void atualizarListaIdioma() {
        List<String> idiomas = idiomaController.listarNomeIdioma();
        adicionarListaIdiomas(idiomas);
    }

    private void adicionarListaIdiomas(List<String> idiomas) {
        if (!idiomas.isEmpty()) {
            for (String idioma : idiomas) {
                idiomas.add(idioma);
            }
        } else {
            idiomas.add("Não existe nenhum idioma cadastrado. Cadastre um no menu de Idioma acima.");
        }
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
