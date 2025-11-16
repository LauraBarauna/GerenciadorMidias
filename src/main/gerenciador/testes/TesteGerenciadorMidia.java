package main.gerenciador.testes;

import main.gerenciador.GerenciadorMidia;
import main.model.midias.Categoria;
import main.model.midias.Midia;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TesteGerenciadorMidia {

    private static Path tempDirOriginal;
    private static Path tempDirTeste;

    /**
     * Prepara o ambiente de teste antes de cada execução.
     *
     * Cria um diretório temporário isolado
     * Define o "user.dir" para esse diretório
     * Remove qualquer pasta 'dados_midias' existente
     *
     * Isso garante que cada teste comece sem arquivos anteriores,
     * mantendo o ambiente limpo e totalmente controlado.
     */
    @BeforeEach
    public void prepararAmbiente() throws Exception {

        tempDirOriginal = Paths.get(System.getProperty("user.dir"));

        tempDirTeste = Files.createTempDirectory("test_midias_");

        System.setProperty("user.dir", tempDirTeste.toString());

        File pasta = new File("dados_midias");

        if (pasta.exists()) {
            File[] arquivos = pasta.listFiles();
            if (arquivos != null) {
                for (File f : arquivos) {
                    f.delete();
                }
            }
            pasta.delete();
        }
    }

    /**
     * Restaura o ambiente depois de cada teste.
     *
     * Restaura o diretório original do projeto
     * Remove completamente o diretório usado nos testes
     *
     * Garante que nenhum arquivo temporário fique no computador.
     */
    @AfterEach
    public void limparAmbiente() throws Exception {

        System.setProperty("user.dir", tempDirOriginal.toString());

        if (Files.exists(tempDirTeste)) {
            Files.walk(tempDirTeste)
                    .sorted((a, b) -> b.compareTo(a))
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    /**
     * Método auxiliar que cria uma instância válida de Midia,
     * usada repetidamente nos testes.
     */
    private Midia criarMidia(String titulo, String local, int duracao) {
        return new Midia(
                1, local, 10.0,
                titulo, duracao,
                new Categoria("Ação")
        );
    }

    /**
     * Testa a inclusão de uma mídia válida.
     * O método deve retornar true, criar a pasta dados_midia, e gerar o arquivo .tpoo correspondente dentro dela.
     */
    @Test
    public void testIncluirMidia() {

        GerenciadorMidia gm = new GerenciadorMidia();

        Midia m = criarMidia("O senhor do anéis", "arquivoA.mp4", 120);

        boolean ok = gm.incluirMidia(m);
        assertTrue(ok);

        File pasta = new File("dados_midias");
        assertTrue(pasta.exists());

        assertEquals(1, pasta.listFiles().length);
    }

    /**
     * Testa o comportamento ao tentar incluir uma mídia nula.
     * Deve retornar false, sem criar arquivos.
     */
    @Test
    public void testIncluirMidiaNull() {

        GerenciadorMidia gm = new GerenciadorMidia();

        assertFalse(gm.incluirMidia(null));
    }

    /**
     * Testa a remoção de uma mídia existente.
     * Deve insere a mídia, remover pelo caminho do arquivo, verificar que o arquivo foi apagado, e garatir qeu a lista fique vazia
     */
    @Test
    public void testRemoverMidia() {

        GerenciadorMidia gm = new GerenciadorMidia();
        Midia m = criarMidia("Harry Potter", "harryPotter.mp4", 90);

        gm.incluirMidia(m);

        boolean removido = gm.removerMidia(m.getId()); // usar o ID
        assertTrue(removido);

        File pasta = new File("dados_midias");
        assertEquals(0, pasta.listFiles().length);

        assertEquals(0, gm.listarTodasMidias().size());
    }

    /**
     * Testa a tentativa de remover uma mídia que não existe.
     * Deve retornar false e não alterar nenhuma estrutura interna.
     */
    @Test
    public void testRemoverMidiaInexistente() {

        GerenciadorMidia gm = new GerenciadorMidia();
        boolean removido = gm.removerMidia(999); // ID que não existe
        assertFalse(removido);

        assertFalse(removido);
    }

    /**
     * Testa a atualização de uma mídia existente.
     * Deve retornar que a mídia é inserida, uma nova com o mesmo id, e verificar se os dados foram substituidos corretamente
     */
    @Test
    public void testAtualizarMidia() {

        GerenciadorMidia gm = new GerenciadorMidia();

        Midia m = criarMidia("Titanic", "titanic.mp4", 100);
        gm.incluirMidia(m);

        Midia nova = criarMidia("Titanic 2", "titanic.mp4", 200);
        nova.setId(m.getId());

        boolean ok = gm.atualizarMidia(nova);
        assertTrue(ok);

        assertEquals(200, gm.buscarMidiaPorTitulo("Titanic 2").getDuracao());
    }

    /**
     * Testa o comportamento quando tentar atualizar uma mídia cujo ID não está cadastrado.
     * Deve retornar false.
     */
    @Test
    public void testAtualizarMidiaInexistente() {

        GerenciadorMidia gm = new GerenciadorMidia();

        Midia m = criarMidia("X", "x.mp4", 10);
        m.setId(999);

        assertFalse(gm.atualizarMidia(m));
    }

    /**
     * Testa a busca de mídia pelo título.
     * Após incluir uma mídia,o método deve retornar um objeto não-nulo.
     */
    @Test
    public void testBuscarMidiaPorTitulo() {

        GerenciadorMidia gm = new GerenciadorMidia();
        gm.incluirMidia(criarMidia("Titanic", "titanic.mp4", 30));

        assertNotNull(gm.buscarMidiaPorTitulo("Titanic"));
    }

    /**
     * Testa o filtro por categoria.
     * Duas mídias com mesma categoria devem ser listadas corretamente.
     */
    @Test
    public void testListarPorCategoria() {

        GerenciadorMidia gm = new GerenciadorMidia();

        Categoria cat = new Categoria("Ação");

        gm.incluirMidia(new Midia(1, "007.mp4", 10, "007", 100, cat));
        gm.incluirMidia(new Midia(1, "pequenosEspiões.mp4", 10, "Pequenos espiões", 200, cat));

        assertEquals(2, gm.listarPorCategoria("Ação").size());
    }

    /**
     * Testa a ordenação por título em ordem alfabética.
     * Deve verificar se a lista retornada está corretamente ordenada.
     */
    @Test
    public void testOrdenarPorTitulo() {

        GerenciadorMidia gm = new GerenciadorMidia();

        gm.incluirMidia(criarMidia("Zebra", "z.mp4", 10));
        gm.incluirMidia(criarMidia("Macaco", "m.mp4", 10));
        gm.incluirMidia(criarMidia("Avestruz", "a.mp4", 10));

        List<Midia> lista = gm.ordenarPorTitulo();

        assertEquals("Avestruz", lista.get(0).getTitulo());
        assertEquals("Macaco", lista.get(1).getTitulo());
        assertEquals("Zebra", lista.get(2).getTitulo());
    }

    /**
     * Testa a ordenação por duração.
     * Deve verifica se a ordem da menor duração para a maior está correta.
     */
    @Test
    public void testOrdenarPorDuracao() {

        GerenciadorMidia gm = new GerenciadorMidia();

        gm.incluirMidia(criarMidia("O senhor dos anéis", "oSenhorDosAneis.mp4", 300));
        gm.incluirMidia(criarMidia("Harry Potter", "harryPotter.mp4", 100));
        gm.incluirMidia(criarMidia("Titanic", "titanic.mp4", 200));

        List<Midia> lista = gm.ordenarPorDuracao();

        assertEquals(100, lista.get(0).getDuracao());
        assertEquals(200, lista.get(1).getDuracao());
        assertEquals(300, lista.get(2).getDuracao());
    }
}
