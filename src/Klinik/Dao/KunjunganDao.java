package Klinik.Dao;

import Klinik.Model.Dokter;
import Klinik.Model.Kunjungan;
import Klinik.Model.Pasien;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KunjunganDao implements KunjunganInterface {
    Connection connection;
    final String insert="INSERT INTO visits (patientId, doctorId, visitDate,diagnosis,treatment) VALUES (?, ?, ?,?,?)";
    final String delete="DELETE FROM visits WHERE visitId=?";
    final String selectAll="SELECT * FROM visits JOIN doctors ON visits.doctorId = doctors.id";
    final String selectAllByPatientName="SELECT * FROM kunjungan JOIN pasien ON kunjungan.patientId = pasien.patientId WHERE pasien.firstName = 'John' AND pasien.lastName = 'Doe'";

    @Override
    public void insert(Kunjungan k) {
        PreparedStatement statement = null;
        String dateNow;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try {
            statement=connection.prepareStatement(insert);
            statement.setInt(1,k.getPasien().getPatientId());
            statement.setString(2, now.format(formatter));
            statement.setString(3,k.getVisitDate());
            statement.setString(4,k.getDiagnosis());
            statement.setString(5,k.getTreatment());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()){
                while (rs.next()){
                    k.setVisitId(rs.getInt(0));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int visitId) {
        PreparedStatement statement= null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1,visitId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Kunjungan> getAll() {
        List<Kunjungan> kj = null;
        try {
            kj = new ArrayList<Kunjungan>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(selectAll);
            while (rs.next()){
                Kunjungan kunjungan = new Kunjungan();
                kunjungan.setVisitId(rs.getInt("visitId"));
                Pasien pasien = new Pasien();
                pasien.setPatientId(rs.getInt("patientId"));
                pasien.setFirstName(rs.getString("firstName"));
                pasien.setLastName(rs.getString("lastName"));
                pasien.setDateOfBirth(rs.getString("dateOfBirth"));
                pasien.setGender(rs.getString("gender"));
                pasien.setAddress(rs.getString("address"));
                kunjungan.setPasien(pasien);
                Dokter dokter = new Dokter();
                dokter.setDoctorId(rs.getInt("doctorId"));
                dokter.setFirstName(rs.getString("firstName"));
                dokter.setLastName(rs.getString("lastName"));
                dokter.setSpecialty(rs.getString("specialty"));
                dokter.setPhoneNumber(rs.getString("phoneNumber"));
                kunjungan.setDokter(dokter);
                kunjungan.setVisitDate(rs.getString("visitDate"));
                kunjungan.setDiagnosis(rs.getString("diagnosis"));
                kunjungan.setTreatment(rs.getString("treatment"));
                kj.add(kunjungan);
            }
        } catch (SQLException e) {
            Logger.getLogger(KunjunganDao.class.getName()).log(Level.SEVERE,null,e);
            throw new RuntimeException(e);
        }
        return kj;
    }

    @Override
    public List<Kunjungan> getFindPatientName(String name) {
        return null;
    }
}
