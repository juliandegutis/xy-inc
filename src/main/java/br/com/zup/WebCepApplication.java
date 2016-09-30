package br.com.zup;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import br.com.zup.rest.ViaCepRestAPI;
import br.com.zup.util.NullOnEmptyConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Iniciador do spring-boot
 * 
 * @author juliang
 *
 */

@SpringBootApplication
public class WebCepApplication {
	
	private OkHttpClient okHttpClient;
	
	@Autowired
	private Environment environment;

	public static void main( String[] args ) {
		SpringApplication.run( WebCepApplication.class, args );
	}

	/**
	 * 
	 * @return Bean para a injeção do Spring
	 */
	@Bean( name = "viaCepRestAPI" )
	public ViaCepRestAPI retrofitViaCep() {
		
		if( environment.getProperty( "http.proxy.url" ).isEmpty() ) {
			okHttpClient = new OkHttpClient.Builder().readTimeout( 60, TimeUnit.SECONDS )
				.connectTimeout( 60, TimeUnit.SECONDS ).build();
		} else {
		
			Proxy proxyTest = new Proxy( Proxy.Type.HTTP, new InetSocketAddress( environment.getProperty( "http.proxy.url" ), 
			     Integer.parseInt( environment.getProperty( "http.proxy.port" ) ) ) );
	
			okHttpClient = new OkHttpClient.Builder().readTimeout( 60, TimeUnit.SECONDS )
				.connectTimeout( 60, TimeUnit.SECONDS ).proxy( proxyTest ).build();
			
		}
				
		return new Retrofit.Builder().baseUrl( environment.getProperty( "zup.microservice.cep.url" ) )
			.addConverterFactory( new NullOnEmptyConverterFactory() )
			.addConverterFactory( JacksonConverterFactory.create() ).client( okHttpClient ).build()
			.create( ViaCepRestAPI.class );
	}

}
