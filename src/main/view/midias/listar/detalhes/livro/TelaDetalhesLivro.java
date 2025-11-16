package main.view.midias.listar.detalhes.livro;

import main.controller.MidiaController;
import main.controller.PessoaController;
import main.model.midias.livro.Livro;
import main.model.pessoa.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaDetalhesLivro {
    private JPanel jPanelPrincipal;
    private JPanel jPanelAutoresSelecionados;
    private JPanel jPanelNovosAutores;
    private JScrollPane jScrollPaneNovosAutores;
    private JScrollPane jScrollPaneAutores;

    private PessoaController pessoaController;

    private List<Pessoa> autoresSelecionados;

    private Livro livro;
    private String titulo;

    public TelaDetalhesLivro(MidiaController midiaController, PessoaController pessoaController, String titulo) {
        this.pessoaController = pessoaController;

        this.autoresSelecionados = new ArrayList<>();

        this.titulo = titulo;
        this.livro = (Livro) midiaController.buscarMidiaPorTitulo(this.titulo);
        layouts();
        atualizarAutoresSelecionados();
    }

    private void layouts() {
        jPanelAutoresSelecionados.setLayout(new BoxLayout(jPanelAutoresSelecionados, BoxLayout.Y_AXIS));
        jPanelAutoresSelecionados.setAlignmentX(Component.CENTER_ALIGNMENT);

        jPanelNovosAutores.setLayout(new BoxLayout(jPanelNovosAutores, BoxLayout.Y_AXIS));
        jPanelNovosAutores.setAlignmentX(Component.CENTER_ALIGNMENT);

        jScrollPaneNovosAutores.setViewportView(jPanelNovosAutores);
        jScrollPaneAutores.setViewportView(jPanelAutoresSelecionados);
    }

    public void atualizarAutoresSelecionados() {
        autoresSelecionados.clear();
        autoresSelecionados.addAll(livro.getAutores());
        atualizarAutoresNovos();
        listarAutoresSelecionados();
    }

    private void atualizarAutoresNovos() {
        List<Pessoa> autoresNovos = new ArrayList<>();
        List<Pessoa> autores = this.pessoaController.getPessoas();

        for (Pessoa p : autores) {
            boolean encontrado = false;
            for (Pessoa sel : autoresSelecionados) {
                if (sel.getNome().equalsIgnoreCase(p.getNome())) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                autoresNovos.add(p);
            }
        }
        listarAutoresNovos(autoresNovos);
    }


    private void listarAutoresNovos(List<Pessoa> autoresNovos) {
        jPanelNovosAutores.removeAll();
        for (Pessoa autor : autoresNovos) {
            JPanel linha = new JPanel(new BorderLayout());
            linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
            JLabel lblAutor = new JLabel(autor.getNome());
            JButton btnAdicionar = new JButton("Adicionar");

            btnAdicionar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    livro.incluirAutor(autor);
                    atualizarAutoresSelecionados();
                }
            });

            linha.add(lblAutor);
            linha.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
            linha.add(btnAdicionar);

            linha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // borda pequena
            jPanelNovosAutores.add(linha);
        }
        jPanelNovosAutores.revalidate();
        jPanelNovosAutores.repaint();
    }

    private void listarAutoresSelecionados() {
        jPanelAutoresSelecionados.removeAll();
        for (Pessoa autor : this.autoresSelecionados) {
            JPanel linha = new JPanel(new BorderLayout());
            linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
            JLabel lblAutor = new JLabel(autor.getNome());
            JButton btnRemover = new JButton("Remover");

            btnRemover.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    autoresSelecionados.remove(autor);
                    livro.removerAutor(autor.getNome());
                    atualizarAutoresSelecionados();
                }
            });

            linha.add(lblAutor);
            linha.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
            linha.add(btnRemover);

            linha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // borda pequena
            jPanelAutoresSelecionados.add(linha);
        }
        jPanelAutoresSelecionados.revalidate();
        jPanelAutoresSelecionados.repaint();

    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    public List<Pessoa> getAutoresSelecionados() {
        return autoresSelecionados;
    }
}
