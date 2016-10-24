package mars.tools;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.sun.xml.internal.ws.api.Component;

import javafx.scene.control.Cell;
import mars.Globals;
import mars.mips.hardware.RegisterFile;
import mars.pluginRJ.management.memory.AlgorithmType;
import mars.pluginRJ.management.memory.MemoryManagement;
import mars.pluginRJ.management.memory.Page;
import mars.pluginRJ.management.memory.PageTable;

@SuppressWarnings("serial")
public class VirtualMemoryIllustrator extends AbstractMarsToolAndApplication {

	private static String heading =  "Virtual Memory Illustrator";
	private static String version = " Version 1.0";
	
	private javax.swing.JButton buttonHit;
    private javax.swing.JButton buttonMiss;
    private javax.swing.JScrollPane jScrollPaneResult;
    private javax.swing.JPanel panelResult;
    private javax.swing.JRadioButton radioFIFO;
    private javax.swing.ButtonGroup radioGroup;
    private javax.swing.JRadioButton radioLRU;
    private javax.swing.JRadioButton radioNRU;
    private javax.swing.JRadioButton radioSC;
    private javax.swing.JTable tableHitMiss;
    private javax.swing.JTable tableVirtualMemory;
    private javax.swing.JTextField textAddressHit;
	
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
	    JPanel p = new JPanel();
	    
	    radioGroup = new javax.swing.ButtonGroup();
        panelResult = new javax.swing.JPanel();
        jScrollPaneResult = new javax.swing.JScrollPane();
        tableHitMiss = new javax.swing.JTable();
        buttonMiss = new javax.swing.JButton();
        buttonHit = new javax.swing.JButton();
        textAddressHit = new javax.swing.JTextField();
        radioFIFO = new javax.swing.JRadioButton();
        radioLRU = new javax.swing.JRadioButton();
        radioSC = new javax.swing.JRadioButton();
        radioNRU = new javax.swing.JRadioButton();
        tableVirtualMemory = new javax.swing.JTable();

        radioGroup.add(radioFIFO);
        radioGroup.add(radioLRU);
        radioGroup.add(radioNRU);
        radioGroup.add(radioSC);

        tableHitMiss.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {1, null, null},
                        {2, null, null},
                        {3, null, null},
                        {4, null, null}
                },
                new String [] {
                        "Process", "HIT", "MISS"
                }
        ) {
                boolean[] canEdit = new boolean [] {
                        false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                }
        });
        jScrollPaneResult.setViewportView(tableHitMiss);
        if (tableHitMiss.getColumnModel().getColumnCount() > 0) {
                tableHitMiss.getColumnModel().getColumn(0).setResizable(false);
                tableHitMiss.getColumnModel().getColumn(0).setPreferredWidth(100);
        }

        buttonMiss.setText("MISS");

        buttonHit.setText("HIT");

        textAddressHit.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        radioFIFO.setText("FIFO");
        radioFIFO.setActionCommand("FIFO");

        radioLRU.setText("LRU");
        radioLRU.setActionCommand("LRU");

        radioSC.setText("SC");
        radioSC.setActionCommand("SC");

        radioNRU.setText("NRU");
        radioNRU.setActionCommand("NRU");

        javax.swing.GroupLayout panelResultLayout = new javax.swing.GroupLayout(panelResult);
        panelResult.setLayout(panelResultLayout);
        panelResultLayout.setHorizontalGroup(
                panelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelResultLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textAddressHit)
                                .addComponent(jScrollPaneResult, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                .addGroup(panelResultLayout.createSequentialGroup()
                                        .addGroup(panelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(radioFIFO, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                                .addComponent(buttonMiss, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(radioLRU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelResultLayout.createSequentialGroup()
                                                        .addComponent(radioNRU, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(buttonHit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(radioSC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
        );
        panelResultLayout.setVerticalGroup(
                panelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelResultLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPaneResult, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(radioFIFO)
                                .addComponent(radioSC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(radioLRU)
                                .addComponent(radioNRU))
                        .addGap(26, 26, 26)
                        .addGroup(panelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(buttonMiss, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonHit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textAddressHit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableVirtualMemory.setBorder(null);
        tableVirtualMemory.setModel(new ModelTableVirtual(16,1));
        tableVirtualMemory.setGridColor(new java.awt.Color(35,35,35));
        tableVirtualMemory.setSurrendersFocusOnKeystroke(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(tableVirtualMemory, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 12, Short.MAX_VALUE)
                                        .addComponent(tableVirtualMemory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(panelResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );

        if (tableVirtualMemory.getColumnModel().getColumnCount() > 0) {
                tableVirtualMemory.getColumnModel().getColumn(0).setResizable(false);
        }
        
	    p.add(panelResult);
	    p.add(tableVirtualMemory);
	    
	    return p;
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
	public void update(Observable r, Object accessNotice) {
		PageTable table = (PageTable) r;
		ModelTableVirtual d = (ModelTableVirtual) tableVirtualMemory.getModel();
		DefaultTableModel tableResult = (DefaultTableModel) tableHitMiss.getModel();
		
		
		if(table.getPageFault()){
			//add address na table
			d.setValueAt(table.getTable().get(table.getIndexMap()).getValue(), table.getIndexMap(), 0);
			buttonMiss.setBackground(Color.red);
			buttonHit.setBackground(Color.gray);
		}else{
			//set address que deu hit
			textAddressHit.setText(String.valueOf(table.getTable().get(table.getIndexMap()).getValue()));
			buttonHit.setBackground(Color.green);
			buttonMiss.setBackground(Color.gray);
		}
		
		//set table miss/hit
		tableResult.setValueAt(table.getOption().get(table.getIndexProcess()).getHit(), table.getIndexProcess(), 1);
		tableResult.setValueAt(table.getOption().get(table.getIndexProcess()).getMiss(), table.getIndexProcess(), 2);
    }
	
	@Override
	protected void updateDisplay(){
		//Atualização do display...
	}
	
	
	///////////////////////// BUTTONS ////////////////////////////////
	
	protected void initilizerVirtualMemoryIllustrator(){
		String b = radioGroup.getSelection().getActionCommand();
		
		if(b.equals("FIFO")){
			MemoryManagement.setInstace(AlgorithmType.FIFO);
			System.out.println("Algoritmo escolhido -> FIFO");
		}else
		if(b.equals("SC")){
			MemoryManagement.setInstace(AlgorithmType.SC);
			System.out.println("Algoritmo escolhido -> SC");
		}else
		if(b.equals("NRU")){
			MemoryManagement.setInstace(AlgorithmType.NRU);
		}else
		if(b.equals("LRU")){
			MemoryManagement.setInstace(AlgorithmType.LRU);
		}
		
		MemoryManagement.getInstace().getTable().addObserver(this);
		RegisterFile.addPCObserver(MemoryManagement.getInstace());
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

/// tables ///

class ModelTableVirtual extends DefaultTableModel{
    Class[] types;
    boolean[] canEdit;
    
    public ModelTableVirtual(){
        super();
        types = new Class [] {Integer.class};
        canEdit = new boolean [] {false};   
    }
    
    public ModelTableVirtual(int row, int colunm){
        super(row,colunm);
        types = new Class [] {Integer.class};
        canEdit = new boolean [] {false};   
    }
    
    public ModelTableVirtual(Object[][] obj, String[] obj2){
        super(obj, obj2);
        types = new Class [] {Integer.class};
        canEdit = new boolean [] {false};   
    }

    public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
    }
}
