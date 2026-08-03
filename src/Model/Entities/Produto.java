package Model.Entities;

public class Produto {
    private int idProduto;
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;

    public Produto() {}

    // Sem ID — para INSERT (ID é AUTO_INCREMENT)
    public Produto(String nome, String descricao, double preco, int estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    // Completo — para leitura do banco
    public Produto(int idProduto, String nome, String descricao, double preco, int estoque) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getIdProduto()            { return idProduto; }
    public void setIdProduto(int id)     { this.idProduto = id; }
    public String getNome()              { return nome; }
    public void setNome(String nome)     { this.nome = nome; }
    public String getDescricao()         { return descricao; }
    public void setDescricao(String d)   { this.descricao = d; }
    public double getPreco()             { return preco; }
    public void setPreco(double preco)   { this.preco = preco; }
    public int getEstoque()              { return estoque; }
    public void setEstoque(int estoque)  { this.estoque = estoque; }

    @Override
    public String toString() {
        return "Produto{" +
                "idProduto=" + idProduto +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", estoque=" + estoque +
                '}';
    }
}


