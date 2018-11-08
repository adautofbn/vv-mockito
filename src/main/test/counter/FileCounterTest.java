package counter;

import analysis.UsageLog;
import choice.SimpleWordChoice;
import fileService.FileService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class FileCounterTest {

    public static final String WORD_CHOICE = "string";
    private final int WORD_STRING_QUANTITY_BOURNE = 87;
    private final String BOURNE_PATH = "./src/main/resources/bourne.txt";

    @Mock
    FileService mockFileService;

    @Spy
    UsageLog log;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Esse teste verifica que quando não existe um arquivo em FileService, o resultado de checkFileOnRemoteRepository
     * retorna false, em vez de buscar o arquivo no FileService ele procura na pasta resource.
     *
     * FileService ainda não foi implementando causando o teste a falhar com NotImplementedException. O teste deve
     * contornar esse problema e fazer a verificação da contagem da quantidade de palavras 'string' no
     * arquivo contido em "./src/main/resources/bourne.txt"
     */
    @Test
    void fileServiceSemArquivoNoRemote(){

        Mockito.doReturn(false).when(mockFileService).checkFileOnRemoteRepository(Mockito.anyString());

        FileCounter counter = new FileCounter(BOURNE_PATH, mockFileService);

        int count = counter.count(new SimpleWordChoice(WORD_CHOICE));
        Assert.assertEquals(count, WORD_STRING_QUANTITY_BOURNE);
    }

    /**
     * Esse teste verifica que quando existe um arquivo em FileService, o resultado de checkFileOnRemoteRepository
     * retorna true, o construtor de FileCounter irá buscar o arquivo no repositorio remoto através do método
     * retrieveFileFromRemote do FileService.
     *
     * FileService ainda não foi implementando. O teste deve contornar esse problema e verificar que FileCounter foi
     * implementado corretamente e faz as interações corretas com FileService. Esse teste deve verificar que:
     *
     * - Ao utilizar o construtor passando um path de arquivo. Exemplo: new FileCounter(BOURNE_PATH, ...)
     * - E quando FileService retornar 'true' para o path de arquivo passado.
     * - O método 'retrieveFileFromRemote' deve ser invocado com o mesmo path de arquivo passado no construtor.
     *
     * Não há necessidade de verificar a contagem de palavras ou fazer com que FileService retorne algum arquivo válido.
     */
    @Test
    void fileServiceComArquivoNoRemote(){
        Mockito.doReturn(true).when(mockFileService).checkFileOnRemoteRepository(Mockito.anyString());
        FileCounter counter = new FileCounter(BOURNE_PATH, mockFileService);
        Mockito.verify(mockFileService).retrieveFileFromRemote(BOURNE_PATH);
    }

    /**
     * Teste que verifica se ao chamar o método 'count' de um FileCounter a palavra passada como parâmetro é passsada
     * para o UsageLog contido dentro do FileCounter.
     *
     * Esse teste utiliza STATE_VERIFICATION. https://martinfowler.com/articles/mocksArentStubs.html
     * Logo, o estado do UsageLog deve ser verificado após o exercício do FileCounter.
     */
    @Test
    void usageLogStateVerification(){
        Mockito.doReturn(false).when(mockFileService).checkFileOnRemoteRepository(Mockito.anyString());
        FileCounter counter = new FileCounter(BOURNE_PATH, mockFileService);

        counter.setUsageLog(log);

        int count = counter.count(new SimpleWordChoice(WORD_CHOICE));

        Assert.assertTrue(log.getUsageMap().containsKey(WORD_CHOICE));
    }

    /**
     * Teste que verifica se ao chamar o método 'count' de um FileCounter a palavra passada como parâmetro é passsada
     * para o UsageLog contido dentro do FileCounter.
     *
     * Esse teste utiliza BEHAVIOUR_VERIFICATION. https://martinfowler.com/articles/mocksArentStubs.html
     * Logo, as interações entre o FileCounter e o UsageLog devem ser verificadas após o exercício do FileCounter.
     */
    @Test
    void usageLogBehaviourVerification(){
        Mockito.doReturn(false).when(mockFileService).checkFileOnRemoteRepository(Mockito.anyString());
        FileCounter counter = new FileCounter(BOURNE_PATH, mockFileService);

        counter.setUsageLog(log);

        int count = counter.count(new SimpleWordChoice(WORD_CHOICE));

        Mockito.verify(log).addStringUsage(WORD_CHOICE);
    }
}
