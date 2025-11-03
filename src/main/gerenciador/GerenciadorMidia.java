package main.gerenciador;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import main.model.midias.Midia;
import main.model.midias.filme.Filme;
import main.model.midias.livro.Livro;
import main.model.midias.musica.Musica;

public class GerenciadorMidia {
    private static final String DIRETORIO_DADOS = "dados_midias";
    private static final String EXTENSAO_ARQUIVO = ".tpoo";
    private List<Midia> midias;

    public GerenciadorMidia() {
        this.midias = new ArrayList<>();
        criarDiretorioDados();
        carregarDados();
    }

    private void criarDiretorioDados() {
        File diretorio = new File(DIRETORIO_DADOS);
        if (!diretorio.exists()) {
            if (diretorio.mkdir()) {
                System.out.println("Diretório de dados criado: " + DIRETORIO_DADOS);
            }
        }
    }

    private String gerarNomeArquivo(int id) {
        return DIRETORIO_DADOS + File.separator + "midia_" + id + EXTENSAO_ARQUIVO;
    }

    private void salvarMidia(Midia midia) {
        String nomeArquivo = gerarNomeArquivo(midia.getId());
        
        try {
            File arquivo = new File(nomeArquivo);
            FileOutputStream fos = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(midia);
            
            oos.close();
            
            System.out.println("Mídia salva: " + nomeArquivo);
            
        } catch (IOException e) {
            System.err.println("Falha ao salvar mídia ID " + midia.getId() + ": " + e.getMessage());
        }
    }

    public void carregarDados() {
        midias.clear();
        File diretorio = new File(DIRETORIO_DADOS);
        
        if (!diretorio.exists() || !diretorio.isDirectory()) {
            System.out.println("Diretório de dados não encontrado. Iniciando com lista vazia.");
            return;
        }
        
        File[] arquivos = diretorio.listFiles();
        
        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum arquivo de mídia encontrado.");
            return;
        }
        
        for (File arquivo : arquivos) {
            if (arquivo.isFile() && arquivo.getName().endsWith(EXTENSAO_ARQUIVO)) {
                try {
                    FileInputStream fis = new FileInputStream(arquivo);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Midia midia = (Midia) ois.readObject();
                    midias.add(midia);
                    ois.close();
                    
                } catch (FileNotFoundException e) {
                    System.err.println("Arquivo não encontrado: " + arquivo.getName());
                } catch (IOException e) {
                    System.err.println("Erro ao ler arquivo " + arquivo.getName() + ": " + e.getMessage());
                } catch (ClassNotFoundException e) {
                    System.err.println("Classe não encontrada ao ler " + arquivo.getName() + ": " + e.getMessage());
                }
            }
        }
        
