package Klinik.Controller.Patient;

import Klinik.Dao.PatientDao;
import Klinik.Dao.PatientInterface;
import Klinik.Model.DoctorTableModel;
import Klinik.Model.Patient;
import Klinik.Model.PatientTableModel;
import Klinik.View.Doctor.AddDoctor;
import Klinik.View.Patient.AddPatient;
import Klinik.View.Patient.MainPatient;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientController {

    MainPatient frame;
    PatientInterface patientInterface;
    List<Patient> patients;
    public PatientController(MainPatient frame) {
        this.frame = frame;
        this.patientInterface = new PatientDao();
    }
    public void loadTablePatient(){
        Font font = frame.getTblPatient().getFont();
        int rowHeight = (int) (font.getSize() * 1.5);
        frame.getTblPatient().setRowHeight(rowHeight);
        patients = patientInterface.getAll();
        PatientTableModel patientTableModel = new PatientTableModel(patients);
        frame.getTblPatient().setModel(patientTableModel);
    }
    public Patient selectTablePatient(){
        int row =-1;
        row = frame.getTblPatient().getSelectedRow();
        if (row >= 0) {
            Patient patient = new Patient();
            PatientTableModel model = new PatientTableModel(patients);
            patient.setPatientId(model.getIdAt(row));
            patient.setFirstName((String) frame.getTblPatient().getValueAt(row, 1));
            patient.setLastName((String) frame.getTblPatient().getValueAt(row, 2));
            patient.setDateOfBirth((String) frame.getTblPatient().getValueAt(row, 3));
            patient.setGender((String) frame.getTblPatient().getValueAt(row, 4));
            patient.setAddress((String) frame.getTblPatient().getValueAt(row, 5));
            return patient;
        }else {
                return null;
        }
    }
    public void searchPatient(){
        if (frame.getTxtSearch().getText().equals("")){
            JOptionPane.showMessageDialog(frame, "Please enter the Patient's name", "Enter name", JOptionPane.PLAIN_MESSAGE);
            loadTablePatient();
        }else {
            List<Patient> patientList = new ArrayList<>();
            String name = frame.getTxtSearch().getText();
            Font font = frame.getTblPatient().getFont();
            int rowHeight = (int) (font.getSize() * 1.5);
            frame.getTblPatient().setRowHeight(rowHeight);
            patientList = patientInterface.getFindPatientName(name);
            if (!patientList.isEmpty()) {
                PatientTableModel patientTableModel = new PatientTableModel(patientList);
                frame.getTblPatient().setModel(patientTableModel);
            } else {
                JOptionPane.showMessageDialog(frame, "Patient not found", "Error", JOptionPane.ERROR_MESSAGE);
                resetSearch();
            }
        }
    }
    public void resetSearch(){

        frame.getTxtSearch().setText("");
        loadTablePatient();
    }
    public void deletePatient(){
        Patient patient = selectTablePatient();
        if (patient != null){
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure want to delete this patient?\nPatient name : "+patient.getFirstName()+" "+patient.getLastName(), "Delete Patient", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION){
                patientInterface.delete(patient.getPatientId());
                JOptionPane.showMessageDialog(frame, "Patient has been deleted", "Delete Patient", JOptionPane.INFORMATION_MESSAGE);
                loadTablePatient();
            }
        }else {
            JOptionPane.showMessageDialog(frame, "Please select the patient", "Select Patient", JOptionPane.PLAIN_MESSAGE);
        }
    }
    public void moveToViewAddPatient(){
        frame.dispose();
        new AddPatient().setVisible(true);
    }
    public void moveToViewEditPatient()  {
        Patient patient = selectTablePatient();
        if (patient != null){
             AddPatient addPatient = new AddPatient();
            addPatient.setIdPatient(patient.getPatientId());
            addPatient.getTxtFirstName().setText(patient.getFirstName());
            addPatient.getTxtLastName().setText(patient.getLastName());
            try {
                String dateString = patient.getDateOfBirth();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(dateString);
                addPatient.getJdcDateofBirth().setDate(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (patient.getGender().equals("Perempuan")){
                addPatient.getCmbGender().setSelectedIndex(1);
            }else {
                addPatient.getCmbGender().setSelectedIndex(0);
            }
            addPatient.getTxtAddress().setText(patient.getAddress());
            frame.dispose();
            addPatient.setVisible(true);
        }else {
            JOptionPane.showMessageDialog(frame, "Please select the patient", "Select Patient", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
