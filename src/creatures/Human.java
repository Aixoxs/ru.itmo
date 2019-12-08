package creatures;

import place.Place;
import place.Room;

import java.util.Objects;

public class Human extends Creature {

    private int IQ;
    private int age;
    private String act;

    public Human(Gender gender, Act act, String name, Place room, boolean smell, boolean first, double distanceToTarget, double speed, boolean last, int IQ, int age) {
        super(gender, act, name, room, smell, first, distanceToTarget, speed, last);
        this.IQ = IQ;
        this.age = age;
    }

    @Override
    public String toString(){
        return super.toString() + "[IQ =" + this.getIQ() + ", возраст =" + this.getAge() + "]";
    }

    @Override
    public int hashCode(){
        Double distanceToTarget = new Double(this.getDistanceToTarget());
        return Objects.hash(this.getName(), this.getMyGender(), this.getFirst(), this.getSmell(), this.getMyAct(), distanceToTarget) * 11 +
                this.getLastRoom().hashCode() * 13 +
                this.getAge() * 17 +
                this.getIQ() * 19 +
                7;
    }

    @Override
    public boolean equals (Object otherObject){
        if (!super.equals(otherObject)) return false;

        Human other = (Human) otherObject;
        return this.getIQ() == other.getIQ() &&
                this.getAge() == other.getAge() &&
                this.getName().equals(other.getName());
    }

    public int getIQ() {return this.IQ;}

    public int getAge() {return this.age;}
}

