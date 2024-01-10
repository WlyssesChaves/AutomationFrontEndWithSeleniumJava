package br.at.automacao.pages;

import static br.at.automacao.core.DriverFactory.getDriver;

import br.at.automacao.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.at.automacao.core.*;

import java.time.Duration;
import java.util.List;

public class RegisterPage extends BasePage {
	
	WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));
	
	public void newRegister(User user)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Register')]")));
		escrever(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[1]/div[1]/input"),user.getFirstName());
		escrever(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[1]/div[2]/input"),user.getLastName());
		escrever(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[2]/div/textarea"),user.getAddress());
		escrever(By.xpath("//*[@id=\"eid\"]/input"),user.getEmail());
		escrever(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[4]/div/input"),user.getPhone());
		//Gender
		if (user.getGender().equalsIgnoreCase("male")) {
			selecionarRadioButton(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[5]/div/label[1]/input"));
		} else if (user.getGender().equalsIgnoreCase("female")) {
			selecionarRadioButton(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[5]/div/label[2]/input"));
		}
		//hobbies
		List<WebElement> checkboxesHobbies = obterElementosCheckboxHobbies();
		for (String hobby : user.getHobbies()) {
			selecionarCheckboxPorTexto(checkboxesHobbies, hobby);
		}
		//Languages
		for (String idioma : user.getLanguages()) {
			selecionarMultiselect(idioma);
		}
		//Skills
		selecionarComboPorTexto("Skills", user.getSkills());
		//Country
		// Adiciona a opção no combobox de países
		adicionarOpcaoCountry("Brazil", "Brazil");
		adicionarOpcaoCountry("United States of America", "United States of America");
		// Agora seleciona a opção adicionada
		selecionarComboPorTexto("countries", user.getCountry());
		selecionarComboPorTexto("country", user.getCountry());
		//Date Of Birth
		selecionarComboPorTexto("yearbox", user.getBirthYear());
		selecionarComboPorTexto(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[11]/div[2]/select"), user.getBirthMonth());
		selecionarComboPorTexto("daybox", user.getBirthDay());
		//Password
		escrever("firstpassword",user.getPassword());
		//Confirm Password
		escrever("secondpassword",user.getPassword());
		//Submit
		clicarBotao("submitbtn");
	}

	
	public String validaRegistro()
	{
		return obterTexto(By.xpath("//h2[contains(text(),'Register')]"));
	}
	
	

}
