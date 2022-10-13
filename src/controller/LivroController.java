package controller;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Livro;
import model.dao.LivroDAO;
import view.LivroView;

/**
 *
 * @author luan
 */
public class LivroController {

    private LivroView view;
    private Livro model;
    private LivroDAO dao;

    public LivroController(LivroView view) {
        this.view = view;
    }

    public void add() {
        if (view.checksData()) {

            model = new Livro();

            model.setTitulo(view.getTxtTitle().getText());
            model.setAutor(view.getTxtAuthor().getText());
            model.setEdicao(view.getTxtEdition().getText());
            model.setAno(view.getTxtYear().getText());
            model.setDisponivel(view.getCmbAvailable().getSelectedItem().toString());

            try {
                dao = new LivroDAO();

                dao.insert(model);

                view.showMessage("Registrado com sucesso");
                view.cleanFields();
                view.disableFields();
                
                this.showList();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                view.showMessage("Erro ao cadastrar Aluno");
            }

        } else {
            view.showMessage("Preencha todos os dados");
        }
    }

    public void alter() {
        if (view.checksData()) {

            if (view.showConfirm("Confirmar alteração de registro?")) {
                model = new Livro();

                model.setId(Integer.parseInt(view.getTxtId().getText()));
                model.setTitulo(view.getTxtTitle().getText());
                model.setAutor(view.getTxtAuthor().getText());
                model.setEdicao(view.getTxtEdition().getText());
                model.setAno(view.getTxtYear().getText());
                model.setDisponivel(view.getCmbAvailable().getSelectedItem().toString());

                try {
                    dao = new LivroDAO();

                    dao.update(model);

                    view.showMessage("Alterado com sucesso");
                    view.cleanFields();
                    view.disableFields();

                    this.showList();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    view.showMessage("Erro ao cadastrar o livro");
                }
            }
        } else {
            view.showMessage("Preencha todos os dados");
        }
    }

    public void delete() {
        if (view.showConfirm("Excluir registro?")) {

            model = new Livro();

            model.setId(Integer.parseInt(view.getTxtId().getText()));

            try {
                dao = new LivroDAO();

                dao.delete(model.getId());

                view.showMessage("Apagado com sucesso");
                view.cleanFields();
                view.disableFields();

                this.showList();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                view.showMessage("Erro ao excluir o livro");
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
                    view.getTblSearch().getValueAt(selectedLine, 4).toString(),
                    view.getTblSearch().getValueAt(selectedLine, 5).toString()
            );
        }

        view.enableFields();
    }

    public void showList() {
        List<Livro> book;

        try {
            dao = new LivroDAO();

            String title = "%" + view.getTxtTitleSearch().getText() + "%";

            book = dao.select(title);

            if (!book.isEmpty()) {

                String[] line = new String[]{null, null, null, null, null, null};

                DefaultTableModel data = (DefaultTableModel) view.getTblSearch().getModel();
                data.setNumRows(0);

                for (int i = 0; i < book.size(); i++) {
                    data.addRow(line);
                    data.setValueAt(book.get(i).getId(), i, 0);
                    data.setValueAt(book.get(i).getTitulo(), i, 1);
                    data.setValueAt(book.get(i).getAutor(), i, 2);
                    data.setValueAt(book.get(i).getEdicao(), i, 3);
                    data.setValueAt(book.get(i).getAno(), i, 4);
                    data.setValueAt(book.get(i).getDisponivel(), i, 5);
                }

            } 

        } catch (SQLException e) {
            view.showMessage("Erro ao listar Aluno");
        }

    }
}
