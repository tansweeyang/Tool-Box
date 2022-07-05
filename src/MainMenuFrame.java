import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainMenuFrame extends JFrame implements ActionListener {
	JMenuItem closeMenuItem;
	JMenuItem backgroundColorMenuItem;
	JMenuItem aboutMenuItem;
	JButton buttonTodoList;
	JButton buttonWritter;
	JPanel panelButton;

	Writter writter;

	MainMenuFrame() {

		ImageIcon appIcon = new ImageIcon("catbleh.jpg");
		ImageIcon todoListIcon = new ImageIcon("todolistIcon.png");
		ImageIcon notepadIcon = new ImageIcon("notepadIcon.png");

		this.setTitle("Main Menu");
		this.setIconImage(appIcon.getImage());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500, 500));
		this.setResizable(true);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);

		// button
		buttonTodoList = new JButton("Todo-List");
		buttonTodoList.setVisible(false);
		buttonWritter = new JButton("Writter");
		buttonTodoList.setFocusable(false);
		buttonWritter.setFocusable(false);
		buttonTodoList.setPreferredSize(new Dimension(90, 95));
		buttonTodoList.setIcon(todoListIcon);
		buttonTodoList.setBackground(Color.WHITE);
		buttonTodoList.setBorder(BorderFactory.createLineBorder(Color.black));
		buttonTodoList.setHorizontalTextPosition(JButton.CENTER);
		buttonTodoList.setVerticalTextPosition(JButton.BOTTOM);
		buttonTodoList.addActionListener(this);
		buttonWritter.setPreferredSize(new Dimension(90, 95));
		buttonWritter.setIcon(notepadIcon);
		buttonWritter.setBackground(Color.white);
		buttonWritter.setBorder(BorderFactory.createLineBorder(Color.black));
		buttonWritter.setHorizontalTextPosition(JButton.CENTER);
		buttonWritter.setVerticalTextPosition(JButton.BOTTOM);
		buttonWritter.addActionListener(this);

		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelMenu.setBorder(BorderFactory.createLineBorder(Color.black));
		panelMenu.setBackground(Color.black);
		panelMenu.add(menuBar);

		panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout(FlowLayout.LEADING));
		panelButton.setBackground(Color.gray);
		panelButton.add(buttonTodoList);
		panelButton.add(buttonWritter);

		JMenu fileMenu = new JMenu("File");
		JMenu optionMenu = new JMenu("Option");
		JMenu helpMenu = new JMenu("Help");
		fileMenu.setForeground(Color.white);
		optionMenu.setForeground(Color.white);
		helpMenu.setForeground(Color.white);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		optionMenu.setMnemonic(KeyEvent.VK_O);
		helpMenu.setMnemonic(KeyEvent.VK_H);

		menuBar.add(fileMenu);
		menuBar.add(optionMenu);
		menuBar.add(helpMenu);
		menuBar.setBackground(Color.black);

		closeMenuItem = new JMenuItem("Close");
		closeMenuItem.addActionListener(this);
		closeMenuItem.setMnemonic(KeyEvent.VK_C);
		fileMenu.add(closeMenuItem);

		backgroundColorMenuItem = new JMenuItem("Set Background Color");
		backgroundColorMenuItem.addActionListener(this);
		backgroundColorMenuItem.setMnemonic(KeyEvent.VK_B);
		optionMenu.add(backgroundColorMenuItem);

		aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(this);
		aboutMenuItem.setMnemonic(KeyEvent.VK_A);
		helpMenu.add(aboutMenuItem);

		this.add(panelMenu, BorderLayout.NORTH);
		this.add(panelButton, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == closeMenuItem) {
			System.exit(0);
		}
		if (e.getSource() == backgroundColorMenuItem) {
			new JColorChooser();
			Color color = JColorChooser.showDialog(null, "Set Background Color", Color.black);
			panelButton.setBackground(color);
		}
		if (e.getSource() == aboutMenuItem) {
			new About();
		}
		if (e.getSource() == buttonWritter) {
			writter = new Writter();
		}
	}

}
