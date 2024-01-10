package br.at.automacao.pages;

import br.at.automacao.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static br.at.automacao.core.DriverFactory.getDriver;

public class DataPickerPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

	public String validPage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("datepicker1")));
		return obterTexto(By.xpath("/html/body/section/div[1]/div/div/form/div[1]/div[1]/label"));
	}


	public void selecionarDataNoDatePicker1(int dia, String mes, String ano) {
		// Abre o DatePicker
		WebElement datePickerInput = getDriver().findElement(By.id("datepicker1"));
		datePickerInput.click();

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));

		boolean dataEncontrada = false;

		while (!dataEncontrada) {
			// Localiza o título do mês e ano
			String mesAtual = getDriver().findElement(By.className("ui-datepicker-month")).getText();
			String anoAtual = getDriver().findElement(By.className("ui-datepicker-year")).getText();

			// Verifica se o mês e ano atuais correspondem ao desejado
			if (mesAtual.equals(mes) && anoAtual.equals(ano)) {
				dataEncontrada = true;
			} else {
				// Clica no botão "Prev" para ir para o mês anterior
				getDriver().findElement(By.className("ui-datepicker-prev")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
			}
		}

		// Seleciona o dia
		getDriver().findElement(By.xpath(String.format("//a[text()='%d']", dia))).click();
	}

	public void selecionarDataNoDatePicker2(int dia, String mes, String ano) {
		// Abre o DatePicker
		WebElement datePickerInput = getDriver().findElement(By.id("datepicker2"));
		datePickerInput.click();

		// Espera até que o DatePicker esteja visível
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[2]/div")));

		// Seleciona o mês
		WebElement mesDropdown = getDriver().findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/select[1]"));
		Select selectMes = new Select(mesDropdown);
		selectMes.selectByVisibleText(mes);

		WebElement anoDropdown;
		Select selectAno;
		boolean anoDisponivel;

		do {
			// Localiza o dropdown de anos e cria a instância Select
			anoDropdown = getDriver().findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/select[2]"));
			anoDropdown.click(); // Abre o dropdown de anos
			selectAno = new Select(anoDropdown);

			// Verifica se o ano desejado está disponível
			anoDisponivel = selectAno.getOptions().stream().anyMatch(option -> option.getText().equals(ano));

			if (!anoDisponivel) {
				// Relocaliza o botão de navegação de anos
				WebElement botaoNavegacaoAno = getDriver().findElement(By.cssSelector("body > div.datepick-popup > div > div.datepick-month-row > div > div > select:nth-child(2) > option:nth-child(1)"));
				botaoNavegacaoAno.click(); // Clica para navegar para anos anteriores
				wait.until(ExpectedConditions.stalenessOf(anoDropdown)); // Aguarda a atualização do dropdown
			}
		} while (!anoDisponivel);

		// Seleciona o ano
		selectAno.selectByVisibleText(ano);

		// Seleciona o dia
		WebElement diaElemento = getDriver().findElement(By.xpath(String.format("//a[text()='%d']", dia)));
		diaElemento.click();
	}

	
	

}
