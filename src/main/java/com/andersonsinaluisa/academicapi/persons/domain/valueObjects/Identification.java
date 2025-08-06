package com.andersonsinaluisa.academicapi.persons.domain.valueObjects;

public class Identification {

    public enum Type {
        CEDULA, RUC, PASAPORTE
    }

    private final String value;
    private final Type type;

    public Identification(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("La identificación no puede ser nula o vacía");
        }

        value = value.trim();
        this.value = value;
        this.type = detectType(value);

        if (this.type == Type.CEDULA && !validateCedula(value)) {
            throw new IllegalArgumentException("Cédula inválida");
        }

        if (this.type == Type.RUC && !validateRuc(value)) {
            throw new IllegalArgumentException("RUC inválido");
        }
    }

    public String getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    private Type detectType(String id) {
        if (id.matches("\\d{10}")) return Type.CEDULA;
        if (id.matches("\\d{13}")) return Type.RUC;
        return Type.PASAPORTE;
    }

    private boolean validateCedula(String cedula) {
        if (!cedula.matches("\\d{10}")) return false;

        int province = Integer.parseInt(cedula.substring(0, 2));
        if (province < 1 || province > 24) return false;

        int thirdDigit = Integer.parseInt(String.valueOf(cedula.charAt(2)));
        if (thirdDigit >= 6) return false; // Solo personas naturales

        int[] coef = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int sum = 0;
        for (int i = 0; i < coef.length; i++) {
            int digit = Character.getNumericValue(cedula.charAt(i)) * coef[i];
            sum += (digit > 9) ? digit - 9 : digit;
        }

        int verifier = 10 - (sum % 10);
        if (verifier == 10) verifier = 0;

        return verifier == Character.getNumericValue(cedula.charAt(9));
    }

    private boolean validateRuc(String ruc) {
        if (!ruc.matches("\\d{13}")) return false;

        String cedulaPart = ruc.substring(0, 10);
        return validateCedula(cedulaPart) && ruc.endsWith("001");
    }
}