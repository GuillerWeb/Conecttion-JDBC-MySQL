import Model.Dao.DaoFactory;
import Model.Dao.Interfaces.IClienteDao;
import Model.Dao.Interfaces.IItemPedidoDao;
import Model.Dao.Interfaces.IPedidoDao;
import Model.Dao.Interfaces.IProdutoDao;
import Model.Entities.Cliente;
import Model.Entities.ItemPedido;
import Model.Entities.Pedido;
import Model.Entities.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static IClienteDao clienteDao;
    private static IProdutoDao produtoDao;
    private static IPedidoDao pedidoDao;
    private static IItemPedidoDao itemPedidoDao;

    public static void main(String[] args) {
        try {
            clienteDao = DaoFactory.createClienteDao();
            produtoDao = DaoFactory.createProdutoDao();
            pedidoDao = DaoFactory.createPedidoDao();
            itemPedidoDao = DaoFactory.createItemPedidoDao();
            menuPrincipal();
        } catch (Exception e) {
            System.out.println("Erro ao iniciar o sistema: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void menuPrincipal() {
        int opcao;
        do {
            System.out.println("\n========== SISTEMA DA LOJA ==========");
            System.out.println("1 - Clientes");
            System.out.println("2 - Produtos");
            System.out.println("3 - Pedidos e itens");
            System.out.println("0 - Sair");
            opcao = lerInt("Opcao: ");
            try {
                switch (opcao) {
                    case 1 -> menuClientes();
                    case 2 -> menuProdutos();
                    case 3 -> menuPedidos();
                    case 0 -> System.out.println("Sistema encerrado.");
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Operacao nao concluida: " + mensagemErro(e));
            }
        } while (opcao != 0);
    }

    private static void menuClientes() {
        int opcao;
        do {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("1 - Cadastrar | 2 - Listar | 3 - Buscar por id");
            System.out.println("4 - Atualizar | 5 - Excluir | 0 - Voltar");
            opcao = lerInt("Opcao: ");
            switch (opcao) {
                case 1 -> { clienteDao.insert(lerCliente()); System.out.println("Cliente cadastrado."); }
                case 2 -> listar(clienteDao.findAll());
                case 3 -> System.out.println(clienteDao.findById(lerInt("Id do cliente: ")));
                case 4 -> atualizarCliente();
                case 5 -> { clienteDao.deleteById(lerInt("Id do cliente: ")); System.out.println("Cliente excluido."); }
                case 0 -> { }
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private static Cliente lerCliente() {
        return new Cliente(lerTexto("Nome: "), lerTexto("Telefone: "),
                lerTexto("E-mail: "), lerTexto("Cidade: "));
    }

    private static void atualizarCliente() {
        int id = lerInt("Id do cliente: ");
        System.out.println("Dados atuais: " + clienteDao.findById(id));
        Cliente cliente = lerCliente();
        cliente.setIdCliente(id);
        clienteDao.updateById(cliente);
        System.out.println("Cliente atualizado.");
    }

    private static void menuProdutos() {
        int opcao;
        do {
            System.out.println("\n--- PRODUTOS ---");
            System.out.println("1 - Cadastrar | 2 - Listar | 3 - Buscar por id");
            System.out.println("4 - Buscar por nome | 5 - Atualizar | 6 - Excluir | 0 - Voltar");
            opcao = lerInt("Opcao: ");
            switch (opcao) {
                case 1 -> { produtoDao.insert(lerProduto()); System.out.println("Produto cadastrado."); }
                case 2 -> listar(produtoDao.findAll());
                case 3 -> System.out.println(produtoDao.findById(lerInt("Id do produto: ")));
                case 4 -> System.out.println(produtoDao.findByNome(lerTexto("Nome exato: ")));
                case 5 -> atualizarProduto();
                case 6 -> { produtoDao.deleteById(lerInt("Id do produto: ")); System.out.println("Produto excluido."); }
                case 0 -> { }
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private static Produto lerProduto() {
        return new Produto(lerTexto("Nome: "), lerTexto("Descricao: "),
                lerDecimal("Preco: ").doubleValue(), lerIntNaoNegativo("Estoque: "));
    }

    private static void atualizarProduto() {
        int id = lerInt("Id do produto: ");
        System.out.println("Dados atuais: " + produtoDao.findById(id));
        Produto produto = lerProduto();
        produto.setIdProduto(id);
        produtoDao.updateById(produto);
        System.out.println("Produto atualizado.");
    }

    private static void menuPedidos() {
        int opcao;
        do {
            System.out.println("\n--- PEDIDOS E ITENS ---");
            System.out.println("1 - Cadastrar pedido | 2 - Listar pedidos | 3 - Buscar pedido");
            System.out.println("4 - Excluir pedido | 5 - Adicionar item");
            System.out.println("6 - Listar itens do pedido | 7 - Listar itens por cliente");
            System.out.println("8 - Remover item pendente | 0 - Voltar");
            opcao = lerInt("Opcao: ");
            switch (opcao) {
                case 1 -> cadastrarPedido();
                case 2 -> listar(pedidoDao.findAll());
                case 3 -> System.out.println(pedidoDao.findById(lerInt("Id do pedido: ")));
                case 4 -> { pedidoDao.deleteById(lerInt("Id do pedido: ")); System.out.println("Pedido excluido."); }
                case 5 -> adicionarItem();
                case 6 -> listar(itemPedidoDao.listAll(lerInt("Id do pedido: ")));
                case 7 -> listar(itemPedidoDao.listAllByClient(lerInt("Id do cliente: ")));
                case 8 -> { itemPedidoDao.deleteById(lerInt("Id do pedido: "), lerInt("Id do produto: ")); System.out.println("Item removido se o pedido estava PENDENTE."); }
                case 0 -> { }
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarPedido() {
        int idCliente = lerInt("Id do cliente: ");
        clienteDao.findById(idCliente);
        Pedido pedido = new Pedido();
        pedido.setIdCliente(idCliente);
        pedido.setData(LocalDate.now());
        pedido.setTotal(lerDecimal("Total do pedido: "));
        pedido.setStatus(lerTexto("Status (ex.: PENDENTE): "));
        pedidoDao.insert(pedido);
        System.out.println("Pedido cadastrado.");
    }

    private static void adicionarItem() {
        int idPedido = lerInt("Id do pedido: ");
        int idProduto = lerInt("Id do produto: ");
        int quantidade = lerIntPositivo("Quantidade: ");
        BigDecimal preco = lerDecimal("Preco unitario: ");
        BigDecimal subtotal = preco.multiply(BigDecimal.valueOf(quantidade));
        itemPedidoDao.insert(new ItemPedido(idPedido, idProduto, quantidade, preco, subtotal));
        System.out.println("Item adicionado. Subtotal: R$ " + subtotal);
    }

    private static void listar(List<?> lista) {
        if (lista.isEmpty()) System.out.println("Nenhum registro encontrado.");
        else lista.forEach(System.out::println);
    }

    private static int lerInt(String mensagem) {
        while (true) {
            try { return Integer.parseInt(lerTexto(mensagem)); }
            catch (NumberFormatException e) { System.out.println("Informe um numero inteiro valido."); }
        }
    }

    private static int lerIntPositivo(String mensagem) {
        while (true) {
            int valor = lerInt(mensagem);
            if (valor > 0) return valor;
            System.out.println("Informe um valor maior que zero.");
        }
    }

    private static int lerIntNaoNegativo(String mensagem) {
        while (true) {
            int valor = lerInt(mensagem);
            if (valor >= 0) return valor;
            System.out.println("Informe um valor nao negativo.");
        }
    }

    private static BigDecimal lerDecimal(String mensagem) {
        while (true) {
            try {
                BigDecimal valor = new BigDecimal(lerTexto(mensagem).replace(',', '.'));
                if (valor.signum() >= 0) return valor;
                System.out.println("Informe um valor nao negativo.");
            } catch (NumberFormatException e) { System.out.println("Informe um valor monetario valido."); }
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static String mensagemErro(Throwable erro) {
        Throwable causa = erro;
        while (causa.getCause() != null) causa = causa.getCause();
        return causa.getMessage() == null ? causa.getClass().getSimpleName() : causa.getMessage();
    }
}