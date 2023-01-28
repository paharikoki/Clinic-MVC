package Klinik.Model;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientTableModel extends AbstractTableModel {
    List<Patient> patientList;
    private Object[][] data;
    private Map<Integer,Integer> idMap;
    private String[] columnName={"No","First Name","Last Name","Date Of Birth","Gender","Address"};

    public PatientTableModel(List<Patient> patientList) {
        this.patientList = patientList;
        data= new Object[patientList.size()][columnName.length];
        idMap = new HashMap<>();
        for (int i=0;i<patientList.size();i++){
            Patient patients = patientList.get(i);
            data[i][0]=i+1;
            data[i][1]=patients.getFirstName();
            data[i][2]=patients.getLastName();
            data[i][3]=patients.getDateOfBirth();
            data[i][4]=patients.getGender();
            data[i][5]=patients.getAddress();
            idMap.put(i,patients.getPatientId());
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
