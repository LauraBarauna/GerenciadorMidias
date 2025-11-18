package main.view.midias.listar.detalhes.midia;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.MidiaController;
import main.controller.PessoaController;
import main.model.midias.Categoria;
import main.model.midias.Midia;
import main.model.midias.filme.Filme;
import main.model.midias.livro.Livro;
import main.model.midias.musica.Musica;
import main.model.pessoa.Pessoa;
import main.view.midias.listar.detalhes.filme.TelaDetalhesFilme;
import main.view.midias.listar.detalhes.livro.TelaDetalhesLivro;
import main.view.midias.listar.detalhes.musica.TelaDetalhesMusica;
import main.view.midias.listar.telaListar.TelaListarMidia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * @author Laura Barauna
 */

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
    private PessoaController pessoaController;

    private TelaDetalhesMusica telaDetalhesMusica;
    private TelaDetalhesFilme telaDetalhesFilme;
    private TelaDetalhesLivro telaDetalhesLivro;

    private JFrame janela;

    private Categoria categoria;

    private String tipoMidia;

    private int idMidia;
    
    /**
     * Construtor da Tela de Detalhes de Mídia. Inicializa os Controllers e os sub-painéis de detalhes (Filme e Música),
     * configura o layout do painel dinâmico e anexa os Listeners aos botões "Salvar" e "Remover".
     * @param midiaController: Controller de Mídias.
     * @param pessoaController: Controller de Pessoas.
     * @param categoriaController: Controller de Categorias.
     * @param idiomaController: Controller de Idiomas.
     * @param telaListarMidia: Referência à tela de listagem para atualização.
     */

    public TelaDetalhesMidia(MidiaController midiaController, PessoaController pessoaController,
                             CategoriaController categoriaController, IdiomaController idiomaController, TelaListarMidia telaListarMidia) {
        this.midiaController = midiaController;
        this.pessoaController = pessoaController;
        this.telaListarMidia = telaListarMidia;
        this.categoriaController = categoriaController;

        this.telaDetalhesMusica = new TelaDetalhesMusica(pessoaController);
        this.telaDetalhesFilme = new TelaDetalhesFilme(idiomaController);

        jPanelTipoMidia.setLayout(new CardLayout());
        salvarAlteracoes();
        removerMidia();
    }
    
    /**
     * Configura o ActionListener para o botão "Remover". Solicita ao MidiaController a remoção da mídia pelo ID, exibe mensagem de sucesso,
     * atualiza a lista principal e fecha a janela de detalhes.
     */

    private void removerMidia() {
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                midiaController.removerMidia(idMidia);
                JOptionPane.showMessageDialog(janela, "Midia removida com sucesso!");
                telaListarMidia.atualizarListaMidias();
                janela.dispose();
            }
        });
    }
    
    /**
     * Configura o ActionListener para o botão "Salvar". Coleta os dados dos campos gerais e dos sub-painéis, cria um novo objeto Mídia
     * (Filme, Música ou Livro) com o ID original, delega a atualização ao Controller, e fecha a janela.
     */

    private void salvarAlteracoes() {
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Categoria novaCategoria = new Categoria(categorias.getSelectedItem().toString());
                switch (tipoMidia) {
                    case "Filme":
                        midiaController.atualizarMidia(new Filme(idMidia, local.getText(),
                                Double.parseDouble(tamanho.getText()), titulo.getText(),
                                Integer.parseInt(duracao.getText()), novaCategoria, telaDetalhesFilme.getIdioma()));
                        JOptionPane.showMessageDialog(null, "Filme alterado com sucesso!");
                        telaListarMidia.atualizarListaMidias();
                        janela.dispose();
                        break;
                    case "Musica":
                        midiaController.atualizarMidia(new Musica(idMidia, local.getText(),
                                Double.parseDouble(tamanho.getText()), titulo.getText(),
                                Integer.parseInt(duracao.getText()), novaCategoria, telaDetalhesMusica.getArtista()));
                        JOptionPane.showMessageDialog(null, "Música alterada com sucesso!");
                        telaListarMidia.atualizarListaMidias();
                        janela.dispose();
                        break;
                    case "Livro":
                        Livro livro = new Livro(idMidia, local.getText(),
                                Double.parseDouble(tamanho.getText()), titulo.getText(),
                                Integer.parseInt(duracao.getText()), novaCategoria);

                        for (Pessoa p : telaDetalhesLivro.getAutoresSelecionados()) {
                            livro.incluirAutor(p);
                        }
                        midiaController.atualizarMidia(livro);
                        JOptionPane.showMessageDialog(null, "Livro alterado com sucesso!");
                        telaListarMidia.atualizarListaMidias();
                        janela.dispose();
                        break;
                }
            }
        });
    }
    
    /**
     * Preenche o ComboBox de categorias com as categorias válidas para o tipo de mídia atual.
     * @param categorias: A lista de nomes de categorias a serem exibidas.
     */

    public void atualizarListaCategoria(List<String> categorias) {
        this.categorias.removeAllItems();
        for (String c : categorias) {
            this.categorias.addItem(c);
        }

    }
    
    /**
     * Carrega todas as informações da mídia fornecida nos campos da tela. Este método determina o tipo da mídia, preenche os campos comuns, carrega as 
     * categorias e, crucialmente, carrega o sub-painel de detalhes específico (Filme, Música ou Livro).
     * @param midia: O objeto Midia a ser carregado na tela de detalhes.
     */

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
                telaDetalhesFilme.atualizarLista(midia);
                jPanelTipoMidia.removeAll();
                jPanelTipoMidia.add(telaDetalhesFilme.getjPanelPrincipal());
                jPanelTipoMidia.revalidate();
                jPanelTipoMidia.repaint();
                break;
            case "Musica":
                telaDetalhesMusica.atualizarLista(midia);
                jPanelTipoMidia.removeAll();
                jPanelTipoMidia.add(telaDetalhesMusica.getjPanelPrincipal());
                jPanelTipoMidia.revalidate();
                jPanelTipoMidia.repaint();
                break;
            case "Livro":
                this.telaDetalhesLivro = new TelaDetalhesLivro(midiaController, pessoaController, midia.getId());
                jPanelTipoMidia.removeAll();
                jPanelTipoMidia.add(telaDetalhesLivro.getjPanelPrincipal());
                jPanelTipoMidia.revalidate();
                jPanelTipoMidia.repaint();
                break;
        }

    }
    
    /**
     * Retorna o painel principal da tela.
     * @return O JPanel principal da interface.
     */

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
    
    /**
     * Define a referência do JFrame pai. É necessário para poder fechar a janela programaticamente após salvar ou remover.
     * @param janela: O JFrame que contém esta tela.
     */

    public void setJanela(JFrame janela) {
        this.janela = janela;
    }
}
