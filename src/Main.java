import creatures.Creature;
import creatures.Dog;
import creatures.Ghost;
import creatures.Human;
import place.Apartments;
import place.Room;


public class Main {

    public static void main(String[] args) throws InterruptedException{
        Room kitchen = new Room("Кухня", 10.1, 12.3);
        Room diningRoom = new Room("Столовая", 9.4, 11.5);
        Room malishRoom = new Room("Комната Малыша", 5.6, 6.7);
        Room bigRoom = new Room("Большая комната", 10.1, 12.3);
        Apartments apartments = new Apartments("квартира", 3.6, 5, kitchen, diningRoom, malishRoom, bigRoom);
        apartments.addCommunication(kitchen, diningRoom, bigRoom);
        apartments.addCommunication(diningRoom, kitchen, malishRoom, bigRoom);
        apartments.addCommunication(malishRoom, diningRoom);
        apartments.addCommunication(bigRoom, diningRoom, kitchen);
        Ghost ghost= new Ghost(Creature.Gender.UNKNOWN, Creature.Act.FLY, "Привидиение", kitchen, true, false, 18, 6, false, Ghost.ColorGhost.BLUE);
        Human malish= new Human(Creature.Gender.MALE, Creature.Act.RUN, "Малыш", kitchen, false, false, 19,10, false, 97, 8);
        Human frekenBok= new Human(Creature.Gender.FEMAALE, Creature.Act.JUMP,"фрекен Бок", kitchen, false, false, 23, 4, false, 47, 59);
        Dog bimbo= new Dog(Creature.Gender.MALE, Creature.Act.RUSH, "Бимбо", kitchen, false, false, 33,17, false,  Dog.ColorDog.BLACK);
        Game game = new Game(apartments);
        game.addPlayer(frekenBok);
        game.addPlayer(ghost);
        game.addPlayer(malish);
        game.addPlayer(bimbo);
        game.startGame();
    }
}
