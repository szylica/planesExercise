package org.szylica;

import java.util.ArrayList;
import java.util.List;

//Class where all Planes are stored
public class AllPlanes {
    private static List<Plane> allPlanes = new ArrayList<>();

    public static void addPlane(Plane plane) {
        allPlanes.add(plane);
    }

    public static Plane getPlane(int index) {
        if (index >= 0 && index < allPlanes.size()) {
            return allPlanes.get(index);
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + allPlanes.size());
    }

    public static int size() {
        return allPlanes.size();
    }

    public static List<Plane> getAllPlanes() {
        return new ArrayList<>(allPlanes);
    }
}
