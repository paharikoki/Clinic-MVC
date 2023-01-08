package Klinik.Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DokterTableModel extends AbstractTableModel {
    List<Dokter> dokterList;

    public DokterTableModel(List<Dokter> dokterList) {
        this.dokterList = dokterList;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "doctorId";
            case 1:
                return "fisrtName";
            case 2:
                return "lastName";
            case 3 :
                return "specialty";
            case 4:
                return "phoneNumber";
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        return dokterList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return dokterList.get(rowIndex).getDoctorId();
            case 1:
                return dokterList.get(rowIndex).getFirstName();
            case 2:
                return dokterList.get(rowIndex).getLastName();
            case 3:
                return dokterList.get(rowIndex).getSpecialty();
            case 4:
                return dokterList.get(rowIndex).getPhoneNumber();
            default:
                return null;
        }
    }
}
