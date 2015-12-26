import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javazoom.jl.player.advanced.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

public class GUI {

	JFrame frame;
	private JList list, music;
	private City panel;
	private JScrollPane scroll;
	private JSplitPane splitPane;
	private String choose;
	private Start s;
	private PlayMusic m;
	public Towns city, city1;
	private JList jlist;
	private ListOfMusic mm;
	private int lang;
	public static AdvancedPlayer explay;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() throws IOException, UnsupportedAudioFileException, LineUnavailableException, JavaLayerException {
		choose = "thf";
		m = new PlayMusic();

		Object[] options = { "English", "�������", };
		lang = JOptionPane.showOptionDialog(null, "�������� ����", "A Silly Question", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		initialize();
		if (lang == 1)
			city1 = new Towns(2);
		else

			city1 = new Towns(4);

	}

	public GUI(int num) throws IOException {
		choose = "thf";
		m = new PlayMusic();
		if (lang == 1)
			city1 = new Towns(2);
		else

			city1 = new Towns(4);
	}

	public Towns getCity1() {
		return city1;
	}

	public void setCity1(Towns t) {
		city1 = t;

	}

	private void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		if (lang == 1)
			s = new Start("������� ����");
		else
			s = new Start("Capitals of the world");
		s.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(s);
		music = new JList();
		mm = new ListOfMusic(lang);
		music.setModel(mm);
		JScrollPane scroll1 = new JScrollPane(music);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		list = new JList();
		if (lang == 1)
			city = new Towns(1);
		else
			city = new Towns(3);
		list.setModel(city);
		// list.setBackground(Color.BLUE);
		scroll = new JScrollPane(list);
		list.setFont(new Font("Segoe Script", Font.PLAIN, 18));
		music.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		JButton find;
		JButton likes;
		if (lang == 1) {
			find = new JButton("�����");
		} else {
			find = new JButton("Find");
		}
		if (lang == 1) {
			likes = new JButton("���������");
		} else {
			likes = new JButton("My favorites");
		}

		find.setMinimumSize(new Dimension(60, 25));
		find.setMaximumSize(new Dimension(100, 50));
		likes.setMinimumSize(new Dimension(60, 25));
		likes.setMaximumSize(new Dimension(100, 50));
		panel1.add(find);
		panel1.add(likes);

		panel1.add(scroll1);
		// panel1.setBackground(Color.YELLOW);
		panel = new City();
		JPanel forBut = new JPanel();
		forBut.setLayout(new GridLayout(2, 1));
		forBut.add(scroll);
		forBut.add(panel1);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, forBut, panel);
		splitPane.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		frame.getContentPane().add(splitPane);

		find.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name;
				if (lang == 1)

					name = JOptionPane.showInputDialog(frame, "������� �������� ������:");
				else
					name = JOptionPane.showInputDialog(frame, "Write name of the city:");
				if (city.findTown(name)) {

					try {
						name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
						panel = new City(name, lang);
						splitPane.setRightComponent(panel);
						frame.validate();
						s = new Start(name);
						frame.validate();
					} catch (IOException | UnsupportedAudioFileException | LineUnavailableException
							| JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					if (lang == 1)
						JOptionPane.showMessageDialog(null, "����� �����������");
					
					else
						JOptionPane.showMessageDialog(null, "This city wasn't found");
				}

			}

		});
		likes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					forDialog();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try {
					if (!choose.equals(list.getSelectedValue().toString())) {
						panel.removeAll();
						 
						s.removeAll();
						choose = list.getSelectedValue().toString();
						System.out.println(list.getSelectedValue().toString());
						panel = new City(list.getSelectedValue().toString(), lang);
						splitPane.setRightComponent(panel);
						frame.validate();
						s = new Start(list.getSelectedValue().toString());
						
					} else {

					}
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException
						| JavaLayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		music.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!choose.equals(music.getSelectedValue().toString())) {
					choose = music.getSelectedValue().toString();
					int num = music.getSelectedIndex();
					if (m.isPlay()) {
						System.out.println("proverka");
						m.stop();
					}
					m = new PlayMusic(Integer.toString(num + 1));
					m.start();
					System.out.println(Integer.toString(num + 1));
				} else {

				}
			}

		});
	}

	void forDialog() throws IOException {
		JDialog dialog = new JDialog();
		dialog.setBounds(150, 150, 200, 300);
		forDia p = new forDia();
		dialog.getContentPane().add(p);
		jlist = new JList(city1);
		jlist.setOpaque(false);
		p.add(jlist);
		dialog.setVisible(true);
		jlist.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try {
					dialog.setVisible(false);
					if (!choose.equals(jlist.getSelectedValue().toString())) {
						panel.removeAll();
						choose = jlist.getSelectedValue().toString();
						System.out.println(jlist.getSelectedValue().toString());
						panel = new City(jlist.getSelectedValue().toString(), lang);
						splitPane.setRightComponent(panel);
						frame.validate();
						s = new Start(jlist.getSelectedValue().toString());
						frame.getContentPane().validate();

					} else {

					}
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException
						| JavaLayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
	}
}

class forDia extends JPanel {
	public void paintComponent(Graphics g) {
		Image im = null;
		try {
			im = ImageIO.read(new File("fon.jpg"));
		} catch (IOException e) {
		}
		g.drawImage(im, 0, 0, getWidth(), getHeight(), null);
	}
}
