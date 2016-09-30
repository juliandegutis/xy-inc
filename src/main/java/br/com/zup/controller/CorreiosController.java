package br.com.zup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.exception.WebCepException;
import br.com.zup.service.WebCepService;
import br.com.zup.to.EnderecoTO;
import br.com.zup.util.Constantes;

@RestController
@RequestMapping( value = Constantes.CORREIOS )
public class CorreiosController {

	private WebCepService webCepService;
	
	@Autowired
	public CorreiosController( WebCepService webCepService ) {
		this.webCepService = webCepService;
	}

	@RequestMapping( path = { "", "/" }, method = RequestMethod.GET )
	public @ResponseBody List< EnderecoTO > buscaEndereco( @RequestParam( "enderecoCep" ) String enderecoCep )
		throws WebCepException {
		try {
			return webCepService.buscaEndereco( enderecoCep );
		} catch ( Exception ex ) {
			throw new WebCepException( ex.getMessage() );
		}
	}

}
