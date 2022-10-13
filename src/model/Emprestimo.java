package model;

/**
 *
 * @author luan
 */
public class Emprestimo {
    
    private int id;
    private int idContaEmprestimo;
    private int idLivro;
    private String EmprestimoDate;
    private String Retornodata;

    public Emprestimo() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdContaEmprestimo() {
        return idContaEmprestimo;
    }

    public void setIdContaEmprestimo(int idContaEmprestimo) {
        this.idContaEmprestimo = idContaEmprestimo;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getEmprestimoDate() {
        return EmprestimoDate;
    }

    public void setEmprestimoDate(String EmprestimoDate) {
        this.EmprestimoDate = EmprestimoDate;
    }

    public String getRetornodata() {
        return Retornodata;
    }

    public void setRetornodata(String Retornodata) {
        this.Retornodata = Retornodata;
    }
    
}
