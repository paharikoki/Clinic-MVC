package Klinik.Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class KunjunganTableModel extends AbstractTableModel {
    List<Kunjungan> kunjunganList;

    public KunjunganTableModel(List<Kunjungan> kunjunganList) {
        this.kunjunganList = kunjunganList;
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
        return kunjunganList.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return kunjunganList.get(rowIndex).getVisitId();
            case 1:
                return kunjunganList.get(rowIndex).getPasien().getPatientId();
            case 2:
                return kunjunganList.get(rowIndex).getPasien().getFirstName()+" "+kunjunganList.get(rowIndex).getPasien().getLastName();
            case 3:
                return kunjunganList.get(rowIndex).getDokter().getDoctorId();
            case 4:
                return kunjunganList.get(rowIndex).getDokter().getFirstName()+" "+kunjunganList.get(rowIndex).getPasien().getLastName();
            case 5:
                return kunjunganList.get(rowIndex).getVisitDate();
            case 6:
                return kunjunganList.get(rowIndex).getDiagnosis();
            case 7:
                return kunjunganList.get(rowIndex).getTreatment();
            default:
                return null;
        }
    }
}
