package br.com.zup.enums;

public enum EnderecoEnum {

	LOGRADOURO( 0 ),
	BAIRRO( 1 ),
	LOCALIDADE( 2 ),
	CEP( 3 );

	private Integer value;

	private EnderecoEnum( Integer value ) {
		this.value = value;
	}

	public Integer value() {
		return this.value;
	}

}
