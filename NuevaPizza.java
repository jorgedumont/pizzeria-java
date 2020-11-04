package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import modelo.Base;
import modelo.Ingrediente;
import modelo.Pizza;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;

public class NuevaPizza extends JFrame {

	private JPanel contentPane;
	private final JButton btnFinPizza = new JButton("Fin Pizza");
	private NuevaOrden nuevaOrden;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JComboBox<Pizza> comboPizza;
	private JComboBox<Base> comboBase;
	private JComboBox<Ingrediente> comboIngredientes;
	private DefaultComboBoxModel<Pizza> pizzas;
	private DefaultComboBoxModel<Ingrediente> opcionesIngredientes;
	private DefaultComboBoxModel<Base> bases;
	private JList<Ingrediente> listIngredientes;
	private DefaultListModel<Ingrediente> pizzaActualIngredientes;
	private JLabel lblNewLabel_2;
	private JTextField textPrecio;
	private Pizza pizzaActual;
	private JButton btnElegirPizza;
	private JButton btnElegirBase;
	private JButton btnPonIngrediente;
	private JLabel lblAptaCeliacos;
	/**
	 * Create the frame.
	 */
	public NuevaPizza(NuevaOrden nuevaOrden) {
		super("Nueva Pizza");
		this.pizzas = new DefaultComboBoxModel<Pizza> ();
		this.opcionesIngredientes= new DefaultComboBoxModel<Ingrediente> ();
		this.bases = new DefaultComboBoxModel<Base> ();
		
		this.nuevaOrden = nuevaOrden;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnFinPizza.setEnabled(false);
		btnFinPizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaPizza.this.setVisible(false);
				nuevaOrden.setVisible(true);
				nuevaOrden.aniadirPizza(NuevaPizza.this.pizzaActual);
			}
		});
		btnFinPizza.setBounds(41, 222, 89, 29);
		contentPane.add(btnFinPizza);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaPizza.this.setVisible(false);
				nuevaOrden.setVisible(true);
			}
		});
		btnNewButton.setBounds(154, 223, 89, 27);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Nombre Pizza");
		lblNewLabel.setBounds(20, 11, 85, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Elegir Base");
		lblNewLabel_1.setBounds(20, 45, 66, 14);
		contentPane.add(lblNewLabel_1);
		
		this.comboPizza = new JComboBox<Pizza>();
		comboPizza.setBounds(115, 8, 216, 20);
		contentPane.add(comboPizza);
		
		this.comboBase = new JComboBox<Base>();
		comboBase.setEnabled(false);
		comboBase.setBounds(115, 42, 216, 20);
		contentPane.add(comboBase);
		
		this.comboIngredientes = new JComboBox<Ingrediente>();
		comboIngredientes.setEnabled(false);
		comboIngredientes.setBounds(115, 73, 189, 20);
		contentPane.add(comboIngredientes);
		
		this.cargarDatosCombos();
		
		JLabel lblListaIngredientes = new JLabel("Elegir Ingredientes");
		lblListaIngredientes.setBounds(10, 76, 107, 14);
		contentPane.add(lblListaIngredientes);
		
		lblAptaCeliacos = new JLabel("Apta Celiacos");
		lblAptaCeliacos.setBounds(274, 196, 136, 14);
		contentPane.add(lblAptaCeliacos);
		
		JLabel lblListaIngredientes_1 = new JLabel("Lista Ingredientes");
		lblListaIngredientes_1.setBounds(41, 104, 107, 14);
		contentPane.add(lblListaIngredientes_1);
		
		listIngredientes = new JList<Ingrediente>();
		listIngredientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listIngredientes.setBounds(41, 121, 220, 90);
		contentPane.add(listIngredientes);
		
		
		btnPonIngrediente = new JButton("Pon Ingrediente");
		btnPonIngrediente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaPizza.this.pizzaActualIngredientes.addElement((Ingrediente) NuevaPizza.this.comboIngredientes.getSelectedItem());
				NuevaPizza.this.listIngredientes.setModel(NuevaPizza.this.pizzaActualIngredientes);
				NuevaPizza.this.pizzaActual.ponerIngrediente((Ingrediente) NuevaPizza.this.comboIngredientes.getSelectedItem());
				NuevaPizza.this.actualizarPizza();
			}
		});
		btnPonIngrediente.setEnabled(false);
		btnPonIngrediente.setBounds(315, 72, 115, 23);
		contentPane.add(btnPonIngrediente);
		
		lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setBounds(274, 171, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setBounds(324, 165, 86, 20);
		contentPane.add(textPrecio);
		textPrecio.setColumns(10);
		
		btnElegirPizza = new JButton("Elegir");
		btnElegirPizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaPizza.this.comboBase.setEnabled(true);
				NuevaPizza.this.comboPizza.setEnabled(false);
				//Creamos la pizza actual copiando la que selecciona del primer combox (puede ser vacía con "Elige tu pizza")
				NuevaPizza.this.pizzaActual = new Pizza((Pizza) NuevaPizza.this.comboPizza.getSelectedItem());
				NuevaPizza.this.cargarIngredientes();
				NuevaPizza.this.btnElegirPizza.setEnabled(false);
				NuevaPizza.this.btnElegirBase.setEnabled(true);
			}
		});
		btnElegirPizza.setBounds(335, 7, 89, 23);
		contentPane.add(btnElegirPizza);
		
		btnElegirBase = new JButton("Elegir");
		btnElegirBase.setEnabled(false);
		btnElegirBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaPizza.this.comboBase.setEnabled(false);
				NuevaPizza.this.comboIngredientes.setEnabled(true);
				NuevaPizza.this.btnElegirBase.setEnabled(false);
				NuevaPizza.this.btnPonIngrediente.setEnabled(true);
				NuevaPizza.this.btnFinPizza.setEnabled(true);
				NuevaPizza.this.pizzaActual.setBase((Base) NuevaPizza.this.comboBase.getSelectedItem());
				NuevaPizza.this.actualizarPizza();
			}
		});
		btnElegirBase.setBounds(341, 41, 89, 23);
		contentPane.add(btnElegirBase);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listIngredientes.getSelectedIndex()==-1){
					JOptionPane.showMessageDialog(null, "Error. Elige un ingrediente a borrar.", "Error borrando", JOptionPane.ERROR_MESSAGE);
				}
				else{
					Ingrediente i = listIngredientes.getSelectedValue();
					pizzaActual.quitarIngrediente(i);
					actualizarPizza();
					pizzaActualIngredientes.removeElement(i);
					listIngredientes.setModel(pizzaActualIngredientes);
				}
			}
		});
		btnQuitar.setBounds(271, 131, 89, 23);
		contentPane.add(btnQuitar);
	}
	
	protected void actualizarPizza() {
		final NumberFormat formatter = new DecimalFormat("#0.00"); //para escribir el precio con 2 decimales
		if (this.pizzaActual != null){
			this.textPrecio.setText("" + formatter.format(this.pizzaActual.getPrecio()) + "€");
			if (this.pizzaActual.aptaCeliacos())
				this.lblAptaCeliacos.setText("Apta para celiacos");
			else
				this.lblAptaCeliacos.setText("No apta para celiacos");
		}
	}

	protected void cargarIngredientes() {
		this.pizzaActualIngredientes = new DefaultListModel<>();
		for (int i=0; i < this.pizzaActual.getIngredientes().size(); i++){
			this.pizzaActualIngredientes.addElement(this.pizzaActual.getIngredientes().get(i));
		}
		this.listIngredientes.setModel(pizzaActualIngredientes);
	}
	
	private void cargarDatosCombos (){
		Ingrediente i1 = new Ingrediente ("Queso", true,0.95);
		this.opcionesIngredientes.addElement(i1);
		Ingrediente i2 = new Ingrediente ("Jamon", false,0.55);
		this.opcionesIngredientes.addElement(i2);
		Ingrediente i3 = new Ingrediente ("Tomate", true,0.65);
		this.opcionesIngredientes.addElement(i3);
		Ingrediente i4 = new Ingrediente ("Bacon", false,0.45);
		this.opcionesIngredientes.addElement(i4);
		Ingrediente i5 = new Ingrediente ("Peperoni", true,0.35);
		this.opcionesIngredientes.addElement(i5);
		
		Base b1 = new Base ("Fina",true,4.50, "Grande");
		this.bases.addElement(b1);
		Base b2 = new Base ("Normal",false,6.50, "Pequeña");
		this.bases.addElement(b2);
		Base b3 = new Base ("Gruesa",true,5.50, "Mediana");
		this.bases.addElement(b3);
		Base b4 = new Base ("Fina",false,7.50, "Grande");
		this.bases.addElement(b4);
		Base b5 = new Base ("Normal",false,5, "Mediana");
		this.bases.addElement(b5);
		
		Pizza p1 = new Pizza("Queso");
		p1.ponerIngrediente(i1);
		p1.ponerIngrediente(i4);
		this.pizzas.addElement(p1);
		Pizza p2 = new Pizza("Barbacoa");
		p2.ponerIngrediente(i4);
		p2.ponerIngrediente(i2);
		this.pizzas.addElement(p2);
		Pizza p3 = new Pizza("Carbonara");
		p3.ponerIngrediente(i5);
		this.pizzas.addElement(p3);
		Pizza p4 = new Pizza("Tomate");
		p4.ponerIngrediente(i3);
		this.pizzas.addElement(p4);
		Pizza p5 = new Pizza("Jamon");
		p5.ponerIngrediente(i2);
		this.pizzas.addElement(p5);
		Pizza p6 = new Pizza("Elige tu pizza");
		p6.ponerIngrediente(i1);
		this.pizzas.addElement(p6);
		
		this.comboBase.setModel(this.bases);
		this.comboIngredientes.setModel(this.opcionesIngredientes);
		this.comboPizza.setModel(this.pizzas);
	}
}
