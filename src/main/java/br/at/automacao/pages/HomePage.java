package br.at.automacao.pages;

import static br.at.automacao.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.at.automacao.core.*;

import java.time.Duration;

public class HomePage extends BasePage {

	WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));
	
	public void acessarPaginaInicial(){
		DriverFactory.getDriver().get("https://demo.automationtesting.in/Register.html");
		DriverFactory.getDriver().manage().deleteAllCookies();
	}

	public void clicarEmDropdown(String menu, String subMenu) {
		// Localiza e clica no elemento que ativa o dropdown
		WebElement dropdownSwitchTo = getDriver().findElement(By.linkText(menu));
		dropdownSwitchTo.click();

		// Clica na opção "Frames" dentro do dropdown
		WebElement opcaoFrames = getDriver().findElement(By.linkText(subMenu));
		opcaoFrames.click();

	}
	

}
