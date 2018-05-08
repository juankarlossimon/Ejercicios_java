import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.panamahitek.PanamaHitek_Arduino;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

public class Eventos extends JFrame {
//	private JPanel contentPane;
	private JButton btnOn;
	private JButton btnOff;
	private static JPanel panel;
	private static JLabel lblEstado;

	static PanamaHitek_Arduino ardu = new PanamaHitek_Arduino();

	static SerialPortEventListener evento = new SerialPortEventListener() {

		@Override
		public void serialEvent(SerialPortEvent ev) {
			try {
				if (ardu.isMessageAvailable() == true) {
					String mensage = ardu.printMessage();
					System.out.println(mensage);
					if (mensage.equals("encendido")) {
						panel.setBackground(new Color(0, 255, 0));
						lblEstado.setText("ARDUINO DICE: *** led encendido");
					}

					if (mensage.equals("apagado")) {
						panel.setBackground(new Color(255, 0, 0));
						lblEstado.setText("ARDUINO DICE: *** led apagado");
					}
				}
			} catch (Exception ex) {
					System.out.println("error al realizar conexion arduino" + ex.getMessage().toString());
			}
	
		}
	};
}
