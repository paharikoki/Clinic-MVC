package Klinik.Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PatientTableModel extends AbstractTableModel {
    List<Patient> patientList;

    public PatientTableModel(List<Patient> patientList) {
        this.patientList = patientList;
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
        return patientList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return patientList.get(rowIndex).getFirstName();
            case 1:
                return patientList.get(rowIndex).getLastName();
            case 2:
                return patientList.get(rowIndex).getDateOfBirth();
            case 3:
                return patientList.get(rowIndex).getGender();
            case 4:
                return patientList.get(rowIndex).getAddress();
            default:
                return null;
        }
    }
}
