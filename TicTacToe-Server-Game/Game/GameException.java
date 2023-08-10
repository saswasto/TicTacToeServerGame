package Game;

public class GameException extends Exception {
}

class PositionAlreadyMarkedException extends GameException {
}

class GameDrawException extends GameException {
}

class PlayerWonException extends GameException {
    String pos;

    PlayerWonException(String pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "pos='" + pos;
    }
}