package modelo;
public class Base {
	// Atributos
	private String tipo;
	private boolean gluten;
	private double precio;
	private String tamanio;
	
	// Constructor
	public Base(String tipo, boolean gluten, double precio, String tamanio) {
		this.tipo = tipo;
		this.gluten = gluten;
		this.precio = precio;
		this.tamanio = tamanio;
	}
	
	public Base(boolean gluten, double precio) {
		this.gluten = gluten;
		this.precio = precio;
		this.tipo = "masa normal";
		this.tamanio = "mediano";
	}
	
	@Override
	public String toString() {
		String salida = "";
		salida = salida + tipo + tamanio + " ";
		if(this.gluten==false){
			salida = salida + "sin gluten" + " ";
		}
		salida = salida + "por " + precio + "euros";
		return salida;
	}
	
	// Getters y Setters
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}	
}
