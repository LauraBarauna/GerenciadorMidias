package main.gerenciador;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import main.model.midias.Midia;

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
                System.out.println("LOG: Diretório de dados criado: " + DIRETORIO_DADOS);
            }
        }
    }
    
    private String gerarNomeArquivo(int id) {
        return DIRETORIO_DADOS + File.separator + "midia_" + id + EXTENSAO_ARQUIVO;
    }

    private int proximoIdDisponivel() {
        OptionalInt maxId = this.midias.stream()
                                       .mapToInt(Midia::getId)
                                       .max();
        return maxId.orElse(0) + 1;
    }

    /**
     * Salva uma ÚNICA mídia no seu arquivo .tpoo individual.
     * Este método sobrescreve o arquivo se ele já existir.
     */
    private void salvarMidia(Midia midia) {
        if (midia.getId() <= 0) {
             midia.setId(proximoIdDisponivel());
        }

        String nomeArquivo = gerarNomeArquivo(midia.getId());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(midia);
            System.out.println("LOG: Mídia ID " + midia.getId() + " salva em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao salvar mídia ID " + midia.getId() + ": " + e.getMessage());
        }
    }

    /**
     * Carrega TODAS as mídias do diretório DIRETORIO_DADOS, lendo cada arquivo .tpoo.
     */
    private void carregarDados() {
        File diretorio = new File(DIRETORIO_DADOS);
        File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(EXTENSAO_ARQUIVO));
        this.midias.clear();

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                    Midia midia = (Midia) ois.readObject();
                    this.midias.add(midia);
                } catch (FileNotFoundException e) {
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("ERRO: Falha ao carregar arquivo " + arquivo.getName() + " (Corrompido?): " + e.getMessage());
                }
            }
        }
        System.out.println("LOG: " + this.midias.size() + " mídias carregadas da persistência.");
    }

    /**
     * Insere uma nova mídia na lista e persiste no seu arquivo individual (.tpoo).
     */
    public boolean incluirMidia(Midia midia) {
        if (midia == null) return false;
        midia.setId(proximoIdDisponivel()); 
        this.midias.add(midia);
        salvarMidia(midia);
        return true;
    }

    /**
     * Remove uma mídia do sistema, e deleta o arquivo .tpoo correspondente.
     * @param localArquivo O caminho de arquivo da mídia (chave única para busca).
     */
    public boolean removerMidia(String localArquivo) {
        if (localArquivo == null || localArquivo.isBlank()) return false;
        Midia midiaRemover = this.midias.stream()
                .filter(m -> m.getLocal().equalsIgnoreCase(localArquivo))
                .findFirst()
                .orElse(null);

        if (midiaRemover == null) {
            return false;
        }
        String nomeArquivo = gerarNomeArquivo(midiaRemover.getId());
        Path caminhoArquivo = Paths.get(nomeArquivo);
        
        try {
            boolean arquivoDeletado = Files.deleteIfExists(caminhoArquivo);
            if (arquivoDeletado) {
                System.out.println("LOG: Arquivo " + nomeArquivo + " deletado com sucesso.");
            }
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao deletar arquivo " + nomeArquivo + ". Remoção da lista cancelada.");
            return false;
        }

        this.midias.remove(midiaRemover);
        return true;
    }
    
    /**
     * Atualiza um objeto Midia existente na lista e sobrescreve seu arquivo .tpoo.
     * @param midiaAtualizada O objeto Midia com os novos dados.
     */
    public boolean atualizarMidia(Midia midiaAtualizada) {
        if (midiaAtualizada == null || midiaAtualizada.getId() <= 0) return false;

        for (int i = 0; i < this.midias.size(); i++) {
            if (this.midias.get(i).getId() == midiaAtualizada.getId()) {
                this.midias.set(i, midiaAtualizada);
                salvarMidia(midiaAtualizada);
                return true;
            }
        }
        return false;
    }

}