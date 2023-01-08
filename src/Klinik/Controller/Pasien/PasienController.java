package Klinik.Controller.Pasien;

import Klinik.Dao.PasienInterface;
import Klinik.Model.Pasien;
import Klinik.View.Pasien.AddPasien;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PasienController {

    AddPasien viewPasien;
    PasienInterface pasienInterface;

    public PasienController(AddPasien viewPasien) {
        this.viewPasien = viewPasien;
        this.pasienInterface = pasienInterface;
    }
    public void addPasien(){


        Date date = viewPasien.getJdcDateofBirth().getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateString =dateFormat.format(date);

        String gender = viewPasien.getCmbGender().getSelectedItem().toString();
        try {
            Pasien pasien = new Pasien();
            pasien.setFirstName(viewPasien.getTxtFirstName().getText());
            pasien.setLastName(viewPasien.getTxtLastName().getText());
            pasien.setDateOfBirth(dateString);
            pasien.setGender(gender);
            pasien.setAddress(viewPasien.getTxtAddress().getText());
            pasienInterface.insert(pasien);
            JOptionPane.showMessageDialog(viewPasien, "Data added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(viewPasien, "Error : " + e, "Error", JOptionPane.ERROR_MESSAGE);

        }

    }
}
