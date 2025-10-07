import javax.swing.*;
import static java.awt.event.InputEvent.*;
import static java.awt.AWTEvent.*;
import java.awt.event.*;

public class SketchFrame extends JFrame
{
    public SketchFrame(String title)
    {
        setTitle(title);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);

        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("FIle");
        JMenu elementMenu = new JMenu("Elements");
        fileMenu.setMnemonic('F');
        elementMenu.setMnemonic('E');

        menuBar.add(fileMenu);
        menuBar.add(elementMenu);
        enableEvents(WINDOW_EVENT_MASK);

        //construct the file dropdown menu
        newitem = fileMenu.add("New");
        openitem = fileMenu.add("Open");
        closeitem = fileMenu.add("Close");
        fileMenu.addSeparator();
        saveitem = fileMenu.add("Save");
        saveasitem = fileMenu.add("Save As");
        fileMenu.addSeparator();
        printitem = fileMenu.add("Print");

        //add file menu accelerators
        newitem.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
        openitem.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
        saveitem.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        printitem.setAccelerator(KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK));

        //construct the element dropdown menu
        elementMenu.add(lineitem = new JRadioButtonMenuItem("Line", true));
        elementMenu.add(rectitem = new JRadioButtonMenuItem("Rectangle", true));
        elementMenu.add(circitem = new JRadioButtonMenuItem("Circle", true));
        elementMenu.add(curvitem = new JRadioButtonMenuItem("Curve", true));
        ButtonGroup types = new ButtonGroup();
        types.add(lineitem);
        types.add(rectitem);
        types.add(circitem);
        types.add(curvitem);

        //add element accelerator
        lineitem.setAccelerator(KeyStroke.getKeyStroke('L', CTRL_DOWN_MASK));
        circitem.setAccelerator(KeyStroke.getKeyStroke('I', CTRL_DOWN_MASK));
        curvitem.setAccelerator(KeyStroke.getKeyStroke('V', CTRL_DOWN_MASK));
        rectitem.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));

        elementMenu.addSeparator();
        
        elementMenu.add(reditem = new JCheckBoxMenuItem("Red", false));
        elementMenu.add(greenitem = new JCheckBoxMenuItem("Green", false));
        elementMenu.add(blueitem = new JCheckBoxMenuItem("Blue", false));
        elementMenu.add(yellowitem = new JCheckBoxMenuItem("Yellow", false));

        //add color accelerator
        reditem.setAccelerator(KeyStroke.getKeyStroke('R', CTRL_DOWN_MASK));
        greenitem.setAccelerator(KeyStroke.getKeyStroke('G', CTRL_DOWN_MASK));
        blueitem.setAccelerator(KeyStroke.getKeyStroke('B', CTRL_DOWN_MASK));
        yellowitem.setAccelerator(KeyStroke.getKeyStroke('Y', CTRL_DOWN_MASK));
    }

    //handle window events
    protected void processWindowEvent(WindowEvent e)
    {
        if (e.getID() == WindowEvent.WINDOW_CLOSING)
        {
            dispose();
            System.exit(0);
        }
        super.processWindowEvent(e);
    }

    private JMenuBar menuBar = new JMenuBar();

    //file menu items
    private JMenuItem newitem, openitem, closeitem, saveitem, saveasitem, printitem;
    private JRadioButtonMenuItem lineitem, rectitem, circitem, curvitem;
    private JCheckBoxMenuItem reditem, yellowitem, greenitem, blueitem;
}