package br.com.zup.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Transfer Object para o retorno Endereço
 * 
 * @author juliang
 *
 */

@JsonInclude( Include.NON_NULL )
@JsonIgnoreProperties( ignoreUnknown = true )
public class EnderecoTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1010504241793303143L;

	private String cep;

	private String logradouro;

	private String complemento;

	private String bairro;

	private String cidade;

	private String estado;


	public EnderecoTO() {

	}

	public String getCep() {
		return cep;
	}

	public void setCep( String cep ) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro( String logradouro ) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento( String complemento ) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro( String bairro ) {
		this.bairro = bairro;
	}

	
	public String getCidade() {
		return cidade;
	}

	
	public void setCidade( String cidade ) {
		this.cidade = cidade;
	}

	
	public String getEstado() {
		return estado;
	}

	
	public void setEstado( String estado ) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( bairro == null ) ? 0 : bairro.hashCode() );
		result = prime * result + ( ( cep == null ) ? 0 : cep.hashCode() );
		result = prime * result + ( ( cidade == null ) ? 0 : cidade.hashCode() );
		result = prime * result + ( ( complemento == null ) ? 0 : complemento.hashCode() );
		result = prime * result + ( ( estado == null ) ? 0 : estado.hashCode() );
		result = prime * result + ( ( logradouro == null ) ? 0 : logradouro.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		EnderecoTO other = (EnderecoTO) obj;
		if ( bairro == null ) {
			if ( other.bairro != null )
				return false;
		} else if ( !bairro.equals( other.bairro ) )
			return false;
		if ( cep == null ) {
			if ( other.cep != null )
				return false;
		} else if ( !cep.equals( other.cep ) )
			return false;
		if ( cidade == null ) {
			if ( other.cidade != null )
				return false;
		} else if ( !cidade.equals( other.cidade ) )
			return false;
		if ( complemento == null ) {
			if ( other.complemento != null )
				return false;
		} else if ( !complemento.equals( other.complemento ) )
			return false;
		if ( estado == null ) {
			if ( other.estado != null )
				return false;
		} else if ( !estado.equals( other.estado ) )
			return false;
		if ( logradouro == null ) {
			if ( other.logradouro != null )
				return false;
		} else if ( !logradouro.equals( other.logradouro ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EnderecoTO [cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento + ", bairro="
			+ bairro + ", cidade=" + cidade + ", estado=" + estado + "]";
	}

}
