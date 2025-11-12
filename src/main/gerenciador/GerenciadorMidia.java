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
<<<<<<< HEAD
 * @author Yasmin Darlen Schneider
 * 
=======
 * Gerenciador responsável por manipular a lista de mídias em memória e persistir/carregar cada mídia em um arquivo auxiliar (.tpoo) individual,
 * simulando um sistema de persistência descentralizado ('nuvem').
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
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
<<<<<<< HEAD
     * Verifica e cria o diretório de persistência. Se a pasta definida em DIRETORIO_DADOS não existir, ela é criada no diretório raiz do projeto para armazenar os arquivos .tpoo.
=======
     * Verifica e cria o diretório onde os arquivos .tpoo serão salvos.
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
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
<<<<<<< HEAD
     * Gera o caminho de arquivo completo para uma mídia com base no seu ID.
     * * @param id: O identificador único da mídia.
     * @return Uma String contendo o caminho completo e o nome do arquivo (e.g., "dados_midias/midia_5.tpoo").
=======
     * Gera o nome completo do arquivo .tpoo para uma dada ID.
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
     */
    private String gerarNomeArquivo(int id) {
        return DIRETORIO_DADOS + File.separator + "midia_" + id + EXTENSAO_ARQUIVO;
    }
    
    /**
<<<<<<< HEAD
     * Calcula o próximo ID disponível e único para uma nova mídia. Garante que o ID gerado será o maior ID existente + 1, evitando colisões ao nomear os arquivos.
     * @return O próximo ID inteiro sequencial disponível.
     */
    private int proximoIdDisponivel() {
        // Encontra o maior ID na lista atual + 1. Se a lista estiver vazia, retorna 1.
=======
     * Gera o próximo ID disponível, garantindo que o nome do arquivo seja único.
     */
    private int proximoIdDisponivel() {
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
        OptionalInt maxId = this.midias.stream()
                                       .mapToInt(Midia::getId)
                                       .max();
        return maxId.orElse(0) + 1;
    }

    /**
<<<<<<< HEAD
     * Salva uma única mídia no arquivo .tpoo individual usando a Serialização. Utiliza o FileOutputStream para escrever no arquivo e o ObjectOutputStream para converter o objeto Java em bytes. Se o arquivo já existe, ele é sobrescrito.
     * @param midia: O objeto Midia a ser serializado e salvo.
=======
     * Salva uma ÚNICA mídia no seu arquivo .tpoo individual.
     * Sobrescreve o arquivo se ele já existir (essencial para o 'atualizarMidia').
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
     */
    private void salvarMidia(Midia midia) {
        if (midia.getId() <= 0) {
             midia.setId(proximoIdDisponivel());
        }

        String nomeArquivo = gerarNomeArquivo(midia.getId());
        
<<<<<<< HEAD
=======
        // FileOutputStream: Abrir o arquivo para escrita
        // ObjectOutputStream: Serializa o objeto (converte em bytes)
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(midia);
            System.out.println("LOG: Mídia ID " + midia.getId() + " salva/atualizada em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao salvar mídia ID " + midia.getId() + ": " + e.getMessage());
        }
    }

    /**
<<<<<<< HEAD
     * Carrega todas as mídias do disco, lendo cada arquivo .tpoo individualmente. Percorre o diretório de dados, utiliza o FileInputStream para ler os bytes e o ObjectInputStream para reconstruir (desserializar) o objeto Java.
=======
     * Carrega TODAS as mídias do diretório, lendo cada arquivo .tpoo.
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
     */
    private void carregarDados() {
        File diretorio = new File(DIRETORIO_DADOS);
        // Filtra para pegar apenas arquivos com a extensão .tpoo
        File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(EXTENSAO_ARQUIVO));
        this.midias.clear();

        if (arquivos != null) {
            for (File arquivo : arquivos) {
<<<<<<< HEAD
=======
                // FileInputStream: Abrir o arquivo para leitura
                // ObjectInputStream: Desserializa o objeto (converte bytes em objeto Java)
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
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
<<<<<<< HEAD
     * Insere uma nova mídia na lista em memória e persiste no arquivo individual.
     * * @param midia: O objeto Midia a ser incluído.
     * @return Se true a inclusão foi bem-sucedida, e false se o objeto Midia for nulo.
=======
     * Insere uma nova mídia na lista e persiste no seu arquivo individual.
     * @param midia O objeto Midia a ser incluído.
     * @return true se a inclusão foi bem-sucedida.
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
     */
    public boolean incluirMidia(Midia midia) {
        if (midia == null) {
        	return false;
        }
<<<<<<< HEAD
        // Atribui um ID antes de salvar para gerar o nome do arquivo.
=======
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
        midia.setId(proximoIdDisponivel()); 
        this.midias.add(midia);
        salvarMidia(midia);
        return true;
    }

    /**
<<<<<<< HEAD
     * Remove uma mídia do sistema e deleta o arquivo .tpoo correspondente.
     * * @param localArquivo: O caminho de arquivo da mídia (usado como chave única para busca).
     * @return Se true a remoção e a deleção do arquivo foram bem-sucedidas, e false caso contrário.
=======
     * Remove uma mídia do sistema E deleta o arquivo .tpoo correspondente (Requisito).
     * @param localArquivo O caminho de arquivo da mídia (chave única para busca).
     * @return true se a remoção foi bem-sucedida, false caso contrário.
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
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
<<<<<<< HEAD
=======
        
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
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
<<<<<<< HEAD
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
=======
     * Atualiza um objeto Midia existente e sobrescreve seu arquivo .tpoo (Deletando a versão anterior).
     * @param midiaAtualizada O objeto Midia com os novos dados.
     * @return true se a atualização foi bem-sucedida.
     */
    public boolean atualizarMidia(Midia midiaAtualizada) {
        if (midiaAtualizada == null || midiaAtualizada.getId() <= 0) return false;
        for (int i = 0; i < this.midias.size(); i++) {
            if (this.midias.get(i).getId() == midiaAtualizada.getId()) {
                this.midias.set(i, midiaAtualizada);
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
                salvarMidia(midiaAtualizada);
                return true;
            }
        }
        return false;
    }

    /**
<<<<<<< HEAD
     * Busca uma mídia na lista pelo seu título.
     * * @param titulo: O título da mídia a ser buscada.
     * @return O objeto Midia encontrado (comparação *case insensitive*) ou null se não for encontrado.
=======
     * Busca uma mídia pelo título (chave única). Usa loop for para clareza.
     * @param titulo O título da mídia a ser buscada.
     * @return O objeto Midia encontrado ou null.
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
     */
    public Midia buscarMidiaPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return null;
        }
<<<<<<< HEAD
=======
        
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
        for (Midia midia : this.midias) {
            if (midia.getTitulo().equalsIgnoreCase(titulo)) {
                return midia;
            }
        }
        return null;
    }
    
    /**
<<<<<<< HEAD
     * Lista todas as mídias em memória, filtrando por uma categoria específica. Se o parâmetro categoria for nulo ou vazio, retorna a lista completa.
     * @param categoria: Nome da categoria (e.g., "Filme", "Musica", "Livro").
     * @return Uma List<Midia> contendo apenas os objetos que pertencem à categoria.
=======
     * Lista todas as mídias filtrando por uma categoria específica. Usa o loop for para clareza (lógica simplificada).
     * @param categoria Nome da categoria (e.g., "Filme", "Musica", "Livro").
     * @return Uma lista de objetos Midia que pertencem à categoria.
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
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
<<<<<<< HEAD
     * Retorna uma cópia da lista de todas as mídias em memória.
     * * @return Uma List<Midia> contendo todas as mídias cadastradas.
=======
     * Retorna uma lista de todas as mídias (cópia).
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
     */
    public List<Midia> listarTodasMidias() {
        return new ArrayList<>(this.midias);
    }
    
    /**
<<<<<<< HEAD
     * Ordena a lista de mídias pelo Título em ordem alfabética.
     * * @return Uma nova lista de mídias ordenada pelo Título.
=======
     * Ordena as mídias pelo Título, ignorando maiúsculas/minúsculas.
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
     */
    public List<Midia> ordenarPorTitulo() {
        var lista = new ArrayList<>(midias);
        lista.sort((m1, m2) -> m1.getTitulo().compareToIgnoreCase(m2.getTitulo()));
        return lista;
    }
    
    /**
<<<<<<< HEAD
     * Ordena a lista de mídias pela Duração (minutos, segundos ou páginas) em ordem crescente.
     * * @return Uma nova lista de mídias ordenada pela Duração.
=======
     * Ordena as mídias pela Duração.
>>>>>>> 0ce8b4539d8f1b8b252bd39af59c040ea3c43a56
     */
    public List<Midia> ordenarPorDuracao() {
        var lista = new ArrayList<>(midias);
        // Usa Integer.compare para garantir a ordenação correta de inteiros
        lista.sort((m1, m2) -> Integer.compare(m1.getDuracao(), m2.getDuracao()));
        return lista;
    }
}