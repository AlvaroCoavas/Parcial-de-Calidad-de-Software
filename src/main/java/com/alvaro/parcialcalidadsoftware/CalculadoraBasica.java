package com.alvaro.parcialcalidadsoftware;

import java.util.Scanner;

public class CalculadoraBasica {
    
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        CalculadoraBasica c = new CalculadoraBasica();
        boolean seguir = true;
        while (seguir) {
            System.out.println("Calculadora basica");
            System.out.println("1) Sumar");
            System.out.println("2) Restar");
            System.out.println("3) Multiplicar");
            System.out.println("4) Dividir");
            System.out.println("5) Ver ultimo resultado");
            System.out.println("0) Salir");
            System.out.print("Elige una opcion: ");
            String texto = lector.nextLine();
            int opcion;
            try {
                opcion = Integer.parseInt(texto);
            } catch (Exception e) {
                opcion = -1;
            }

            if (opcion == 0) {
                seguir = false;
            } else {
                int numeroUno = 0;
                int numeroDos = 0;
                if (opcion >= 1 && opcion <= 4) {
                    System.out.print("Escribe numero uno: ");
                    String t1 = lector.nextLine();
                    try {
                        numeroUno = Integer.parseInt(t1);
                    } catch (Exception e) {
                        numeroUno = 0;
                    }
                    System.out.print("Escribe numero dos: ");
                    String t2 = lector.nextLine();
                    try {
                        numeroDos = Integer.parseInt(t2);
                    } catch (Exception e) {
                        numeroDos = 0;
                    }
                }

                int resultado = 0;
                switch (opcion) {
                    case 1 -> {
                        resultado = c.sumar(numeroUno, numeroDos);
                        System.out.println("Resultado: " + resultado);
                    }
                    case 2 -> {
                        resultado = c.restar(numeroUno, numeroDos);
                        System.out.println("Resultado: " + resultado);
                    }
                    case 3 -> {
                        resultado = c.multiplicar(numeroUno, numeroDos);
                        System.out.println("Resultado: " + resultado);
                    }
                    case 4 -> {
                        resultado = c.dividir(numeroUno, numeroDos);
                        System.out.println("Resultado: " + resultado);
                    }
                    case 5 -> System.out.println("Ultimo: " + c.obtenerUltimoResultado());
                    default -> System.out.println("Opcion no valida");
                }

                System.out.print("Continuar? s/n: ");
                String resp = lector.nextLine();
                if (!resp.equalsIgnoreCase("s")) {
                    seguir = false;
                }
            }
        }
        System.out.println("Fin");
    }
    
    private int ultimoResultado;

    public int sumar(int numeroUno, int numeroDos) {
        int suma = numeroUno + numeroDos;
        ultimoResultado = suma;
        return suma;
    }

    public int restar(int numeroUno, int numeroDos) {
        int r = numeroUno - numeroDos;
        ultimoResultado = r;
        return r;
    }

    public int multiplicar(int numeroUno, int numeroDos) {
        int multi = numeroUno * numeroDos;
        ultimoResultado = multi;
        return multi;
    }

    public int dividir(int numeroUno, int numeroDos) {
        if (numeroDos == 0) {
            ultimoResultado = 0;
            return 0;
        }
        int d = numeroUno / numeroDos;
        ultimoResultado = d;
        return d;
    }

    public int obtenerUltimoResultado() {
        return ultimoResultado;
    }

    
}