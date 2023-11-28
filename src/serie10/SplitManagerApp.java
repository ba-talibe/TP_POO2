package serie10;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.EnumMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import serie10.model.*;


public class SplitManagerApp {
	
	private SplitManager model;
	private JFrame frame;
	private JTextField filenameTf;
	private JButton browserBtn;
	private JComboBox fragmentNbBox;
	private JTextField framgmentSizeTf;
	private JTextArea infoArea ;
	private JButton fragmentBtn;
	private JLabel tipsInfo ;
	
	
	public SplitManagerApp() {
		model = new StdSplitManager();
	}
	
	private void placeComponents(){
	
	}
	
	public void display() {
        refresh();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
	
	private void connectControllers(){
		
	}
	
	private void refresh(){
		
	}
	
	private void createController(){
		
	}
	
	private JFrame createFrame() {
        final int frameWidth = 600;
        final int frameHeight = 300;
        
        JFrame f = new JFrame("Distributeur de boissons");
        f.setLayout(new BorderLayout());
        f.setPreferredSize(new Dimension(frameWidth, frameHeight));
        return f;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SplitManagerApp().display();
            }
        });
    }
	
}


















