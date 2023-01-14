package Klinik.Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DoctorTableModel extends AbstractTableModel {
    List<Doctor> doctorList;

    public DoctorTableModel(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Number";
            case 1:
                return "First Name";
            case 2:
                return "Last Name";
            case 3 :
                return "Specialty";
            case 4:
                return "Phone Number";
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        return doctorList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return doctorList.get(rowIndex).getDoctorId();
            case 1:
                return doctorList.get(rowIndex).getFirstName();
            case 2:
                return doctorList.get(rowIndex).getLastName();
            case 3:
                return doctorList.get(rowIndex).getSpecialty();
            case 4:
                return doctorList.get(rowIndex).getPhoneNumber();
            default:
                return null;
        }
    }
}
