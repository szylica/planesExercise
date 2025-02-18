package org.szylica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    private Plane plane;

    @BeforeEach
    void setUp() {
        plane = new Plane(100); // Startujemy z samolotem o początkowej pojemności 100
    }

    @Test
    void testInitialCapacity() {
        assertEquals(100, plane.getCapacity(0), "Początkowa pojemność powinna wynosić 100");
    }

    @Test
    void testUpdateCapacity() {
        plane.update(5, 150);
        assertEquals(150, plane.getCapacity(5), "Pojemność w dniu 5 powinna wynosić 150");
    }

    @Test
    void testUpdateAndGetCapacityForPreviousDay() {
        plane.update(10, 200);
        assertEquals(100, plane.getCapacity(5), "Pojemność przed dniem 10 powinna wynosić początkową wartość 100");
    }

    @Test
    void testClearHistory() {
        plane.update(5, 150);
        plane.clear(10);
        assertEquals(0, plane.getCapacity(10), "Pojemność po wyczyszczeniu historii powinna wynosić 0");
        assertEquals(0, plane.getCapacity(15), "Pojemność po wyczyszczeniu historii powinna wynosić 0 w dniach późniejszych");
    }

    @Test
    void testSumCapacityWithSingleChange() {
        plane.update(5, 150);
        long sum = plane.sumCapacity(0, 10);
        assertEquals(100 * 5 + 150 * 5, sum, "Suma pojemności powinna obejmować 5 dni o pojemności 100 i 5 dni o pojemności 150");
    }

    @Test
    void testSumCapacityWithMultipleChanges() {
        plane.update(3, 120);
        plane.update(7, 180);
        long sum = plane.sumCapacity(0, 10);
        assertEquals(100 * 3 + 120 * 4 + 180 * 3, sum, "Suma pojemności powinna być odpowiednio obliczona dla przedziałów");
    }

    @Test
    void testSumCapacityOutsideRange() {
        plane.update(5, 150);
        long sum = plane.sumCapacity(20, 30);
        assertEquals(1500, sum,  "Suma pojemności powinna uwzględniać wartość z najbliższego poprzedniego dnia");
    }

    @Test
    void testSumCapacityNoPreviousCapacity() {
        plane.clear(0); // Czyszczenie historii (ustawia capacity na 0)
        long sum = plane.sumCapacity(20, 30); // Zakres w pustej historii
        assertEquals(0, sum, "Suma pojemności powinna wynosić 0, gdy historia jest pusta");
    }

    @Test
    void testToString() {
        plane.update(5, 150);
        String expected = "{0=100, 5=150}";
        assertEquals(expected, plane.toString(), "Reprezentacja tekstowa powinna zawierać zmiany w historii");
    }
}