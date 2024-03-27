package com.javeriana.Modelo;

public class Regla {
    private String antecedente;
    private String consecuente;

    public Regla(String antecedente, String consecuente) {
        this.antecedente = antecedente;
        this.consecuente = consecuente;
    }

    public String getAntecedente() {
        return antecedente;
    }

    public String getConsecuente() {
        return consecuente;
    }
}
