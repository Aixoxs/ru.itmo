package creatures;

import place.Place;

import java.util.Objects;

public class Ghost extends Creature {
    public enum ColorGhost {WHITE, BLUE, TRANSPARENT, PURPLE}
    private ColorGhost MyColor;

    public Ghost(Gender gender, Act act, String name, Place room, boolean smell, boolean first, double distanceToTarget, double speed, boolean last, ColorGhost color) {
        super(gender, act, name, room, smell, first, distanceToTarget, speed, last);
        this.MyColor = color;
    }

    @Override
    public String toString(){
        return super.toString() + "[цвет =" + this.getMyColor() + "]";
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

        Ghost other = (Ghost) otherObject;
        return this.getMyColor().equals(other.getMyColor());
    }

    public ColorGhost getMyColor() {
        return this.MyColor;
    }
}


