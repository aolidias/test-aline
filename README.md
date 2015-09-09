# test-aline



## Arquitetura Geral ##

Para as questões 1, 2 e 3 criei um único projeto para facilitar os testes e a integração. As tecnologias usadas são:

* Java 8 como linguaguem de programação
* Spring MVC para os controllers
* Bean Validation para validações
* Spring Data para a persistência dos dados na base
* Spring Rest para a criação da api rest
* Google Gson nos testes para facilitar a conversão de json para objeto e vice-versa.
* H2 database como base de dados em memória
* Maven para build 
* Hamcrest para assertions dos testes
* Spring Test para os testes
* Logback para os logs da aplicação
* Spring Boot para ganhar produtividade no desenvolvimento pois facilita a configuração do Spring.
* Como servidor de aplicação utilizei o embutido no spring boot 
* Projeto segue a arquitetura MVC

Dados adicionais:

* Inserts iniciais estão no arquivo data.sql
* Configurações estão no arquivo application.properties

Comando para rodar o projeto:
mvn clean package spring-boot:run

## Questão 1 - Estratégia ##

A estratégia foi de utilizar o MVC e construir uma aplicação simples e fácil de entender.
Na primeira questão a estratégia foi criar a entidade para o Cep, o repositório desta entidade para persistência dos dados na base de dados (com spring boot não precisei configurar a base pois só colocando a dependência ela já faz automaticamente).

Depois criei o serviço que iria consultar o Cep via repositório e o RestController aonde contém o serviço Rest que recebe as requisições de consulta de Cep, aonde o parâmetro é uma String de Cep e faz a validação deste campo e caso seja valido faz a consulta de cep no service e retorna um json com os dados encontrados. 

Foi criada a classe CepTest que contém os testes para garantir o funcionamento da aplicação e documentação da mesma.

Outras considerações deste serviço:
* As validações são feitas em uma classe específica.
* A um handler exception para tratar as exceções e mostrar uma mensagem mais amigável ao usuário.

## Questão 2 - Estratégia ##

A estratégia foi de utilizar o MVC e construir uma aplicação simples e fácil de entender.
Foi criado um RestController com as operações de criar, atualizar, listar, remover e listar pelo id. Foi mapeado a entidade Endereço e criado o repositório para a persistência dos dados e o serviço que contém a lógica de comunicação entre o controller e a base. 

Foi criada uma classe que consome a api da questão 1 utilizando o RestTemplate para ser utilizada na validação da operação de criar endereço.

Foi criada a classe EnderecoTest para testar as ações de listar, atualizar, buscar por ID e remover para garantir o funcionamento e documentação.

Foi criada a classe CriarEnderecoTest para testar a ação de criar um endereço na base para garantir o funcionamento e documentação.

Outras considerações deste serviço:
* As validações das operações criar e atualizar são validadas com BeanValidation.
* Utilizar um handlers exception para o tratamento de exceções.

## Questão 3 - Estratégia ##

A estratégia utilizada foi simplicidade e legibilidade. Foi criada a interface Stream e sua implementação StreamImpl. 
A classe StreamImpl possui um construtor que recebe uma String.
A classe StreamImpl possui dois métodos:
 * getNext() que utiliza um index para saber qual a próxima String.
 * hasNext() que utiliza o index e o lenght da String para saber se tem mais algum caracter para ler.

Foi criado a classe StreamMain que contém a lógica para indentificar o primeiro caracter que não se repete.

Na classe StreamMain ele recebe uma Stream vai percorrer com um laço while todos os seus caracteres, dentro do laço ele vai criar um String que contém a palavra inteira da Stream e uma lista de caracters.

Após esse laço ele vai percorrer a lista de caracteres pra descobrir quantas vezes aquele caracter aparece na palavra. 

O primeiro caracter que retorna que ocorre uma vez ele retorna no método.

Caso não aja caracteres que não se repita ele lança uma exceção com uma mensagem amigável.

Foi criada a classe StreamMainTest para garantir o funcionamento do método e documentar.


