package Klinik.Dao;

import Klinik.Model.Doctor;

import java.util.List;

public interface DoctorInterface {
    public void insert(Doctor doctor);

    public void update(Doctor doctor);

    public void delete(int id);

    public List<Doctor> getAll();
    public List<Doctor> getFindDoctorName(String name);
}
