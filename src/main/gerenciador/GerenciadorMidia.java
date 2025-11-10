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

/**
 * Gerenciador responsável por manipular a lista de mídias em memória e persistir/carregar cada mídia em um arquivo auxiliar (.tpoo) individual,
 * simulando um sistema de persistência descentralizado ('nuvem').
 */
public class GerenciadorMidia {

    private static final String DIRETORIO_DADOS = "dados_midias";
    private static final String EXTENSAO_ARQUIVO = ".tpoo";
    
    private List<Midia> midias;

    public GerenciadorMidia() {
        this.midias = new ArrayList<>();
        criarDiretorioDados();
        carregarDados();
    }

    /**
     * Verifica e cria o diretório onde os arquivos .tpoo serão salvos.
     */
    private void criarDiretorioDados() {
        File diretorio = new File(DIRETORIO_DADOS);
        if (!diretorio.exists()) {
            if (diretorio.mkdir()) {
                System.out.println("LOG: Diretório de persistência criado: " + DIRETORIO_DADOS);
            }
        }
    }

    /**
     * Gera o nome completo do arquivo .tpoo para uma dada ID.
     */
    private String gerarNomeArquivo(int id) {
        return DIRETORIO_DADOS + File.separator + "midia_" + id + EXTENSAO_ARQUIVO;
    }
    
    /**
     * Gera o próximo ID disponível, garantindo que o nome do arquivo seja único.
     */
    private int proximoIdDisponivel() {
        OptionalInt maxId = this.midias.stream()
                                       .mapToInt(Midia::getId)
                                       .max();
        return maxId.orElse(0) + 1;
    }

    /**
     * Salva uma ÚNICA mídia no seu arquivo .tpoo individual.
     * Sobrescreve o arquivo se ele já existir (essencial para o 'atualizarMidia').
     */
    private void salvarMidia(Midia midia) {
        if (midia.getId() <= 0) {
             midia.setId(proximoIdDisponivel());
        }

        String nomeArquivo = gerarNomeArquivo(midia.getId());
        
        // FileOutputStream: Abrir o arquivo para escrita
        // ObjectOutputStream: Serializa o objeto (converte em bytes)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(midia);
            System.out.println("LOG: Mídia ID " + midia.getId() + " salva/atualizada em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao salvar mídia ID " + midia.getId() + ": " + e.getMessage());
        }
    }

    /**
     * Carrega TODAS as mídias do diretório, lendo cada arquivo .tpoo.
     */
    private void carregarDados() {
        File diretorio = new File(DIRETORIO_DADOS);
        // Filtra para pegar apenas arquivos com a extensão .tpoo
        File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(EXTENSAO_ARQUIVO));
        this.midias.clear();

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                // FileInputStream: Abrir o arquivo para leitura
                // ObjectInputStream: Desserializa o objeto (converte bytes em objeto Java)
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
     * Insere uma nova mídia na lista e persiste no seu arquivo individual.
     * @param midia O objeto Midia a ser incluído.
     * @return true se a inclusão foi bem-sucedida.
     */
    public boolean incluirMidia(Midia midia) {
        if (midia == null) {
        	return false;
        }
        midia.setId(proximoIdDisponivel()); 
        this.midias.add(midia);
        salvarMidia(midia);
        return true;
    }

    /**
     * Remove uma mídia do sistema E deleta o arquivo .tpoo correspondente (Requisito).
     * @param localArquivo O caminho de arquivo da mídia (chave única para busca).
     * @return true se a remoção foi bem-sucedida, false caso contrário.
     */
    public boolean removerMidia(String localArquivo) {
        if (localArquivo == null || localArquivo.isBlank()) {
        	return false;
        }
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
     * Atualiza um objeto Midia existente e sobrescreve seu arquivo .tpoo (Deletando a versão anterior).
     * @param midiaAtualizada O objeto Midia com os novos dados.
     * @return true se a atualização foi bem-sucedida.
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

    /**
     * Busca uma mídia pelo título (chave única). Usa loop for para clareza.
     * @param titulo O título da mídia a ser buscada.
     * @return O objeto Midia encontrado ou null.
     */
    public Midia buscarMidiaPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return null;
        }
        
        for (Midia midia : this.midias) {
            if (midia.getTitulo().equalsIgnoreCase(titulo)) {
                return midia;
            }
        }
        return null;
    }
    
    /**
     * Lista todas as mídias filtrando por uma categoria específica. Usa o loop for para clareza (lógica simplificada).
     * @param categoria Nome da categoria (e.g., "Filme", "Musica", "Livro").
     * @return Uma lista de objetos Midia que pertencem à categoria.
     */
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

    /**
     * Retorna uma lista de todas as mídias (cópia).
     */
    public List<Midia> listarTodasMidias() {
        return new ArrayList<>(this.midias);
    }
    
    /**
     * Ordena as mídias pelo Título, ignorando maiúsculas/minúsculas.
     */
    public List<Midia> ordenarPorTitulo() {
        var lista = new ArrayList<>(midias);
        lista.sort((m1, m2) -> m1.getTitulo().compareToIgnoreCase(m2.getTitulo()));
        return lista;
    }
    
    /**
     * Ordena as mídias pela Duração.
     */
    public List<Midia> ordenarPorDuracao() {
        var lista = new ArrayList<>(midias);
        lista.sort((m1, m2) -> Integer.compare(m1.getDuracao(), m2.getDuracao()));
        return lista;
    }
}