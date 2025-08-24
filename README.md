# POO trabalho sistema de aluguel

## Equipe

| Equipe            | Email                   |
|-------------------|-------------------------|
| Murilo Hinckel    | muriloxz5jc@gmail.com   |
| Leonardo Winters  | Email B                 |
| Vinícius Rezende  | vinicrezende@gmail.com  |

## Configurações

| Item           | Valor     |
|----------------|-----------|
| Banco de Dados | MySQL     |
| Schema         | meu_banco |

## Diagrama de classe da UML

![Diagrama de classe](/DiagramaClassesUML.png)

## Diagrama MER

![MER](/DiagramaMER.pdf)

## Instruções SQL

Criação do banco e tabelas.
```SQL
--- Criação do banco
CREATE DATABASE Aluguel;
USE Aluguel;

--- Tabela imóvel
CREATE TABLE Imovel (
	id_imovel INT PRIMARY KEY AUTO_INCREMENT,
	matricula INT UNIQUE NOT NULL,
    tamanho INT
);

--- Tabela cliente
CREATE TABLE Cliente (
	id_cliente INT PRIMARY KEY AUTO_INCREMENT,
	CPF VARCHAR(11) UNIQUE NOT NULL,
	nome VARCHAR(255) NOT NULL
);

--- Tabela contrato
CREATE TABLE Contrato (
	id_contrato INT PRIMARY KEY AUTO_INCREMENT,
    id_imovel INT NOT NULL,
    id_cliente INT NOT NULL,
    data_inicio_aluguel DATETIME NOT NULL,
    data_fim_aluguel DATETIME NOT NULL,
    valor FLOAT NOT NULL,
    FOREIGN KEY(id_imovel) REFERENCES Imovel(id_imovel),
    FOREIGN KEY(id_cliente) REFERENCES Cliente(id_cliente)
);
```