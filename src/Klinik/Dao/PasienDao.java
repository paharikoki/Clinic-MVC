package Klinik.Dao;

import Klinik.Database.Koneksi;
import Klinik.Model.Dokter;
import Klinik.Model.Pasien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasienDao implements  PasienInterface{
    Connection connection;
    final String insert="INSERT INTO patients(firstName,lastName,dateOfBirth,gender,address) VALUES (?,?,?,?,?)";
    final String update="UPDATE patients SET firstName=?,lastName=?,dateOfBirth=?,gender=?,address=? WHERE patientId=?";
    final String delete="DELETE FROM patients WHERE patientId=?";
    final String selectAll ="SELECT * FROM patients";
    final String findDoctorName="SELECT * FROM patients WHERE firstName LIKE ? OR lastName LIKE ?";
    public PasienDao(){
        Koneksi.connection();
    }
    @Override
    public void insert(Pasien p) {
        PreparedStatement statement =null;
        try {
            statement= connection.prepareStatement(insert);
            statement.setString(1,p.getFirstName());
            statement.setString(2,p.getLastName());
            statement.setString(3,p.getDateOfBirth());
            statement.setString(4,p.getGender());
            statement.setString(5,p.getAddress());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()){
                p.setPatientId(rs.getInt(0));
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
    public void update(Pasien p) {
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
    public List<Pasien> getAll() {
        List<Pasien> ps = null;
        try {
            ps = new ArrayList<Pasien>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(selectAll);
            while (rs.next()){
                Pasien pasien = new Pasien();
                pasien.setPatientId(rs.getInt("patientId"));
                pasien.setFirstName(rs.getString("firstName"));
                pasien.setLastName(rs.getString("lastName"));
                pasien.setDateOfBirth(rs.getString("dateOfBirth"));
                pasien.setGender(rs.getString("gender"));
                pasien.setAddress(rs.getString("address"));
                ps.add(pasien);
            }
        } catch (SQLException e) {
            Logger.getLogger(PasienDao.class.getName()).log(Level.SEVERE,null,e);
            throw new RuntimeException(e);
        }
        return ps;
    }

    @Override
    public List<Pasien> getFindDoctorName(String name) {
        List<Pasien> ps = null;
        try {
            ps= new ArrayList<Pasien>();
            PreparedStatement statement = connection.prepareStatement(findDoctorName);
            statement.setString(1,"%"+name+"%");
            statement.setString(2,"%"+name+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Pasien pasien =new Pasien();
                pasien.setPatientId(rs.getInt("patientId"));
                pasien.setFirstName(rs.getString("firstName"));
                pasien.setLastName(rs.getString("lastName"));
                pasien.setDateOfBirth(rs.getString("dateOfBirth"));
                pasien.setGender(rs.getString("gender"));
                pasien.setAddress(rs.getString("address"));
                ps.add(pasien);
            }
        } catch (SQLException e) {
            Logger.getLogger(PasienDao.class.getName()).log(Level.SEVERE,null,e);
        }
        return ps;
    }
}
