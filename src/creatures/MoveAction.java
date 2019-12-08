package creatures;

//классы использующие этот интерфейс могут участвовать в погоне
public interface MoveAction {
    double getSpeed();
    double getDistanceToTarget();
}
