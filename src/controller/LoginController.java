package controller;

import view.LoginView;
import view.PrincipalView;
import model.dao.LoginDAO;

/**
 *
 * @author luan
 */
public class LoginController {

    private final LoginView view;
    private final PrincipalView main;
    private final LoginDAO dao;

    public LoginController(LoginView view) {
        this.view = view;
        this.main = new PrincipalView();
        this.dao = new LoginDAO();
    }
    
    public void enterSystem() {
        String username = view.getUsername();
        String password = view.getPassword();
        
        boolean login = dao.makeLogin(username, password);
        
        if (login) {
            main.setVisible(true);
            view.dispose();
        } else {
            view.showMessage("Usuário ou senha incorretos!");
        }
        
    }
    
}
