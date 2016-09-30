package br.com.zup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.exception.WebCepException;
import br.com.zup.service.WebCepService;
import br.com.zup.to.EnderecoTO;
import br.com.zup.util.Constantes;

@RestController
@RequestMapping( value = Constantes.MAIN_PATH )
public class WebCepController {

	private WebCepService webCepService;
	
	@Autowired
	public WebCepController( WebCepService webCepService ) {
		this.webCepService = webCepService;
	}

	@RequestMapping( value = Constantes.CEP, method = RequestMethod.GET )
	public @ResponseBody EnderecoTO cep( @PathVariable( "cep" ) String cep ) throws WebCepException {
		try {
			return webCepService.buscaEnderecoPorCep( cep );
		} catch ( Exception ex ) {
			throw new WebCepException( ex.getMessage() );
		}
	}

}
