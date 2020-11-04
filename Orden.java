package modelo;
import java.io.BufferedWriter;
import java.util.Date;
import java.util.Vector;

public class Orden {
	// Atributos
	private String nombre;
	private String direccion;
	private String telefono;
	private Date fecha_pedido;
	private Vector<Pizza> pizzas;
	private boolean entregado;
	private double precioTotal;
	
	// Constructor con todos los atributos
	public Orden(String nombre, String direccion, String telefono,
			Date fecha_pedido, boolean entregado, double precioTotal) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.fecha_pedido = fecha_pedido;
		this.pizzas = null;
		this.entregado = entregado;
		this.precioTotal = precioTotal;
	}
	
	
	// Métodos
	public void aniadirPizza(Pizza p){
		this.pizzas.add(p);
	}
	
	public void eliminarUltimaPizza() {
		this.pizzas.remove(this.pizzas.size()-1);
	}
	
	public double calcularCostePedido() {
		
		return this.precioTotal;
	}
	
	public void confirmarRecepcion() {
		this.setEntregado(true);
	}
	
	// Getters y Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Date getFecha_pedido() {
		return fecha_pedido;
	}
	public void setFecha_pedido(Date fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}
	public Vector<Pizza> getPizzas() {
		return pizzas;
	}
	public void setPizzas(Vector<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	public boolean isEntregado() {
		return entregado;
	}
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
	
	public String toString(){
		return this.nombre + "        " + this.direccion
				+ "        " + this.telefono + "        " + this.precioTotal
				+ "        " + (this.entregado?"Sí":"No");
	}
	
	public String toStringFichero() {
		String salida = "";
		
		salida = salida + this.nombre + ";"
						+ this.direccion + ";"
						+ this.telefono + ";";
		String fecha = this.fecha_pedido.getDay() + "/" +
						this.fecha_pedido.getMonth() + "/" + 
						this.fecha_pedido.getYear();
		salida = salida + fecha + ";"
						+ this.entregado + ";"
						+ this.precioTotal;
		return salida;
	}
	
	
	
	
	
}
