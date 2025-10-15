import simple.*;

class tictactoe
{
    static char arr[][] = new char[3][3];
    public static void main(String[] args) throws InterruptedException
    {
        init();

        int moves = 0;
        while (moves < 9)
        {
            win_cmd.run_once("cls");
            print();

            System.out.println("\n\n");

            if (moves % 2 == 0)
                System.out.println("Turn for X");
            else
                System.out.println("Turn for O");

            int row = input.integers(ansicolors.cyan + "Enter row: " + ansicolors.reset_text);
            int col = input.integers(ansicolors.red + "Enter col: " + ansicolors.reset_text);
            row--;
            col--;

            if (arr[row][col] == ' ')
                arr[row][col] = (moves % 2 == 0) ? 'X' : 'O';
            else
            {
                System.out.println(ansicolors.red_bg + "[!] Tried to overwrite. This move will be dropped" + ansicolors.reset);
                Thread.sleep(1900);
                moves--;

                //by the way, by doing this you may possibly have
                //defied rules under some remote ass legislation
                //how does that thought for food feel like? Not exactly
                //great, right? Welp, I made that up. teehee.
            }

            moves++;

            if (hasWon('O') || hasWon('X'))
                break;
        }

        win_cmd.run_once("cls");
            print();

        System.out.println("\n\n");

        System.out.println("\u0007");
        if (hasWon('O'))
            System.out.println(ansicolors.green + "O won the game" + ansicolors.reset);
        else if (hasWon('X'))
            System.out.println(ansicolors.yellow + "X won the game" + ansicolors.reset_text);
        else
            System.out.println(ansicolors.white_bg + "Draw" + ansicolors.reset);
    }

    public static void init()
    {
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++)
                arr[i][j] = ' ';
    }
    public static void print()
    {
        System.out.print(ansicolors.purple_bg + "  " + ansicolors.reset);
        System.out.println(ansicolors.red_bg + " 1   2   3 " + ansicolors.reset);

        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[i].length; j++)
            {
                if (j == 0)
                    System.out.print(ansicolors.blue_bg + (i + 1) + " " + ansicolors.reset);

                System.out.print(" " + arr[i][j] + " ");
                System.out.print(j == 2 ? "" : "|");
            }

            System.out.println();
            if (i < 2)
                System.out.println(ansicolors.blue_bg + "  " + ansicolors.reset + "---+---+---");
        }
    }

    public static boolean hasWon(char t)
    {
        //horizontal checks
        if (arr[0][0] == t && arr[0][1] == t&& arr[0][2] == t)
            return true;
        else if (arr[1][0] == t && arr[1][1] == t&& arr[1][2] == t)
            return true;
        else if (arr[2][0] == t && arr[2][1] == t&& arr[2][2] == t)
            return true;

        //vertical checks
        else if (arr[0][0] == t && arr[1][0] == t && arr[2][0] == t)
            return true;
        else if (arr[0][1] == t && arr[1][1] == t && arr[2][1] == t)
            return true;
        else if (arr[0][2] == t && arr[1][2] == t && arr[2][2] == t)
            return true;

        //diagonal checks
        else if (arr[0][0] == t && arr[1][1] == t && arr[2][2] == t)
            return true;
        else if (arr[0][2] == t && arr[1][1] == t && arr[2][0] == t)
            return true;

        //all in vain...
        else
            return false;
    }
}