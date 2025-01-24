import java.util.*;

public class Plane {
    TreeMap<Integer, Integer> history = new TreeMap<>();

    Plane(int passengers){
        this.update(0, passengers);
    }


    void update(int day, int capacity) {
        history.put(day, capacity);
    }

    void clear(int day){
        history.clear();
        this.update(day, 0);
    }

    int getCapacity(int day) {
        Map.Entry<Integer, Integer> entry = history.floorEntry(day);
        return entry != null ? entry.getValue() : 0;
    }

    int sumCapacity(int startDay, int endDay) {
        int sum = 0;

        // Znajdź wszystkie kluczowe zmiany w zakresie [startDay, endDay)
        NavigableMap<Integer, Integer> subMap = history.subMap(startDay, true, endDay, false);

        // Znajdź wartość obowiązującą w dniu startDay, jeśli brak wcześniejszych zmian
        int prevDay = startDay;
        int prevCapacity = getCapacity(startDay);

        for (Map.Entry<Integer, Integer> entry : subMap.entrySet()) {
            int currentDay = entry.getKey();

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

}