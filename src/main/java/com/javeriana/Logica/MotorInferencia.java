package com.javeriana.Logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javeriana.Modelo.Regla;

public class MotorInferencia {
    private List<Regla> reglas;
    private Map<String, Boolean> hechos;
    private String conclusion;

    public MotorInferencia(List<Regla> reglas, List<String> hechosConocidos) {
        this.reglas = reglas;
        this.hechos = new HashMap<>();
        this.conclusion = null;

        for (String hecho : hechosConocidos) {
            this.hechos.put(hecho, true);
        }
    }

    public void ejecutarInferenciaConDesarrollo() {
        boolean huboNuevosHechos = true;
        int iteracion = 1;

        while (huboNuevosHechos) {
            huboNuevosHechos = false;

            System.out.println("Iteración " + iteracion + ":");
            List<String> inferenciasIteracion = new ArrayList<>();

            for (Regla regla : reglas) {
                String antecedente = regla.getAntecedente();
                String consecuente = regla.getConsecuente();

                boolean cumpleAntecedente = evaluarAntecedente(antecedente);

                if (cumpleAntecedente && !hechos.containsKey(consecuente)) {
                    hechos.put(consecuente, true);
                    inferenciasIteracion.add(consecuente);
                    huboNuevosHechos = true;
                }
            }

            // Imprimir el desarrollo de esta iteración
            for (String inferencia : inferenciasIteracion) {
                boolean valorInferencia = hechos.get(inferencia);
                System.out.println("Inferencia: " + inferencia + " = " + valorInferencia);
            }

            mostrarHechosConocidos();
            iteracion++;
        }

        // Determinar la conclusión final en función de los hechos conocidos
        if (hechos.containsKey("chances of rain") && hechos.get("chances of rain")) {
            conclusion = "chances of rain";
        } else if (hechos.containsKey("temp < 20") && hechos.get("temp < 20")) {
            conclusion = "temp < 20";
        } else if (hechos.containsKey("humidity") && hechos.get("humidity")) {
            conclusion = "humidity";
        }

        mostrarConclusion();
    }

    public void mostrarHechosConocidos() {
        System.out.println("Hechos conocidos:");
        for (String hecho : hechos.keySet()) {
            System.out.println(hecho + " = " + hechos.get(hecho));
        }
    }

    public void mostrarConclusion() {
        System.out.println("Conclusión:");
        if (conclusion != null) {
            System.out.println(conclusion);
        } else {
            System.out.println("No se ha llegado a una conclusión.");
        }
    }

    private boolean evaluarAntecedente(String antecedente) {
        String[] tokens = antecedente.split("&&");

        for (String token : tokens) {
            String condicion = token.trim();

            if (!hechos.containsKey(condicion) || !hechos.get(condicion)) {
                return false;
            }
        }

        return true;
    }
}
