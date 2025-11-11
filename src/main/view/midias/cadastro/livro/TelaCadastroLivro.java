package main.view.midias.cadastro.livro;

import main.controller.PessoaController;

import javax.swing.*;
import java.util.List;

public class TelaCadastroLivro {
    private JPanel jPanelPrincipal;
    private JComboBox<String> autores;
    private JButton cadastrarLivroButton;

    private PessoaController pessoaController;

    public TelaCadastroLivro(PessoaController pessoaController) {
        this.pessoaController = pessoaController;
    }

    public void atualizarListaIdioma() {
        List<String> autores = pessoaController.listarPessoas();
        adicionarListAutores(autores);
    }

    private void adicionarListAutores(List<String> autoresList) {
        autores.removeAllItems(); // limpa o combo antes de adicionar novos itens

        if (!autoresList.isEmpty()) {
            for (String artista : autoresList) {
                autores.addItem(artista); // adiciona ao JComboBox
            }
        } else {
            autores.addItem("Não existe nenhum artista cadastrado. Cadastre um no menu de Pessoa acima.");
        }
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
