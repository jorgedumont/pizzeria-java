package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Orden;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerOrdenes extends JFrame {

	private JPanel contentPane;
	private Principal p;

	private JList<Orden> list;
	private DefaultListModel<Orden> listModel;

	/**
	 * Cosntructor de VerOrdenes
	 * @param p Ventana principal
	 */
	public VerOrdenes(Principal p) {
		super("Lista Ordenes");
		this.p=p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list = new JList<Orden>();
		list.setBounds(29, 61, 383, 152);
		contentPane.add(list);	
		
		listModel = new DefaultListModel<>();
		for (int i=0; i<this.p.getOrdenes().size(); i++)
			listModel.addElement(this.p.getOrdenes().get(i));
		list.setModel(listModel);
		
		JLabel lblOrdenes = new JLabel("Ordenes:");
		lblOrdenes.setBounds(29, 24, 72, 14);
		contentPane.add(lblOrdenes);
		
		JButton btnBorrarOrden = new JButton("Borrar Orden");
		btnBorrarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex()==-1){
					JOptionPane.showMessageDialog(null, "Error. Elige una orden a borrar.", "Error borrando", JOptionPane.ERROR_MESSAGE);
				}
				else{
					p.getOrdenes().remove(list.getSelectedValue()); //elimino la orden de la lista de órdenes
					listModel.remove(list.getSelectedIndex());
					list.setModel(listModel);
				}
			}
		});
		btnBorrarOrden.setBounds(300, 227, 112, 23);
		contentPane.add(btnBorrarOrden);
		
		JButton btnRecibida = new JButton("Recibida");
		btnRecibida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex()==-1){
					JOptionPane.showMessageDialog(null, "Error. Elige una orden a marcar como entregada.", "Error entrega", JOptionPane.ERROR_MESSAGE);
				}
				else{
					Orden orden = list.getSelectedValue();
					orden.setEntregado(true);
					list.updateUI();
				}
			}
		});
		btnRecibida.setBounds(153, 227, 112, 23);
		contentPane.add(btnRecibida);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(39, 49, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(126, 49, 46, 14);
		contentPane.add(lblDireccin);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(219, 49, 46, 14);
		contentPane.add(lblTelfono);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(297, 49, 46, 14);
		contentPane.add(lblPrecio);
		
		JLabel lblEntregado = new JLabel("Entregado");
		lblEntregado.setBounds(340, 49, 72, 14);
		contentPane.add(lblEntregado);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerOrdenes.this.setVisible(false);
				VerOrdenes.this.p.setVisible(true);
				
			
			}
		});
		btnVolver.setBounds(39, 227, 89, 23);
		contentPane.add(btnVolver);
	}
}
