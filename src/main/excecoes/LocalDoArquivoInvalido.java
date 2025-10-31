package main.excecoes;

public class LocalDoArquivoInvalido extends RuntimeException {
    public LocalDoArquivoInvalido(String message) {
        super(message);
    }
}
