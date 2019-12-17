package item;
public abstract class Item {
    private String Name;

    public Item(String name) {
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

        Item item = (Item) otherObject;
        return this.getName().equals(item.getName());
    }
}