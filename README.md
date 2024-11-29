# Livraria Online
Este é um projeto de uma livraria online, desenvolvido em Java utilizando o framework Spring Boot e JDBC para interação com o banco de dados. O sistema permite buscar, registrar e listar livros e autores, além de permitir a pesquisa de livros por idioma e listar autores vivos em determinados anos.

## Funcionalidades
1. Buscar livro pelo título: Permite ao usuário buscar um livro e inserir seus dados na base de dados a partir de uma API externa.
2. Listar livros registrados: Exibe todos os livros registrados no sistema.
3. Listar autores registrados: Exibe todos os autores cadastrados no sistema.
4. Listar autores vivos em determinado ano: Permite listar autores que estavam vivos em um ano específico.
5. Listar livros por idioma: Exibe os livros registrados em determinado idioma (por exemplo: Português, Inglês, Italiano, Francês).

## Tecnologias utilizadas
* Java: Linguagem principal utilizada para o desenvolvimento.
* Spring Boot: Framework para desenvolvimento de aplicações Java, facilitando a configuração e o gerenciamento do ciclo de vida dos componentes.
* JDBC: Para interação com o banco de dados.
* PostgreSQL: Banco de dados utilizado para armazenar informações de livros e autores.
* Gson: Biblioteca para manipulação de JSON, usada para fazer parse das respostas da API.

## Estrutura do Projeto
A estrutura do projeto segue a arquitetura padrão do Spring Boot, com os seguintes pacotes principais:
* rolins.bilbiotecaOnline: Pacote principal contendo as classes de inicialização e o menu interativo.
* rolins.bilbiotecaOnline.servicos: Pacote contendo os serviços responsáveis por interagir com o banco de dados e a API externa.
