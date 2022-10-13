package model;

/**
 *
 * @author luan
 */
public class CadUsuario extends Pessoa {
    
    private String username;
    private String password;

    public CadUsuario() {
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
