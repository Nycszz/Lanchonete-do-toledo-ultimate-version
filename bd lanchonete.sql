CREATE DATABASE lanchonete_bd;
USE lanchonete_bd;
 
CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100),
    preco DOUBLE,
    categoria VARCHAR(50),
    estoque INT
);
 
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    telefone VARCHAR(20)
);
 
CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    clienteId INT,
    valorTotal DOUBLE,
    observacao VARCHAR(255),
    status VARCHAR(50),
    FOREIGN KEY (clienteId) REFERENCES clientes(id)
);
 
CREATE TABLE itens_pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedidoId INT,
    produtoId INT,
    quantidade INT,
    subtotal DOUBLE,
    FOREIGN KEY (pedidoId) REFERENCES pedidos(id),
    FOREIGN KEY (produtoId) REFERENCES produtos(id)
);
 
CREATE TABLE pagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedidoId INT UNIQUE,
    formaPagamento VARCHAR(50),
    valor DOUBLE,
    statusPagamento VARCHAR(30),
 
    FOREIGN KEY (pedidoId) REFERENCES pedidos(id)
);
 
SELECT * FROM produtos;
SELECT * FROM clientes;
SELECT * FROM pedidos;
SELECT * FROM itens_pedido;
SELECT * FROM pagamento;