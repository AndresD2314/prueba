package com.javeriana;

import java.util.ArrayList;
import java.util.List;

import com.javeriana.Logica.MotorInferencia;
import com.javeriana.Modelo.Regla;

public class App {

    public static void main(String[] args) {
        // Crear una lista de reglas
        List<Regla> reglas = new ArrayList<>();
        reglas.add(new Regla("temp < 20 && humidity", "chances of rain"));
        reglas.add(new Regla("cloudy && cool", "temp < 20"));
        reglas.add(new Regla("heavy air", "humidity"));

        // Crear una lista de hechos conocidos
        List<String> hechosConocidos = new ArrayList<>();
        hechosConocidos.add("cloudy");
        hechosConocidos.add("heavy air");
        hechosConocidos.add("cool");

        MotorInferencia motor = new MotorInferencia(reglas, hechosConocidos);
        motor.ejecutarInferenciaConDesarrollo();
    }
}