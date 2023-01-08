package Klinik.Dao;

import Klinik.Database.Koneksi;
import Klinik.Model.Dokter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DokterDao implements DokterInterface{
    Connection connection;
    final String insert="INSERT INTO doctors(firstName,lastName,specialty,phoneNumber) VALUES (?,?,?,?)";
    final String update="update doctors set firstName=?,lastName=?,specialty=?,phoneNumber=? where doctorId=?";
    final String delete="delete from doctors where doctorId=?";
    final String selectAll ="select * from doctors";
    final String findDoctorName="SELECT * FROM doctors WHERE firstName LIKE ? OR lastName LIKE ?";
    public DokterDao(){
        Koneksi.connection();
    }
    @Override
    public void insert(Dokter d) {
        PreparedStatement statement =null;
        try {
            statement= connection.prepareStatement(insert);
            statement.setString(1,d.getFirstName());
            statement.setString(2,d.getLastName());
            statement.setString(3,d.getSpecialty());
            statement.setString(4,d.getPhoneNumber());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()){
                d.setDoctorId(rs.getInt(0));
            }
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
    public void update(Dokter d) {
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
    public List<Dokter> getAll() {
        List<Dokter> dc = null;
        try {
            dc = new ArrayList<Dokter>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(selectAll);
            while (rs.next()){
                Dokter doctors = new Dokter();
                doctors.setDoctorId(rs.getInt("id"));
                doctors.setFirstName(rs.getString("firstName"));
                doctors.setLastName(rs.getString("lastName"));
                doctors.setSpecialty(rs.getString("specialty"));
                doctors.setPhoneNumber(rs.getString("phoneNumber"));
                dc.add(doctors);
            }
        } catch (SQLException e) {
            Logger.getLogger(DokterDao.class.getName()).log(Level.SEVERE,null,e);
            throw new RuntimeException(e);
        }
        return dc;
    }

    @Override
    public List<Dokter> getFindDoctorName(String name) {
        List<Dokter> dc = null;
        try {
            dc= new ArrayList<Dokter>();
            PreparedStatement statement = connection.prepareStatement(findDoctorName);
            statement.setString(1,"%"+name+"%");
            statement.setString(2,"%"+name+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Dokter doctors =new Dokter();
                doctors.setDoctorId(rs.getInt("id"));
                doctors.setFirstName(rs.getString("firstName"));
                doctors.setLastName(rs.getString("lastName"));
                doctors.setSpecialty(rs.getString("specialty"));
                doctors.setPhoneNumber(rs.getString("phoneNumber"));
                dc.add(doctors);
            }
        } catch (SQLException e) {
            Logger.getLogger(DokterDao.class.getName()).log(Level.SEVERE,null,e);
        }
        return dc;
    }
}
