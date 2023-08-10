package Game;

import java.io.IOException;

public class GameEngine implements Runnable {

    //client1 variables.......
    Client client1;
    //client2 variables.......
    Client client2;
    //Type of game
    TicTacToe game;

    public GameEngine(Client client1, Client client2) throws IOException {
        //client1 initializing......
        this.client1 = client1;
        //client2 initializing......
        this.client2 = client2;

        //assigning game program
        game = new TicTacToe();
    }

    @Override
    public void run() {

        //initial state
        client1.write("initial state\n" + game.arrayToString());
        client2.write("initial state\n" + game.arrayToString());

        boolean exit = false;
        boolean chance = true;
        String c1Mark = "x";
        String c2Mark = "o";

        while (!exit) {
            //client1 move
            if (chance) {
                exit = play(client1, client2, c1Mark);
                chance = false;
            }

            //client2 move
            else {
                exit = play(client2, client1, c2Mark);
                chance = true;
            }

        }
        client1.close();
        client2.close();
    }

    private boolean play(Client c1, Client c2, String mark) {
        String msg;
        try {
            boolean marked = false;
            while (!marked) {
                try {
//                    System.out.println("Please enter from 1-9: ");
                    c1.write("Please enter from 1-9: ");
                    msg = c1.read();    //read client1 next move
                    game.cardinal(msg, mark); //send client1 move to the game program
                    marked = true;     // if the given move is marked successfully then exit
                } catch (PositionAlreadyMarkedException ge) {
                    // exception thrown by game program is Position alreasy marked
//                    System.out.println("Position already occupied\nSelect another");
                    c1.write("Position already occupied\nSelect another");
                }
            }

            //send current state to the players
            c1.write(game.arrayToString());
            c2.write(game.arrayToString());

            c1.write("Wait for opponents move.........");
            c2.write("Your move!!!...........");
        } catch (PlayerWonException pwe) {
            c1.write("\n............Congrats!!!.........\n.....You won " + pwe);
            c2.write("\n.........You Lost " + pwe);
            c1.write(game.arrayToString());
            c2.write(game.arrayToString());
            return true;
        } catch (GameDrawException gde) {
            c1.write("...........Game Draw!!!...........");
            c2.write("...........Game Draw!!!...........");
            c1.write(game.arrayToString());
            c2.write(game.arrayToString());
            return true;
        }
        return false;
    }

}
