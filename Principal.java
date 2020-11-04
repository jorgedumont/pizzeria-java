package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.INTERNAL;

import modelo.Orden;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {
	// private NuevaOrden nuevaOrden;
	// private VerOrdenes verOrdenes;
	private JPanel contentPane;
	private Vector<Orden> ordenes;
	private static final String ruta = "ordenes.txt"; //aqui declaras la ruta del fichero para cargar y guardar los datos

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * COnstructor de la ventana principal.
	 */
	public Principal() {
		super("Ventana Principal");

		this.ordenes = new Vector<>();
		File f = new File(ruta);
		if (f.exists())//si existe el fichero cargas los datos de las ordenes
			cargarDatos();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//para que cuando le des a la x se cierre la ventana
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Nueva Orden");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.this.setVisible(false); //la ventana principal deja de ser visible
				NuevaOrden nuevaOrden = new NuevaOrden(Principal.this); //creo una ventana nueva para la nueva orden
				nuevaOrden.setVisible(true); //pomgo esa nueva ventana como visible
			}
		});
		btnNewButton.setBounds(135, 28, 125, 64);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Ver Ordenes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VerOrdenes verOrdenes = new VerOrdenes(Principal.this);
				verOrdenes.setVisible(true);

			}
		});
		btnNewButton_1.setBounds(135, 122, 125, 64);
		contentPane.add(btnNewButton_1);

		JButton btnGuardarOrdenes = new JButton("Guardar Ordenes");
		btnGuardarOrdenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarDatos();
			}
		});
		btnGuardarOrdenes.setBounds(277, 216, 115, 23);
		contentPane.add(btnGuardarOrdenes);
	}

	/**
	 * Carga los datos del fichero con el nombre del atributo ruta
	 */
	private void cargarDatos() {
		BufferedReader br = null;

		try {	//las operaciones sobre ficheros pueden dar excepciones, por eso se menten en un try-catch
			//Abrimos un flujo para lectura del fichero
			br = new BufferedReader(new FileReader(ruta));
			String linea;
			do {
				linea = br.readLine(); //leo una línea del fichero
				if (linea != null) { //si la he leído (no he llegado al final)
					String[] datos = linea.split(";"); //parto la línea en parte con el separador ';'
					Date fecha;
					String[] datosFecha = datos[3].split("/");
					int dia, mes, año;
					dia = Integer.parseInt(datosFecha[0]);
					mes = Integer.parseInt(datosFecha[1]);
					año = Integer.parseInt(datosFecha[2]);
					fecha = new Date(año, mes, dia);
					boolean entregado = Boolean.parseBoolean(datos[4]);
					double precio = Double.parseDouble(datos[5]);
					//Creo una nueva orden con los datos obtenidos de la línea del fichero
					Orden orden = new Orden(datos[0], datos[1], datos[2], fecha, entregado, precio);
					//Añado la nueva orden cargada desde el fichero al Vector de órdenes
					this.ordenes.addElement(orden);
				}
			}while (linea != null);
		} catch (EOFException ex) { //excepción que salta cuando llego a fin de fichero
			ex.printStackTrace();
		} catch (FileNotFoundException e) { //Excepción que salta si el fichero no existe
			//Compruebo en el constructor la existencia del fichero, luego no podrá darse esta excepción
			//aunque java obliga a controlarla
			e.printStackTrace();
		} catch (IOException e) { //excepción si se produce algún error en la lectura del fichero
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//Metodo para añadir una nueva orden
	public void addOrden(Orden orden) {
		this.ordenes.addElement(orden);
	}
	/**
	 * Getter del vector de ordenes
	 * @return el vector de ordenes
	 */
	public Vector<Orden> getOrdenes() {
		return ordenes;
	}
//Metodo para guardar los datos de las ordenes
	private void guardarDatos() {
		BufferedWriter bf;
		try {
			bf = new BufferedWriter(new FileWriter(new File(ruta)));
//bucle para ir escribiendo las ordenes en el fichero
			for (int i = 0; i < this.ordenes.size(); i++) {
				Orden orden = this.ordenes.get(i);
				bf.write(orden.toStringFichero() + "\n");
				//llamamos a la funcion toStringFichero
				//para que nos escriba el fichero con las 
				//cosas que queremos que aparezcan.
			}

			bf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
