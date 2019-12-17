package item;

import java.util.ArrayList;

// Классы использующие этот интерфейс имеют соседствующие объекты
public interface Communication {
    Item getRoom(int number);
    Item getCommunication(Item room);
    void addCommunication(Item receiving, Item... neighboringRoom);
    void addCommunication(int number, ArrayList rooms);
    void addRoom(Item item);
    ArrayList getRooms();
    ArrayList<ArrayList<Item>> getCommunications();
}
