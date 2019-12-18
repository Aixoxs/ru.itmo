import creatures.*;
import exceptions.NotEnoughIQ;
import item.*;

import java.util.ArrayList;

public class Game {
    ArrayList<Creature> characters = new ArrayList<>();
    Communication apartments;

    public Game(Communication apartments) {
        this.apartments = apartments;
    }

    public void addPlayer(Creature creature) {
        characters.add(creature);
    }

    public void startGame() throws InterruptedException {
            final int[] fatigue = new int[1];
            int count = 0;
            //объект внутреннего класса Barricade может служить препятствием для персонажа, преграда разбирается в зависимости ото скорости персонажа, если персонаж не смог разобрать преграду до того, как кого то догнали, то игра заканчивается
            class Barricade extends Item {
                public Barricade(String name) {
                    super(name);
                }

                public void disassembly(Creature creature) {
                    System.out.println(creature.getName() + " кинулась к двери и стала расшвыривать мебель");
                    int countFurniture = 5;
                    countFurniture -= creature.getSpeed();
                    if (countFurniture <= 0) {
                        System.out.println("В мгновение ока, разобрав " + getName() + "," + creature.getName() + " с громким криком выбежала в " + creature.getLastRoom().getName());
                    } else {
                        cikl:
                        while (countFurniture > 0) {
                            countFurniture -= creature.getSpeed();
                            for (int i = 1; i < characters.size(); i++) {
                                try {
                                    characters.get(i).changeDistance(characters.get(i - 1));
                                }
                                catch (NotEnoughIQ e) {
                                    System.out.println(characters.get(i).getName() + " не может догнать, потому что тупой");
                                }
                                if (characters.get(i).getDistanceToTarget() <= 0) {
                                    System.out.println(characters.get(i).getName() + " догнал " + characters.get(i - 1).getName() + " и игра закончилась");
                                    fatigue[0] = -1;
                                    break cikl;
                                }
                            }
                        }
                        if (fatigue[0] != -1){
                            System.out.println(creature.getName() + " с громким криком выбежала в " + creature.getLastRoom().getName());
                        }
                    }
                }
            }

            //объект анонимного класса, который является комнатой в которой начинается игра, и у которого есть связь со всеми комнатами
            Item firstRoom = new Item("переднюю") {
                public void checkFirstCreature(Creature creature) {
                    if (creature.getFirst()) creature.setLastRoom(this);
                }
            };
            apartments.addRoom(firstRoom);
            apartments.getCommunications().add(new ArrayList<Item>());
            apartments.addCommunication(apartments.getRooms().size()-1, apartments.getRooms());

            Barricade barricade = new Barricade("баррикаду");

            characters.get(0).setFirst(true);
            characters.get(characters.size() - 1).setLast(true);
            System.out.println("И " + characters.get(1).getName() + " разразилось долгим глухим смехом");
            Thread.sleep(1000);
            System.out.println("Но " + characters.get(0).getName() + " было не до смеха");
            Thread.sleep(1000);
            barricade.disassembly(characters.get(0));
            Thread.sleep(1000);

            if(fatigue[0] != -1) {
                for (int i = 1; i < characters.size() * 10; i++) {
                    int number = i % characters.size();
                    //Конец игры если преследователь догнал цель
                    if (characters.get(number).getDistanceToTarget() <= 0) {
                        System.out.println(characters.get(number).getName() + " догнал " + characters.get(number - 1).getName() + " и игра закончилась");
                        fatigue[0] = -1;
                        break;
                    }
                    //Условие для первого игрока, так как он никого не догоняет, то distanceToTarget у него не изменяется
                    if (characters.get(number).getFirst()) {
                        characters.get(number).setLastRoom(apartments.getCommunication(characters.get(0).getLastRoom()));
                        System.out.println("Погоня переместилась и ее продолжением стала " + characters.get(0).getLastRoom().getName());
                        System.out.println("Впереди все еще " + characters.get(0).getMyAct().getAction() + " " + characters.get(0).getName());
                        Thread.sleep(1000);
                    }
                    //действие последнего в игре и изменение расстояний
                    else if (characters.get(number).getLast()) {
                        System.out.println("Последним " + characters.get(number).getMyAct().getAction() + " " + characters.get(number).getName());
                        Thread.sleep(1000);
                        try {
                            characters.get(number).changeDistance(characters.get(number - 1));
                        }
                        catch (NotEnoughIQ e) {
                            System.out.println(characters.get(number).getName() + " не может догнать, потому что тупой");
                        }
                    }
                    //Два варианта предложения для игроков, которые не являются ни последними, ни перавыми. В обоих случаях расстояние изменяется
                    else if (Math.random() < 0.5) {
                        System.out.println(characters.get(number).getName() + " " + characters.get(number).getMyAct().getAction() + " следом");
                        Thread.sleep(1000);
                        try {
                            characters.get(number).changeDistance(characters.get(number - 1));
                        }
                        catch (NotEnoughIQ e) {
                            System.out.println(characters.get(number).getName() + " не может догнать, потому что тупой");
                        }
                    } else {
                        System.out.println(characters.get(number).getName() + " " + characters.get(number).getMyAct().getAction() + " за ним");
                        Thread.sleep(1000);
                        try {
                            characters.get(number).changeDistance(characters.get(number - 1));
                        }
                        catch (NotEnoughIQ e) {
                            System.out.println(characters.get(number).getName() + " не может догнать, потому что тупой");
                        }
                    }
                    //Собака узнает игрока только один раз
                    if (characters.get(number) instanceof Dog && count < 1) {
                        for (int k = 1; k < characters.size(); k++) {
                            if (((Dog) characters.get(number)).sniff(characters.get(k))) {
                                System.out.println("Он узнал " + characters.get(k).getName() + " по запаху и думал, что началась веселая игра.");
                            }
                        }
                        count += 1;
                    }
                }
            }
            if (fatigue[0] > -1) {
                System.out.println("Игра закончилась потому что игроки устали");
            }
    }
}
