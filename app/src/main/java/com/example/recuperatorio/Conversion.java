package com.example.recuperatorio;

public enum Conversion {
    DOLAR_A_EURO(0.85),
    EURO_A_DOLAR(1.17);

    private final double tasa;

    Conversion(double tasa) {
        this.tasa = tasa;
    }

    public double getTasa() {
        return tasa;
    }
}