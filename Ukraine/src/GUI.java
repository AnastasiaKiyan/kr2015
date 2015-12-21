import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javazoom.jl.player.advanced.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import javax.swing.JFrame;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;

public class GUI {

	private JFrame frame;
	JList list;
	City panel;
	ArrayList<String> like;
	JScrollPane scroll;
	JSplitPane splitPane;
	String choose;
	Start s;
	Music m;
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
		like = new ArrayList<String>();
		choose = "thf";
		initialize();
		
		
	}

	private void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		 m=new Music();
		frame = new JFrame();
		frame.setBounds(100, 100, 781, 536);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		 s = new Start("Путешествие по Украине");
		frame.getContentPane().add(s);

		list = new JList();
		Towns city = new Towns();
		list.setModel(city);
		//list.setBackground(Color.BLUE);
		scroll = new JScrollPane(list);
		list.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		JPanel forMusic = new JPanel();
		ImageIcon ii1 = new ImageIcon("stop.png");
		JLabel stop = new JLabel();
		stop.setIcon(new ImageIcon(ii1.getImage().getScaledInstance(50, 30, ii1.getImage().SCALE_DEFAULT)));
		stop.setSize(50, 30);
		forMusic.add(stop);
		ImageIcon ii = new ImageIcon("play.png");
		JLabel play = new JLabel();
		// play.setBackground(Color.WHITE);
		play.setIcon(new ImageIcon(ii.getImage().getScaledInstance(50, 30, ii.getImage().SCALE_DEFAULT)));
		play.setSize(50, 30);
		forMusic.add(play);
play.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				m.start();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	
		});

		ImageIcon ii2 = new ImageIcon("pause.png");
		JLabel pause = new JLabel();
		pause.setIcon(new ImageIcon(ii2.getImage().getScaledInstance(50, 30, ii2.getImage().SCALE_DEFAULT)));
		pause.setSize(50, 30);
		forMusic.add(pause);
		JButton find = new JButton("Найти");
		JButton likes = new JButton("Избранное");
		find.setMinimumSize(new Dimension(60, 25));
		find.setMaximumSize(new Dimension(100, 50));
		likes.setMinimumSize(new Dimension(60, 25));
		likes.setMaximumSize(new Dimension(100, 50));
		panel1.add(find);
		panel1.add(likes);
		panel1.add(forMusic);
		//panel1.setBackground(Color.YELLOW);
		panel = new City();
		JPanel forBut=new JPanel();
		forBut.setLayout(new GridLayout(2,1));
		forBut.add(scroll);
		forBut.add(panel1);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, forBut, panel);
		splitPane.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		frame.getContentPane().add(splitPane);
		pause.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				m.stop();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		stop.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				m.stop();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
						panel = new City(list.getSelectedValue().toString(), like);
						splitPane.setRightComponent(panel);
						frame.validate();
						s = new Start(list.getSelectedValue().toString());
						frame.validate();

					} else {

					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
	}
}
