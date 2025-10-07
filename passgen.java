import java.util.Scanner;

class passgen
{
    public static void main (String args[])
    {
        Scanner sc = new Scanner (System.in);
        System.out.print("Enter password length: ");
        int len = sc.nextInt();

        char caps [] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char small [] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char sym [] = {'!', '~', '@', '#', '$', '%', '^', '&', '*', '-', '=', '_', '+', '(', ')', '/'};
        char num [] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

        for (int k = 1; k <= 20; k++)
        {
            for (int i = 1; i <= len; i++)
            {
                int rand = (int) (Math.random() * 4);
                if (rand == 0)
                {
                    rand = (int) (Math.random() * 26);
                    System.out.print(caps[rand]);
                }
                else if (rand == 1)
                {
                    rand = (int) (Math.random() * 26);
                    System.out.print(small[rand]);
                }
                else if (rand == 2)
                {
                    rand = (int) (Math.random() * 16);
                    System.out.print(sym[rand]);
                }
                else if (rand == 3)
                {
                    rand = (int) (Math.random() * 10);
                    System.out.print(num[rand]);
                }
            }
            System.out.println();
        }

        System.out.println("Success!");
        System.out.println("Press enter to continue...");
        sc.nextLine();
        sc.nextLine();

        sc.close();
    }
}