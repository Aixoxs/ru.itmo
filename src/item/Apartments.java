package item;

import java.util.ArrayList;

public class Apartments extends Item implements Communication {
    private ArrayList<Item> rooms = new ArrayList<>();
    private ArrayList<ArrayList<Item>> communications = new ArrayList<>();
    private double height;
    private int floor;

    public Apartments(String name, double height, int floor, Item... R) {
        super(name);
        this.height = height;
        this.floor = floor;
        for (Item r: R) {
            rooms.add(r);
        }
        for (int i = 0; i < rooms.size(); i++) {
            this.communications.add(new ArrayList<Item>());
        }
    }

    //Установка соседних комнат
    public void addCommunication(Item receiving, Item... neighboringRoom) {
        int num = 0;
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.rooms.get(i).equals(receiving)) {
                num = i;
                break;
            }
            else num = -1;
        }
        if (num > -1) {
            for (Item r: neighboringRoom) {
                this.communications.get(num).add(r);
            }
        }
    }

    public void addCommunication(int number, ArrayList rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            if (i != number) {
                this.communications.get(number).add(this.rooms.get(i));
            }
        }
    }

    // Возврат случайной соседней комнаты
    public Item getCommunication(Item room) {
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

    public ArrayList<ArrayList<Item>> getCommunications() {
        return communications;
    }

    public ArrayList<Item> getRooms() {
        return rooms;
    }

    public Item getRoom(int number) {
        return rooms.get(number);
    }

    public void setAllCommunication(int number) {

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

    public void addRoom(Item item) {rooms.add(item);}
}
