package main.view.midias.cadastro.filme;

import main.controller.IdiomaController;
import main.controller.MidiaController;
import main.excecoes.midia.MidiaException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class TelaCadastroFilme {
    private JPanel jPanelPrincipal;
    private JComboBox<String> idiomas;
    private JButton cadastrarFilmeButton;
    private JTextField textFieldDuracao;

    private IdiomaController idiomaController;
    private MidiaController midiaController;

    private String caminhoArquivo;
    private String extensaoArquivo;
    private String tituloArquivo;
    private String categoriaArquivo;
    private double tamanhoArquivo;
    
    /**
     * Construtor da tela de cadastro de Filme. Inicializa os Controllers, atualiza a lista de idiomas disponíveis no ComboBox
     * e configura o listener para o botão de cadastro.
     * @param idiomaController: O Controller de Idiomas para listar os idiomas.
     * @param midiaController: O Controller de Mídias para realizar o cadastro.
     */

    public TelaCadastroFilme(IdiomaController idiomaController, MidiaController midiaController) {
        this.idiomaController = idiomaController;
        this.midiaController = midiaController;
        atualizarListaIdioma();
        adicionarFilme();
    }
    
    /**
     * Configura o ActionListener para o botão "Cadastrar Filme". Realiza a validação da duração, coleta os dados do formulário e os metadados
     * do arquivo, e delega o cadastro ao MidiaController.
     */

    private void adicionarFilme() {
        cadastrarFilmeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int duracao = Integer.parseInt(textFieldDuracao.getText());

                if (duracao <= 0 ) {
                    JOptionPane.showMessageDialog(null, "Duração não pode ser menor ou igual a 0.");
                    return;
                }

                try {
                    midiaController.cadastrarFilme(caminhoArquivo, tamanhoArquivo, tituloArquivo,
                            duracao, categoriaArquivo, idiomas.getSelectedItem().toString());
                } catch (MidiaException error) {
                    JOptionPane.showMessageDialog(null, error.getMessage());
                } catch (IllegalArgumentException error) {
                    JOptionPane.showMessageDialog(null, error.getMessage());
                }

                JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso!");
            }
        });
    }
    
    /**
     * Solicita a lista atualizada de nomes de Idiomas ao Controller e chama o método para preencher o ComboBox.
     */

    public void atualizarListaIdioma() {
        List<String> idiomas = idiomaController.listarNomeIdioma();
        adicionarListaIdiomas(idiomas);
    }
    
    /**
     * Preenche o JComboBox idiomas com a lista de nomes de idiomas. Se a lista estiver vazia, exibe uma mensagem informativa no ComboBox.
     * @param idiomasList A lista de nomes (String) de idiomas disponíveis.
     */

    private void adicionarListaIdiomas(List<String> idiomasList) {
        idiomas.removeAllItems();
        if (!idiomasList.isEmpty()) {
            for (String idioma : idiomasList) {
                idiomas.addItem(idioma);
            }
        } else {
            idiomas.addItem("Não existe nenhum idioma cadastrado. Cadastre um no menu de Idioma acima.");
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
