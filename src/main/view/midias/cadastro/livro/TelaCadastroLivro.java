package main.view.midias.cadastro.livro;

import main.controller.IdiomaController;

import javax.swing.*;
import java.util.List;

public class TelaCadastroLivro {
    private JPanel jPanelPrincipal;
    private JComboBox<String> idiomas;
    private JButton cadastrarLivroButton;

    private IdiomaController idiomaController;

    public TelaCadastroLivro(IdiomaController idiomaController) {
        this.idiomaController = idiomaController;
    }

    public void atualizarListaIdioma() {
        List<String> idiomas = idiomaController.listarNomeIdioma();
        adicionarListaIdiomas(idiomas);
    }

    private void adicionarListaIdiomas(List<String> idiomasList) {
        if (!idiomasList.isEmpty()) {
            for (String idioma : idiomasList) {
                idiomas.addItem(idioma);
            }
        } else {
            idiomas.addItem("Não existe nenhum idioma cadastrado. Cadastre um no menu de Idioma acima.");
        }
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
