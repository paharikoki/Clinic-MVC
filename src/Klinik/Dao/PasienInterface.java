package Klinik.Dao;


import Klinik.Model.Pasien;

import java.util.List;

public interface PasienInterface {
    public void insert(Pasien pasien);

    public void update(Pasien dokter);

    public void delete(int id);

    public List<Pasien> getAll();
    public List<Pasien> getFindDoctorName(String name);
}
