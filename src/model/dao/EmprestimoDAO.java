package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Livro;
import model.Emprestimo;
import model.Conta;

/**
 *
 * @author luan
 */
public class EmprestimoDAO {

    private Connection connection;

    public EmprestimoDAO() throws SQLException {
        this.connection = ConnectionDAO.getConnection();
    }
    
    public void insert(Emprestimo loan) throws SQLException {
        
        String sql = "INSERT INTO emprestimo (id_contaemprestimo, id_livro, data_emprestimo, retorno_data) "
                + "VALUES (?, ?, ?, ?)";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setInt(1, loan.getIdContaEmprestimo());
        stmt.setInt(2, loan.getIdLivro());
        stmt.setString(3, loan.getEmprestimoDate());
        stmt.setString(4, loan.getRetornodata());
        
        stmt.execute();
        stmt.close();
    }
    
    public void delete(int id) throws SQLException {
        
        String sql = "DELETE FROM emprestimo WHERE id=?";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setInt(1, id);
        
        stmt.execute();
        stmt.close();
    } 
    
    public List<Emprestimo> select() throws SQLException {
    
        String sql = "SELECT * FROM emprestimo";
        PreparedStatement stmt = connection.prepareCall(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        List<Emprestimo> list = new ArrayList<>();
        
        while (rs.next()) {
            Emprestimo loan = new Emprestimo();
            
            loan.setId(rs.getInt("id"));
            loan.setIdContaEmprestimo(rs.getInt("id_contaemprestimo"));
            loan.setIdLivro(rs.getInt("id_livro"));
            loan.setEmprestimoDate(rs.getString("data_emprestimo"));
            loan.setRetornodata(rs.getString("retorno_data"));
            
            list.add(loan);
        }
        
        rs.close();
        stmt.close();
        
        return list;
        
    }
    
    public List<Conta> selectStudent(String name) throws SQLException {
    
        String sql = "SELECT * FROM contaemprestimo WHERE nome LIKE ?";
        PreparedStatement stmt = connection.prepareCall(sql);
            
        stmt.setString(1, name);
        
        ResultSet rs = stmt.executeQuery();
        
        List<Conta> list = new ArrayList<>();
        
        while (rs.next()) {
            Conta student = new Conta();
            
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("nome"));
            student.setCpf(rs.getString("cpf"));
            student.setGenero(rs.getString("genero"));
            student.setDNascimento(rs.getString("dnascimento"));
            student.setEndereco(rs.getString("endereco"));
            student.setTelefone(rs.getString("telefone"));
            student.setEmail(rs.getString("email"));
            
            list.add(student);
        }
        
        rs.close();
        stmt.close();
        
        return list;
        
    }
    
    public List<Livro> selectBook(String title) throws SQLException {
    
        String sql = "SELECT * FROM livro WHERE titulo LIKE ?";
        PreparedStatement stmt = connection.prepareCall(sql);
            
        stmt.setString(1, title);
        
        ResultSet rs = stmt.executeQuery();
        
        List<Livro> list = new ArrayList<>();
        
        while (rs.next()) {
            Livro book = new Livro();
            
            book.setId(rs.getInt("id"));
            book.setTitulo(rs.getString("titulo"));
            book.setAutor(rs.getString("autor"));
            book.setEdicao(rs.getString("edicao"));
            book.setAno(rs.getString("ano"));
            book.setDisponivel(rs.getString("disponivel"));
            
            list.add(book);
        }
        
        rs.close();
        stmt.close();
        
        return list;
        
    }
}
