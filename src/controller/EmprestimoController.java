package controller;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Livro;
import model.Emprestimo;
import model.Conta;
import model.dao.EmprestimoDAO;
import view.EmprestimoView;
import model.dao.LivroDAO;

/**
 *
 * @author luan
 */
public class EmprestimoController {

    private EmprestimoView view;
    private Emprestimo model;
    private EmprestimoDAO dao;
    private LivroDAO LivroDAO;
    private Livro book;

    public EmprestimoController(EmprestimoView view) {
        this.view = view;
    }

    public void add() {
        if (view.checksData()) {

            model = new Emprestimo();

            model.setIdContaEmprestimo(Integer.parseInt(view.getTxtIdStudent().getText()));
            model.setIdLivro(Integer.parseInt(view.getTxtIdLivro().getText()));
            model.setEmprestimoDate(view.loanDate());
            model.setRetornodata(view.returnDate());

            try {
                dao = new EmprestimoDAO();

                dao.insert(model);
                this.changesAvailability("no");

                view.showMessage("Empréstimo bem sucedido");
                view.cleanFields();
                view.disableFields();

                this.showList();
                this.showStudentList();
                this.showBookList();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                view.showMessage("Erro ao fazer empréstimo");
            }

        } else {
            view.showMessage("Preencha todos os dados");
        }
    }

    public void delete() {
        if (view.showConfirm("Excluir registro?")) {

            model = new Emprestimo();

            model.setId(Integer.parseInt(view.getTxtId().getText()));

            try {
                dao = new EmprestimoDAO();

                dao.delete(model.getId());
                this.changesAvailability("yes");

                view.showMessage("Apagado com sucesso");
                view.cleanFields();
                view.disableFields();

                this.showList();
                this.showStudentList();
                this.showBookList();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                view.showMessage("Erro ao excluir Cadastro");
            }

        }
    }

    public void giveBack() {
        if (view.showConfirm("Confirmar a devolução do livro?")) {

            model = new Emprestimo();

            model.setId(Integer.parseInt(view.getTxtId().getText()));

            try {
                dao = new EmprestimoDAO();

                dao.delete(model.getId());
                this.changesAvailability("yes");

                view.showMessage("Livro devolvido com sucesso");
                view.cleanFields();
                view.disableFields();

                this.showList();
                this.showStudentList();
                this.showBookList();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                view.showMessage("Erro ao devolver o livro");
            }

        }
    }

    public void changesAvailability(String status) throws SQLException {
        LivroDAO = new LivroDAO();
        book = new Livro();

        book.setId(Integer.parseInt(view.getIdLivro()));
        book.setDisponivel(status);

        LivroDAO.updateAvailability(book);

    }

    public void fillInData() {

        int selectedLine = view.getTblLoans().getSelectedRow();

        if (selectedLine != -1) {

            view.fillsFields(
                    view.getTblSearch().getValueAt(selectedLine, 0).hashCode(),
                    view.getTblSearch().getValueAt(selectedLine, 1).hashCode(),
                    view.getTblSearch().getValueAt(selectedLine, 2).hashCode(),
                    view.getTblSearch().getValueAt(selectedLine, 3).toString().replace("-", ""),
                    view.getTblSearch().getValueAt(selectedLine, 4).toString().replace("-", "")
            );
        }

        view.enableFields();
    }

    public void fillStudentData() {

        int selectedLine = view.getTblSearchStudent().getSelectedRow();

        if (selectedLine != -1) {

            view.fillStudentFields(
                    view.getTblSearchStudent().getValueAt(selectedLine, 0).hashCode()
            );
        }

        view.enableFields();
    }

    public void fillBookData() {

        int selectedLine = view.getTblSearchBook().getSelectedRow();

        if (selectedLine != -1) {
            
            if (view.getTblSearchBook().getValueAt(selectedLine, 3).equals("yes")) {
                view.fillBookFields(
                        view.getTblSearchBook().getValueAt(selectedLine, 0).hashCode()
                );
            } else {
                view.showMessage("livro indisponível");
            }
        }

        view.enableFields();
    }

    public void showStudentList() {
        List<Conta> student;

        try {
            dao = new EmprestimoDAO();

            String name = "%" + view.getTxtTitleSearch().getText() + "%";

            student = dao.selectStudent(name);

            if (!student.isEmpty()) {

                String[] line = new String[]{null, null, null};

                DefaultTableModel data = (DefaultTableModel) view.getTblSearchStudent().getModel();
                data.setNumRows(0);

                for (int i = 0; i < student.size(); i++) {
                    data.addRow(line);
                    data.setValueAt(student.get(i).getId(), i, 0);
                    data.setValueAt(student.get(i).getName(), i, 1);
                    data.setValueAt(student.get(i).getCpf(), i, 2);
                }

            }

        } catch (SQLException e) {
            view.showMessage("Erro ao listar Alunos");
        }

    }

    public void showBookList() {
        List<Livro> book;

        try {
            dao = new EmprestimoDAO();

            String title = "%" + view.getTxtTitleSearch().getText() + "%";

            book = dao.selectBook(title);

            if (!book.isEmpty()) {

                String[] line = new String[]{null, null, null};

                DefaultTableModel data = (DefaultTableModel) view.getTblSearchBook().getModel();
                data.setNumRows(0);

                for (int i = 0; i < book.size(); i++) {
                    data.addRow(line);
                    data.setValueAt(book.get(i).getId(), i, 0);
                    data.setValueAt(book.get(i).getTitulo(), i, 1);
                    data.setValueAt(book.get(i).getAutor(), i, 2);
                    data.setValueAt(book.get(i).getDisponivel(), i, 3);
                }

            }

        } catch (SQLException e) {
            view.showMessage("Erro ao listar livros");
        }
    }

    public void showList() {
        List<Emprestimo> loan;

        try {
            dao = new EmprestimoDAO();

            loan = dao.select();

            if (!loan.isEmpty()) {

                String[] line = new String[]{null, null, null, null, null};

                DefaultTableModel data = (DefaultTableModel) view.getTblLoans().getModel();
                data.setNumRows(0);

                for (int i = 0; i < loan.size(); i++) {
                    data.addRow(line);
                    data.setValueAt(loan.get(i).getId(), i, 0);
                    data.setValueAt(loan.get(i).getIdContaEmprestimo(), i, 1);
                    data.setValueAt(loan.get(i).getIdLivro(), i, 2);
                    data.setValueAt(loan.get(i).getEmprestimoDate(), i, 3);
                    data.setValueAt(loan.get(i).getRetornodata(), i, 4);
                }

            }

        } catch (SQLException e) {
            view.showMessage("Erro ao listar Empréstimos");
        }
    }
}
