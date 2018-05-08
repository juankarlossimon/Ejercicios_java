import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.panamahitek.PanamaHitek_Arduino;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Brazo_Arduino extends JFrame {

	private JButton btnOn;
	private JButton btnOff;
	private static JPanel panel;
	private static JLabel mensaje;
	private JPanel contentPane;

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
						mensaje.setText("ARDUINO DICE: *** led encendido");
					}

					if (mensage.equals("apagado")) {
						panel.setBackground(new Color(255, 0, 0));
						mensaje.setText("ARDUINO DICE: *** led apagado");
						
					}
				}

			} catch (Exception ex) {
				System.out.println("error " + ex.getMessage().toString());
			}

		}
	};
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Brazo_Arduino frame = new Brazo_Arduino();
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
	public Brazo_Arduino() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\juank\\Pictures\\icono.ico"));
		setTitle("Control de Brazo UMG");
		
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
		
		JButton btnNewButton = new JButton("<<");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//ardu.sendByte(2);
					ardu.sendData("b");
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} // envia un Byte desde 0 a 255
			}
		});
		btnNewButton.setBounds(75, 66, 79, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(">>");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//ardu.sendByte(1);
					ardu.sendData("a");
				} catch (Exception ex) {					
					ex.printStackTrace();
				} // envia un Byte desde 0 a 255
			}
		});
		btnNewButton_1.setBounds(227, 66, 79, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("arriba");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//ardu.sendByte(3);
					ardu.sendData("c");
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} // envia un Byte desde 0 a 255
			}
		});
		btnNewButton_2.setBounds(153, 23, 89, 32);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("abajo");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//ardu.sendByte(4);
					ardu.sendData("d");
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} // envia un Byte desde 0 a 255
			}
		});
		btnNewButton_3.setBounds(153, 116, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnDibujar = new JButton("dibujar ");
		btnDibujar.setBounds(43, 175, 89, 23);
		contentPane.add(btnDibujar);
		
		JButton btnReiniciarPocicion = new JButton("Reiniciar Posici\u00F3n");
		btnReiniciarPocicion.setBounds(297, 175, 113, 23);
		contentPane.add(btnReiniciarPocicion);
		
		JLabel lblNewLabel = new JLabel("Respuesta de Arduino");
		lblNewLabel.setBounds(29, 225, 155, 14);
		contentPane.add(lblNewLabel);
		
		JLabel mensaje = new JLabel("");
		mensaje.setBounds(194, 225, 128, 14);
		contentPane.add(mensaje);
	}
}
