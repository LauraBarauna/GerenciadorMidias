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
     * Construtor da tela de listagem de Idioma. Inicializa o Controller, configura o BoxLayout vertical para a listagem
     * e anexa o Listener para o botão "Remover Tudo".
     * @param cotroller A instância do IdiomaController a ser usada.
     */

    public TelaListarIdioma(IdiomaController cotroller) {
        this.cotroller = cotroller;
        jPanelIdiomas.setLayout(new BoxLayout(jPanelIdiomas, BoxLayout.Y_AXIS));
        jPanelIdiomas.setAlignmentX(Component.CENTER_ALIGNMENT);
        removerTudo();
    }
    
    /**
     * Configura o  ActionListener para o botão "Remover Tudo". Antes de remover, verifica se há idiomas para remover e solicita confirmação
     * via JOptionPane.showInputDialog.
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
     * Solicita a lista atualizada de nomes de idiomas ao Controller e dispara a renderização da lista. Este método é chamado sempre que a 
     * lista precisa ser recarregada.
     */

    public void atualizarLista() {
        List<String> idiomas = cotroller.listarNomeIdioma();
        listarIdiomas(idiomas);
    }
    
    /**
     * Renderiza a lista de idiomas no jPanelIdiomas. Para cada idioma, cria uma linha contendo o nome e um botão "Remover",
     * configurando o Listener para remoção individual.
     * @param idiomas: A lista de nomes (String) dos idiomas a serem exibidos.
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
                        cotroller.removerIdioma(idioma);
                        JOptionPane.showMessageDialog(null, "Idioma " + idioma + " removida com sucesso!");
                        atualizarLista();
                    }
                });

                linha.add(lblIdioma);
                linha.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
                linha.add(btnRemover);

                linha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // borda pequena
                jPanelIdiomas.add(linha);
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
