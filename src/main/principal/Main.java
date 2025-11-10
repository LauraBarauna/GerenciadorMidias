package main.principal;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.PessoaController;
import main.gerenciador.GerenciadorTela;

public class Main {
    public static void main(String[] args)  {
        CategoriaController categoriaController = new CategoriaController();
        IdiomaController idiomaController = new IdiomaController();
        PessoaController pessoaController = new PessoaController();
        new GerenciadorTela(categoriaController, idiomaController, pessoaController);
    }
}
