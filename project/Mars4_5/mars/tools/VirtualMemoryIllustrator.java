package mars.tools;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import mars.Globals;

@SuppressWarnings("serial")
public class VirtualMemoryIllustrator extends AbstractMarsToolAndApplication {

	private static String heading =  "Virtual Memory Illustrator";
	private static String version = " Version 1.0";
	
	public static void main(String[] args) {
        new VirtualMemoryIllustrator(heading+", "+version,heading).go();
     }
	
	public VirtualMemoryIllustrator(String title, String heading) {
		super(title, heading);
	}
	
	public VirtualMemoryIllustrator(){
        super (heading+", "+version, heading);
    }

	@Override
	public String getName() {
		return "Virtual Memory Illustrator";
	}
	
	@Override
	public void action() {
        this.isBeingUsedAsAMarsTool = true;
        dialog = new JDialog(Globals.getGui(), this.title);
     	// assure the dialog goes away if user clicks the X
        dialog.addWindowListener(
               new WindowAdapter() {
                  public void windowClosing(WindowEvent e) {
                    performSpecialClosingDuties();
                 }
              });		
        theWindow = dialog;
        initializePreGUI();
        JPanel contentPane = new JPanel(new BorderLayout(5,5));
        contentPane.setBorder(emptyBorder);
        contentPane.setOpaque(true);
        contentPane.add(buildHeadingArea(), BorderLayout.NORTH);
        contentPane.add(buildMainDisplayArea(),BorderLayout.CENTER);
        contentPane.add(buildButtonAreaMarsTool(), BorderLayout.SOUTH);
        initializePostGUI();
        dialog.setContentPane(contentPane);
        dialog.pack();      
        dialog.setLocationRelativeTo(Globals.getGui());
        dialog.setVisible(true);
     }

	@Override
	protected JComponent buildMainDisplayArea() {
		JTextArea message = new JTextArea();
		message.setEditable(false);
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		message.setFont(new Font("Ariel",Font.PLAIN,12));
		message.setText("fffffffffffffffffffffffffffffffff");
		message.setCaretPosition(0); // Assure first line is visible and at top of scroll pane.
	    return new JScrollPane(message);
	}
	
	@Override
	protected JComponent buildButtonAreaMarsTool() {
        Box buttonArea = Box.createHorizontalBox();
        TitledBorder tc = new TitledBorder("Tool Control");
        tc.setTitleJustification(TitledBorder.CENTER);
        buttonArea.setBorder(tc);
        JButton initializerButton = new JButton("Initilizer Memory");
        initializerButton.setToolTipText("Initilizer Virtual Memory Illustrator");
        initializerButton.addActionListener(
               new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                    initilizerVirtualMemoryIllustrator();
                 }
              });
        initializerButton.addKeyListener(new EnterKeyListener(initializerButton));
     	
        JButton resetButton = new JButton("Reset");
        resetButton.setToolTipText("Reset all counters and other structures");
        resetButton.addActionListener(
               new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    reset();
                 }
              });
        resetButton.addKeyListener(new EnterKeyListener(resetButton));
     	
        JButton closeButton = new JButton("Close");
        closeButton.setToolTipText("Close (exit) this tool");
        closeButton.addActionListener(
               new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    performSpecialClosingDuties();
                 }
              });
        closeButton.addKeyListener(new EnterKeyListener(closeButton));
     
     	// Add all the buttons...
        buttonArea.add(initializerButton);
        buttonArea.add(Box.createHorizontalGlue());
        buttonArea.add(resetButton);
        buttonArea.add(Box.createHorizontalGlue());
        JComponent helpComponent = getHelpComponent();
        if (helpComponent != null) {
           buttonArea.add(helpComponent);
           buttonArea.add(Box.createHorizontalGlue());
        }
        buttonArea.add(closeButton);
        return buttonArea;
     }
	
	@Override
	public void update(Observable resource, Object accessNotice) {
		
        updateDisplay();
    }
	
	@Override
	protected void updateDisplay(){
		//Atualização do display...
	}
	
	@Override
	protected void initializePreGUI() {
		//grafico
	}
  
	@Override
    protected void initializePostGUI() {
    }
	
	///////////////////////// BUTTONS ////////////////////////////////
	
	protected void initilizerVirtualMemoryIllustrator(){
		//button initializer
	}
	
	@Override
	protected void reset() {
		//button reset
    }
    
    @Override
	protected void performSpecialClosingDuties() {
    	//button close ou (X)
		//O que fazer quando fechar pelo (X) ou close;
    	dialog.setVisible(false);
        dialog.dispose();
    }
}
