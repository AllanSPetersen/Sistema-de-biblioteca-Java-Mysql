package controller;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Conta;
import model.dao.ContaEmprestimoDAO;
import view.ContaView;

/**
 *
 * @author luan
 */
public class ContaEmprestimoController {
    
    private ContaView view;
    private Conta model;
    private ContaEmprestimoDAO dao;

    public ContaEmprestimoController(ContaView view) {
        this.view = view;
    }
    
    public void add() {

        if (view.checksData()) {
            if (view.checkPasswords()) {

                model = new Conta();

                model.setName(view.getTxtName().getText());
                model.setCpf(view.getTxtCPF().getText().replace(".", "").replace("-", ""));
                model.setGenero(view.getCmbSex().getSelectedItem().toString());
                model.setDNascimento(view.getTxtBirthday().getText().replace("/", "-"));
                model.setEndereco(view.getTxtAddress().getText());
                model.setTelefone(view.getTxtTelephone().getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""));
                model.setEmail(view.getTxtEmail().getText());

                try {
                    dao = new ContaEmprestimoDAO();

                    dao.insert(model);

                    view.showMessage("Registrado com sucesso");
                    view.cleanFields();
                    view.disableFields();

                    this.showList();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    view.showMessage("Erro ao cadastrar usuario de emprestimo");
                }

            } else {
                view.showMessage("Erro: e-mails diferentes");
            }

        } else {
            view.showMessage("Preencha todos os dados!");
        }

    }

    public void alter() {
        if (view.checksData()) {
            if (view.checkPasswords()) {

                if (view.showConfirm("Confirmar alteração de registro?")) {

                    model = new Conta();

                    model.setId(Integer.parseInt(view.getTxtId().getText()));
                    model.setName(view.getTxtName().getText());
                    model.setCpf(view.getTxtCPF().getText().replace(".", "").replace("-", ""));
                    model.setGenero(view.getCmbSex().getSelectedItem().toString());
                    model.setDNascimento(view.getTxtBirthday().getText().replace("/", "-"));
                    model.setEndereco(view.getTxtAddress().getText());
                    model.setTelefone(view.getTxtTelephone().getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""));
                    model.setEmail(view.getTxtEmail().getText());

                    try {
                        dao = new ContaEmprestimoDAO();

                        dao.update(model);

                        view.showMessage("Alterado com sucesso");
                        view.cleanFields();
                        view.disableFields();

                        this.showList();

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        view.showMessage("Erro ao cadastrar Aluno");
                    }
                }

            } else {
                view.showMessage("Erro: e-mails diferentes");
            }

        } else {
            view.showMessage("Preencha todos os dados!");
        }

    }

    public void delete() {
        if (view.showConfirm("Excluir registro?")) {

            model = new Conta();

            model.setId(Integer.parseInt(view.getTxtId().getText()));

            try {
                dao = new ContaEmprestimoDAO();

                dao.delete(model.getId());

                view.showMessage("Apagado com sucesso");
                view.cleanFields();
                view.disableFields();

                this.showList();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                view.showMessage("Erro ao excluir Aluno");
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
        List<Conta> student;

        try {
            dao = new ContaEmprestimoDAO();
            
            String name = "%" + view.getTxtNameSearch().getText() + "%";

            student = dao.select(name);

            if (!student.isEmpty()) {

                String[] line = new String[]{null, null, null, null, null, null, null};

                DefaultTableModel data = (DefaultTableModel) view.getTblSearch().getModel();
                data.setNumRows(0);

                for (int i = 0; i < student.size(); i++) {
                    data.addRow(line);
                    data.setValueAt(student.get(i).getId(), i, 0);
                    data.setValueAt(student.get(i).getName(), i, 1);
                    data.setValueAt(student.get(i).getCpf(), i, 2);
                    data.setValueAt(student.get(i).getGenero(), i, 3);
                    data.setValueAt(student.get(i).getDNascimento(), i, 4);
                    data.setValueAt(student.get(i).getTelefone(), i, 5);
                    data.setValueAt(student.get(i).getEndereco(), i, 6);
                    data.setValueAt(student.get(i).getEmail(), i, 7);
                }

            }

        } catch (SQLException e) {
            view.showMessage("Erro ao listar Aluno");
        }

    }
}
