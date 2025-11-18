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

/**
 * @author Laura Barauna
 */

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
    
    /**
     * Construtor da Tela de Detalhes de Livro. Inicializa os Controllers, recupera a instância de Livro pelo ID,
     * configura os layouts verticais e inicia a listagem dos autores.
     * @param midiaController: O Controller de Mídias (usado para buscar o Livro pelo ID).
     * @param pessoaController: O Controller de Pessoas (usado para listar autores disponíveis).
     * @param id: O ID da Mídia (Livro) a ser detalhada.
     */

    public TelaDetalhesLivro(MidiaController midiaController, PessoaController pessoaController, int id) {
        this.pessoaController = pessoaController;

        this.autoresSelecionados = new ArrayList<>();

        this.titulo = titulo;
        this.livro = (Livro) midiaController.buscarMidiaPorId(id);
        layouts();
        atualizarAutoresSelecionados();
    }
    
    /**
     * Configura o layout dos painéis de listagem. Define o BoxLayout verticalmente para jPanelAutoresSelecionados e
     * jPanelNovosAutores, e associa-os aos seus respectivos JScrollPanes.
     */

    private void layouts() {
        jPanelAutoresSelecionados.setLayout(new BoxLayout(jPanelAutoresSelecionados, BoxLayout.Y_AXIS));
        jPanelAutoresSelecionados.setAlignmentX(Component.CENTER_ALIGNMENT);

        jPanelNovosAutores.setLayout(new BoxLayout(jPanelNovosAutores, BoxLayout.Y_AXIS));
        jPanelNovosAutores.setAlignmentX(Component.CENTER_ALIGNMENT);

        jScrollPaneNovosAutores.setViewportView(jPanelNovosAutores);
        jScrollPaneAutores.setViewportView(jPanelAutoresSelecionados);
    }
    
    /**
     * Método principal para sincronizar o estado dos autores. Limpa a lista temporária autoresSelecionados e a preenche com os autores atuais do livro,
     * chama o método para recalcular os autores novos/disponíveis e, finalmente, renderiza a lista de autores selecionados.
     */

    public void atualizarAutoresSelecionados() {
        autoresSelecionados.clear();
        autoresSelecionados.addAll(livro.getAutores());
        atualizarAutoresNovos();
        listarAutoresSelecionados();
    }
    
    /**
     * Calcula quais Pessoas cadastradas estão disponíveis para serem adicionadas como autores (Autores Novos). 
     * Filtra a lista total de Pessoas, removendo aquelas que já estão em autoresSelecionados.
     * O resultado é então enviado para o método de renderização.
     */

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
    
    /**
     * Renderiza a lista de autores disponíveis no jPanelNovosAutores. Para cada autor, cria um botão "Adicionar" que, ao ser clicado,
     * adiciona o autor ao livro e atualiza a interface.
     * @param autoresNovos A lista de Pessoa disponíveis para serem autores.
     */

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
    
    /**
     * Renderiza a lista de autores atualmente associados ao livro no jPanelAutoresSelecionados. Para cada autor, cria um botão "Remover" 
     * que, ao ser clicado, remove o autor da lista temporária e do objeto Livro, e atualiza a interface.
     */

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
    
    /**
     * Retorna o painel principal (container) desta sub-tela.
     * @return O JPanel principal da interface.
     */

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
    
    /**
     * Retorna a lista atual de autores que foram selecionados para o livro. Este método é chamado pela TelaDetalhesMidia quando o usuário 
     * clica em "Salvar" para obter a lista final de autores a serem persistidos.
     * @return A List de Pessoa dos Autores selecionados.
     */

    public List<Pessoa> getAutoresSelecionados() {
        return autoresSelecionados;
    }
}
