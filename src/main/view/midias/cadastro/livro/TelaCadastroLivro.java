package main.view.midias.cadastro.livro;

import main.controller.PessoaController;
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

    private List<Pessoa> listaPessoas;

    private PessoaController pessoaController;

    public TelaCadastroLivro(PessoaController pessoaController) {
        this.listaPessoas = new ArrayList<>();
        this.pessoaController = pessoaController;

        jPanelAutores.setLayout(new BoxLayout(jPanelAutores, BoxLayout.Y_AXIS));
        jPanelAutores.setAlignmentX(Component.CENTER_ALIGNMENT);
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
}
