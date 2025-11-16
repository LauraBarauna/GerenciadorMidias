package main.view.midias.listar.telaListar;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.MidiaController;
import main.controller.PessoaController;
import main.model.midias.Midia;
import main.view.midias.listar.detalhes.midia.TelaDetalhesMidia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaListarMidia {
    private JPanel jPanelPrincipal;
    private JComboBox tipoMidias;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton duraçãoButton;
    private JButton títuloButton;
    private JButton buscarButton;
    private JPanel jPanelEscolhasBusca;
    private JPanel jPanelMidias;

    private MidiaController midiaController;
    private PessoaController pessoaController;
    private CategoriaController categoriaController;
    private IdiomaController idiomaController;

    private TelaDetalhesMidia detalhes;

    public TelaListarMidia(MidiaController midiaController, PessoaController pessoaController,
                           CategoriaController categoriaController, IdiomaController idiomaController) {
        this.midiaController = midiaController;
        this.pessoaController = pessoaController;
        this.categoriaController = categoriaController;
        this.detalhes = new TelaDetalhesMidia(midiaController, pessoaController, categoriaController, idiomaController,this);
        jPanelMidias.setLayout(new BoxLayout(jPanelMidias, BoxLayout.Y_AXIS));
        jPanelMidias.setAlignmentX(Component.CENTER_ALIGNMENT);
        selecioarTipoMidia();
    }

    private void selecioarTipoMidia() {
        tipoMidias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) tipoMidias.getSelectedItem();

                switch (tipo.toUpperCase()) {
                    case "TODOS":
                        atualizarListaMidias();
                        break;
                }

            }
        });
    }

    public void atualizarListaMidias() {
        List<Midia> midias = midiaController.listarMidias();
        listarTodasMidias(midias);
    }

    private void listarTodasMidias(List<Midia> midias) {
        jPanelMidias.removeAll();
        if (!midias.isEmpty()) {
            for (Midia midia : midias) {
                JPanel linha = new JPanel(new BorderLayout());
                linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
                JLabel lblMidia = new JLabel("TÍTULO: " + midia.getTitulo() + " - ID: " + midia.getId());
                JButton btnDetalhes = new JButton("Detalhes");

                btnDetalhes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        detalhes.carregarInfos(midia);
                        JFrame frame = new JFrame("Detalhes de Midia");
                        frame.setContentPane(detalhes.getjPanelPrincipal());
                        frame.setSize(500, 400);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setResizable(false);
                        detalhes.setJanela(frame);
                    }
                });

                linha.add(lblMidia);
                linha.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
                linha.add(btnDetalhes);

                linha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                jPanelMidias.add(linha);
            }
            jPanelMidias.revalidate();
            jPanelMidias.repaint();
        } else {
            JPanel linha = new JPanel(new BorderLayout());
            linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
            JLabel lblSemNada = new JLabel("Nenhuma midia foi cadastrada!");
            lblSemNada.setAlignmentX(Component.CENTER_ALIGNMENT);
            linha.add(lblSemNada, BorderLayout.CENTER);
            linha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            jPanelMidias.add(linha);
            jPanelMidias.revalidate();
            jPanelMidias.repaint();
        }
    }

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
