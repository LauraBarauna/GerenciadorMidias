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

    public TelaCadastroLivro(PessoaController pessoaController, MidiaController midiaController) {
        this.listaPessoas = new ArrayList<>();
        this.pessoaController = pessoaController;
        this.midiaController = midiaController;

        jPanelAutores.setLayout(new BoxLayout(jPanelAutores, BoxLayout.Y_AXIS));
        jPanelAutores.setAlignmentX(Component.CENTER_ALIGNMENT);
        cadastrarLivro();
    }

    private void cadastrarLivro() {
        cadastrarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int qtdPaginas = Integer.parseInt(textFieldPaginas.getText());

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

    public void atualizarLista() {
        List<String> autores = this.pessoaController.listarPessoas();
        listarAutores(autores);
    }

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
                            System.out.println(listaPessoas);
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
            JLabel lblSemNada = new JLabel("Não existe nenhuma pessoa cadastrado. Cadastre uma no menu de Pessoa acima.");
            lblSemNada.setAlignmentX(Component.CENTER_ALIGNMENT);
            linha.add(lblSemNada, BorderLayout.CENTER);
            linha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            jPanelAutores.add(linha);
            jPanelAutores.revalidate();
            jPanelAutores.repaint();
        }

    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

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
