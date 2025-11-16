package main.view.pessoa.listar;

import main.controller.PessoaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Laura Barauna
 */


public class TelaListarPessoa {
    private JPanel jPanelPrincipal;
    private JButton btnRemoverTudo;
    private JPanel jPanelPessoas;

    private PessoaController controller;
    
    /**
     * Construtor da tela de listagem de Pessoa. Inicializa o Controller, configura o layout do painel de listagem para ser vertical e anexa o ouvinte de evento (listener) para o botão de remoção em massa.
     * @param controller: A instância do PessoaController a ser usada.
     */

    public TelaListarPessoa(PessoaController controller) {
        this.controller = controller;
        jPanelPessoas.setLayout(new BoxLayout(jPanelPessoas, BoxLayout.Y_AXIS));
        jPanelPessoas.setAlignmentX(Component.CENTER_ALIGNMENT);
        removerTudo();
    }
    
    /**
     * Configura o ActionListener para o botão "Remover Tudo". Obtém a lista de pessoas, solicita confirmação do usuário e, se afirmativo,delega a remoção em massa para o Controller e atualiza a lista.
     */

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
    
    /**
     * Força a atualização completa da lista de pessoas exibida. Delega a busca dos nomes das pessoas para o Controller e chama o método de renderização.
     */

    public void atualizarLista() {
        List<String> pessoas = controller.listarPessoas();
        listarPessoas(pessoas);
    }
    
    /**
     * Renderiza a lista de pessoas no jPanelPessoas. Limpa o painel e cria dinamicamente um JPanel (linha) para cada pessoa, incluindo um botão de "Remover" com seu respectivo ActionListener.
     * @param pessoas: A lista de nomes de pessoas (String) a serem exibidas.
     */

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
    
    /**
     * Retorna o painel principal da tela.
     * @return O JPanel principal da interface.
     */

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
