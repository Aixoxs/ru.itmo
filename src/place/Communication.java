package place;

import java.util.ArrayList;
// Классы использующие этот интерфейс имеют соседствующие объекты
public interface Communication {
    Place getRoom(int number);
    Place getCommunication(Place room);
    void addCommunication(Place receiving, Place... neighboringRoom);
}
