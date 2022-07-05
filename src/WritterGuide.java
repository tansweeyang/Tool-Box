import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WritterGuide extends JFrame{
	ImageIcon writterGuideIcon = new ImageIcon("notepadIcon.png");
	JPanel panelTextArea;
	JTextArea guideTextArea;
	
	WritterGuide(){
		this.setTitle("Writter Guide");
		this.setIconImage(writterGuideIcon.getImage());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400, 400));
		this.setResizable(true);
		this.setPreferredSize(new Dimension(1000,600));
		
		guideTextArea = new JTextArea();
		
		panelTextArea = new JPanel();
		panelTextArea.setLayout(new BorderLayout());
		panelTextArea.add(guideTextArea,BorderLayout.CENTER);
		
		guideTextArea.setFont(new Font("Arial", Font.PLAIN, 17));
		guideTextArea.setLineWrap(true);
		guideTextArea.setWrapStyleWord(true);
		guideTextArea.setForeground(Color.white);
		guideTextArea.setBackground(Color.black);
		guideTextArea.setEditable(false);
		guideTextArea.append("Writter Guide: \n");
		guideTextArea.append("Commands: Type in the text area and press Run on menu bar(Do not type anything above commands but feel free to add anything below)\n");
		guideTextArea.append("1.This writter is capable on running any application or any type of file as long as you have a external program to start it or it is independent\n");
		guideTextArea.append("   -Command: start + Enter Key + full file path and press run\n");
		guideTextArea.append("   -Note: start not Start (case sensitive)\n");
		guideTextArea.append("2.This writter can do basic calculations of addition, substraction, multiplication and division\n");
		guideTextArea.append("   -Command: add + Enter key + number + number + number if you have more and press run\n");
		guideTextArea.append("   -Command: sub + Enter key + number + number + number if you have more and press run\n");
		guideTextArea.append("   -Command: mul + Enter key + number + number + number if you have more and press run\n");
		guideTextArea.append("   -Command: div + Enter key + number + number + number if you have more and press run\n");
		guideTextArea.append("2.Shortcuts for menubar: Alt+Underlined letter\n");
		guideTextArea.append("then\n");
		guideTextArea.append("3.Shortcuts for menubarItems: Underlined letter\n");
		guideTextArea.append("4.To screenshot: Type ss and press run");
		
		this.add(panelTextArea,BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
		
	}

}
