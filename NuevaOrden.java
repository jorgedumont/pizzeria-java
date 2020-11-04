package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Orden;
import modelo.Pizza;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class NuevaOrden extends JFrame {

	private JPanel contentPane;
	private Principal principal;
	private final JLabel lblNewLabel_1 = new JLabel("Direccion");
	private JTextField tfNombre;
	private JTextField tfDireccion;
	private JTextField tfTelefono;
	private DefaultListModel<Pizza> pizzas;
	private JList<Pizza> listPizzas;
	private JTextField tfPrecio;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @param principal 
	 */
	public NuevaOrden(Principal principal) {
		super ("Nueva Orden");
		this.pizzas= new DefaultListModel ();
		this.principal = principal;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFinOrden = new JButton("Fin Orden");
		btnFinOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//comprobar datos, si falta alguno mostrar error
				if (tfDireccion.getText().isEmpty() ||
					tfNombre.getText().isEmpty() ||
					tfTelefono.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Error. Introduzca su nombre, dirección y teléfono", "Faltan datos", JOptionPane.ERROR_MESSAGE);
				}else if (pizzas.isEmpty()){
					JOptionPane.showMessageDialog(null, "Error. La orden no tiene pizzas", "Orden vacía", JOptionPane.ERROR_MESSAGE);
				}else{
					
					Orden orden = new Orden (tfNombre.getText(),
											tfDireccion.getText(),
											tfTelefono.getText(),
											new Date(),
											false,
											Double.parseDouble(tfPrecio.getText().replaceAll(",", ".")));
					principal.addOrden(orden);
					NuevaOrden.this.setVisible(false);
					NuevaOrden.this.principal.setVisible(true);
				}
			}
		});
		btnFinOrden.setBounds(51, 227, 89, 23);
		contentPane.add(btnFinOrden);
		
		JButton btnCancelarOrden = new JButton("Cancelar Orden");
		btnCancelarOrden.setBounds(246, 227, 89, 23);
		btnCancelarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaOrden.this.setVisible(false);
				NuevaOrden.this.principal.setVisible(true);
			}
		});
		contentPane.add(btnCancelarOrden);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(51, 11, 46, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel_1.setBounds(51, 41, 98, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Telefono");
		lblNewLabel_2.setBounds(51, 83, 73, 14);
		contentPane.add(lblNewLabel_2);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(149, 8, 86, 20);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(149, 46, 86, 20);
		contentPane.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setBounds(149, 80, 86, 20);
		contentPane.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		JButton btnAadirPizza = new JButton("A\u00F1adir Pizza");
		btnAadirPizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaOrden.this.setVisible(false);
				NuevaPizza n = new NuevaPizza(NuevaOrden.this);
				n.setVisible(true);
			}
		});
		btnAadirPizza.setBounds(287, 45, 123, 23);
		contentPane.add(btnAadirPizza);
		
		this.listPizzas = new JList();
		listPizzas.setBounds(51, 126, 211, 90);
		contentPane.add(listPizzas);
		
		JButton btnBorrarPizza = new JButton("Borrar Pizza");
		btnBorrarPizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listPizzas.getSelectedIndex()==-1){
					JOptionPane.showMessageDialog(null, "Error. Elige una pizza a borrar.", "Error borrando", JOptionPane.ERROR_MESSAGE);
				}
				else{
					pizzas.remove(listPizzas.getSelectedIndex());
					listPizzas.setModel(pizzas);
					actualizarOrden();
				}
			}
		});
		btnBorrarPizza.setBounds(287, 126, 89, 23);
		contentPane.add(btnBorrarPizza);
		
		JLabel lblListaPizzas = new JLabel("Lista Pizzas:");
		lblListaPizzas.setBounds(51, 108, 98, 14);
		contentPane.add(lblListaPizzas);
		
		JLabel lblPrecioTotal = new JLabel("Precio Total");
		lblPrecioTotal.setBounds(272, 174, 80, 14);
		contentPane.add(lblPrecioTotal);
		
		tfPrecio = new JTextField();
		tfPrecio.setEditable(false);
		tfPrecio.setBounds(350, 171, 46, 20);
		contentPane.add(tfPrecio);
		tfPrecio.setColumns(10);
		
		JLabel label = new JLabel(" \u20AC");
		label.setBounds(410, 174, 24, 14);
		contentPane.add(label);
	}
	public void aniadirPizza(Pizza pizza){
		this.pizzas.addElement(pizza);
		this.listPizzas.setModel(pizzas);
		this.actualizarOrden();
	}
	private void actualizarOrden() {
		final NumberFormat formatter = new DecimalFormat("#0.00");
		
		double precioTotal = 0;
		
		for (int i=0; i < pizzas.size(); i++){
			precioTotal = precioTotal + pizzas.get(i).getPrecio();
		}
		
		tfPrecio.setText(formatter.format(precioTotal));
		
	}
}
