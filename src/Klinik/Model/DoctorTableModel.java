package Klinik.Model;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorTableModel extends AbstractTableModel {
    List<Doctor> doctorList;
    private Object[][] data;
    private Map<Integer, Integer> idMap;
    private String[] columnName={"No","First Name","Last Name","Specialty","Phone Number"};

    public DoctorTableModel(List<Doctor> doctorList) {
        this.doctorList = doctorList;
        data = new Object[doctorList.size()][columnName.length];
        idMap = new HashMap<>();
        for (int i =0;i<doctorList.size();i++){
            Doctor doctors = doctorList.get(i);
            data[i][0]=i+1;
            data[i][1]=doctors.getFirstName();
            data[i][2]=doctors.getLastName();
            data[i][3]=doctors.getSpecialty();
            data[i][4]=doctors.getPhoneNumber();
            idMap.put(i,doctors.getDoctorId());
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    public int getIdAt(int rowIndex){
        return idMap.get(rowIndex);
    }
}
