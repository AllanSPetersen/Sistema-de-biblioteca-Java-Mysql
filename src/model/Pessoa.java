package model;

/**
 *
 * @author luan
 */
abstract public class Pessoa {
    
    private int id;
    private String name;
    private String cpf;
    private String genero;
    private String dnascimento;
    private String endereco;
    private String telefone;

    public Pessoa(int id, String name, String cpf, String genero, String dnascimento, String endereco, String telefone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.genero = genero;
        this.dnascimento = dnascimento;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    
    public Pessoa(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pessoa() {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero.equals("Masculino")) {
            this.genero = "m";
        } else if (genero.equals("Feminino")) {
            this.genero = "f";
        }
        
        if (genero.equals("m")) {
            this.genero = "Masculino";
        } else if (genero.equals("f"))  {
            this.genero = "Feminino";
        }
    }

    public String getDNascimento() {
        return dnascimento;
    }

    public void setDNascimento(String dnascimento) {
        this.dnascimento = dnascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
