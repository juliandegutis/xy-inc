package br.com.zup.service;

import java.util.List;

import br.com.zup.exception.WebCepException;
import br.com.zup.to.EnderecoTO;

public interface WebCepService {

	EnderecoTO buscaEnderecoPorCep( String cep ) throws WebCepException;

	List< EnderecoTO > buscaEndereco( String enderecoCep ) throws WebCepException;

}
