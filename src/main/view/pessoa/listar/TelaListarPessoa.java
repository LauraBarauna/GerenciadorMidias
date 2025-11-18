package main.view.pessoa.listar;

import main.controller.PessoaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class TelaListarPessoa {
    private JPanel jPanelPrincipal;
    private JButton btnRemoverTudo;
    private JPanel jPanelPessoas;

    private PessoaController controller;

    public TelaListarPessoa(PessoaController controller) {
        this.controller = controller;
        jPanelPessoas.setLayout(new BoxLayout(jPanelPessoas, BoxLayout.Y_AXIS));
        jPanelPessoas.setAlignmentX(Component.CENTER_ALIGNMENT);
        removerTudo();
    }

    private void removerTudo() {
        btnRemoverTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> pessoas = controller.listarPessoas();

                if (!pessoas.isEmpty()) {
                    int opcao = Integer.parseInt(JOptionPane.showInputDialog(
                            "Tem certeza que quer remover tudo?\n" +
                                    "1 - Sim\n" +
                                    "2 - Não\n"
                    ));

                    switch (opcao) {
                        case 1:
                            controller.removerTodasPessoas();
                            atualizarLista();
                            break;
                        case 2:
                            atualizarLista();
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Você não tem nada para remover");
                    atualizarLista();
                }

            }
        });
    }

    public void atualizarLista() {
        List<String> pessoas = controller.listarPessoas();
        listarPessoas(pessoas);
    }

    private void listarPessoas(List<String> pessoas) {
        jPanelPessoas.removeAll();

        if (!pessoas.isEmpty()) {
            for (String p : pessoas) {
                JPanel linha = new JPanel(new BorderLayout());
                linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
                JLabel lblIdioma = new JLabel(p);
                JButton btnRemover = new JButton("Remover");

                btnRemover.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        controller.removerPessoa(p);
                        JOptionPane.showMessageDialog(null, "Pessoa " + p + " removida com sucesso!");
                        atualizarLista();
                    }
                });

                linha.add(lblIdioma);
                linha.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
                linha.add(btnRemover);

                linha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // borda pequena
                jPanelPessoas.add(linha);
            }
            jPanelPessoas.revalidate();
            jPanelPessoas.repaint();
        } else {
            JPanel linha = new JPanel(new BorderLayout());
            linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
            JLabel lblSemNada = new JLabel("Não existe nenhuma pessoa cadastrada!");
            lblSemNada.setAlignmentX(Component.CENTER_ALIGNMENT);
            linha.add(lblSemNada, BorderLayout.CENTER);
            linha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            jPanelPessoas.add(linha);
            jPanelPessoas.revalidate();
            jPanelPessoas.repaint();
        }
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
