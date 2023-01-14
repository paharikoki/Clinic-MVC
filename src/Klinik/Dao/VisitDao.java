package Klinik.Dao;

import Klinik.Model.Doctor;
import Klinik.Model.Visit;
import Klinik.Model.Patient;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisitDao implements VisitInterface {
    Connection connection;
    final String insert="INSERT INTO visits (patientId, doctorId, visitDate,diagnosis,treatment) VALUES (?, ?, ?,?,?)";
    final String delete="DELETE FROM visits WHERE visitId=?";
    final String selectAll="SELECT * FROM visits JOIN doctors ON visits.doctorId = doctors.id";
    final String selectAllByPatientName="SELECT * FROM kunjungan JOIN pasien ON kunjungan.patientId = pasien.patientId WHERE pasien.firstName = 'John' AND pasien.lastName = 'Doe'";

    @Override
    public void insert(Visit k) {
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
    public List<Visit> getAll() {
        List<Visit> kj = null;
        try {
            kj = new ArrayList<Visit>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(selectAll);
            while (rs.next()){
                Visit visit = new Visit();
                visit.setVisitId(rs.getInt("visitId"));
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patientId"));
                patient.setFirstName(rs.getString("firstName"));
                patient.setLastName(rs.getString("lastName"));
                patient.setDateOfBirth(rs.getString("dateOfBirth"));
                patient.setGender(rs.getString("gender"));
                patient.setAddress(rs.getString("address"));
                visit.setPasien(patient);
                Doctor doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctorId"));
                doctor.setFirstName(rs.getString("firstName"));
                doctor.setLastName(rs.getString("lastName"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setPhoneNumber(rs.getString("phoneNumber"));
                visit.setDokter(doctor);
                visit.setVisitDate(rs.getString("visitDate"));
                visit.setDiagnosis(rs.getString("diagnosis"));
                visit.setTreatment(rs.getString("treatment"));
                kj.add(visit);
            }
        } catch (SQLException e) {
            Logger.getLogger(VisitDao.class.getName()).log(Level.SEVERE,null,e);
            throw new RuntimeException(e);
        }
        return kj;
    }

    @Override
    public List<Visit> getFindPatientName(String name) {
        return null;
    }
}
