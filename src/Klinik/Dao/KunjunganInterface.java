package Klinik.Dao;


import Klinik.Model.Kunjungan;

import java.util.List;

public interface KunjunganInterface {
    public void insert(Kunjungan kunjungan);
    public void delete(int id);
    public List<Kunjungan> getAll();
    public List<Kunjungan> getFindPatientName(String name);
}
