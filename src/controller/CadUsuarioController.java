package controller;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.CadUsuario;
import model.dao.CadUsuarioDAO;
import view.CadUsuarioView;

/**
 *
 * @author luan
 */
public class CadUsuarioController {

    private CadUsuarioView view;
    private CadUsuario model;
    private CadUsuarioDAO dao;

    public CadUsuarioController(CadUsuarioView view) {
        this.view = view;
    }

    public void add() {

        if (view.checksData()) {
            if (view.checkPasswords()) {

                model = new CadUsuario();

                model.setName(view.getTxtName().getText());
                model.setCpf(view.getTxtCPF().getText().replace(".", "").replace("-", ""));
                model.setGenero(view.getCmbSex().getSelectedItem().toString());
                model.setDNascimento(view.getTxtBirthday().getText().replace("/", "-"));
                model.setEndereco(view.getTxtAddress().getText());
                model.setTelefone(view.getTxtTelephone().getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""));
                model.setUsername(view.getTxtUsername().getText());
                model.setPassword(new String(view.getPwdPassword().getPassword()));

                try {
                    dao = new CadUsuarioDAO();

                    dao.insert(model);

                    view.showMessage("Registrado com sucesso");
                    view.cleanFields();
                    view.disableFields();

                    this.showList();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    view.showMessage("Erro ao cadastrar Funcionário");
                }

            } else {
                view.showMessage("ERRO: Senhas diferentes");
            }

        } else {
            view.showMessage("Preencha todos os dados!");
        }

    }

    public void alter() {
        if (view.checksData()) {
            if (view.checkPasswords()) {

                if (view.showConfirm("Confirmar alteração de registro?")) {

                    model = new CadUsuario();

                    model.setId(Integer.parseInt(view.getTxtId().getText()));
                    model.setName(view.getTxtName().getText());
                    model.setCpf(view.getTxtCPF().getText().replace(".", "").replace("-", ""));
                    model.setGenero(view.getCmbSex().getSelectedItem().toString());
                    model.setDNascimento(view.getTxtBirthday().getText().replace("/", "-"));
                    model.setEndereco(view.getTxtAddress().getText());
                    model.setTelefone(view.getTxtTelephone().getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""));
                    model.setUsername(view.getTxtUsername().getText());
                    model.setPassword(new String(view.getPwdPassword().getPassword()));

                    try {
                        dao = new CadUsuarioDAO();

                        dao.update(model);

                        view.showMessage("Alterado com sucesso");
                        view.cleanFields();
                        view.disableFields();

                        this.showList();

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        view.showMessage("Erro ao cadastrar Funcionário");
                    }
                }

            } else {
                view.showMessage("Erro: senhas diferentes");
            }

        } else {
            view.showMessage("Preencha todos os dados!");
        }

    }

    public void delete() {
        if (view.showConfirm("Excluir registro?")) {

            model = new CadUsuario();

            model.setId(Integer.parseInt(view.getTxtId().getText()));

            try {
                dao = new CadUsuarioDAO();

                dao.delete(model.getId());

                view.showMessage("Apagado com sucesso");
                view.cleanFields();
                view.disableFields();

                this.showList();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                view.showMessage("Erro ao excluir Funcionário");
            }

        }
    }

    public void fillInData() {

        int selectedLine = view.getTblSearch().getSelectedRow();

        if (selectedLine != -1) {

            view.fillsFields(
                    view.getTblSearch().getValueAt(selectedLine, 0).hashCode(),
                    view.getTblSearch().getValueAt(selectedLine, 1).toString(),
                    view.getTblSearch().getValueAt(selectedLine, 2).toString(),
                    view.getTblSearch().getValueAt(selectedLine, 3).toString(),
                    view.getTblSearch().getValueAt(selectedLine, 4).toString().replace("-", ""),
                    view.getTblSearch().getValueAt(selectedLine, 5).toString(),
                    view.getTblSearch().getValueAt(selectedLine, 6).toString(),
                    view.getTblSearch().getValueAt(selectedLine, 7).toString()
            );
        }

        view.enableFields();
    }

    public void showList() {
        List<CadUsuario> employee;

        try {
            dao = new CadUsuarioDAO();
            
            String name = "%" + view.getTxtNameSearch().getText() + "%";

            employee = dao.select(name);

            if (!employee.isEmpty()) {

                String[] line = new String[]{null, null, null, null, null, null, null, null};

                DefaultTableModel data = (DefaultTableModel) view.getTblSearch().getModel();
                data.setNumRows(0);

                for (int i = 0; i < employee.size(); i++) {
                    data.addRow(line);
                    data.setValueAt(employee.get(i).getId(), i, 0);
                    data.setValueAt(employee.get(i).getName(), i, 1);
                    data.setValueAt(employee.get(i).getCpf(), i, 2);
                    data.setValueAt(employee.get(i).getGenero(), i, 3);
                    data.setValueAt(employee.get(i).getDNascimento(), i, 4);
                    data.setValueAt(employee.get(i).getTelefone(), i, 5);
                    data.setValueAt(employee.get(i).getEndereco(), i, 6);
                    data.setValueAt(employee.get(i).getUsername(), i, 7);
                }

            }

        } catch (SQLException e) {
            view.showMessage("Erro ao excluir Funcionário");
        }

    }

}
