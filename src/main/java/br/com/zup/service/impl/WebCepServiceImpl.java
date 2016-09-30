package br.com.zup.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.zup.enums.EnderecoEnum;
import br.com.zup.exception.WebCepException;
import br.com.zup.rest.ViaCepRestAPI;
import br.com.zup.service.WebCepService;
import br.com.zup.to.EnderecoTO;
import br.com.zup.util.Constantes;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class WebCepServiceImpl implements WebCepService {

	@Autowired
	private ViaCepRestAPI viaCebRestAPI;
	
	@Autowired
	private Environment environment;

	@Override
	public EnderecoTO buscaEnderecoPorCep( String cep ) throws WebCepException {

		try {

			String cepFormatado = cep.replaceAll( "[^0-9]+", "" );

			Call< EnderecoTO > call = viaCebRestAPI.buscaEnderecoPorCep( Long.parseLong( cepFormatado ) );
			Response< EnderecoTO > response = call.execute();

			if ( response.errorBody() == null ) {
				return response.body();
			} else {
				throw new WebCepException( "Não foi possível acessar o serviço. " + response.errorBody().toString() );
			}

		} catch ( Exception ex ) {
			throw new WebCepException( ex.getMessage() );
		}

	}

	@Override
	public List< EnderecoTO > buscaEndereco( String enderecoCep ) throws WebCepException {

		try {

			List< EnderecoTO > enderecoList = new ArrayList< EnderecoTO >();

			Elements elements = buscaEnderecoCorreios( enderecoCep );

			for ( Element current : elements ) {

				EnderecoTO endereco = new EnderecoTO();

				Elements coluna = current.getElementsByTag( "td" );

				if ( coluna.get( EnderecoEnum.LOGRADOURO.value() ) != null ) {
					endereco.setLogradouro(
						coluna.get( EnderecoEnum.LOGRADOURO.value() ).childNodes().get( 0 ).attr( "text" ) );
				}
				if ( coluna.get( EnderecoEnum.BAIRRO.value() ) != null ) {
					endereco.setBairro( coluna.get( EnderecoEnum.BAIRRO.value() ).childNodes().get( 0 ).attr( "text" ) );
				}
				if ( coluna.get( EnderecoEnum.LOCALIDADE.value() ) != null ) {
					endereco.setCidade(
						coluna.get( EnderecoEnum.LOCALIDADE.value() ).childNodes().get( 0 ).attr( "text" )
							.split( "/" )[ 0 ] );
					endereco.setEstado(
						coluna.get( EnderecoEnum.LOCALIDADE.value() ).childNodes().get( 0 ).attr( "text" )
							.split( "/" )[ 1 ] );
				}
				if ( coluna.get( EnderecoEnum.CEP.value() ) != null ) {
					endereco.setCep( coluna.get( EnderecoEnum.CEP.value() ).childNodes().get( 0 ).attr( "text" ) );
				}

				enderecoList.add( endereco );

			}

			return enderecoList;

		} catch ( Exception ex ) {
			throw new WebCepException( "Não foi possível formatar o endereço obtido. " + ex );
		}
	}

	private Elements buscaEnderecoCorreios( String enderecoCep ) throws WebCepException {
		try {
			
			if( !( environment.getProperty( "http.proxy.url" ).isEmpty() ) ) {
				System.setProperty( "http.proxyHost", environment.getProperty( "http.proxy.url" ) );
				System.setProperty( "http.proxyPort", environment.getProperty( "http.proxy.port" ).toString() );
			} 

			Document doc = Jsoup.connect( Constantes.BUSCA_CEP_CORREIOS )
				.header( "Content-Type", "application/x-www-form-urlencoded;charset=UTF-8" )
				.data( "relaxation", enderecoCep ).data( "tipoCEP", "ALL" ).data( "semelhante", "N" ).post();
			Elements rows = (Elements) doc.select( "table.tmptabela tr:not(:first-child)" );
			return rows;
		} catch ( Exception ex ) {
			throw new WebCepException( ex.getMessage() );
		}
	}

}
