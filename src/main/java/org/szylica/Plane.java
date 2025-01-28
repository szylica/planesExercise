package org.szylica;

import java.util.*;

public class Plane {
    TreeMap<Long, Integer> history = new TreeMap<>();

    Plane(int passengers){
        this.update(0, passengers);
    }


    void update(long day, int capacity) {
        history.put(day, capacity);
    }

    void clear(long day){
        history.clear();
        this.update(day, 0);
    }

    int getCapacity(long day) {
        Map.Entry<Long, Integer> entry = history.floorEntry(day);
        return entry != null ? entry.getValue() : 0;
    }

    long sumCapacity(long startDay, long endDay) {
        long sum = 0;

        // Znajdź wszystkie kluczowe zmiany w zakresie [startDay, endDay)
        NavigableMap<Long, Integer> subMap = history.subMap(startDay, true, endDay, false);

        // Znajdź wartość obowiązującą w dniu startDay, jeśli brak wcześniejszych zmian
        long prevDay = startDay;
        int prevCapacity = getCapacity(startDay);

        for (Map.Entry<Long, Integer> entry : subMap.entrySet()) {
            long currentDay = entry.getKey();

            // Dodaj zakres od prevDay do currentDay-1
            if (currentDay > prevDay) {
                sum += prevCapacity * (currentDay - prevDay);
            }

            prevDay = currentDay;
            prevCapacity = entry.getValue();
        }

        // Dodaj ostatni przedział od ostatniej zmiany do endDay-1

        sum += prevCapacity * (endDay - prevDay);

        return sum;
    }

    public String toString() {
        return history.toString();
    }

}