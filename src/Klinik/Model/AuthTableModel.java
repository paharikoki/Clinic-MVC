package Klinik.Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AuthTableModel extends AbstractTableModel {
    List<Auth> authList;

    public AuthTableModel(List<Auth> authList) {
        this.authList = authList;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "id";
            case 1:
                return "fullname";
            case 2:
                return "username";
            case 3:
                return "password";
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        return authList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return authList.get(rowIndex).getId();
            case 1:
                return authList.get(rowIndex).getFullname();
            case 2:
                return authList.get(rowIndex).getUsername();
            case 3:
                return authList.get(rowIndex).getPassword();
            default:
                return null;
        }
    }
}
