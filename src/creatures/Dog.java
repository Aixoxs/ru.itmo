package creatures;

import item.Item;

import java.util.Objects;

public class Dog extends Creature {

    public enum ColorDog{RED, BLUE, BLACK, WHITE, YELLOW, GRAY, PINK};
    private ColorDog MyColor;

    public Dog(Gender gender,Act act, String name, Item room, boolean smell, boolean first, double distanceToTarget, double speed, boolean last, ColorDog color) {
        super(gender, act, name, room, smell, first, distanceToTarget, speed, last);
        this.MyColor = color;
    }

//возвращает true если собака учуяла запах
    public boolean sniff(Creature creature) {
        return creature.getSmell();
    }

    @Override
    public String toString() {
        return super.toString() + "[цвет = " + this.getMyColor() + "]";
    }

    @Override
    public int hashCode(){
        Double distanceToTarget = new Double(this.getDistanceToTarget());
        return Objects.hash(this.getName(), this.getMyGender(), this.getFirst(), this.getSmell(), this.getMyAct(), distanceToTarget, this.getMyColor()) * 11 +
                this.getLastRoom().hashCode() * 13 +
                7;
    }

    @Override
    public boolean equals (Object otherObject){
        if (!super.equals(otherObject)) return false;

        Dog other = (Dog) otherObject;
        return this.getMyColor().equals(other.getMyColor());
    }

    public ColorDog getMyColor() {
        return MyColor;
    }
}


