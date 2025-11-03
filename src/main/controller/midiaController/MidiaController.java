package main.controller.midiaController;

import main.excecoes.arquivo.ExtensaoInvalidaException;

import javax.swing.*;
import java.io.File;

public class MidiaController {

    private File arquivoMidia;
    private String extensaoMidia;

    public MidiaController() {
        importarMidia();
    }

    public void importarMidia() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            this.arquivoMidia = chooser.getSelectedFile();
            setExtensaoMidia(getArquivoMidia().getName().substring(getArquivoMidia().getName().lastIndexOf(".") + 1));
        } else {
            this.arquivoMidia = null;
        }

    }

    public String getCaminhoMidia(File arquivo) {
        return arquivo.getAbsolutePath();
    }

    public double getTamanhoMidia(File arquivo) {
        return arquivo.length();
    }

    public File getArquivoMidia() {
        return arquivoMidia;
    }

    public String getExtensaoMidia() {
        return extensaoMidia;
    }

    public void setExtensaoMidia(String extensaoMidia) {
        if (!extensaoMidia.equalsIgnoreCase("mp4")
                && !extensaoMidia.equalsIgnoreCase("mkv")
                && !extensaoMidia.equalsIgnoreCase("mp3")
                && !extensaoMidia.equalsIgnoreCase("pdf")
                && !extensaoMidia.equalsIgnoreCase("epub")) {
            throw new ExtensaoInvalidaException(extensaoMidia);
        }
        this.extensaoMidia = extensaoMidia;
    }
}
