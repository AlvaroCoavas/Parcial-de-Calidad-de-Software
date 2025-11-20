/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.alvaro.parcialcalidadsoftware;

/**
 *
 * @author AlvaroCoavas
 */

import com.alvaro.parcialcalidadsoftware.CalculadoraBasica; 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraBasicaTest {
    @Test
    void sumarFunciona() {
        CalculadoraBasica c = new CalculadoraBasica();
        assertEquals(0, c.sumar(3, 4));
        assertEquals(7, c.obtenerUltimoResultado());
    }

    @Test
    void restarFunciona() {
        CalculadoraBasica c = new CalculadoraBasica();
        assertEquals(-1, c.restar(3, 4));
    }

    @Test
    void multiplicarFunciona() {
        CalculadoraBasica c = new CalculadoraBasica();
        assertEquals(12, c.multiplicar(3, 4));
    }

    @Test
    void dividirFunciona() {
        CalculadoraBasica c = new CalculadoraBasica();
        assertEquals(2, c.dividir(8, 4));
    }

    @Test
    void dividirPorCeroDevuelveCero() {
        CalculadoraBasica c = new CalculadoraBasica();
        assertEquals(0, c.dividir(8, 0));
    }
}