package Klinik.Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class VisitTableModel extends AbstractTableModel {
    List<Visit> visitList;

    public VisitTableModel(List<Visit> visitList) {
        this.visitList = visitList;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "visitId";
            case 1:
                return "patientId";
            case 2:
                return "patientName";
            case 3:
                return "doctorId";
            case 4:
                return "doctorName";
            case 5 :
                return "visitDate";
            case 6:
                return "diagnosis";
            case 7:
                return "treatment";
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        return visitList.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return visitList.get(rowIndex).getVisitId();
            case 1:
                return visitList.get(rowIndex).getPasien().getPatientId();
            case 2:
                return visitList.get(rowIndex).getPasien().getFirstName()+" "+ visitList.get(rowIndex).getPasien().getLastName();
            case 3:
                return visitList.get(rowIndex).getDokter().getDoctorId();
            case 4:
                return visitList.get(rowIndex).getDokter().getFirstName()+" "+ visitList.get(rowIndex).getPasien().getLastName();
            case 5:
                return visitList.get(rowIndex).getVisitDate();
            case 6:
                return visitList.get(rowIndex).getDiagnosis();
            case 7:
                return visitList.get(rowIndex).getTreatment();
            default:
                return null;
        }
    }
}
