package Klinik.Dao;

import Klinik.Database.Connection;
import Klinik.Model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDao implements DoctorInterface {
    java.sql.Connection connection;
    final String insert="INSERT INTO doctors(firstName,lastName,specialty,phoneNumber) VALUES (?,?,?,?)";
    final String update="UPDATE doctors SET firstName=?,lastName=?,specialty=?,phoneNumber=? WHERE doctorId=?";
    final String delete="DELETE FROM doctors WHERE doctorId=?";
    final String selectAll ="SELECT * FROM doctors";
    final String findDoctorName="SELECT * FROM doctors WHERE firstName LIKE ? OR lastName LIKE ?";
    public DoctorDao(){
        connection= Connection.connection();
    }
    @Override
    public void insert(Doctor d) {
        PreparedStatement statement =null;
        try {
            statement= connection.prepareStatement(insert);
            statement.setString(1,d.getFirstName());
            statement.setString(2,d.getLastName());
            statement.setString(3,d.getSpecialty());
            statement.setString(4,d.getPhoneNumber());
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
    public void update(Doctor d) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1,d.getFirstName());
            statement.setString(2,d.getLastName());
            statement.setString(3,d.getSpecialty());
            statement.setString(4,d.getPhoneNumber());
            statement.setInt(5,d.getDoctorId());
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
    public void delete(int doctorID) {
        PreparedStatement statement= null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1,doctorID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> dc = null;
        try {
            dc = new ArrayList<Doctor>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(selectAll);
            while (rs.next()){
                Doctor doctors = new Doctor();
                doctors.setDoctorId(rs.getInt("doctorId"));
                doctors.setFirstName(rs.getString("firstName"));
                doctors.setLastName(rs.getString("lastName"));
                doctors.setSpecialty(rs.getString("specialty"));
                doctors.setPhoneNumber(rs.getString("phoneNumber"));
                dc.add(doctors);
            }
        } catch (SQLException e) {
            Logger.getLogger(DoctorDao.class.getName()).log(Level.SEVERE,null,e);
            throw new RuntimeException(e);
        }
        return dc;
    }

    @Override
    public List<Doctor> getFindDoctorName(String name) {
        List<Doctor> dc = null;
        try {
            dc= new ArrayList<Doctor>();
            PreparedStatement statement = connection.prepareStatement(findDoctorName);
            statement.setString(1,"%"+name+"%");
            statement.setString(2,"%"+name+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Doctor doctors =new Doctor();
                doctors.setDoctorId(rs.getInt("doctorId"));
                doctors.setFirstName(rs.getString("firstName"));
                doctors.setLastName(rs.getString("lastName"));
                doctors.setSpecialty(rs.getString("specialty"));
                doctors.setPhoneNumber(rs.getString("phoneNumber"));
                dc.add(doctors);
            }
        } catch (SQLException e) {
            Logger.getLogger(DoctorDao.class.getName()).log(Level.SEVERE,null,e);
        }
        return dc;
    }
}
