package Klinik.Dao;

import Klinik.Model.Dokter;

import java.util.List;

public interface DokterInterface {
    public void insert(Dokter dokter);

    public void update(Dokter dokter);

    public void delete(int id);

    public List<Dokter> getAll();
    public List<Dokter> getFindDoctorName(String name);
}
