package com.example.demo.model;

public class Relationship {
    private int id;
    private int idPrimaPersoana;
    private int idADouaPersoana;
    private String tipulRelatiei;

    public Relationship(int id, int idPrimaPersoana, int idADouaPersoana, String tipulRelatiei) {
        this.id = id;
        this.idPrimaPersoana = idPrimaPersoana;
        this.idADouaPersoana = idADouaPersoana;
        this.tipulRelatiei = tipulRelatiei;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPrimaPersoana() {
        return idPrimaPersoana;
    }

    public void setIdPrimaPersoana(int idPrimaPersoana) {
        this.idPrimaPersoana = idPrimaPersoana;
    }

    public int getIdADouaPersoana() {
        return idADouaPersoana;
    }

    public void setIdADouaPersoana(int idADouaPersoana) {
        this.idADouaPersoana = idADouaPersoana;
    }

    public String getTipulRelatiei() {
        return tipulRelatiei;
    }

    public void setTipulRelatiei(String tipulRelatiei) {
        this.tipulRelatiei = tipulRelatiei;
    }
}
