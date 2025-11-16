package main.view.midias.cadastro.livro;

import main.controller.MidiaController;
import main.controller.PessoaController;
import main.excecoes.midia.MidiaException;
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

public class TelaCadastroLivro {
    
    private JPanel jPanelPrincipal;
    private JPanel jPanelAutores;
    private JButton cadastrarLivroButton;
    private JTextField textFieldPaginas;
    private List<Pessoa> listaPessoas;
    private PessoaController pessoaController;
    private MidiaController midiaController;
    private String caminhoArquivo;
    private String extensaoArquivo;
    private String tituloArquivo;
    private String categoriaArquivo;
    private double tamanhoArquivo;

    /**
     * Construtor da tela de cadastro de Livro.  Inicializa os Controllers, a lista de autores, configura o layout do painel de autores e anexa o ouvinte do botão de cadastro.
     * @param pessoaController: O controller para buscar autores.
     * @param midiaController: O controller para realizar o cadastro do livro.
     */
    public TelaCadastroLivro(PessoaController pessoaController, MidiaController midiaController) {
        this.listaPessoas = new ArrayList<>();
        this.pessoaController = pessoaController;
        this.midiaController = midiaController;
        jPanelAutores.setLayout(new BoxLayout(jPanelAutores, BoxLayout.Y_AXIS));
        jPanelAutores.setAlignmentX(Component.CENTER_ALIGNMENT);
        cadastrarLivro();
    }

    /**
     * Configura o ActionListener para o botão "Cadastrar Livro". Realiza validação básica da quantidade de páginas, obtém todos os dados da mídia e delega a ação de cadastro para o MidiaController, tratando exceções.
     */
    private void cadastrarLivro() {
        cadastrarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int qtdPaginas;
                try {
                     qtdPaginas = Integer.parseInt(textFieldPaginas.getText());
                } catch (NumberFormatException ex) {
                     JOptionPane.showMessageDialog(null, "Quantidade de páginas deve ser um número inteiro.");
                     return;
                }
                if (qtdPaginas <= 0 ) {
                    JOptionPane.showMessageDialog(null, "Quantidade de páginas não pode ser menor ou igual a 0.");
                    return;
                }
                try {
                    midiaController.cadastrarLivro(caminhoArquivo, tamanhoArquivo, tituloArquivo,
                            qtdPaginas, categoriaArquivo);
                } catch (MidiaException error) {
                    JOptionPane.showMessageDialog(null, error.getMessage());
                } catch (IllegalAccessError error) {
                    JOptionPane.showMessageDialog(null, error.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
            }
        });
    }

    /**
     * Solicita ao PessoaController a lista de nomes de autores/pessoas e repassa para o método de renderização.
     */
    public void atualizarLista() {
        List<String> autores = this.pessoaController.listarPessoas();
        listarAutores(autores);
    }

    /**
     * Renderiza a lista de autores no jPanelAutores. Cria dinamicamente um JPanel para cada nome de autor, incluindo um botão "Adicionar" que permite incluir o autor na listaPessoas local.
     * @param pessoasAutores: A lista de nomes de pessoas (String) a serem exibidos.
     */
    private void listarAutores(List<String> pessoasAutores) {
        jPanelAutores.removeAll();
        if (!pessoasAutores.isEmpty()) {
            for (String a : pessoasAutores) {
                JPanel linha = new JPanel(new BorderLayout());
                linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
                JLabel lblIdioma = new JLabel(a);
                JButton btnAdd = new JButton("Adicionar");
                btnAdd.addActionListener(new ActionListener() {
                    boolean jaAdicionado = false;
                    public void actionPerformed(ActionEvent e) {
                        boolean jaAdicionado = false;
                        for (Pessoa p : listaPessoas) {
                            if (p.getNome().equalsIgnoreCase(a)) {
                                jaAdicionado = true;
                                break;
                            }
                        }
                        if (jaAdicionado) {
                            JOptionPane.showMessageDialog(null, "Pessoa " + a + " já foi adicionada!");
                        } else {
                            listaPessoas.add(new Pessoa(a));
                            JOptionPane.showMessageDialog(null, "Pessoa " + a + " adicionada com sucesso!");
                            System.out.println(listaPessoas); // Debug ou log
                        }

                    }
                });
                linha.add(lblIdioma);
                linha.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
                linha.add(btnAdd);

                linha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // borda pequena
                jPanelAutores.add(linha);
            }
            jPanelAutores.revalidate();
            jPanelAutores.repaint();
        } else {
            JPanel linha = new JPanel(new BorderLayout());
            linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
            JLabel lblSemNada = new JLabel("Não existe nenhuma pessoa cadastrada. Cadastre uma no menu de Pessoa acima.");
            lblSemNada.setAlignmentX(Component.CENTER_ALIGNMENT);
            linha.add(lblSemNada, BorderLayout.CENTER);
            linha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            jPanelAutores.add(linha);
            jPanelAutores.revalidate();
            jPanelAutores.repaint();
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
     * Retorna a lista de pessoas (autores) selecionadas para este livro.
     * @return A List<Pessoa> selecionada.
     */
    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
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