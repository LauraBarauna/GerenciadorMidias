package main.view.midias.cadastro.musica;

import main.controller.MidiaController;
import main.controller.PessoaController;
import main.excecoes.midia.MidiaException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class TelaCadastroMusica {
    private JPanel jPanelPrincipal;
    private JComboBox<String> artistas;
    private JButton cadastrarMusicaButton;
    private JTextField textFieldSegundos;

    private PessoaController pessoaController;
    private MidiaController midiaController;

    private String caminhoArquivo;
    private String extensaoArquivo;
    private String tituloArquivo;
    private String categoriaArquivo;
    private double tamanhoArquivo;
    
    /**
     * Construtor da tela de cadastro de Música. Inicializa os Controllers, atualiza a lista de artistas disponíveis no ComboBox
     * e configura o listener para o botão de cadastro.
     * @param pessoaController: O Controller de Pessoas para listar artistas.
     * @param midiaController: O Controller de Mídias para realizar o cadastro.
     */

    public TelaCadastroMusica(PessoaController pessoaController, MidiaController midiaController) {
        this.pessoaController = pessoaController;
        this.midiaController = midiaController;
        atualizarListaArtistas();
        cadastrarMusica();
    }
    
    /**
     * Configura o ActionListener para o botão "Cadastrar Música". Realiza a validação da duração (segundos), coleta os dados do formulário e os metadados
     * do arquivo, e delega o cadastro ao MidiaController.
     */

    private void cadastrarMusica() {
        cadastrarMusicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldSegundos.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Digite a duração da música em segundos!");
                    return;
                }

                int segundos = Integer.parseInt(textFieldSegundos.getText());

                if (segundos <= 0) {
                    JOptionPane.showMessageDialog(null, "Duração da música não pode ser menor ou igual a 0!");
                    return;
                }

                try {
                    midiaController.cadastrarMusica(caminhoArquivo, tamanhoArquivo,
                            tituloArquivo, segundos, categoriaArquivo, artistas.getSelectedItem().toString());
                } catch (MidiaException error) {
                    JOptionPane.showMessageDialog(null, error.getMessage());
                } catch (IllegalAccessError error) {
                    JOptionPane.showMessageDialog(null, error.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Música cadastrada com sucesso!");
            }
        });
    }
    
    /**
     * Solicita a lista atualizada de Pessoas/Artistas ao Controller e chama o método para preencher o ComboBox.
     */

    public void atualizarListaArtistas() {
        List<String> artistas = pessoaController.listarPessoas();
        adicionarArtistas(artistas);
    }
    
    /**
     * Preenche o JComboBox artistas com a lista de nomes de Pessoas. Se a lista estiver vazia, exibe uma mensagem informativa no ComboBox.
     * @param artistas A lista de nomes (String) de artistas disponíveis.
     */

    private void adicionarArtistas(List<String> artistas) {
        this.artistas.removeAllItems();

        if (!artistas.isEmpty()) {
            for (String a : artistas) {
                this.artistas.addItem(a);
            }
        } else {
            this.artistas.addItem("Não existe nenhuma pessoa cadastrada. Cadastre um no menu de Pessoa acima.");
        }
    }
    
    /**
     * Retorna o painel principal da tela.
     * @return O JPanel principal da interface.
     */

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
    

    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public void setCategoriaArquivo(String categoriaArquivo) {
        this.categoriaArquivo = categoriaArquivo;
    }

    public void setTituloArquivo(String tituloArquivo) {
        this.tituloArquivo = tituloArquivo;
    }

    public void setExtensaoArquivo(String extensaoArquivo) {
        this.extensaoArquivo = extensaoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }
}
