package item;

public class Room extends Item {
    private double width;
    private double length;

    public Room(String name, double width, double length) {
        super(name);
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return this.width;
    }

    public double getLength() {
        return this.length;
    }

    @Override
    public int hashCode() {
        Double width = new Double(getWidth());
        Double length = new Double(getLength());
        return this.getName().hashCode() * 7 +
                11 * length.hashCode() +
                13 * width.hashCode() +
                17;
    }

    @Override
    public String toString() {
        return super.toString() + "[ширина = " + this.getWidth() + ", длина = " + this.getLength() + "]";
    }

    @Override
    public boolean equals (Object otherObject){
        if (!super.equals(otherObject)) return false;

        Room other = (Room) otherObject;
        return this.getLength() == other.getLength() &&
                this.getWidth() == other.getWidth();
    }
}