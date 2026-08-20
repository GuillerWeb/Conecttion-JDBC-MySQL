package Model.Entities;

import java.math.BigDecimal;

public class ItemPedido {
    String nomeProduto;
    private int idPedido;
    private int idProduto;
    private int quantidade;
    private BigDecimal preco_unitario;
    private BigDecimal subtotal;

    public ItemPedido() {}

    // Sem ID separado — a PK aqui é composta (idPedido + idProduto), não há AUTO_INCREMENT
    public ItemPedido(int idPedido, int idProduto, int quantidade, BigDecimal preco_unitario, BigDecimal subtotal) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
        this.subtotal = subtotal;
    }

    public int getIdPedido() {return idPedido;}
    public int getIdProduto() {return idProduto;}
    public int getQuantidade() {return quantidade;}
    public BigDecimal getPreco_unitario() {return preco_unitario;}
    public BigDecimal getSubtotal() {return subtotal;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    public void setPreco_unitario(BigDecimal preco_unitario) {this.preco_unitario = preco_unitario;}
    public void setSubtotal(BigDecimal subtotal) {this.subtotal = subtotal;}
    public void setIdPedido(int idPedido) {this.idPedido = idPedido;}
    public void setIdProduto(int idProduto) {this.idProduto = idProduto;}
    public String getNomeProduto() {return nomeProduto;}
    public void setNomeProduto(String nomeProduto) {this.nomeProduto = nomeProduto;}

    @Override
    public String toString() {
        return "ItemPedido{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", idPedido=" + idPedido +
                ", idProduto=" + idProduto +
                ", quantidade=" + quantidade +
                ", preco_unitario=" + preco_unitario +
                ", subtotal=" + subtotal +
                '}';
    }
}
