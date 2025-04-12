## Desafio Full-Stack (Spring Boot + Angular)

Este projeto é uma aplicação Full-Stack desenvolvida utilizando Spring Boot no back-end e Angular no front-end. O sistema permite a gestão de empresas e fornecedores, garantindo regras de negócio como validação de CPF/CNPJ, restrição de idade para fornecedores pessoa física no Paraná e integração com API de CEP.

## 🎥Vídeo do projeto 


https://github.com/user-attachments/assets/1ec0c92f-0b73-4598-a8eb-f3389af54995




## 🚀 Tecnologias Utilizadas

## Backend:
- **[Java 17]**
- **[Spring Boot(Spring Web, Spring Data JPA, Validation)]**
- **[Hibernate (ORM para interação com banco de dados) ]**
- **[Maven]**
- **[mysql (Banco de dados relacional)]**
- **[Lombok (Redução de código boilerplate)]**
- **[Postman]**
- **[springdoc (Documentação da API)]**
- **[cors]**

## Frontend:

- Angular (Framework front-end)
- TypeScript
- Bootstrap (Para estilização)

## ⚙️ Funcionalidades

✅Cadastro de Empresas (com nome, CNPJ, CEP, e-mail)

✅Cadastro de Fornecedores (pessoa física e jurídica)

✅Validações:

✅CPF/CNPJ

✅Idade mínima para fornecedores pessoa física no Paraná

✅RG e Data de Nascimento obrigatórios para pessoa física

✅Integração com API de CEP para busca de endereços

✅Listagem e Filtros por Nome e CPF/CNPJ

✅Edição e Exclusão de empresas e fornecedores

## 🚀 Como Rodar o Projeto

📌 1. Configuração do Banco de Dados

Certifique-se de que você tem o MySQL instalado e crie um banco de dados:
CREATE DATABASE desafio_fullstack;

📌 2. Configurar o application.properties

No diretório src/main/resources/application.properties, configure a conexão com o banco:

- spring.datasource.url=jdbc:mysql://localhost:3306/desafio_fullstack
- spring.datasource.username=root
- spring.datasource.password=senha
- spring.jpa.hibernate.ddl-auto=update
 
📌 3. Rodar o Backend (Spring Boot)
- ./mvnw spring-boot:run


Execute o seguinte comando na raiz do projeto backend:

Pré-requisitos: Java 17

# clonar repositório
git clone https://github.com/JacquelineCasali/Teste-de-Sistema-de-gestao.git

# executar o projeto
./mvnw spring-boot:run

A API estará disponível em: http://localhost:8080


📌 4. Rodar o Frontend (Angular)
Entre na pasta do frontend e execute:

- npm install  # Instalar dependências
- ng serve     # Rodar o projeto
  O frontend estará acessível em: http://localhost:4200
- 
# clonar repositório
git clone https://github.com/JacquelineCasali/Teste-de-Sistema-de-gestao-angular.git

📖 Documentação da API

Após iniciar o backend, acesse a documentação da API no Swagger:
http://localhost:8080/swagger-ui/index.html

## 📝 Projeto Desenvolvido por
Jacqueline Casali
https://www.linkedin.com/in/jaquelinecasali/



