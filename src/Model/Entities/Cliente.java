package Model.Entities;

public class Cliente{

    private String nome;
    private int idCliente;
    private String telefone;
    private String email;
    private String cidade;

    public Cliente() {}

    //Construtor para Insert no banco(sem id por causa do AUTO_INCREMENT no banco)
    public Cliente(String nome, String telefone, String email, String cidade) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cidade = cidade;
    }

    //Construtor para buscar no banco
    public Cliente(String nome, int idNome, String telefone, String email, String cidade) {
        this.nome = nome;
        this.idCliente = idCliente;
        this.telefone = telefone;
        this.email = email;
        this.cidade = cidade;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public int getIdCliente(){return idCliente;}
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getCidade() {return cidade;}
    public void setCidade(String cidade) {this.cidade = cidade;}
    public void setIdCliente(int idCliente) {this.idCliente = idCliente;}

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", idCliente=" + idCliente +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}

