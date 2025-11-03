package main.gerenciador;

import main.model.midias.Midia;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorMidia {
    private List<Midia> midias;

    public GerenciadorMidia() {
        this.midias = new ArrayList<>();
    }

    public void incluirMidia(Midia midia) {
        this.midias.add(midia);
    }
}
