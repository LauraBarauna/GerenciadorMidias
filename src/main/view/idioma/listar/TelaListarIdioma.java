package main.view.idioma.listar;

import main.controller.IdiomaController;
import main.model.idioma.Idioma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class TelaListarIdioma {

    private JPanel jPanelPrincipal;
    private JButton btnRemoverTudo;
    private JPanel jPanelIdiomas;
    private IdiomaController cotroller;


    /**
     * Construtor da tela de listagem de idioma. Inicializa o Controller, configura o layout do painel de listagem e anexa o ouvinte de evento (listener) para o botão de remoção em massa.
     * @param cotroller: A instância do IdiomaController a ser usada.
     */
    public TelaListarIdioma(IdiomaController cotroller) {
        this.cotroller = cotroller;
        jPanelIdiomas.setLayout(new BoxLayout(jPanelIdiomas, BoxLayout.Y_AXIS));
        jPanelIdiomas.setAlignmentX(Component.CENTER_ALIGNMENT);
        removerTudo();
    }

    /**
     * Configura o ActionListener para o botão "Remover Tudo". Obtém a lista de idiomas, solicita confirmação do usuário e, se afirmativo, delega a remoção em massa para o Controller e atualiza a lista.
     */
    private void removerTudo() {
        btnRemoverTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               List<Idioma> idiomas = cotroller.listarIdiomas();

               if (!idiomas.isEmpty()) {
                   int opcao = Integer.parseInt(JOptionPane.showInputDialog(
                           "Tem certeza que quer remover tudo?\n" +
                                   "1 - Sim\n" +
                                   "2 - Não\n"
                   ));

                   switch (opcao) {
                       case 1:
                           cotroller.removerTudo();
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
     * Força a atualização completa da lista de idiomas exibida. Delega a busca dos nomes dos idiomas para o Controller e chama o método de renderização.
     */
    public void atualizarLista() {
        List<String> idiomas = cotroller.listarNomeIdioma();
        listarIdiomas(idiomas);
    }

    /**
     * Renderiza a lista de idiomas no jPanelIdiomas. Limpa o painel e cria dinamicamente um JPanel (linha) para cada idioma, incluindo um botão de "Remover" com seu respectivo ActionListener.
     * @param idiomas: A lista de nomes de idiomas a serem exibidos.
     */
    private void listarIdiomas(List<String> idiomas) {
        jPanelIdiomas.removeAll();

        if (!idiomas.isEmpty()) {
            for (String idioma : idiomas) {
                JPanel linha = new JPanel(new BorderLayout());
                linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
                JLabel lblIdioma = new JLabel(idioma);
                JButton btnRemover = new JButton("Remover");

                btnRemover.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            cotroller.removerIdioma(idioma);
                            JOptionPane.showMessageDialog(null, "Idioma " + idioma + " removida com sucesso!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        atualizarLista();
                    }
                });

                linha.add(lblIdioma);
                linha.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
                linha.add(btnRemover);
                linha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }
            jPanelIdiomas.revalidate();
            jPanelIdiomas.repaint();
        } else {
            JPanel linha = new JPanel(new BorderLayout());
            linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
            JLabel lblSemNada = new JLabel("Não existe nenhum idioma cadastrado!");
            lblSemNada.setAlignmentX(Component.CENTER_ALIGNMENT);
            linha.add(lblSemNada, BorderLayout.CENTER);
            linha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            jPanelIdiomas.add(linha);
            jPanelIdiomas.revalidate();
            jPanelIdiomas.repaint();
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