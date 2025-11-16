package main.view.midias.listar.detalhes.midia;

import main.controller.CategoriaController;
import main.controller.MidiaController;
import main.controller.PessoaController;
import main.model.midias.Categoria;
import main.model.midias.Midia;
import main.model.midias.filme.Filme;
import main.model.midias.musica.Musica;
import main.view.midias.listar.detalhes.musica.TelaDetalhesMusica;
import main.view.midias.listar.telaListar.TelaListarMidia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class TelaDetalhesMidia {
    private JPanel jPanelPrincipal;
    private JTextField titulo;
    private JTextField local;
    private JTextField tamanho;
    private JTextField duracao;
    private JComboBox categorias;
    private JPanel jPanelTipoMidia;
    private JPanel jPanelInfos;
    private JLabel lblId;
    private JButton btnSalvar;
    private JButton btnRemover;

    private TelaListarMidia telaListarMidia;
    private MidiaController midiaController;
    private CategoriaController categoriaController;

    private TelaDetalhesMusica telaDetalhesMusica;

    private JFrame janela;

    private Categoria categoria;

    private String tipoMidia;

    private int idMidia;

    public TelaDetalhesMidia(MidiaController midiaController, PessoaController pessoaController,
                             CategoriaController categoriaController, TelaListarMidia telaListarMidia) {
        this.midiaController = midiaController;
        this.telaListarMidia = telaListarMidia;
        this.categoriaController = categoriaController;
        this.telaDetalhesMusica = new TelaDetalhesMusica(pessoaController);
        jPanelTipoMidia.setLayout(new CardLayout());
        salvarAlteracoes();
    }

    private void salvarAlteracoes() {
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (tipoMidia) {
                    case "Filme":
                        break;
                    case "Musica":
                        Categoria novaCategoria = new Categoria(categorias.getSelectedItem().toString());
                        System.out.println("btn " + novaCategoria.getCategoria());
                        midiaController.atualizarMidia(new Musica(idMidia, local.getText(),
                                Double.parseDouble(tamanho.getText()), titulo.getText(),
                                Integer.parseInt(duracao.getText()), novaCategoria, telaDetalhesMusica.getArtista()));
                        JOptionPane.showMessageDialog(null, "Música alterada com sucesso!");
                        telaListarMidia.atualizarListaMidias();
                        janela.dispose();
                }
            }
        });
    }

    public void atualizarListaCategoria(List<String> categorias) {
        this.categorias.removeAllItems();
        for (String c : categorias) {
            this.categorias.addItem(c);
        }

    }

    public void carregarInfos(Midia midia) {
        if (midia instanceof Filme) {
            tipoMidia = "Filme";
        } else if (midia instanceof Musica) {
            tipoMidia = "Musica";
        } else {
            tipoMidia = "Livro";
        }

        idMidia = midia.getId();
        lblId.setText(("ID: " + idMidia));
        titulo.setText(midia.getTitulo());
        local.setText(midia.getLocal());
        tamanho.setText((String.valueOf(midia.getTamanhoEmDisco())));
        duracao.setText((String.valueOf(midia.getDuracao())));
        atualizarListaCategoria(categoriaController.listarCategoriasString(tipoMidia));
        this.categorias.setSelectedItem(midia.getCategoria().getCategoria());


        switch (tipoMidia) {
            case "Filme":
                break;
            case "Musica":
                telaDetalhesMusica.atualizarLista(midia);
                jPanelTipoMidia.removeAll();
                jPanelTipoMidia.add(telaDetalhesMusica.getjPanelPrincipal());
                jPanelTipoMidia.revalidate();
                jPanelTipoMidia.repaint();
                break;
        }

    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    public void setJanela(JFrame janela) {
        this.janela = janela;
    }
}
