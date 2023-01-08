package Klinik.Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PasienTableModel extends AbstractTableModel {
    List<Pasien> pasienList;

    public PasienTableModel(List<Pasien> pasienList) {
        this.pasienList = pasienList;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "patientId";
            case 1:
                return "firstName";
            case 2:
                return "lastName";
            case 3 :
                return "dateOfBirth";
            case 4:
                return "gender";
            case 5:
                return "address";
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        return pasienList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return pasienList.get(rowIndex).getFirstName();
            case 1:
                return pasienList.get(rowIndex).getLastName();
            case 2:
                return pasienList.get(rowIndex).getDateOfBirth();
            case 3:
                return pasienList.get(rowIndex).getGender();
            case 4:
                return pasienList.get(rowIndex).getAddress();
            default:
                return null;
        }
    }
}
