package br.com.zup;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.zup.to.EnderecoTO;

@RunWith( JUnit4.class )
public abstract class BaseControllerTest {
	
	protected EnderecoTO buildTO() {
		
		EnderecoTO endereco = new EnderecoTO();
		
		endereco.setBairro( "BAIRRO" );
		endereco.setCep( "CEP" );
		endereco.setCidade( "CIDADE" );
		endereco.setLogradouro( "LOGRADOURO" );
		endereco.setEstado( "ESTADO" );
		
		return endereco;
		
	}
	
}
