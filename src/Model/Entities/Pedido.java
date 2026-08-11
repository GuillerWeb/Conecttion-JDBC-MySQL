package Model.Entities;

import java.time.LocalDate;;

public class Pedido {
    private int idPedido;
    private int idCliente;
    private LocalDate data;    //(AAAA-MM-DD)
    private double total;
    private String status;

    public Pedido() {};

    // Construtor completo para busca no Banco.
    public Pedido(int idPedido, int idCliente, LocalDate data, double total, String status) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.data = data;
        this.total = total;
        this.status = status;
    }
    //Construtor para Insert no banco(não uso o Id pq já é auto-incrementado pelo Banco)
    public Pedido(LocalDate data, double total, String status) {
        this.data = data;
        this.total = total;
        this.status = status;
    }

    public int getIdPedido() {return idPedido;}
    public int getIdCliente() {return idCliente;}
    public LocalDate getData() {return data;}
    public double getTotal() {return total;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public void setData(LocalDate data) {this.data = data;}
    public void setTotal(double total) {this.total = total;}
    public void setIdCliente(int idCliente) {this.idCliente = idCliente;}
    public void setIdPedido(int idPedido) {this.idPedido = idPedido;}

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", idCliente=" + idCliente +
                ", data=" + data +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }
}
