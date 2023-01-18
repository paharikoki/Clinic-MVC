package Klinik.Controller.Doctor;

import Klinik.Dao.DoctorDao;
import Klinik.Dao.DoctorInterface;
import Klinik.Model.Doctor;
import Klinik.Model.DoctorTableModel;
import Klinik.View.Doctor.EditDoctor;
import Klinik.View.Doctor.MainDoctor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {
    DoctorInterface doctorInterface;
    MainDoctor frame;
    List<Doctor> doctors;

    public DoctorController(MainDoctor frame) {
        this.doctorInterface = new DoctorDao();
        this.frame = frame;
        this.doctors = doctors;
    }
    public void loadTableDoctors() {
        Font font = frame.getTblDoctor().getFont();
        int rowHeight = (int) (font.getSize() * 1.5);
        frame.getTblDoctor().setRowHeight(rowHeight);
        doctors = doctorInterface.getAll();
        DoctorTableModel doctorTableModel = new DoctorTableModel(doctors);
        frame.getTblDoctor().setModel(doctorTableModel);
    }
    public Doctor selectTableDoctor() {
        int row =-1;
        row = frame.getTblDoctor().getSelectedRow();
        if (row >= 0) {
            Doctor doctor = new Doctor();
            doctor.setDoctorId((Integer) frame.getTblDoctor().getValueAt(row, 0));
            doctor.setFirstName((String) frame.getTblDoctor().getValueAt(row, 1));
            doctor.setLastName((String) frame.getTblDoctor().getValueAt(row, 2));
            doctor.setSpecialty((String) frame.getTblDoctor().getValueAt(row, 3));
            doctor.setPhoneNumber((String) frame.getTblDoctor().getValueAt(row, 4));
            return doctor;
        }else{
            return null;
        }
    }
    public void viewEditDoctor(){
        if (selectTableDoctor() != null){
            EditDoctor editDoctor = new EditDoctor();
            int idDoctor =selectTableDoctor().getDoctorId();
            String firstName = selectTableDoctor().getFirstName();
            String lastName = selectTableDoctor().getLastName();
            String specialty = selectTableDoctor().getSpecialty();
            String phoneNumber = selectTableDoctor().getPhoneNumber();
            editDoctor.setIdDoctor(idDoctor);
            editDoctor.getTxtFirstName().setText(firstName);
            editDoctor.getTxtLastName().setText(lastName);
            editDoctor.getTxtSpecialty().setText(specialty);
            editDoctor.getTxtPhoneNumber().setText(phoneNumber);
            editDoctor.setVisible(true);
            frame.dispose();
        }else{
            JOptionPane.showMessageDialog(frame, "Please select a doctor!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteDoctor(){
        if (selectTableDoctor() != null){
            int idDoctor =selectTableDoctor().getDoctorId();
            int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this?\n" +
                    "Doctor name : "+selectTableDoctor().getFirstName()+" " +selectTableDoctor().getLastName(), "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    doctorInterface.delete(idDoctor);
                    loadTableDoctors();
                    JOptionPane.showMessageDialog(frame, "Doctor deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                JOptionPane.showMessageDialog(frame, "Deletion cancelled by user.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(frame, "Please select a doctor!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void searchDoctor(){
        if (frame.getTxtSearch().getText().equals("")){
            JOptionPane.showMessageDialog(frame, "Please enter the doctor's name", "Enter name", JOptionPane.PLAIN_MESSAGE);
            loadTableDoctors();
        }else {
            List<Doctor> doctorList = new ArrayList<Doctor>();
            String name = frame.getTxtSearch().getText();
            Font font = frame.getTblDoctor().getFont();
            int rowHeight = (int) (font.getSize() * 1.5);
            frame.getTblDoctor().setRowHeight(rowHeight);
            doctorList = doctorInterface.getFindDoctorName(name);
            if (!doctorList.isEmpty()){
                DoctorTableModel doctorTableModel = new DoctorTableModel(doctorList);
                frame.getTblDoctor().setModel(doctorTableModel);
            }else {
                DoctorTableModel doctorTableModel = new DoctorTableModel(doctorList);
                frame.getTblDoctor().setModel(doctorTableModel);
                JOptionPane.showMessageDialog(frame, "Doctor not found!", "Error", JOptionPane.ERROR_MESSAGE);
                loadTableDoctors();
            }
        }
    }
}
