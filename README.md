# ifpb_monteiro_dac_2021_2_grupo_1
Este sistema é uma loja virtual que realiza a venda de livros online. Possui dois papéis principais: o de administrador, que seria o responsável por cadastrar livros, adicionar livros ao estoque e outras funcionalidades próprias de administrador, e de cliente que seria o usuário que efetua a compra dos livros. 

## Documentação
 -  [Instruções Gerais](https://docs.google.com/document/d/1h9WgWNCWHihQcih7ODqruqdtr4nahPz_8D9dsW0R22I/edit?usp=sharing)

## Desenvolvimento do Produto
O desenvolvimento do projeto foi dividida nas seguintes etapas:
  
#### 1. Camada de Dados
Nesta etapa foi desenvolvido o mapeamento em JPA para um banco de dados MySQL e as principais funcionalidades do sistema como cadastro de usuário, cadastro de livro, consulta de livro, etc. Além disso, uma aplicação no console com um menu que exibe essas funcionalidades e permite ao usuário (ainda não havendo distinção de papel) já executá-las.

#### 2. Camada de Apresentção
Nesta fase será feita as regras de negócio, uma interface e a integração com a camada de dados seguindo o modelo MVC.
    
#### 3. Camada de Integração
Nesta etapa será desenvolvido uma interface Web usando um single page application e uma API REST que fornecerá os serviços.

## Execução do Sistema
  - Clone este [repositório](https://github.com/joaopaulopbjp/ifpb_monteiro_dac_2021_2_grupo_1.git) do github na seu computador
  - Crie um banco de dados MySQL com nome de livraria
  - Execute ifpb_monteiro_dac_2021_2_grupo_1/livrariaProject/Livraria/src/main/java/com/example/livraria/LivrariaApplication.java

## Sobre o Projeto
Este projeto foi desenvolvido para disciplina de Desenvolvimento de Aplicações Corporativas do curso de Análise e Desenvolvimento de Sistemas (IFPB - Campus Monteiro).
