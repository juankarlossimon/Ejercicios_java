
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.panamahitek.PanamaHitek_Arduino;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LedArduino extends JFrame {
	private JPanel contentPane;
	private JButton btnOn;
	private JButton btnOff;
	private static JPanel panel;
	private static JLabel lblEstado;

	static PanamaHitek_Arduino ardu = new PanamaHitek_Arduino(); // Creamos Objeto ardu

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
				System.out.println("error " + ex.getMessage().toString());
			}

		}
	};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LedArduino frame = new LedArduino();
					frame.setTitle("Arduiono");
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	
	/**
	 * Create the frame.
	 */
	public LedArduino() {
		
		
		//ardu..ArduinoRXTX("COM5", 2000, 9600, evento);
		try {
		ardu.arduinoRXTX("COM3", 9600, evento);
		} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("ENCENDER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ardu.sendByte(10);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} // envia un Byte desde 0 a 255
			}
		});
		btnNewButton.setBounds(84, 78, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("APAGAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ardu.sendByte(78);
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

					}
			}
		});
		btnNewButton_1.setBounds(84, 150, 89, 23);
		contentPane.add(btnNewButton_1);

		JTextField txtRespuestaDeArduino = new JTextField();
		txtRespuestaDeArduino.setBackground(SystemColor.menu);
		txtRespuestaDeArduino.setText("Respuesta de Arduino:");
		txtRespuestaDeArduino.setBounds(10, 213, 177, 20);
		contentPane.add(txtRespuestaDeArduino);
		txtRespuestaDeArduino.setColumns(10);
	}
}
