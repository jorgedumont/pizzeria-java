package modelo;
public class Ingrediente {
	// Atributos
	private String nombre;
	private boolean gluten;
	private double precio;
	
	// Constructor
	public Ingrediente(String nombre, boolean gluten, double precio) {
		this.nombre = nombre;
		this.gluten = gluten;
		this.precio = precio;
	}
	
	// Getters y Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isGluten() {
		return gluten;
	}
	public void setGluten(boolean gluten) {
		this.gluten = gluten;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		String salida = "";
		salida = salida + nombre + " ";
		if(this.gluten==false){
			salida = salida + "sin gluten" + " ";
		}
		salida = salida + "por " + precio + "euros";
		return salida;
	}
	
}
