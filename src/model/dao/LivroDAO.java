package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Livro;

/**
 *
 * @author luan
 */
public class LivroDAO {

    private Connection connection;

    public LivroDAO() throws SQLException {
        this.connection = ConnectionDAO.getConnection();
    }
    
    public void insert(Livro livro) throws SQLException {
        
        String sql = "INSERT INTO livro (titulo, autor, edicao, ano, disponivel) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getAutor());
        stmt.setString(3, livro.getEdicao());
        stmt.setString(4, livro.getAno());
        stmt.setString(5, livro.getDisponivel());
        
        stmt.execute();
        stmt.close();
    }
    
    public void update(Livro livro) throws SQLException {
        
        String sql = "UPDATE livro SET titulo=?, autor=?, edicao=?, ano=?, disponivel=? WHERE id=?";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setInt(6, livro.getId());
        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getAutor());
        stmt.setString(3, livro.getEdicao());
        stmt.setString(4, livro.getAno());
        stmt.setString(5, livro.getDisponivel());
        
        stmt.execute();
        stmt.close();
    }
    
    public void updateAvailability(Livro livro) throws SQLException {
        
        String sql = "UPDATE livro SET disponivel=? WHERE id=?";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setString(1, livro.getDisponivel());
        stmt.setInt(2, livro.getId());
        
        stmt.execute();
        stmt.close();
    }
    
    public void delete(int id) throws SQLException {
        
        String sql = "DELETE FROM livro WHERE id=?";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setInt(1, id);
        
        stmt.execute();
        stmt.close();
    } 
    
    public List<Livro> select(String title) throws SQLException {
    
        String sql = "SELECT * FROM livro WHERE titulo LIKE ?";
        PreparedStatement stmt = connection.prepareCall(sql);
            
        stmt.setString(1, title);
        
        ResultSet rs = stmt.executeQuery();
        
        List<Livro> list = new ArrayList<>();
        
        while (rs.next()) {
            Livro livro = new Livro();
            
            livro.setId(rs.getInt("id"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setEdicao(rs.getString("edicao"));
            livro.setAno(rs.getString("ano"));
            livro.setDisponivel(rs.getString("disponivel"));
            
            list.add(livro);
        }
        
        rs.close();
        stmt.close();
        
        return list;
        
    }
    
}