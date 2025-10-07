import java.util.*;

class shiftPattern
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter the no...");
        int n = sc.nextInt();
        sc.close();

        boolean layout = true;
        shiftPrint(n, layout);
    }

    public static void shiftPrint(int n, boolean dir)
    {
        String str = Integer.toString(n);
        String fused = str;

        for (int i = 0; i < str.length(); i++)
        {
            System.out.println(fused);
            if(!dir)
                fused = fused.substring(1) + fused.charAt(0);
            else
                fused = fused.charAt(str.length() - 1) + fused.substring(0, (str.length() - 1));
        }
    }
}