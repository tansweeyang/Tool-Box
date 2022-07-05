import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class About extends JFrame {
	JPanel panelImage;
	JLabel imageLabel;
	JTextArea textArea;
	
	About() {
		ImageIcon aboutIcon = new ImageIcon("catbleh.jpg");
		
		imageLabel = new JLabel();
		imageLabel.setIcon(aboutIcon);
		
		panelImage = new JPanel();
		panelImage.setBackground(Color.black);
		panelImage.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelImage.add(imageLabel);
		
		textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createLineBorder(Color.pink));
		textArea.setForeground(Color.pink);
		textArea.setBackground(Color.black);
		textArea.setCaretColor(Color.white);
		textArea.append("About This Program\n");
		textArea.append("Name: ToolBox(I Guess?)\n");
		textArea.append("Date Created: 27/9/2021\n");
		textArea.append("Language: Java\n");
		textArea.append("Version: Testing\n\n");
		textArea.append("About Me\n");
		textArea.append("Name: Eislyn\n");
		textArea.append("Education Level: Undergraduate Student\n");
		textArea.append("Programming Languages: Java, C++, mySQL\n");
		textArea.append("Contact: eislyngwyneth@gmail.com");
		
		textArea.setEditable(false);
		
		this.setTitle("About");
		this.setIconImage(aboutIcon.getImage());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400, 400));
		this.setResizable(false);
		
		this.add(panelImage,BorderLayout.NORTH);
		this.add(textArea,BorderLayout.CENTER);

		this.pack();
		this.setVisible(true);
	}
}