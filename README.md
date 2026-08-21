# Conecttion-JDBC-MySQL

Projeto acadêmico de estudo focado no funcionamento **puro do driver JDBC** em Java, sem o uso de frameworks como **Hibernate** ou **Spring Data JPA**.
 
O objetivo principal não foi construir um sistema completo de loja, mas sim **entender profundamente como o JDBC se comunica com o banco de dados MySQL** — desde a abertura da conexão, passando pela montagem de queries com `PreparedStatement`, até o mapeamento manual do `ResultSet` para os objetos Java (o que frameworks como Hibernate e Spring Data fazem automaticamente "por baixo dos panos").

## Tecnologias utilizadas
 
- Java
- JDBC (java.sql)
- MySQL
- MySQL Connector/J

 ## Estrutura do projeto

```
Conecttion-JDBC-MySQL/
├── src/
│   ├── Connection/
│   │   └── BD.java                    # abre e gerencia a conexão com o MySQL
│   ├── Model/
│   │   ├── Dao/                       # Pacote responsável pelas operações de acesso a dados (CRUD) via JDBC puro.
│   │   │   ├── Impl/                  # implementam o acesso real ao banco via JDBC 
│   │   │   ├── Interfaces/            # definem o contrato de operações de cada DAO
│   │   │   └── DaoFactory.java        # centraliza a criação dos DAOs
│   │   │
│   │   └── Entities/                  # representam fielmente as tabelas do banco (sem lógica de acesso a dados)
│   │       ├── Cliente.java
│   │       ├── ItemPedido.java
│   │       ├── Pedido.java
│   │       └── Produto.java
│   │
│   └── Main.java                      # menu interativo via terminal
│
├── .gitignore
├── db.properties.example              # modelo de configuração (sem credenciais reais)
└── README.md
```

## Funcionalidades
 
Todas as entidades (`Cliente`, `Produto`, `Pedido`, `ItemPedido`) possuem as seguintes operações via terminal:
 
- Cadastrar
- Listar todos
- Buscar por ID
- Buscar por nome
- Atualizar cadastro
- Deletar
A interação acontece por um **menu no terminal**, onde o usuário escolhe a entidade e a operação desejada.

## Modelo de dados
 
O banco é composto por quatro tabelas principais:
 
- **CLIENTE** — dados dos clientes
- **PRODUTO** — catálogo de produtos
- **PEDIDO** — pedidos realizados, vinculados a um cliente
- **ITEM_PEDIDO** — tabela associativa (relacionamento N:N entre Pedido e Produto), guardando quantidade, preço unitário e subtotal de cada item

## Por que JDBC puro, e não Hibernate/Spring Data?
 
Frameworks como **Hibernate** e **Spring Data JPA** automatizam praticamente todo o processo feito manualmente aqui: abrem e fecham conexões, geram o SQL automaticamente a partir de anotações, convertem `ResultSet` em objetos Java sem necessidade de mapeamento manual, e gerenciam o ciclo de vida das entidades.
 
Usar JDBC puro neste projeto foi uma escolha proposital de aprendizado — entender o que acontece "por baixo dos panos" antes de utilizar essas abstrações torna o uso futuro de um ORM muito mais consciente, facilitando o diagnóstico de problemas e a compreensão do que está realmente acontecendo por trás de cada anotação e método mágico desses frameworks.

