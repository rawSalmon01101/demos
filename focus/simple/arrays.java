package simple;

public class arrays
{
    //one dimensional arrays
    public class $1d
    {
        //whole numbers
        public static int[] inputInteger (int row)
        {
            int arr[] = new int[row];
            
            for (int i = 0; i < row; i++)
                arr[i] = input.integers("Enter row " + i);
            
            return arr;
        }
        public static short[] inputShort (int row)
        {
            short arr[] = new short[row];
            
            for (int i = 0; i < row; i++)
                arr[i] = (short) input.integers("Enter row " + i);
            
            return arr;
        }
        public static byte[] inputByte (int row)
        {
            byte arr[] = new byte[row];
            
            for (int i = 0; i < row; i++)
                arr[i] = (byte) input.integers("Enter row " + i);
            
            return arr;
        }
        
        //real nos.
        public static double[] inputDouble (int row)
        {
            double arr[] = new double[row];
            
            for (int i = 0; i < row; i++)
                arr[i] = input.real("Enter row " + i);
                
            return arr;
        }
        public static float[] inputFloat (int row)
        {
            float arr[] = new float[row];
            
            for (int i = 0; i < row; i++)
                arr[i] = (float) input.real("Enter row " + i);
                
            return arr;
        }
        
        //character and String types
        public static String[] inputString (int row)
        {
            String arr[] = new String[row];
            
            for (int i = 0; i < row; i++)
                arr[i] = input.line("Enter row " + i);
            
            return arr;
        }
        public static char[] inputChar (int row)
        {
            char arr[] = new char[row];
            
            for (int i = 0; i < row; i++)
                arr[i] = input.line("Enter row " + i).charAt(0);
                
            return arr;
        }
    }
    
    public class $2d
    {
        //whole numbers
        public static int[][] inputInteger (int row, int col)
        {
            int arr[][] = new int[row][col];
            
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    arr[i][j] = input.integers("");
            
            return arr;
        }
        public static short[][] inputShort (int row, int col)
        {
            short arr[][] = new short[row][col];
            
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    arr[i][j] = (short) input.integers("");
            
            return arr;
        }
        public static byte[][] inputByte (int row, int col)
        {
            byte arr[][] = new byte[row][col];
            
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    arr[i][j] = (byte) input.integers("");
            
            return arr;
        }
        
        //real nos.
        public static double[][] inputDouble (int row, int col)
        {
            double arr[][] = new double[row][col];
            
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    arr[i][j] = input.real("");
                
            return arr;
        }
        public static float[][] inputFloat (int row, int col)
        {
            float arr[][] = new float[row][col];
            
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    arr[i][j] = (float) input.real("");
                
            return arr;
        }
        
        //character and String types
        public static String[][] inputString (int row, int col)
        {
            String arr[][] = new String[row][col];
            
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    arr[i][j] = input.line("");
            
            return arr;
        }
        public static char[][] inputChar (int row, int col)
        {
            char arr[][] = new char[row][col];
            
            for (int i = 0; i < row; i++)
                for (int j  = 0; j < col; j++)
                    arr[i][j] = input.line("").charAt(0);
                
            return arr;
        }
    }

    public class $copy1d
    {
        public static int[] Integer (int arr[])
        {
            int arr2[] = new int[arr.length];
            for (int i = 0; i < arr2.length; i++)
                arr2[i] = arr[i];

            return arr2;
        }
        public static short[] Short (short arr[])
        {
            short arr2[] = new short[arr.length];
            for (int i = 0; i < arr2.length; i++)
                arr2[i] = arr[i];

            return arr2;
        }
        public static byte[] Byte (byte arr[])
        {
            byte arr2[] = new byte[arr.length];
            for (int i = 0; i < arr2.length; i++)
                arr2[i] = arr[i];

            return arr2;
        }
        public static long[] Long (long arr[])
        {
            long arr2[] = new long[arr.length];
            for (int i = 0; i < arr2.length; i++)
                arr2[i] = arr[i];

            return arr2;
        }
        public static String[] Strings(String str[])
        {
            String str2[] = new String[str.length];
            for (int i = 0; i < str2.length; i++)
                str2[i] = str[i];

            return str2;
        }
        public static char[] Character(char arr[])
        {
            char res[] = new char[arr.length];
            for (int i = 0; i < res.length; i++) 
                res[i] = arr[i];
            
            return res;
        }
        public static boolean[] Boolean(boolean arr[])
        {
            boolean res[] = new boolean[arr.length];
            for (int i = 0; i < res.length; i++)
                res[i] = arr[i];


            return res;
        }
    }
}