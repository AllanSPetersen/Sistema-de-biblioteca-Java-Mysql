package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CadUsuario;

/**
 *
 * @author luan
 */
public class CadUsuarioDAO {
    
    private Connection connection;

    public CadUsuarioDAO() throws SQLException {
        this.connection = ConnectionDAO.getConnection();
    }
    
    public void insert(CadUsuario administrativo) throws SQLException {
        
        String sql = "INSERT INTO administrativo (nome, cpf, genero, dnascimento, endereco, telefone, username, password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, md5(?))";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setString(1, administrativo.getName());
        stmt.setString(2, administrativo.getCpf());
        stmt.setString(3, administrativo.getGenero());
        stmt.setString(4, administrativo.getDNascimento());
        stmt.setString(5, administrativo.getEndereco());
        stmt.setString(6, administrativo.getTelefone());
        stmt.setString(7, administrativo.getUsername());
        stmt.setString(8, administrativo.getPassword());
        
        stmt.execute();
        stmt.close();
    }
    
    public void update(CadUsuario administrativo) throws SQLException {
        
        String sql = "UPDATE administrativo SET nome=?, cpf=?, genero=?, dnascimento=?, endereco=?, telefone=?,"
                + " username=?, password=md5(?) WHERE id=?";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setInt(9, administrativo.getId());
        stmt.setString(1, administrativo.getName());
        stmt.setString(2, administrativo.getCpf());
        stmt.setString(3, administrativo.getGenero());
        stmt.setString(4, administrativo.getDNascimento());
        stmt.setString(5, administrativo.getEndereco());
        stmt.setString(6, administrativo.getTelefone());
        stmt.setString(7, administrativo.getUsername());
        stmt.setString(8, administrativo.getPassword());
                
        stmt.execute();
        stmt.close();
    }
    
    public void delete(int id) throws SQLException {
        
        String sql = "DELETE FROM administrativo WHERE id=?";
        
        PreparedStatement stmt = connection.prepareCall(sql);
        
        stmt.setInt(1, id);
        
        stmt.execute();
        stmt.close();
    } 
    
    public List<CadUsuario> select(String name) throws SQLException {
    
        String sql = "SELECT * FROM administrativo WHERE nome LIKE ?";
        PreparedStatement stmt = connection.prepareCall(sql);
            
        stmt.setString(1, name);
        
        ResultSet rs = stmt.executeQuery();
        
        List<CadUsuario> list = new ArrayList<>();
        
        while (rs.next()) {
            CadUsuario administrativo =  new CadUsuario();
            
            administrativo.setId(rs.getInt("id"));
            administrativo.setName(rs.getString("nome"));
            administrativo.setCpf(rs.getString("cpf"));
            administrativo.setGenero(rs.getString("genero"));
            administrativo.setDNascimento(rs.getString("dnascimento"));
            administrativo.setEndereco(rs.getString("endereco"));
            administrativo.setTelefone(rs.getString("telefone"));
            administrativo.setUsername(rs.getString("username"));
            
            list.add(administrativo);
        }
        
        rs.close();
        stmt.close();
        
        return list;
        
    }
    
}
