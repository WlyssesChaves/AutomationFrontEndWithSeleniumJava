package br.at.automacao.core;

import static br.at.automacao.core.DriverFactory.getDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class BasePage {
	
	/********* TextField e TextArea ************/
	
	public void escrever(By by, String texto) {
		getDriver().findElement(by).sendKeys(Keys.BACK_SPACE);
		getDriver().findElement(by).sendKeys(texto);
	}

	public void escrever(String id_campo, String texto){
		escrever(By.id(id_campo), texto);
	}
	
	/********* Botao ************/
	
	public void clicarBotao(By by) {
		getDriver().findElement(by).click();
	}
	
	public void clicarBotao(String id) {
		clicarBotao(By.id(id));
	}
	

	/********* Textos ************/
	
	public String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	public boolean contemTexto(By by, String texto) {
		return getDriver().findElement(by).getText().contains(texto);

	}
	
	public boolean contemTexto(String id, String texto) {
		return contemTexto(By.id(id),texto);

	}

	/********* Radio Button ************/

	public void selecionarRadioButton(By by) {
		WebElement radioButton = getDriver().findElement(by);
		if (!radioButton.isSelected()) {
			radioButton.click();
		}
	}

	public void selecionarRadioButton(String id) {
		selecionarRadioButton(By.id(id));
	}


	/********* Checkbox ************/

	public void selecionarCheckboxPorTexto(List<WebElement> checkboxes, String textoHobby) {
		for (WebElement checkbox : checkboxes) {
			WebElement label = checkbox.findElement(By.xpath("following-sibling::label"));
			if (label.getText().trim().equals(textoHobby) && !checkbox.isSelected()) {
				checkbox.click();
				break;
			}
		}
	}

	public List<WebElement> obterElementosCheckboxHobbies() {
		// XPath para localizar os checkboxes de hobbies
		String xpathParaHobbies = "//label[contains(text(), 'Hobbies')]/following-sibling::div//input[@type='checkbox']";
		return getDriver().findElements(By.xpath(xpathParaHobbies));
	}


	/********* Combobox e Multiselect ************/

	public void selecionarCombo(By by, String valor) {
		Select combo = new Select(getDriver().findElement(by));
		combo.selectByValue(valor);
	}

	public void selecionarCombo(String id, String valor) {
		selecionarCombo(By.id(id), valor);
	}

	public void selecionarComboPorTexto(By by, String textoVisivel) {
		Select combo = new Select(getDriver().findElement(by));
		combo.selectByVisibleText(textoVisivel);
	}

	public void selecionarComboPorTexto(String id, String textoVisivel) {
		selecionarComboPorTexto(By.id(id), textoVisivel);
	}

	public void selecionarComboPorIndex(By by, int indice) {
		Select combo = new Select(getDriver().findElement(by));
		combo.selectByIndex(indice);
	}


	public void selecionarMultiselect(String idioma) {
		// Clicar na div para abrir a lista de opções
		WebElement multiselectDiv = getDriver().findElement(By.id("msdd"));
		multiselectDiv.click();

		// Esperar até que as opções estejam visíveis
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.ui-autocomplete li a")));

		// Selecionar o idioma
		List<WebElement> opcoesIdiomas = getDriver().findElements(By.cssSelector("ul.ui-autocomplete li a"));
		for (WebElement opcao : opcoesIdiomas) {
			if (opcao.getText().equalsIgnoreCase(idioma)) {
				opcao.click();
				break;
			}
		}
	}


	public void adicionarOpcaoCountry(String valor, String texto) {
		WebElement selectElement = getDriver().findElement(By.id("countries"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();

		String script = "var novaOpcao = document.createElement('option');" +
				"novaOpcao.text = '" + texto + "';" +
				"novaOpcao.value = '" + valor + "';" +
				"arguments[0].appendChild(novaOpcao);";

		jsExecutor.executeScript(script, selectElement);
	}



}
