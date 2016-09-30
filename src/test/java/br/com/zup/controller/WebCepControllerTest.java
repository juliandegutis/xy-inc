package br.com.zup.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.zup.BaseControllerTest;
import br.com.zup.exception.WebCepException;
import br.com.zup.service.WebCepService;
import br.com.zup.service.impl.WebCepServiceImpl;
import br.com.zup.to.EnderecoTO;

public class WebCepControllerTest extends BaseControllerTest {
	
	private WebCepController controller;

	private WebCepService service;
	
	@Before
	public void setUp() {

		service = mock( WebCepServiceImpl.class );
		controller = new WebCepController( service );
	}
	
	@Test
	public void cep() {
		
		try {
			
			EnderecoTO enderecoEsperado = buildTO();
			
			when( service.buscaEnderecoPorCep( enderecoEsperado.getCep() ) ).thenReturn( enderecoEsperado );
			
			EnderecoTO resultado = controller.cep( enderecoEsperado.getCep() );
			
			assertEquals( enderecoEsperado, resultado );
			
		} catch( Exception e ) {
			fail( "An exception is thrown" );
		}
		
	}
	
	@Test
	public void cepException() {
		
		try {
			
			WebCepException excecaoEsperada = new WebCepException();
			
			when( service.buscaEnderecoPorCep( "CEP" ) ).thenThrow( excecaoEsperada );
			
			controller.cep( "CEP" );
			
			
		} catch( WebCepException wce ) {
			return;
		}
		
	}
	
}
