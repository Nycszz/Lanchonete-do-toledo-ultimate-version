# 🍔 Sistema de Gerenciamento de Lanchonete

Sistema web desenvolvido em Java para gerenciamento de uma lanchonete, permitindo o cadastro de clientes, produtos, pedidos e pagamentos.

## 📌 Funcionalidades

### 👤 Clientes

* Cadastro de clientes
* Edição de clientes
* Consulta de clientes cadastrados
* Exclusão de clientes
<img width="1917" height="928" alt="image" src="https://github.com/user-attachments/assets/4d1dbc6d-68e6-4f50-9d8c-98d9ba9e23b4" />
<img width="1920" height="918" alt="image" src="https://github.com/user-attachments/assets/8dafc488-1a4e-40fc-af62-d5e4e298888e" />


### 🍟 Produtos

* Cadastro de produtos
* Categorias:
  * Lanches
  * Bebidas
  * Acompanhamentos
  * Sobremesas
* Consulta de produtos
* Edição de produtos
* Exclusão de produtos
<img width="1920" height="918" alt="image" src="https://github.com/user-attachments/assets/44810934-c212-4ffa-9f78-dfc109bc994b" />
<img width="1919" height="920" alt="image" src="https://github.com/user-attachments/assets/0a54f2e1-d93f-4a42-8af8-341b689c804c" />


### 🛒 Pedidos

* Criação de pedidos
* Seleção de múltiplos produtos
* Observações personalizadas
* Cálculo automático do valor total
* Controle de status do pedido
  <img width="1920" height="918" alt="image" src="https://github.com/user-attachments/assets/68684d11-0cf4-4b31-a300-d2ad1a7d8854" />
  <img width="1918" height="916" alt="image" src="https://github.com/user-attachments/assets/51f43b55-c9c4-43bb-88ea-582326f2c99a" />

---

## 🎯 Design Patterns Utilizados

### Command Pattern

Utilizado para encapsular as ações do sistema.

Exemplos:
* CadastrarClienteCommand
* CadastrarProdutoCommand
* FinalizarPedidoCommand
* ConsultarTodosPedidoCommand

### Decorator Pattern

Utilizado para adicionar funcionalidades aos lanches sem alterar a classe original.

Exemplos:
* Bacon
* Queijo
* Ovo

### Builder Pattern

Utilizado na construção de objetos Produto.

---

## 🗄️ Banco de Dados

Banco utilizado:

* MySQL

---

## 🛠️ Tecnologias Utilizadas

* Java
* JSP
* Servlets
* JDBC
* MySQL
* HTML
* CSS
* Apache Tomcat
* NetBeans

---

## 📖 Conceitos Aplicados

* Programação Orientada a Objetos
* Design Patters
* Princípios SOLID
* Calistenia de Objetos

---

## 🚀 Como Executar o Projeto

### 1. Pré-requisitos

Antes de executar o sistema, certifique-se de ter instalado:

* Java JDK 8 ou superior
* Apache Tomcat
* MySQL Server
* NetBeans IDE
* MySQL Connector/J (Driver JDBC)

---

### 2. Clonar o Repositório

```bash
git clone URL_DO_REPOSITORIO
```

ou faça o download do projeto em formato ZIP pelo GitHub.

---

### 3. Configurar o Banco de Dados

Abra o MySQL Workbench ou outro cliente MySQL e execute o script SQL fornecido no projeto:

```sql
bd lanchonete.sql
```

Esse script irá:

* Criar o banco `lanchonete_bd`
* Criar as tabelas:

  * clientes
  * produtos
  * pedidos
  * itens_pedido
  * pagamento

---

### 4. Configurar a Conexão com o Banco

Abra a classe:

```java
util.Conexao
```

Configure os dados de acesso conforme sua instalação do MySQL:

```java
String URL = "jdbc:mysql://localhost:3306/lanchonete_bd";
String USER = "root";
String PASSWORD = "sua_senha";
```
---

### 5. Adicionar o Driver JDBC

Baixe o MySQL Connector/J:

https://dev.mysql.com/downloads/connector/j/

Extraia o arquivo ZIP e obtenha o arquivo:

```text
mysql-connector-j-x.x.x.jar
```

No NetBeans:

1. Clique com o botão direito no projeto
2. Selecione **Properties**
3. Vá em **Libraries**
4. Clique em **Add JAR/Folder**
5. Selecione o arquivo `mysql-connector-j-x.x.x.jar`

---

### 6. Importar o Projeto no NetBeans

1. Abra o NetBeans
2. Clique em **File > Open Project**
3. Selecione a pasta do projeto
4. Aguarde a indexação e carregamento

---

### 7. Configurar o Servidor

1. Certifique-se de que o Apache Tomcat está configurado no NetBeans
2. Caso não esteja:

   * Tools → Servers
   * Add Server
   * Apache Tomcat
   * Informar o diretório de instalação

---

### 8. Compilar o Projeto e Executar o Sistema

Execute:

```text
Clean and Build
```

para gerar todos os arquivos compilados.

---

Clique em:

```text
Run Project
```
---

### 10. Acessar a Aplicação

Abra o navegador e acesse:

```text
http://localhost:8080/Lanchonete
```
---

## ⚠️ Problemas Comuns

### Erro: `com.mysql.cj.jdbc.Driver`

O driver JDBC não foi adicionado corretamente ao projeto.

Solução:

* Verificar se o arquivo `mysql-connector-j.jar` foi adicionado às bibliotecas do projeto.

### Erro: `Access denied for user`

Usuário ou senha do banco incorretos.

Solução:

* Verificar os dados configurados na classe `Conexao`.

### Erro ao iniciar o Tomcat

Verifique:

* Porta 8080 disponível
* Servidor configurado corretamente no NetBeans
* Projeto compilado sem erros

## 👨‍💻 Autores

Projeto desenvolvido para a disciplina de **Design Patterns** do curso de **Engenharia de Software**.

Desenvolvido por:

* **Beatriz Madeira**
* **Nychollas Freitas**

Estudantes do **5º semestre de Engenharia de Software**.

Projeto desenvolvido sob orientação do **Professor Pedro Toledo**, com aplicação prática de conceitos de Programação Orientada a Objetos, padrões de projeto (Design Patterns), princípios SOLID e arquitetura MVC.

