package modulos.produto;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName(" Testes Web do Modulo de produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        // Abrir o nevegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers web\\chromedriver_win32//chromedriver.exe");
        this.navegador = new ChromeDriver();

        // vou maximizar o nevegador
        this.navegador.manage().window().maximize();

        // vou definir um tempo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // nevegar para a página da loginha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");

    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {

        // fazer login
        String mensagemToast = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Mouse")
                .informarOValorDoProduto("000")
                .informarCoresDoProduto("Preto, Branco")
                .SubmeterOFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);
    }

    @Test
    @DisplayName("Nao e permitido registar um produto com valor maior que 7 mil")
    public void TestNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMil() {
        String mensagemToast = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Celular")
                .informarOValorDoProduto("800000")
                .informarCoresDoProduto("Azul")
                .SubmeterOFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);
    }

    @Test
    @DisplayName("Posso adicionar produtos na faixa permitida")
    public void TestpossoAdicionarProdutosNafaixaDeValorPermitida() {
            String mensagemToast = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Teclado")
                .informarOValorDoProduto("10000")
                .informarCoresDoProduto("Preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

            Assertions.assertEquals("Produto adicionado com sucesso", mensagemToast);

    }

    @AfterEach
    public void afterEach() {
        // vou fechar o navegador
         navegador.quit();

    }
}
