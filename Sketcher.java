import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.SwingUtilities;

public class Sketcher
{
    private static SketchFrame window;
    private static Sketcher theApp;

    class WindowHandler extends WindowAdapter
    {
        public void WindowClosing(WindowEvent e)
        {
            window.dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) 
    {
        theApp = new Sketcher();
        SwingUtilities.invokeLater 
        (
            new Runnable() 
            {
                public void run()
                {
                    theApp.createGUI();
                }
            }
        );
    }

    private void createGUI()
    {
        window = new SketchFrame("Sketcher");
        Toolkit theKit = window.getToolkit();
        Dimension wndSize = theKit.getScreenSize();

        window.setBounds(wndSize.width / 4, wndSize.height / 4, wndSize.width / 2, wndSize.height / 2);
        window.addWindowListener(new WindowHandler());
        window.setVisible(true);
    }

    //handler for window closing event
    public void windowClsosing(WindowEvent e)
    {
        window.dispose();
        System.exit(0);
    }

    //Listener interface functions you must implement - but don't need
    public void windowOpened(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {}
}