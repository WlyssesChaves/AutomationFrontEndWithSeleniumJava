package br.at.automacao.tests;

import br.at.automacao.core.Propriedades;
import br.at.automacao.model.User;
import br.at.automacao.pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static br.at.automacao.core.DriverFactory.killDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DesafioCyber {
	
	HomePage homePage = new HomePage();
	SlidePage slidePage = new SlidePage();
	FramePage framePage = new FramePage();
	DataPickerPage dataPickerPage = new DataPickerPage();
	RegisterPage registerPage = new RegisterPage();
	int closedTopic = 0;

	User fakeUser = new User(
			"Alex",          		 // firstName
			"Santos",                        // lastName
			"1234 Avenida Brasil",           // address
			"alex.santos@example.com",       // email
			"1111111111",                   // phone
			"Male",                          // gender
			new String[]{"Cricket", "Movies"},  // hobbies
			new String[]{"Portuguese", "English"}, // languages
			"C++",            				 // skills
			"United States of America",                         // country
			"1990",                          // birthYear
			"April",                         // birthMonth
			"15",                            // birthDay
			"s3cur3P@ssw0rd"                 // password
	);

	//REGISTRAR NOVO USUÁRIO
	@Test
	public void case1registerNewUser() throws InterruptedException {
		homePage.acessarPaginaInicial();
		registerPage.newRegister(fakeUser);
		//VALIDA RESULTADO DA BUSCA
		Assert.assertEquals("Register", registerPage.validaRegistro());
	}

	//ESCREVER NO FRAME
	@Test
	public void case2writeInFrame() throws InterruptedException {
		homePage.acessarPaginaInicial();
		homePage.clicarEmDropdown("SwitchTo","Frames");
		Assert.assertEquals("iFrame Demo", framePage.validPage());
		framePage.writeInFrame("Desafio Automação Cyber");
		Assert.assertEquals("iFrame Demo", framePage.validTextWrited());

	}

	//SET DATAPICKER
	@Test
	public void case3setDataPicker() throws InterruptedException {
		homePage.acessarPaginaInicial();
		homePage.clicarEmDropdown("Widgets", "Datepicker");
		Assert.assertEquals("DatePicker Disabled", dataPickerPage.validPage());
		dataPickerPage.selecionarDataNoDatePicker2(31,"March","1987");
		dataPickerPage.selecionarDataNoDatePicker1(31,"March","1987");
	}

	//MOVER SLIDE
	@Test
	public void case4moveSlide() throws InterruptedException {
		homePage.acessarPaginaInicial();
		homePage.clicarEmDropdown("Widgets", "Slider");
		slidePage.moverSlider(50);
	}
	
	@After
	public void finaliza() throws IOException {
		if(Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}
	
	
	
}
