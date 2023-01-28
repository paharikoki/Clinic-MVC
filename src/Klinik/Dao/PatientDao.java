package Klinik.Dao;

import Klinik.Database.Connection;
import Klinik.Model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientDao implements PatientInterface {
    java.sql.Connection connection;
    final String insert="INSERT INTO patients(firstName,lastName,dateOfBirth,gender,address) VALUES (?,?,?,?,?)";
    final String update="UPDATE patients SET firstName=?,lastName=?,dateOfBirth=?,gender=?,address=? WHERE patientId=?";
    final String delete="DELETE FROM patients WHERE patientId=?";
    final String selectAll ="SELECT * FROM patients";
    final String findDoctorName="SELECT * FROM patients WHERE firstName LIKE ? OR lastName LIKE ?";
    public PatientDao(){
        connection= Connection.connection();
    }
    @Override
    public void insert(Patient p) {
        PreparedStatement statement =null;
        try {
            statement= connection.prepareStatement(insert);
            statement.setString(1,p.getFirstName());
            statement.setString(2,p.getLastName());
            statement.setString(3,p.getDateOfBirth());
            statement.setString(4,p.getGender());
            statement.setString(5,p.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
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
    public void update(Patient p) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1,p.getFirstName());
            statement.setString(2,p.getLastName());
            statement.setString(3,p.getDateOfBirth());
            statement.setString(4,p.getGender());
            statement.setString(5,p.getAddress());
            statement.setInt(6,p.getPatientId());

            statement.executeUpdate();
        } catch (SQLException e) {
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
    public void delete(int pasienId) {
        PreparedStatement statement= null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1,pasienId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> ps = null;
        try {
            ps = new ArrayList<Patient>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(selectAll);
            while (rs.next()){
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patientId"));
                patient.setFirstName(rs.getString("firstName"));
                patient.setLastName(rs.getString("lastName"));
                patient.setDateOfBirth(rs.getString("dateOfBirth"));
                patient.setGender(rs.getString("gender"));
                patient.setAddress(rs.getString("address"));
                ps.add(patient);
            }
        } catch (SQLException e) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE,null,e);
            throw new RuntimeException(e);
        }
        return ps;
    }

    @Override
    public List<Patient> getFindPatientName(String name) {
        List<Patient> ps = null;
        try {
            ps= new ArrayList<Patient>();
            PreparedStatement statement = connection.prepareStatement(findDoctorName);
            statement.setString(1,"%"+name+"%");
            statement.setString(2,"%"+name+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Patient patient =new Patient();
                patient.setPatientId(rs.getInt("patientId"));
                patient.setFirstName(rs.getString("firstName"));
                patient.setLastName(rs.getString("lastName"));
                patient.setDateOfBirth(rs.getString("dateOfBirth"));
                patient.setGender(rs.getString("gender"));
                patient.setAddress(rs.getString("address"));
                ps.add(patient);
            }
        } catch (SQLException e) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE,null,e);
        }
        return ps;
    }
}
