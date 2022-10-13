package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Conta;

/**
 *
 * @author luan
 */
public class ContaEmprestimoDAO {

    private Connection connection;
    
    public ContaEmprestimoDAO() throws SQLException {
        this.connection = ConnectionDAO.getConnection();
    }

    
    public void insert(Conta student) throws SQLException {
        
        String sql = "INSERT INTO contaemprestimo (nome, cpf, genero, dnascimento, endereco, telefone, email) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setString(1, student.getName());
        stmt.setString(2, student.getCpf());
        stmt.setString(3, student.getGenero());
        stmt.setString(4, student.getDNascimento());
        stmt.setString(5, student.getEndereco());
        stmt.setString(6, student.getTelefone());
        stmt.setString(7, student.getEmail());
        
        stmt.execute();
        stmt.close();
    }
    
    public void update(Conta student) throws SQLException {
        
        String sql = "UPDATE contaemprestimo SET nome=?, cpf=?, genero=?, dnascimento=?, endereco=?, telefone=?,"
                + " email=? WHERE id=?";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setInt(8, student.getId());
        stmt.setString(1, student.getName());
        stmt.setString(2, student.getCpf());
        stmt.setString(3, student.getGenero());
        stmt.setString(4, student.getDNascimento());
        stmt.setString(5, student.getEndereco());
        stmt.setString(6, student.getTelefone());
        stmt.setString(7, student.getEmail());
                
        stmt.execute();
        stmt.close();
    }
    
    public void delete(int id) throws SQLException {
        
        String sql = "DELETE FROM contaemprestimo WHERE id=?";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setInt(1, id);
        
        stmt.execute();
        stmt.close();
    } 
    
    public List<Conta> select(String name) throws SQLException {
    
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
    
}
