package br.at.automacao.suites;
import static br.at.automacao.core.DriverFactory.killDriver;

import java.io.IOException;

import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.at.automacao.core.Propriedades;
import br.at.automacao.tests.DesafioCyber;


@RunWith(Suite.class)
@SuiteClasses({
	DesafioCyber.class,
})

public class SuiteGeral {
	@After
	public void finaliza() throws IOException{
		if(Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}
}

