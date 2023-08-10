package Game;

public class TicTacToe {
    int[] array;
    int count;

    TicTacToe() {
        array = new int[9];
        count = 0;
    }

    public void cardinal(String msg, String mark) throws PositionAlreadyMarkedException, GameDrawException, PlayerWonException {
        int position = Integer.parseInt(msg);
        int m = mark.equals("x") ? 1 : -1; //x = 1
                                            // o = -1

        markZeroesCrosses(position, m);
        continueGameOrNot(m);

    }

    private void markZeroesCrosses(int position, int mark) throws PositionAlreadyMarkedException {
        if (array[position - 1] == 0) {
            array[position - 1] = mark;
            count++;
        } else
            throw new PositionAlreadyMarkedException();
    }

    private boolean continueGameOrNot(int mark) throws GameDrawException, PlayerWonException {
        if (count == 9)
            throw new GameDrawException();
        else {
            threes(mark == 1 ? 3 : -3);
        }
        return true;
    }

    private void threes(int sum) throws PlayerWonException {
        if (array[0] + array[1] + array[2] == sum)
            throw new PlayerWonException("1,2,3");
        else if (array[0] + array[3] + array[6] == sum)
            throw new PlayerWonException("1,4,7");
        else if (array[2] + array[5] + array[8] == sum)
            throw new PlayerWonException("3,6,9");
        else if (array[6] + array[7] + array[8] == sum)
            throw new PlayerWonException("7,8,9");
        else if (array[0] + array[4] + array[8] == sum)
            throw new PlayerWonException("1,5,9");
        else if (array[1] + array[4] + array[7] == sum)
            throw new PlayerWonException("2,5,8");
        else if (array[2] + array[4] + array[6] == sum)
            throw new PlayerWonException("3,5,7");
        else if (array[3] + array[4] + array[5] == sum)
            throw new PlayerWonException("4,5,6");
    }

    public String arrayToString() {
        String msg = "";
        for (int i = 1; i < 10; i++) {
            String mark = array[i - 1] == 0 ? " " : array[i - 1] == -1 ? "O" : "X";
            msg += String.format("%2s",mark);   // to print fixed no. of characters (s = spaces if no char present)
//            System.out.printf("%2s", mark);
            if (i % 3 == 0) {
                msg += "\n---------\n";
//                System.out.println("\n---------");
            }
            else {
                msg += "|";
//                System.out.print("|");
            }
        }
        msg += "\n";
//        System.out.println();

        return msg;
    }

}
