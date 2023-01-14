package Klinik.Controller.Patient;

import Klinik.Dao.PatientDao;
import Klinik.Dao.PatientInterface;
import Klinik.Model.Patient;
import Klinik.View.Patient.AddPatient;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientController {

    AddPatient viewPasien;
    PatientInterface patientInterface;

    public PatientController(AddPatient viewPasien) {
        this.viewPasien = viewPasien;
        this.patientInterface = new PatientDao();
    }
    public void addPasien(){


        Date date = viewPasien.getJdcDateofBirth().getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateString =dateFormat.format(date);

        String gender = viewPasien.getCmbGender().getSelectedItem().toString();
        try {
            Patient patient = new Patient();
            patient.setFirstName(viewPasien.getTxtFirstName().getText());
            patient.setLastName(viewPasien.getTxtLastName().getText());
            patient.setDateOfBirth(dateString);
            patient.setGender(gender);
            patient.setAddress(viewPasien.getTxtAddress().getText());
            patientInterface.insert(patient);
            JOptionPane.showMessageDialog(viewPasien, "Data added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(viewPasien, "Error : " + e, "Error", JOptionPane.ERROR_MESSAGE);

        }

    }
}
