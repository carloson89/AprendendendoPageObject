package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaoDeProdutoPage {
    private WebDriver navegador;

    public FormularioDeAdicaoDeProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public FormularioDeAdicaoDeProdutoPage informarNomeDoProduto(String produtonome) {
        navegador.findElement(By.id("produtonome")).sendKeys(produtonome);

        return this;
    }

    public FormularioDeAdicaoDeProdutoPage informarOValorDoProduto(String produtovalor) {
        navegador.findElement(By.name("produtovalor")).sendKeys(produtovalor);

        return this;
    }

    public FormularioDeAdicaoDeProdutoPage informarCoresDoProduto(String produtocores) {
        navegador.findElement(By.id("produtocores")).sendKeys(produtocores);

        return this;
    }

    public ListaDeProdutosPage SubmeterOFormularioDeAdicaoComErro() {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new ListaDeProdutosPage(navegador);
    }

    public FormularioDeEdicaoDeProdutoPage submeterFormularioDeAdicaoComSucesso () {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new FormularioDeEdicaoDeProdutoPage(navegador);
    }


}
