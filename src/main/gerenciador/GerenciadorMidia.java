package main.gerenciador;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator; // Adicionado para documentação da ordenação
import java.util.List;
import java.util.OptionalInt;
import main.model.midias.Midia;

/**
 * @author Yasmin Darlen Schneider
 * 
 */
public class GerenciadorMidia {

    private static final String DIRETORIO_DADOS = "dados_midias";
    private static final String EXTENSAO_ARQUIVO = ".tpoo";
    private List<Midia> midias;

    /**
     * Construtor da classe GerenciadorMidia. Inicializa a lista de mídias, verifica se o diretório de dados existe e carrega todos os dados persistidos do disco.
     */
    public GerenciadorMidia() {
        this.midias = new ArrayList<>();
        criarDiretorioDados();
        carregarDados();
    }

    /**
     * Verifica e cria o diretório de persistência. Se a pasta definida em DIRETORIO_DADOS não existir, ela é criada no diretório raiz do projeto para armazenar os arquivos .tpoo.
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
     * Gera o caminho de arquivo completo para uma mídia com base no seu ID.
     * * @param id: O identificador único da mídia.
     * @return Uma String contendo o caminho completo e o nome do arquivo (e.g., "dados_midias/midia_5.tpoo").
     */
    private String gerarNomeArquivo(int id) {
        return DIRETORIO_DADOS + File.separator + "midia_" + id + EXTENSAO_ARQUIVO;
    }
    
    /**
     * Calcula o próximo ID disponível e único para uma nova mídia. Garante que o ID gerado será o maior ID existente + 1, evitando colisões ao nomear os arquivos.
     * @return O próximo ID inteiro sequencial disponível.
     */
    private int proximoIdDisponivel() {
        // Encontra o maior ID na lista atual + 1. Se a lista estiver vazia, retorna 1.
        OptionalInt maxId = this.midias.stream()
                                       .mapToInt(Midia::getId)
                                       .max();
        return maxId.orElse(0) + 1;
    }

    /**
     * Salva uma única mídia no arquivo .tpoo individual usando a Serialização. Utiliza o FileOutputStream para escrever no arquivo e o ObjectOutputStream para converter o objeto Java em bytes. Se o arquivo já existe, ele é sobrescrito.
     * @param midia: O objeto Midia a ser serializado e salvo.
     */
    private void salvarMidia(Midia midia) {
        if (midia.getId() <= 0) {
             midia.setId(proximoIdDisponivel());
        }

        String nomeArquivo = gerarNomeArquivo(midia.getId());
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(midia);
            System.out.println("LOG: Mídia ID " + midia.getId() + " salva/atualizada em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao salvar mídia ID " + midia.getId() + ": " + e.getMessage());
        }
    }

    /**
     * Carrega todas as mídias do disco, lendo cada arquivo .tpoo individualmente. Percorre o diretório de dados, utiliza o FileInputStream para ler os bytes e o ObjectInputStream para reconstruir (desserializar) o objeto Java.
     */
    private void carregarDados() {
        File diretorio = new File(DIRETORIO_DADOS);
        // Filtra para pegar apenas arquivos com a extensão .tpoo
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
     * Insere uma nova mídia na lista em memória e persiste no arquivo individual.
     * * @param midia: O objeto Midia a ser incluído.
     * @return Se true a inclusão foi bem-sucedida, e false se o objeto Midia for nulo.
     */
    public boolean incluirMidia(Midia midia) {
        if (midia == null) {
        	return false;
        }
        // Atribui um ID antes de salvar para gerar o nome do arquivo.
        midia.setId(proximoIdDisponivel()); 
        this.midias.add(midia);
        salvarMidia(midia);
        return true;
    }

    /**
     * Remove uma mídia do sistema e deleta o arquivo .tpoo correspondente.
     * * @param localArquivo: O caminho de arquivo da mídia (usado como chave única para busca).
     * @return Se true a remoção e a deleção do arquivo foram bem-sucedidas, e false caso contrário.
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
     * Atualiza um objeto Midia existente e sobrescreve seu arquivo .tpoo. O sobrescrito do arquivo elimina a versão anterior, mantendo apenas a mais recente.
     * @param midiaAtualizada: O objeto Midia com os novos dados deve possuir um ID válido.
     * @return Se true a atualização foi bem-sucedida, e false a mídia não for encontrada ou for inválida.
     */
    public boolean atualizarMidia(Midia midiaAtualizada) {
        if (midiaAtualizada == null || midiaAtualizada.getId() <= 0) return false;
        
        for (int i = 0; i < this.midias.size(); i++) {
            if (this.midias.get(i).getId() == midiaAtualizada.getId()) {
                // Substituir o objeto na lista em memória
                this.midias.set(i, midiaAtualizada);
                
                // Sobrescrever o arquivo auxiliar.
                salvarMidia(midiaAtualizada);
                return true;
            }
        }
        return false;
    }

    /**
     * Busca uma mídia na lista pelo seu título.
     * * @param titulo: O título da mídia a ser buscada.
     * @return O objeto Midia encontrado (comparação *case insensitive*) ou null se não for encontrado.
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
     * Lista todas as mídias em memória, filtrando por uma categoria específica. Se o parâmetro categoria for nulo ou vazio, retorna a lista completa.
     * @param categoria: Nome da categoria (e.g., "Filme", "Musica", "Livro").
     * @return Uma List<Midia> contendo apenas os objetos que pertencem à categoria.
     */
    public List<Midia> listarPorCategoria(String categoria) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        
        if (categoria == null || categoria.isBlank()) {
            return new ArrayList<>(midias);
        }
        
        for (Midia midia : midias) {
            // Compara a categoria da mídia com a categoria buscada (simplificado)
            if (midia.getCategoria().getCategoria().equalsIgnoreCase(categoria)) {
                midiasFiltradas.add(midia);
            }
        }
        return midiasFiltradas;
    }

    /**
     * Retorna uma cópia da lista de todas as mídias em memória.
     * * @return Uma List<Midia> contendo todas as mídias cadastradas.
     */
    public List<Midia> listarTodasMidias() {
        return new ArrayList<>(this.midias);
    }
    
    /**
     * Ordena a lista de mídias pelo Título em ordem alfabética.
     * * @return Uma nova lista de mídias ordenada pelo Título.
     */
    public List<Midia> ordenarPorTitulo() {
        var lista = new ArrayList<>(midias);
        lista.sort((m1, m2) -> m1.getTitulo().compareToIgnoreCase(m2.getTitulo()));
        return lista;
    }
    
    /**
     * Ordena a lista de mídias pela Duração (minutos, segundos ou páginas) em ordem crescente.
     * * @return Uma nova lista de mídias ordenada pela Duração.
     */
    public List<Midia> ordenarPorDuracao() {
        var lista = new ArrayList<>(midias);
        // Usa Integer.compare para garantir a ordenação correta de inteiros
        lista.sort((m1, m2) -> Integer.compare(m1.getDuracao(), m2.getDuracao()));
        return lista;
    }
}