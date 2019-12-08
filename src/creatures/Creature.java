package creatures;

import place.Place;

public abstract class Creature {
    public enum Gender {MALE, FEMAALE, UNKNOWN};

    private Gender MyGender;
    private String Name;
    private double speed;
    private Place lastRoom;
    // Наличие запаха у существа
    private boolean smell;
    // true если сушество первое в погоне
    private boolean first;
    // true если сушество последнее в погоне
    public boolean last;
    private double distanceToTarget;


    public Creature(Gender gender, String name, Place room, boolean smell, boolean first, double distanceToTarget, double speed, boolean last)  {
        this.MyGender = gender;
        this.Name = name;
        this.lastRoom = room;
        this.smell = smell;
        this.first = first;
        this.last = last;
        this.distanceToTarget = distanceToTarget;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return getClass().getName() +  "[Имя = " + this.Name + ", скорость = " + this.speed + ", дистанция до цели = " + this.distanceToTarget + "]";
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;

        if (otherObject == null || this.getClass() != otherObject.getClass()) return false;

        Creature other = (Creature) otherObject;
        return this.getName().equals(other.getName()) &&
                this.distanceToTarget == other.distanceToTarget &&
                this.speed == other.speed &&
                this.first == other.first &&
                this.last == other.last &&
                this.smell == other.smell &&
                this.lastRoom.equals(other.lastRoom) &&
                this.MyGender == other.MyGender;
    }

    public String getName() { return this.Name; }

    public Gender getMyGender() {
        return this.MyGender;
    }

    public double getSpeed() {
        return this.speed;
    }

    public Place getLastRoom() {
        return this.lastRoom;
    }

    public void setLastRoom(Place room) {this.lastRoom = room;}

    public boolean getSmell() {
        return this.smell;
    }

    public boolean getFirst() { return this.first; }

    public void setFirst(boolean first) {this.first = first;}

    public boolean getLast() { return this.last; }

    public void setLast(boolean last) {this.last = last;}

    public void setDistanceToTarget(double distance) {this.distanceToTarget = distance;}

    public double getDistanceToTarget() { return this.distanceToTarget; }

    public String getLastAct(){return "";}
}