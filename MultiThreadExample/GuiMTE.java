import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class GuiMTE extends JPanel
					implements ActionListener {
	//fields
	static private final String newline = "\n";
	JButton runButton;
	JTextArea log;
	
	//constructor
	public GuiMTE() {
		super(new BorderLayout());
		
		//create the output display area
		log = new JTextArea(10,50);
		log.setMargin(new Insets(5,5,5,5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
		
		//create the run button
		runButton = new JButton("Run");
		runButton.addActionListener(this); //this class implements ActionListener
		
		//put the button in a separate panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(runButton);
		
		//add the buttonPanel and display area to this panel
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		log.append("running..." + newline);
		if(ev.getSource() == runButton){
			runButtonAction();
		}
	}
	public void runButtonAction() {
		Thread t = new Thread(new Runnable(){
			public void run(){
				try {
					MultiThreadExample.main(null);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
			});
			t.start();
		while(t.isAlive()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.append("soma total = " + MultiThreadExample.soma1 + " calculado em unico thread em " +
		 MultiThreadExample.tempo1/1000 + "s" + newline);
		log.append("soma total = " + MultiThreadExample.soma2 + " calculado em " + MultiThreadExample.data.length +
				" threads em " + MultiThreadExample.tempo2/1000 + "s" + newline);
	}
	public static void createAndShow() {
		JFrame frame = new JFrame("MultiThreaExample GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new GuiMTE());
		
		frame.pack();
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShow();
			}
		});

	}

}