        System.out.println("Dados carregados. Total: " + midias.size() + " mídias.");
    }
    
    public void incluirMidia(Midia midia) {
        if (midia == null) {
            throw new IllegalArgumentException("Mídia não pode ser nula.");
        }
        for (Midia m : midias) {
            if (m.getId() == midia.getId()) {
                throw new IllegalArgumentException("Já existe uma mídia com o ID: " + midia.getId());
            }
        }
        
        midias.add(midia);
        salvarMidia(midia);
    }
    public boolean removerMidia(String localArquivo) {
        if (localArquivo == null || localArquivo.isBlank()) {
            return false;
        }
        Midia midiaRemover = null;
        for (Midia midia : midias) {
            if (midia.getLocal().equalsIgnoreCase(localArquivo)) {
                midiaRemover = midia;
                break;
            }
        }
        if (midiaRemover != null) {
            midias.remove(midiaRemover);
            excluirArquivoMidia(midiaRemover.getId());
            return true;
        }
        return false;
    }
    private void excluirArquivoMidia(int id) {
        File arquivo = new File(gerarNomeArquivo(id));
        if (arquivo.exists()) {
            if (arquivo.delete()) {
                System.out.println("Arquivo excluído: " + arquivo.getName());
            } else {
                System.err.println("Falha ao excluir arquivo: " + arquivo.getName());
            }
        }
    }
    public boolean moverMidia(String localAntigo, String localNovo) {
        if (localAntigo == null || localAntigo.isBlank() || localNovo == null || localNovo.isBlank()) {
            return false;
        }
        Midia midia = null;
        for (Midia m : midias) {
            if (m.getLocal().equalsIgnoreCase(localAntigo)) {
                midia = m;
                break;
            }
        }
        if (midia == null) {
            return false;
        }
        try {
            File arquivoOrigem = new File(localAntigo);
            File arquivoDestino = new File(localNovo);
            
            if (!arquivoOrigem.exists()) {
                System.err.println("Arquivo não existe: " + localAntigo);
                return false;
            }
            Files.move(arquivoOrigem.toPath(), arquivoDestino.toPath(), 
                StandardCopyOption.REPLACE_EXISTING);
            midia.setLocal(localNovo);
            salvarMidia(midia);
            return true;
            
        } catch (IOException e) {
            System.err.println("Erro ao mover arquivo: " + e.getMessage());
            return false;
        }
    }
    public boolean renomearArquivo(String localAntigo, String novoNome) {
        if (localAntigo == null || localAntigo.isBlank() || novoNome == null || novoNome.isBlank()) {
            return false;
        }
        Midia midia = null;
        for (Midia m : midias) {
            if (m.getLocal().equalsIgnoreCase(localAntigo)) {
                midia = m;
                break;
            }
        }
        if (midia == null) {
            return false;
        }
        
        try {
            File arquivoOrigem = new File(localAntigo);
            
            if (!arquivoOrigem.exists()) {
                System.err.println("Arquivo não existe: " + localAntigo);
                return false;
            }
            String diretorio = arquivoOrigem.getParent();
            String novoLocal = diretorio + File.separator + novoNome;
            File arquivoDestino = new File(novoLocal);
            if (arquivoOrigem.renameTo(arquivoDestino)) {
                midia.setLocal(novoLocal);
                salvarMidia(midia);
                return true;
            }
            return false;
            
        } catch (Exception e) {
            System.err.println("Erro ao renomear arquivo: " + e.getMessage());
            return false;
        }
    }
    public List<Midia> listarPorFormato(String formato) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        
        if (formato == null || formato.isBlank()) {
            return new ArrayList<>(midias);
        }
        String formatoUpper = formato.toUpperCase();
        for (Midia midia : midias) {
            switch (formatoUpper) {
                case "FILME":
                    if (midia instanceof Filme) {
                        midiasFiltradas.add(midia);
                    }
                    break;
                case "MUSICA":
                case "MÚSICA":
                    if (midia instanceof Musica) {
                        midiasFiltradas.add(midia);
                    }
                    break;
                case "LIVRO":
                    if (midia instanceof Livro) {
                        midiasFiltradas.add(midia);
                    }
                    break;
            }
        }
        return midiasFiltradas;
    }
    public List<Midia> listarPorCategoria(String categoria) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        
        if (categoria == null || categoria.isBlank()) {
            return new ArrayList<>(midias);
        }
        for (Midia midia : midias) {
            if (midia.getCategoria().getCategoria().equalsIgnoreCase(categoria)) {
                midiasFiltradas.add(midia);
            }
        }
        return midiasFiltradas;
    }
    public List<Midia> ordenarPorTitulo() {
        List<Midia> midiasOrdenadas = new ArrayList<>(midias);
        midiasOrdenadas.sort(Comparator.comparing(Midia::getTitulo, String.CASE_INSENSITIVE_ORDER));
        return midiasOrdenadas;
    }
    public List<Midia> ordenarPorDuracao() {
        List<Midia> midiasOrdenadas = new ArrayList<>(midias);
        midiasOrdenadas.sort(Comparator.comparingInt(Midia::getDuracao));
        return midiasOrdenadas;
    }
    public List<Midia> listarTodasMidias() {
        return new ArrayList<>(midias);
    }
}