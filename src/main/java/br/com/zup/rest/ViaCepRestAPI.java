package br.com.zup.rest;

import java.util.List;

import br.com.zup.to.EnderecoTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * API Rest para chamada do serviço
 * 
 * @author juliang
 *
 */

public interface ViaCepRestAPI {

	/**
	 * 
	 * @param cep
	 * @return Retorna um TO contendo uma lista de objetos
	 */
	@GET( "{cep}" )
	Call< EnderecoTO > buscaEnderecoPorCep( @Path( "cep" ) Long cep );

	/**
	 * 
	 * @param uf
	 * @param cidade
	 * @param logradouro
	 * @return Retorna uma lista de endereços baseados nos param
	 */
	@GET( "{uf}/{cidade}/{logradouro}/json" )
	Call< List< EnderecoTO > > buscaCepPorEndereco(
		@Path( "uf" ) String uf,
		@Path( "cidade" ) String cidade,
		@Path( "logradouro" ) String logradouro );

}
