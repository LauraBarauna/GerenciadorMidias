package main.view.midias;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Classe responsável pela tela de gerenciamento de mídias.
 *
 * Essa tela contém um ComboBox com as ações possíveis (ex: Adicionar, Editar, Remover)
 * e um painel interno que troca o conteúdo conforme a ação escolhida.
 *
 * Ela utiliza o padrão de layout "CardLayout" para alternar entre diferentes telas
 * (por exemplo, entre a tela de cadastro e outras futuras).
 */
public class TelaMidia {

    /** Painel principal que agrupa todos os componentes da tela de mídia */
    private JPanel jPanelPrincipal;

    /** ComboBox que exibe as ações disponíveis (ex: Adicionar, Remover, etc.) */
    private JComboBox<String> acoesMidia;

    /** Painel dinâmico que muda conforme a ação escolhida no ComboBox */
    private JPanel jPanelAcaoMidia;

    /** Tela de cadastro de mídia (um dos possíveis "cards" do painel) */
    private TelaCadastroMidia cadastro;

    /**
     * Construtor da classe TelaMidia.
     *
     * @param acoes Lista de ações disponíveis (ex: ["Adicionar", "Remover", "Listar"])
     */
    public TelaMidia(List<String> acoes) {
        // Inicialização dos componentes
        this.jPanelPrincipal = new JPanel(new BorderLayout());
        this.jPanelAcaoMidia = new JPanel();
        this.acoesMidia = new JComboBox<>();
        this.cadastro = new TelaCadastroMidia();

        // Configuração da interface
        carregarComboBoxMidias(acoes);
        adicionarLayouts();

        // Adiciona componentes ao painel principal
        jPanelPrincipal.add(acoesMidia, BorderLayout.NORTH);
        jPanelPrincipal.add(jPanelAcaoMidia, BorderLayout.CENTER);

        // Define a primeira opção e atualiza a tela
        if (acoesMidia.getItemCount() > 0) {
            acoesMidia.setSelectedIndex(0);
        atualizarPainel();
        }

        // Atualiza dinamicamente ao trocar opção
        acoesMidia.addActionListener(e -> atualizarPainel());
    }

    /**
     * Define o layout do painel de ações como CardLayout,
     * permitindo a troca dinâmica entre diferentes telas.
     */
    public void adicionarLayouts() {
        this.jPanelAcaoMidia.setLayout(new CardLayout());
    }

    /**
     * Retorna o painel principal desta tela,
     * permitindo que outras telas (como TelaPrincipal) o adicionem.
     *
     * @return o painel principal da tela de mídia
     */
    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    /**
     * Preenche o ComboBox de ações com os valores fornecidos.
     *
     * @param acoes Lista de strings representando as ações disponíveis
     */
    private void carregarComboBoxMidias(List<String> acoes) {
        for (String a : acoes) {
            acoesMidia.addItem(a);
        }
    }

    /**
     * Atualiza o painel de ação com base na opção selecionada no ComboBox.
     *
     * Atualmente, suporta apenas a ação "ADICIONAR", mas pode ser expandido
     * para incluir outras, como "EDITAR" ou "REMOVER".
     */
    private void atualizarPainel() {
        String acaoSelecionada = acoesMidia.getSelectedItem().toString().toUpperCase();

        switch (acaoSelecionada) {
            case "ADICIONAR":
                jPanelAcaoMidia.removeAll();
                jPanelAcaoMidia.add(cadastro.getjPanelPrincipal());
                break;
            // futuro: outras opções como "LISTAR", "EDITAR", "REMOVER"
        }

        jPanelAcaoMidia.revalidate();
        jPanelAcaoMidia.repaint();
    }
}