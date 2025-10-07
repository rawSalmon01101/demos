package simple;

public class calc
{
    //newtonian squareroot approximation
    public static double sqrt_approx(double n)
    {
        double x = n, l = 0.00001;
        double root;
        int count = 0;
        while (true)
        {
            count++;
            root = 0.5 * (x + (n / x));
            if (Math.abs(root - x) < 1)
                break;
            x = root;
        }
        return root;
    }
    
    //Bhaskara L sine approximation algorithm
    //only 0 - 180deg application
    public static double sin_approx(double deg)
    {
        double num = (4 * deg) * (180 - deg);
        double den = (40500 - deg * (180 - deg));
        
        return num / den;
    }
    public class $physics
    {
        
        //inconsistency of overloads will result in calc errors.
        //works best with SI. 
        //CGS, MKS, FPS also partially supported.
        
        public static final double SMALL_G = 9.80665d;
        public static final long LIGHT_VAC = 299792458;
        
        public static double e_kin (double m, double v)
        {
            return (0.5 * m * (v * v));
        }
        public static double e_pot (double m, double h)
        {
            return (m * SMALL_G * h);
        }
        
        public static double ri_abs (double v)
        {
            return (double)LIGHT_VAC / v;
        }
        public static double ri_rel (double v1, double v2)
        {
            return (v1 / v2);
        }
        public static double foc_len (double u, double v)
        {
            return ((v * u) / (v + u));
        }
        
        public static double volts (double amps, double ohms)
        {
            return (amps * ohms);
        }
        public static double amps (double volts, double ohms)
        {
            return (volts / ohms);
        }
        public static double ohms (double volts, double amps)
        {
            return (volts / amps);
        }
        
        
    }
}