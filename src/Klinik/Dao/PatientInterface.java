package Klinik.Dao;


import Klinik.Model.Patient;

import java.util.List;

public interface PatientInterface {
    public void insert(Patient patient);

    public void update(Patient dokter);

    public void delete(int id);

    public List<Patient> getAll();
    public List<Patient> getFindPatientName(String name);
}
