package br.com.zup.controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.zup.BaseControllerTest;
import br.com.zup.exception.WebCepException;
import br.com.zup.service.WebCepService;
import br.com.zup.service.impl.WebCepServiceImpl;
import br.com.zup.to.EnderecoTO;

public class CorreiosControllerTest extends BaseControllerTest {
	
	private CorreiosController controller;

	private WebCepService service;
	
	@Before
	public void setUp() {

		service = mock( WebCepServiceImpl.class );
		controller = new CorreiosController( service );
	}
	
	@Test
	public void buscaEndereco() {
		
		try {
			
			EnderecoTO endereco = buildTO();
			List< EnderecoTO > resultadoEsperado = new ArrayList< EnderecoTO >();
			
			resultadoEsperado.add( endereco );
			
			when( service.buscaEndereco( endereco.getCep() ) ).thenReturn( resultadoEsperado );
			
			List< EnderecoTO > resultado = controller.buscaEndereco( endereco.getCep() );
			
			assertArrayEquals( resultado.toArray(), resultadoEsperado.toArray() );
			
		} catch( Exception ex ) {
			fail( "Uma exceção foi lançada" );
		}
		
	}
	
	@Test
	public void buscaEnderecoException() {
		
		try {
			EnderecoTO endereco = buildTO();
			WebCepException excecaoEsperada = new WebCepException();
			
			when( service.buscaEndereco( endereco.getCep() ) ).thenThrow( excecaoEsperada );
			
			controller.buscaEndereco( endereco.getCep() );
			
		} catch( WebCepException wce ) {
			return;
		}
		
	}
	
}
