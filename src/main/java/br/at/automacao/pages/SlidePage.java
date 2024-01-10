package br.at.automacao.pages;

import br.at.automacao.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static br.at.automacao.core.DriverFactory.getDriver;

public class SlidePage extends BasePage {

	public void moverSlider(int porcentagem) {
		// Localiza o elemento slider
		WebElement slider = getDriver().findElement(By.id("slider"));
		int larguraSlider = slider.getSize().getWidth();

		// Calcula a nova posição do controle deslizante com base na porcentagem desejada
		int novaPosicao = (larguraSlider * porcentagem) / 100;

		// Localiza o controle deslizante (handle)
		WebElement sliderHandle = slider.findElement(By.className("ui-slider-handle"));

		// Cria uma instância da classe Actions
		Actions mover = new Actions(getDriver());
		// Move o controle deslizante para a nova posição
		mover.dragAndDropBy(sliderHandle, novaPosicao, 0).perform();
	}




}
