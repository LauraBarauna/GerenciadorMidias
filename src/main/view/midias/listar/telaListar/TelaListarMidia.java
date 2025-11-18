package main.view.midias.listar.telaListar;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.MidiaController;
import main.controller.PessoaController;
import main.excecoes.midia.MidiaException;
import main.model.midias.Midia;
import main.view.midias.listar.detalhes.midia.TelaDetalhesMidia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class TelaListarMidia {
    private JPanel jPanelPrincipal;
    private JComboBox tipoMidias;
    private JTextField titulo;
    private JComboBox<String> categorias;
    private JButton btnOrdenarDuracao;
    private JButton btnOrdenarTitulo;
    private JButton btnBuscar;
    private JPanel jPanelEscolhasBusca;
    private JPanel jPanelMidias;

    private MidiaController midiaController;
    private PessoaController pessoaController;
    private CategoriaController categoriaController;
    private IdiomaController idiomaController;

    private TelaDetalhesMidia detalhes;
    
    /**
     * Construtor da tela de listagem de Mídias. Inicializa os Controllers, a tela de detalhes e configura os layouts e todos os listeners.
     * @param midiaController: Controller de Mídias.
     * @param pessoaController: Controller de Pessoas.
     * @param categoriaController: Controller de Categorias.
     * @param idiomaController: Controller de Idiomas.
     */

    public TelaListarMidia(MidiaController midiaController, PessoaController pessoaController,
                           CategoriaController categoriaController, IdiomaController idiomaController) {
        this.midiaController = midiaController;
        this.pessoaController = pessoaController;
        this.categoriaController = categoriaController;
        this.detalhes = new TelaDetalhesMidia(midiaController, pessoaController, categoriaController, idiomaController,this);
        jPanelMidias.setLayout(new BoxLayout(jPanelMidias, BoxLayout.Y_AXIS));
        jPanelMidias.setAlignmentX(Component.CENTER_ALIGNMENT);
        selecionarTipoMidia();
        selecionarCategoria();
        ordernarPorDuracao();
        ordenarPorTitulo();
        buscarPorTitulo();
    }
    
    /**
     * Configura o ActionListener para o botão de ordenar por Título.
     * Chama o método de ordenação do Controller e atualiza a lista exibida.
     */

    private void ordenarPorTitulo() {
        btnOrdenarTitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Midia> midias = midiaController.ordenarPorTitulo();
                listarMidias(midias);
            }
        });
    }
    
    /**
     * Configura o ActionListener para o botão de ordenar por Duração/Páginas.
     * Chama o método de ordenação do Controller e atualiza a lista exibida.
     */

    private void ordernarPorDuracao() {
        btnOrdenarDuracao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Midia> midias = midiaController.ordernarPorDuracao();
                listarMidias(midias);
            }
        });
    }
    
    /**
     * Configura o ActionListener para o botão de Buscar (por Título).
     * Valida a entrada, chama o método de busca do Controller e exibe a mídia encontrada ou uma mensagem de erro.
     */
    
    private void buscarPorTitulo() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titulo.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Campo título precisa ser preenchido!");
                    return;
                }
                
                try {
                    Midia midiaEncontrada = midiaController.buscarMidiaPorTitulo(titulo.getText());
                    listarUmaMidia(midiaEncontrada);
                } catch (MidiaException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            }
        });
    }
    
    /**
     * Configura o ActionListener para o ComboBox de Categorias.
     * Filtra a lista de mídias pela categoria selecionada e atualiza a exibição.
     */

    private void selecionarCategoria() {
        categorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object sel = categorias.getSelectedItem();
                if (sel == null) return; // evita NullPointerException

                String categoria = sel.toString();
                try {
                    List<Midia> midias = midiaController.listarPorCategoria(categoria);
                    listarMidias(midias);
                } catch (MidiaException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                    atualizarListaMidias();
                }
            }
        });
    }
    
    /**
     * Preenche o ComboBox de categorias com as categorias válidas para o tipo de mídia selecionado.
     * @param tipo: O tipo de mídia (ex "FILMES", "LIVROS", "MÚSICAS", "TODOS").
     */

    private void atualizarCategoria(String tipo) {
        List<String> catFilmes = categoriaController.listarCategoriasString("Filme");
        List<String> catLivros = categoriaController.listarCategoriasString("Livro");
        List<String> catMusicas = categoriaController.listarCategoriasString("Musica");


        switch (tipo) {
            case "TODOS":
                this.categorias.removeAllItems();
                for (String filme : catFilmes) {
                    this.categorias.addItem(filme);
                }
                for (String livro : catLivros) {
                    this.categorias.addItem(livro);
                }
                for (String musica : catMusicas) {
                    this.categorias.addItem(musica);
                }
                break;
            case "FILMES":
                this.categorias.removeAllItems();
                for (String filme : catFilmes) {
                    this.categorias.addItem(filme);
                }
                break;
            case "LIVROS":
                this.categorias.removeAllItems();
                for (String livro : catLivros) {
                    this.categorias.addItem(livro);
                }
                break;
            case "MÚSICAS":
                this.categorias.removeAllItems();
                for (String musica : catMusicas) {
                    this.categorias.addItem(musica);
                }
                break;
        }
    }
    
    /**
     * Configura o ActionListener para o ComboBox de Tipo de Mídia.
     * Aciona a atualização da lista e das categorias disponíveis com base na seleção.
     */

    private void selecionarTipoMidia() {
        tipoMidias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) tipoMidias.getSelectedItem();

                switch (tipo.toUpperCase()) {
                    case "TODOS":
                        atualizarCategoria(tipo.toUpperCase());
                        atualizarListaMidias();
                        break;
                    case "FILMES":
                        atualizarCategoria(tipo.toUpperCase());
                        atualizarListaFilmes();
                        break;
                    case "MÚSICAS":
                        atualizarCategoria(tipo.toUpperCase());
                        atualizarListaMusica();
                        break;
                    case "LIVROS":
                        atualizarCategoria(tipo.toUpperCase());
                        atualizarListaLivros();
                        break;
                }

            }
        });
    }
    
    /**
     * Atualiza a lista exibida para mostrar apenas Filmes.
     */

    public void atualizarListaFilmes() {
        List<Midia> filmes = midiaController.listarFilmes();
        listarMidias(filmes);
    }
    
    /**
     * Atualiza a lista exibida para mostrar apenas Músicas.
     */

    public void atualizarListaMusica() {
        List<Midia> musicas = midiaController.listarMusicas();
        listarMidias(musicas);
    }
    
    /**
     * Atualiza a lista exibida para mostrar apenas Livros.
     */

    public void atualizarListaLivros() {
        List<Midia> livros = midiaController.listarLivros();
        listarMidias(livros);
    }
    
    /**
     * Atualiza a lista exibida para mostrar todas as Mídias cadastradas.
     */

    public void atualizarListaMidias() {
        List<Midia> midias = midiaController.listarMidias();
        listarMidias(midias);
    }
    
    /**
     * Exibe apenas uma única mídia no painel de listagem (usado após a busca por título).
     * @param midia: A mídia a ser exibida.
     */
    
    private void listarUmaMidia(Midia midia) {
        jPanelMidias.removeAll();
        Listar(midia);
        jPanelMidias.revalidate();
        jPanelMidias.repaint();
    }
    
    /**
     * Cria e adiciona um painel de linha para uma mídia específica. Inclui o título, ID e o botão "Detalhes".
     * @param midia: A mídia a ser renderizada.
     */

    private void Listar(Midia midia) {
        JPanel linha = new JPanel(new BorderLayout());
        linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
        JLabel lblMidia = new JLabel("TÍTULO: " + midia.getTitulo() + " - ID: " + midia.getId());
        JButton btnDetalhes = getJButton(midia);

        linha.add(lblMidia);
        linha.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
        linha.add(btnDetalhes);

        linha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanelMidias.add(linha);
    }
    
    /**
     * Cria o botão "Detalhes" e configura seu ActionListener para abrir uma nova janela com os detalhes da mídia.
     * @param midia: A mídia cujos detalhes serão exibidos.
     * @return O botão "Detalhes" configurado.
     */

    private JButton getJButton(Midia midia) {
        JButton btnDetalhes = new JButton("Detalhes");

        btnDetalhes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                detalhes.carregarInfos(midia);
                JFrame frame = new JFrame("Detalhes de Midia");
                frame.setContentPane(detalhes.getjPanelPrincipal());
                frame.setSize(500, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                detalhes.setJanela(frame);
            }
        });
        return btnDetalhes;
    }
    
    /**
     * Renderiza a lista de mídias no jPanelMidias. Limpa o painel e itera sobre a lista para criar os componentes de linha.
     * Se a lista estiver vazia, exibe uma mensagem.
     * @param midias: A lista de objetos Midia a serem exibidos.
     */

    private void listarMidias(List<Midia> midias) {
        jPanelMidias.removeAll();
        if (!midias.isEmpty()) {
            for (Midia midia : midias) {
                Listar(midia);
            }
        } else {
            JPanel linha = new JPanel(new BorderLayout());
            linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
            JLabel lblSemNada = new JLabel("Nenhuma midia foi cadastrada!");
            lblSemNada.setAlignmentX(Component.CENTER_ALIGNMENT);
            linha.add(lblSemNada, BorderLayout.CENTER);
            linha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            jPanelMidias.add(linha);
        }
        jPanelMidias.revalidate();
        jPanelMidias.repaint();
    }
    
    /**
     * Retorna o painel principal da tela.
     * @return O JPanel principal da interface.
     */

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
