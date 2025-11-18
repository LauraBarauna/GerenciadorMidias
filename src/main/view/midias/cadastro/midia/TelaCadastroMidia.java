package main.view.midias.cadastro.midia;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.MidiaController;
import main.controller.PessoaController;
import main.excecoes.arquivo.ExtensaoInvalidaException;
import main.view.midias.cadastro.filme.TelaCadastroFilme;
import main.view.midias.cadastro.livro.TelaCadastroLivro;
import main.view.midias.cadastro.musica.TelaCadastroMusica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class TelaCadastroMidia {
    private JPanel jPanelPrincipal;
    private JPanel jPanelMidia;

    private JTextField textFieldTitulo;

    private JTextField textFieldCaminho;
    private JButton btnBuscar;
    private JLabel titulo;
    private JLabel caminho;
    private JLabel categoria;

    private JComboBox comboBoxCategoria;

    private JButton btnAdicionar;

    private String caminhoArquivo;
    private String extensaoArquivo;
    private String tituloArquivo;
    private String categoriaArquivo;
    private double tamanhoArquivo;

    private CategoriaController categoriaController;

    private TelaCadastroFilme filme;
    private TelaCadastroMusica musica;
    private TelaCadastroLivro livro;

    private CardLayout layout;
    
    /**
     * Construtor da Tela Principal de Cadastro de Mídia. Inicializa os Controllers, cria as instâncias dos sub-painéis de cadastro específicos,
     * configura o CardLayout e anexa os Listeners.
     * @param categoriaController: Controller de Categorias.
     * @param idiomaController: Controller de Idiomas.
     * @param pessoaController: Controller de Pessoas.
     * @param midiaController: Controller de Mídias.
     */

    public TelaCadastroMidia(CategoriaController categoriaController, IdiomaController idiomaController,
                             PessoaController pessoaController, MidiaController midiaController) {
        buscarCaminhoArquivo();
        this.categoriaController = categoriaController;
        this.filme = new TelaCadastroFilme(idiomaController, midiaController);
        this.musica = new TelaCadastroMusica(pessoaController, midiaController);
        this.livro = new TelaCadastroLivro(pessoaController, midiaController);

        layout = new CardLayout();
        adicionarPainel();
        continuar();
    }
    
    /**
     * Configura o jPanelPrincipal para usar o CardLayout e adiciona todos os painéis, mestre e específicos, como "cartões".
     */

    private void adicionarPainel() {
        jPanelPrincipal.setLayout(layout);

        jPanelPrincipal.add(jPanelMidia, "midia");
        jPanelPrincipal.add(filme.getjPanelPrincipal(), "filme");
        jPanelPrincipal.add(musica.getjPanelPrincipal(), "musica");
        jPanelPrincipal.add(livro.getjPanelPrincipal(), "livro");
    }
    
    /**
     * Configura o ActionListener para o botão "Adicionar" (Continuar). Este método valida se o arquivo, categoria e título foram 
     * selecionados/preenchidos antes de chamar o avancarTela().
     */

    private void continuar() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String semCategoria = comboBoxCategoria.getSelectedItem().toString().substring(0, 3);
                tituloArquivo = textFieldTitulo.getText();

                if (caminhoArquivo == null || caminhoArquivo.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Um arquivo precisa ser selecionado.");
                } else if (semCategoria.equalsIgnoreCase("não") || semCategoria.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Uma categoria precisa ser selecionada.");
                } else if (tituloArquivo.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Campo título precisa ser preenchido.");
                } else {
                    categoriaArquivo = comboBoxCategoria.getSelectedItem().toString();
                    avancarTela();
                }
            }
        });
    }
    
    /**
     * Configura o {@code ActionListener} para o botão "Buscar". Abre um JFileChooser, obtém o caminho, tamanho e extensão do arquivo selecionado,
     * atualiza o campo de caminho e chama listarCategorias().
     */

    private void buscarCaminhoArquivo() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int resposta = fileChooser.showOpenDialog(jPanelPrincipal);

                if (resposta == JFileChooser.APPROVE_OPTION) {
                    caminhoArquivo = fileChooser.getSelectedFile().getAbsolutePath();
                    tamanhoArquivo = fileChooser.getSelectedFile().length();
                    textFieldCaminho.setText(caminhoArquivo);
                    extensaoArquivo = fileChooser.getSelectedFile().getAbsolutePath().toString().substring(caminhoArquivo.length() - 3);
                    listarCategorias(extensaoArquivo);
                }

            }
        });
    }
    
    /**
     * Lista as categorias disponíveis no ComboBox com base na extensão do arquivo selecionado. Se nenhuma categoria for encontrada para a extensão, 
     * exibe uma mensagem no ComboBox.
     * @param extensao A extensão do arquivo selecionado (ex. "mp4", "pdf").
     */

    public void listarCategorias(String extensao) {
        comboBoxCategoria.removeAllItems();

        try {
            List<String> categorias = categoriaController.listarCategoriasExtensaoString(extensao);

            if (categorias.isEmpty()) {
                comboBoxCategoria.addItem("Não existe nenhuma categoria para a extensão ." + extensao + ". Adicione uma no menu Categoria.");
            } else {
                for (String categoria : categorias) {
                    comboBoxCategoria.addItem(categoria);
                }
            }
        } catch (ExtensaoInvalidaException e) {
            JOptionPane.showMessageDialog(jPanelPrincipal, e.getMessage());
        }

    }
    
    /**
     * Reseta a tela para o painel inicial de seleção de arquivo ("midia") e configura o ComboBox. Este método é chamado pela TelaMidia
     * principal ao selecionar a ação "ADICIONAR".
     */

    public void telaMidia() {
        comboBoxCategoria.addItem("Importe um arquivo para ver as categorias.");
        layout.show(jPanelPrincipal, "midia");
    }
    
    /**
     * Roteia o usuário para a tela de cadastro específica (Filme, Música ou Livro)
     * e passa os metadados do arquivo para o painel correspondente.
     */

    private void avancarTela() {
        switch (this.extensaoArquivo.toUpperCase()) {
            case "MP4":
            case "MKV":
                getFilme().setCaminhoArquivo(caminhoArquivo);
                getFilme().setTituloArquivo(tituloArquivo);
                getFilme().setCategoriaArquivo(categoriaArquivo);
                getFilme().setTamanhoArquivo(tamanhoArquivo);
                getFilme().setExtensaoArquivo(extensaoArquivo);
                layout.show(jPanelPrincipal, "filme");
                break;
            case "MP3":
                getMusica().setCaminhoArquivo(caminhoArquivo);
                getMusica().setTituloArquivo(tituloArquivo);
                getMusica().setCategoriaArquivo(categoriaArquivo);
                getMusica().setTamanhoArquivo(tamanhoArquivo);
                getMusica().setExtensaoArquivo(extensaoArquivo);
                layout.show(jPanelPrincipal, "musica");
                break;
            case "PUB":
            case "PDF":
                getLivro().setCaminhoArquivo(caminhoArquivo);
                getLivro().setTituloArquivo(tituloArquivo);
                getLivro().setCategoriaArquivo(categoriaArquivo);
                getLivro().setTamanhoArquivo(tamanhoArquivo);
                getLivro().setExtensaoArquivo(extensaoArquivo);
                layout.show(jPanelPrincipal, "livro");
                break;
        }
    }

    public String getCategoriaArquivo() {
        return categoriaArquivo;
    }

    public String getTituloArquivo() {
        return tituloArquivo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    public String getExtensaoArquivo() {
        return extensaoArquivo;
    }

    public JPanel getjPanelMidia() {
        return jPanelMidia;
    }

    public TelaCadastroFilme getFilme() {
        return filme;
    }

    public TelaCadastroMusica getMusica() {
        return musica;
    }

    public TelaCadastroLivro getLivro() {
        return livro;
    }
}
