package place;

import java.util.ArrayList;
import java.util.Random;

public class Apartments extends Place implements SquareCalculated {
    private ArrayList<Place> rooms = new ArrayList<>();
    private ArrayList<ArrayList<Place>> communications = new ArrayList<>();
    private double height;
    private int floor;

    public Apartments(String name, double height, int floor, Place... R) {
        super(name);
        this.height = height;
        this.floor = floor;
        for (Place r: R) {
            rooms.add(r);
        }
        for (int i = 0; i < rooms.size(); i++) {
            this.communications.add(new ArrayList<Place>());
        }
    }

    //Установка соседних комнат
    public void addCommunication(Place receiving, Place... neighboringRoom) {
        int num = 0;
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.rooms.get(i).equals(receiving)) {
                num = i;
                break;
            }
            else num = -1;
        }
        if (num > -1) {
            for (Place r: neighboringRoom) {
                this.communications.get(num).add(r);
            }
        }
    }

    // Возврат случайной соседней комнаты
    public Place getCommunication(Place room) {
        int num = 0;
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.rooms.get(i).equals(room)) {
                num = i;
                break;
            }
            else num = -1;
        }
        if (num > -1) {
            int itemIndex = (int)(Math.random()*this.communications.get(num).size());
            return this.communications.get(num).get(itemIndex);
        }
        else return null;
    }

    @Override
    public double getSquare() {
        double square = 0;
        for (int i = 0; i < this.rooms.size(); i++){
            square += ((SquareCalculated)rooms.get(i)).getSquare();
        }
        return square;
    }

    @Override
    public int hashCode() {
        Double height = new Double(this.getHeight());
        return rooms.hashCode() * 13 +
                height.hashCode() * 11 +
                communications.hashCode() * 17 +
                this.floor * 19 +
                7;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;

        if (otherObject == null || this.getClass() != otherObject.getClass()) return false;

        Apartments other = (Apartments) otherObject;
        return this.getRooms().equals(other.getRooms()) &&
                this.getCommunications().equals(other.getCommunications()) &&
                this.floor == other.getFloor() &&
                this.height == other.getHeight();
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < rooms.size()-1; i++) {
            s += rooms.get(i).toString() + ", ";
        }
        s += rooms.get(rooms.size()-1).toString();
        String z = "";
        for (int i = 0; i < communications.size()-1; i++) {
            z += communications.get(i).toString() + ", ";
        }
        z += communications.get(communications.size()-1).toString();
        return this.getClass().getName() + "[rooms = " + s + ", communications =" + z + ", floor =" + this.floor + ", height" + this.height + "]";
    }

    public ArrayList<ArrayList<Place>> getCommunications() {
        return communications;
    }

    public ArrayList<Place> getRooms() {
        return rooms;
    }

    public Place getRoom(int number) {
        return rooms.get(number);
    }

    public int getFloor() {
        return floor;
    }

    public double getHeight() {
        return height;
    }

    public int getCountRooms() {
        return rooms.size();
    }
}
