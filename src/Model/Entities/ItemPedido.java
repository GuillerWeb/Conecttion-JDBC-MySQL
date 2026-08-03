package Model.Entities;

public class ItemPedido {
    private int idPedido;
    private int idProduto;
    private int quantidade;
    private double preco_unitario;
    private double subtotal;

    public ItemPedido() {}

    // Sem ID separado — a PK aqui é composta (idPedido + idProduto), não há AUTO_INCREMENT
    public ItemPedido(int idPedido, int idProduto, int quantidade, double preco_unitario, double subtotal) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
        this.subtotal = subtotal;
    }

    public int getIdPedido() {return idPedido;}
    public int getIdProduto() {return idProduto;}
    public int getQuantidade() {return quantidade;}
    public double getPreco_unitario() {return preco_unitario;}
    public double getSubtotal() {return subtotal;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    public void setPreco_unitario(double preco_unitario) {this.preco_unitario = preco_unitario;}
    public void setSubtotal(double subtotal) {this.subtotal = subtotal;}
    public void setIdPedido(int idPedido) {this.idPedido = idPedido;}
    public void setIdProduto(int idProduto) {this.idProduto = idProduto;}

    @Override
    public String toString() {
        return "ItemPedido{" +
                "idPedido=" + idPedido +
                ", idProduto=" + idProduto +
                ", quantidade=" + quantidade +
                ", preco_unitario=" + preco_unitario +
                ", subtotal=" + subtotal +
                '}';
    }
}
