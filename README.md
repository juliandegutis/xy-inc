# xy-inc

WebService de busca de informações de CEP e Endereços.

 - Java 1.7
 - Spring-boot
 - Retrofit2

# Instruções de compilação

 - Alterar o caminho do compilador do Maven no arquivo build.properties
 - Alterar o endereço e porta do proxy (caso houver) no arquivo application.properties
 - Executar o comando mvn clean install no diretório que contém o arquivo pom.xml
 - Executar o artefato gerado na pasta target através do comando java -jar webCep.jar

# Instruções dos serviços

Foram criados 3 tipos de serviço que retornam informações completas dos endereços cadastrados no Correios. (2 buscando diretamente na URL dos Correios (http://www.buscacep.correios.com.br/sistemas/buscacep/) e outro para exemplificar o consumo de uma API que dado um CEP,  me retorna o endereço.
O Tomcat imbudido está configurado para subir na porta 8080 com o contexto /webCep. Parâmetros configuráveis no arquivo application.properties

- Busca de uma lista de endereços recebendo um CEP de parâmetro:
 - GET /correios?cepEndereco
 - Parametros:
 - cepEndereco: String - Recebe um CEP
 - Retorno:
[
{
"cep": String,
"logradouro": String,
"bairro": String,
"cidade": String,
"estado": String
}
]

- Busca os dados do endereço recebendo uma parte do endereço como parâmetro
 - GET /correios?cepEndereco : Exemplo de query - Rua Oswaldo Uberlandia 
 - Parametros:
 - cepEndereco: String - Recebe uma parte do endereço
 - Retorno:
[
{
"cep": String,
"logradouro": String,
"bairro": String,
"cidade": String,
"estado": String
}
]

- Busca os dados do endereço recebendo um CEP como parâmetro (RESTAPI)
 - GET /rest/cep/{cep}
 - Parametros:
 - cepEndereco: String - Recebe um CEP
 - Retorno:
{
"cep": String,
"logradouro": String,
"bairro": String,
"cidade": String,
"estado": String
}
