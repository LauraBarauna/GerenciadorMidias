package main.principal;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.MidiaController;
import main.controller.PessoaController;
import main.gerenciador.GerenciadorTela;

public class Main {
    public static void main(String[] args)  {
        CategoriaController categoriaController = new CategoriaController();
        IdiomaController idiomaController = new IdiomaController();
        PessoaController pessoaController = new PessoaController();
        MidiaController midiaController = new MidiaController();
        new GerenciadorTela(categoriaController, idiomaController, pessoaController, midiaController);
    }
}
