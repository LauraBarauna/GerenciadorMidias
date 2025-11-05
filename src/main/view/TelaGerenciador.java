package main.view;

import main.view.midias.TelaMidia;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class TelaGerenciador {
    private TelaPrincipal telaPrincipal;

    private List<JComboBox> boxes;

    private TelaMidia telaMidia;

    public TelaGerenciador() {
        this.telaMidia = new TelaMidia(adicionarAcoes());
        this.telaPrincipal = new TelaPrincipal(telaMidia);
    }

    private List<String> adicionarAcoes() {
        return Arrays.asList(
                "Adicionar",
                "Listar Todos",
                "Listar um",
                "Atualizar",
                "Remover"
        );
    }


}
