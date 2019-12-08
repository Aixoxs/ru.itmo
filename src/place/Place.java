package place;
public abstract class Place {
    private String Name;

    public Place(String name) {
        this.Name = name;
    }

    public String getName() {
        return this.Name;
    }

    @Override
    public String toString() {
        return getClass().getName() +  "[Имя = " + this.Name + "]";
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;

        if (otherObject == null || this.getClass() != otherObject.getClass()) return false;

        Place place = (Place) otherObject;
        return this.getName().equals(place.getName());
    }
}