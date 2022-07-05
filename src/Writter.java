
//-------------------Imports----------------------//
//java.awt
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
//java.io
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
//java.text
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
//java.util
import java.util.Calendar;
import java.util.Scanner;
//javax.imageio
import javax.imageio.ImageIO;
//javax.swing
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
//-----------------End of Imports-----------------//

public class Writter extends JFrame implements ActionListener {
	private File openFile = null;
	private JLabel filePathLabel;

	private JMenuItem openFileItem;
	private JMenuItem newWindowItem;
	private JMenuItem saveFileItem;
	private JMenuItem saveAsFileItem;
	private JMenuItem closeFileItem;
	private JMenuItem foregroundColorItem;
	private JMenuItem backgroundColorItem;
	private JMenuItem caretColorItem;
	private JMenuItem runScriptItem;
	private JMenuItem guideItem;

	private JScrollPane scrollPane;
	private JTextArea textArea;

	private JSpinner fontSizeSpinner;

	private Color colorFont;
	private JComboBox fontComboBox;
	private JCheckBox boldCheckBox;
	private JCheckBox italicCheckBox;
	private JCheckBox wordWrapCheckBox;

	// ------------------------Constructor----------------------------//
	Writter() {
		// Frame
		// Frame Icon
		ImageIcon notepadIcon = new ImageIcon("notepadIcon.png");
		this.setIconImage(notepadIcon.getImage());

		// Frame
		this.setTitle("Advanced Writter");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1100, 700));
		this.setResizable(true);

		// File Path Label
		filePathLabel = new JLabel("Current File Path: " + "Null");
		filePathLabel.setForeground(Color.white);

		// menuBar & panelMenu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.black);
		menuBar.setBorderPainted(false);
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new BorderLayout());
		panelMenu.setBackground(Color.black);
		panelMenu.add(menuBar, BorderLayout.WEST);
		panelMenu.add(filePathLabel, BorderLayout.EAST);

		JPanel panelFont = new JPanel();
		panelFont.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelFont.setForeground(Color.white);
		panelFont.setBackground(Color.black);

		// JMenu
		JMenu fileMenu = new JMenu("File");
		JMenu optionMenu = new JMenu("Color");
		JMenu runMenu = new JMenu("Run");
		JMenu helpMenu = new JMenu("Help");
		fileMenu.setForeground(Color.white);
		optionMenu.setForeground(Color.white);
		runMenu.setForeground(Color.white);
		helpMenu.setForeground(Color.white);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		optionMenu.setMnemonic(KeyEvent.VK_C);
		runMenu.setMnemonic(KeyEvent.VK_R);
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(fileMenu);
		menuBar.add(optionMenu);
		menuBar.add(runMenu);
		menuBar.add(helpMenu);

		// JMenuItem
		openFileItem = new JMenuItem("Open");
		newWindowItem = new JMenuItem("New Window");
		saveFileItem = new JMenuItem("Save");
		saveAsFileItem = new JMenuItem("Save As");
		closeFileItem = new JMenuItem("Close");
		foregroundColorItem = new JMenuItem("Set Font Color");
		backgroundColorItem = new JMenuItem("Set Background Color");
		caretColorItem = new JMenuItem("Set Caret Color");
		runScriptItem = new JMenuItem("Run Command");
		guideItem = new JMenuItem("Guide");
		openFileItem.setMnemonic(KeyEvent.VK_O);
		newWindowItem.setMnemonic(KeyEvent.VK_N);
		saveFileItem.setMnemonic(KeyEvent.VK_S);
		saveAsFileItem.setMnemonic(KeyEvent.VK_A);
		closeFileItem.setMnemonic(KeyEvent.VK_C);
		foregroundColorItem.setMnemonic(KeyEvent.VK_F);
		backgroundColorItem.setMnemonic(KeyEvent.VK_B);
		caretColorItem.setMnemonic(KeyEvent.VK_C);
		runScriptItem.setMnemonic(KeyEvent.VK_R);
		guideItem.setMnemonic(KeyEvent.VK_G);
		openFileItem.addActionListener(this);
		newWindowItem.addActionListener(this);
		saveFileItem.addActionListener(this);
		saveAsFileItem.addActionListener(this);
		closeFileItem.addActionListener(this);
		foregroundColorItem.addActionListener(this);
		backgroundColorItem.addActionListener(this);
		caretColorItem.addActionListener(this);
		runScriptItem.addActionListener(this);
		guideItem.addActionListener(this);
		fileMenu.add(openFileItem);
		fileMenu.add(newWindowItem);
		fileMenu.add(saveFileItem);
		fileMenu.add(saveAsFileItem);
		fileMenu.add(closeFileItem);
		optionMenu.add(foregroundColorItem);
		optionMenu.add(backgroundColorItem);
		optionMenu.add(caretColorItem);
		runMenu.add(runScriptItem);
		helpMenu.add(guideItem);

		// JSpinner & fontSizeLabel
		fontSizeSpinner = new JSpinner();
		fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
		fontSizeSpinner.setMaximumSize(new Dimension(50, 25));
		fontSizeSpinner.setValue(20);
		fontSizeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				textArea.setFont(
						new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));
			}
		});
		JLabel fontSizeLabel = new JLabel("Font Size:");
		fontSizeLabel.setForeground(Color.white);
		panelMenu.add(panelFont, BorderLayout.CENTER);
		panelFont.add(fontSizeLabel);
		panelFont.add(fontSizeSpinner);

		// textArea
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Arial", Font.PLAIN, 20));
		textArea.setCaretColor(Color.black);

		// FontComboBox
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontComboBox = new JComboBox(fonts);
		fontComboBox.setEditable(true);
		fontComboBox.addActionListener(this);
		fontComboBox.setSelectedItem("Arial");
		panelFont.add(fontComboBox);

		// scrollPane
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400, 550));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// boldCheckBox
		boldCheckBox = new JCheckBox();
		boldCheckBox.setBackground(Color.black);
		JLabel labelBold = new JLabel("Bold");
		labelBold.setForeground(Color.white);
		boldCheckBox.addActionListener(this);
		panelFont.add(labelBold);
		panelFont.add(boldCheckBox);

		// italicCheckBox
		italicCheckBox = new JCheckBox();
		italicCheckBox.setBackground(Color.black);
		JLabel labelItalic = new JLabel("Italic");
		labelItalic.setForeground(Color.white);
		italicCheckBox.addActionListener(this);
		panelFont.add(labelItalic);
		panelFont.add(italicCheckBox);

		// wordWarpCheckBox & labelwrap
		wordWrapCheckBox = new JCheckBox();
		wordWrapCheckBox.setBackground(Color.black);
		wordWrapCheckBox.setSelected(true);
		JLabel labelwrap = new JLabel("Word Wrap");
		labelwrap.setForeground(Color.white);
		wordWrapCheckBox.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (wordWrapCheckBox.isSelected() == true) {
					textArea.setWrapStyleWord(true);
					textArea.setLineWrap(true);
				} else {
					textArea.setWrapStyleWord(false);
					textArea.setLineWrap(false);
				}
			}
		});
		panelFont.add(labelwrap);
		panelFont.add(wordWrapCheckBox);

		// frame
		this.add(panelMenu, BorderLayout.NORTH);
		this.add(scrollPane);
		this.pack();
		this.setVisible(true);

	}
	// ---------------------End of Constructor------------------------//

	// ------------------------ActionPerformed Method----------------------------//
	@Override
	public void actionPerformed(ActionEvent e) {
		// ActionPerformed for MenuItems
		if (e.getSource() == openFileItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileNameExtensionFilter("Text File (*.txt)", "txt"));
			fileChooser.setCurrentDirectory(new File("C:/Users/User/Desktop"));
			int response = fileChooser.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				textArea.setText("");
				openFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
				filePathLabel.setText("Opened File Path: " + fileChooser.getSelectedFile().getAbsolutePath());
				Scanner inputFile = null;
				try {
					inputFile = new Scanner(openFile);
					while (inputFile.hasNext()) {
						String text = inputFile.nextLine() + "\n";
						textArea.append(text);
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} finally {
					inputFile.close();
				}
			}
		}
		if (e.getSource() == saveFileItem) {
			if (openFile == null) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("C:/Users/User/Desktop"));
				int response = fileChooser.showSaveDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					filePathLabel.setText("Saved To File Path: " + fileChooser.getSelectedFile().getAbsolutePath());
					PrintWriter outputFile;
					try {
						outputFile = new PrintWriter(file);
						outputFile.println(textArea.getText());
						outputFile.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				PrintWriter outputFile;
				try {
					outputFile = new PrintWriter(openFile);
					filePathLabel.setText("Saved To File Path: " + openFile.getAbsolutePath());
					outputFile.println(textArea.getText());
					outputFile.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == newWindowItem) {
			new Writter();
		}
		if (e.getSource() == saveAsFileItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileNameExtensionFilter("Text File (*.txt)", "txt"));
			fileChooser.setCurrentDirectory(new File("C:/Users/User/Desktop"));
			int response = fileChooser.showSaveDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				openFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
				filePathLabel.setText("Saved To File Path: " + fileChooser.getSelectedFile().getAbsolutePath());
				PrintWriter outputFile;
				try {
					outputFile = new PrintWriter(openFile);
					outputFile.println(textArea.getText());
					outputFile.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == closeFileItem) {
			this.dispose();
		}
		if (e.getSource() == foregroundColorItem) {
			new JColorChooser();
			colorFont = JColorChooser.showDialog(null, "Set Font Color", Color.black);
			textArea.setForeground(colorFont);
		}
		if (e.getSource() == backgroundColorItem) {
			new JColorChooser();
			Color color = JColorChooser.showDialog(null, "Set Background Color", Color.black);
			textArea.setBackground(color);
		}
		if (e.getSource() == runScriptItem) {
			Scanner input = new Scanner(textArea.getText());
			String start = "start";
			String add = "add";
			String substraction = "sub";
			String multiplication = "mul";
			String division = "div";
			String screenshot = "ss";
			while (input.hasNext()) {
				if (input.hasNext(start)) {
					input.nextLine();
					String fileName = input.nextLine();
					Runtime runTime = Runtime.getRuntime();

					try {
						runTime.exec(fileName);
						filePathLabel.setText("Run successful(" + fileName + ")");
					} catch (IOException e1) {
						filePathLabel.setText("Run failed(" + fileName + "):File does not exist");
					} catch (IllegalArgumentException e1) {
						filePathLabel.setText("Run failed(" + fileName + ")Invalid path format");
					}
				} else if (input.hasNext(add)) {
					input.nextLine();
					DecimalFormat format = new DecimalFormat("0.#");
					double total = 0;
					while (input.hasNextDouble()) {
						double number = input.nextDouble();
						total += number;
					}

					textArea.append("\naddAns ");
					textArea.append(format.format(total));
					filePathLabel.setText("Addition successful");
					if (input.hasNextLine()) {
						input.nextLine();
					}

				} else if (input.hasNext(substraction)) {
					input.nextLine();
					DecimalFormat format = new DecimalFormat("0.#");
					double total = 0;

					if (input.hasNextDouble()) {
						total = input.nextDouble();
						while (input.hasNextDouble()) {
							double number = input.nextDouble();
							total -= number;
						}
					}
					textArea.append("\nsubAns ");
					textArea.append(format.format(total));
					filePathLabel.setText("Substraction successful");
					if (input.hasNextLine()) {
						input.nextLine();
					}
				} else if (input.hasNext(multiplication)) {
					input.nextLine();
					DecimalFormat format = new DecimalFormat("0.#");
					double total = 1;
					while (input.hasNextDouble()) {
						double number = input.nextDouble();
						total *= number;
					}

					textArea.append("\nmulAns ");
					textArea.append(format.format(total));
					filePathLabel.setText("Multiplication successful");
					if (input.hasNextLine()) {
						input.nextLine();
					}
				} else if (input.hasNext(division)) {
					input.nextLine();
					DecimalFormat format = new DecimalFormat("0.###");
					double total = 0;

					if (input.hasNextDouble()) {
						total = input.nextDouble();
						while (input.hasNextDouble()) {
							double number = input.nextDouble();
							total /= number;
						}
					}
					textArea.append("\ndivAns ");
					textArea.append(format.format(total));
					filePathLabel.setText("Division successful");

					if (input.hasNextLine()) {
						input.nextLine();
					}

				} else if (input.hasNext(screenshot)) {
					Calendar now = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");

					Robot robot;
					try {
						robot = new Robot();
						String path = "C:\\Users\\User\\Desktop\\";
						BufferedImage screenShot = robot
								.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
						ImageIO.write(screenShot, "JPG", new File(path + formatter.format(now.getTime()) + ".jpg"));
						filePathLabel.setText("Screenshot successful: " + path);

					} catch (AWTException | IOException e1) {
						filePathLabel.setText("File not found ");
					}
					break;
				}

				else {
					filePathLabel.setText("Invalid command at last line: (Format:start+EnterKey+path+text)");
					input.next();
				}
			}
		}
		if (e.getSource() == caretColorItem) {
			Color color = JColorChooser.showDialog(null, "Set Caret Color", Color.black);
			textArea.setCaretColor(color);
		}
		if (e.getSource() == guideItem) {
			new WritterGuide();
		}

		// ActionPerformed for fontComboBox & italicCheckBox & italicCheckBox
		if (e.getSource() == fontComboBox) {
			textArea.setFont(
					new Font((String) fontComboBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
		}
		if (e.getSource() == boldCheckBox) {
			if (boldCheckBox.isSelected() == true && italicCheckBox.isSelected() == false) {
				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.BOLD, textArea.getFont().getSize()));
			} else if (boldCheckBox.isSelected() == false && italicCheckBox.isSelected() == true) {
				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.ITALIC, textArea.getFont().getSize()));

			} else if (boldCheckBox.isSelected() == true && italicCheckBox.isSelected() == true) {
				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.BOLD | Font.ITALIC,
						textArea.getFont().getSize()));
			} else {
				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, textArea.getFont().getSize()));
			}
		}
		if (e.getSource() == italicCheckBox) {
			if (boldCheckBox.isSelected() == true && italicCheckBox.isSelected() == false) {
				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.BOLD, textArea.getFont().getSize()));
			} else if (boldCheckBox.isSelected() == false && italicCheckBox.isSelected() == true) {
				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.ITALIC, textArea.getFont().getSize()));

			} else if (boldCheckBox.isSelected() == true && italicCheckBox.isSelected() == true) {
				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.BOLD | Font.ITALIC,
						textArea.getFont().getSize()));
			} else {
				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, textArea.getFont().getSize()));
			}
		}
	}
	// ---------------------End Of ActionPerformed Method------------------------//
}
