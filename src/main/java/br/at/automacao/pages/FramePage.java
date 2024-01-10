package br.at.automacao.pages;

import static br.at.automacao.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.at.automacao.core.*;

import java.time.Duration;

public class FramePage extends BasePage {

	WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));

	public String validPage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div[1]/div/div/div/div[1]/div/ul/li[1]/a")));
		getDriver().switchTo().frame("singleframe");
		return obterTexto(By.xpath("//h5[text()='iFrame Demo']"));
	}

	public void writeInFrame(String texto){
		escrever(By.xpath("/html/body/section/div/div/div/input"), texto);
	}

	public String validTextWrited() {
		return obterTexto(By.xpath("//h5[text()='iFrame Demo']"));
	}


}
