package main.view;

import main.view.categoria.TelaCategoria;
import main.view.midias.TelaMidia;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * Classe responsável por gerenciar as telas principais do sistema.
 *
 * A TelaGerenciador atua como um ponto central de inicialização e integração
 * das diferentes telas da aplicação, como as telas de mídia e categoria.
 *
 * Ela também define as ações básicas (como "Adicionar", "Listar", "Remover", etc.)
 * que serão usadas nos componentes visuais das telas.
 */
public class TelaGerenciador {

    /** Tela principal do sistema que conterá as demais telas */
    private TelaPrincipal telaPrincipal;

    /** Lista de combo boxes genéricas (ainda não utilizada no código atual) */
    private List<JComboBox> boxes;

    /** Tela responsável pelo gerenciamento de mídias */
    private TelaMidia telaMidia;

    /** Tela responsável pelo gerenciamento de categorias */
    private TelaCategoria telaCategoria;

    /**
     * Construtor da classe TelaGerenciador.
     *
     * Inicializa as telas de Mídia e Categoria, configurando suas
     * ações padrões (Adicionar, Listar, Atualizar, Remover, etc.)
     * e monta a tela principal que as contém.
     */
    public TelaGerenciador() {
        this.telaMidia = new TelaMidia(adicionarAcoes());
        this.telaCategoria = new TelaCategoria(adicionarAcoes());

        // Inicializa a tela principal com as telas específicas
        this.telaPrincipal = new TelaPrincipal(telaMidia, telaCategoria);
    }

    /**
     * Define e retorna a lista de ações padrão para as telas de gerenciamento.
     *
     * @return uma lista contendo as ações padrão do sistema
     */
    private List<String> adicionarAcoes() {
        return Arrays.asList(
                "Adicionar",
                "Listar Todos",
                "Listar um",
                "Atualizar",
                "Remover"
        );
    }

    /**
     * Retorna a tela principal do sistema.
     *
     * Esse método pode ser usado para exibir a tela principal em um JFrame.
     *
     * @return instância da TelaPrincipal
     */
    public TelaPrincipal getTelaPrincipal() {
        return telaPrincipal;
    }
}