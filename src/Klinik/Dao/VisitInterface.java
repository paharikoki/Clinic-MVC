package Klinik.Dao;


import Klinik.Model.Visit;

import java.util.List;

public interface VisitInterface {
    public void insert(Visit visit);
    public void delete(int id);
    public List<Visit> getAll();
    public List<Visit> getFindPatientName(String name);
}
