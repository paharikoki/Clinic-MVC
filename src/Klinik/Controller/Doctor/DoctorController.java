package Klinik.Controller.Doctor;

import Klinik.Dao.DoctorDao;
import Klinik.Dao.DoctorInterface;
import Klinik.Model.Doctor;
import Klinik.Model.DoctorTableModel;
import Klinik.View.Doctor.AddDoctor;
import Klinik.View.Doctor.MainDoctor;

import javax.swing.*;
import java.awt.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {
    DoctorInterface doctorInterface;
    MainDoctor mainDoctor;
    AddDoctor addDoctor;
    List<Doctor> doctors;

    public DoctorController(MainDoctor mainDoctor, AddDoctor addDoctor) {
        this.doctorInterface = new DoctorDao();
        this.mainDoctor = mainDoctor;
        this.addDoctor = addDoctor;
        this.doctors = doctors;
    }

    public void loadTableDoctors() {
        Font font = mainDoctor.getTblDoctor().getFont();
        int rowHeight = (int) (font.getSize() * 1.5);
        mainDoctor.getTblDoctor().setRowHeight(rowHeight);
        doctors = doctorInterface.getAll();
        DoctorTableModel doctorTableModel = new DoctorTableModel(doctors);
        mainDoctor.getTblDoctor().setModel(doctorTableModel);
    }

    public void insertNewDoctor(){
        if (addDoctor.getTxtFirstName().equals("") || addDoctor.getTxtLastName().equals("") ||
                addDoctor.getTxtSpecialty().equals("") || addDoctor.getTxtPhoneNumber().equals("")){
            JOptionPane.showMessageDialog(addDoctor, "Please fill in the required field!", "Error", JOptionPane.WARNING_MESSAGE);
        }else {
            String phoneNumber = addDoctor.getTxtPhoneNumber().getText();
            phoneNumber = phoneNumber.replaceAll(" ","");

            try {
                Doctor doctor = new Doctor();
                doctor.setFirstName(addDoctor.getTxtFirstName().getText());
                doctor.setLastName(addDoctor.getTxtLastName().getText());
                doctor.setSpecialty(addDoctor.getTxtSpecialty().getText());
                doctor.setPhoneNumber(phoneNumber);
                doctorInterface.insert(doctor);
                JOptionPane.showMessageDialog(addDoctor, "New doctor inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                addDoctor.setTxtFirstName(null);
                addDoctor.setTxtLastName(null);
                addDoctor.setTxtSpecialty(null);
                addDoctor.setTxtPhoneNumber(null);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(addDoctor, "Failed to insert new doctor!\nError : " + e, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }


    }
}
