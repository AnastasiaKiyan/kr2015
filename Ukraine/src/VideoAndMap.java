import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class VideoAndMap {
	static JFrame frame;

	VideoAndMap(String name) {
		NativeInterface.open();
		frame = new JFrame();
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				NativeInterface.close();
				frame.dispose();

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		System.out.println("1");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JWebBrowser b = new JWebBrowser();
		System.out.println("1/1");
		// NativeInterface.runEventPump();
		System.out.println("2");
		panel.add(b, BorderLayout.CENTER);
		b.setBarsVisible(false);
		b.navigate(name);
		System.out.println("3");
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setSize(800, 600);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		System.out.println("4");

	}

}