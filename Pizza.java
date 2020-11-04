package modelo;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

public class Pizza {
	// Atributos
	private String nombre_pizza;
	private Base base;
	private Vector<Ingrediente> ingredientes;
	
	// Constructor
	public Pizza(String nombre_pizza, Base base, Vector<Ingrediente> ingredientes) {
		this.nombre_pizza = nombre_pizza;
		this.base = base;
		this.ingredientes = ingredientes;
	}
	public Pizza(String nombre_pizza, Base base) {
		this.nombre_pizza = nombre_pizza;
		this.base = base;
		this.ingredientes = new Vector<Ingrediente>();
	}
	public Pizza(String nombre_pizza) {
		this.nombre_pizza = nombre_pizza;
		this.base = new Base(true, 5);
		this.ingredientes = new Vector<Ingrediente>();
	}
	
	//Comstructor por copia para copiar una pizza y poder modificarla sin modificar la original
	public Pizza(Pizza p) {
		this.nombre_pizza = p.nombre_pizza;
		this.setIngredientes(new Vector<Ingrediente>(p.getIngredientes()));
	}
	// Métodos
	public void ponerIngrediente(Ingrediente i) {
		this.ingredientes.add(i);
	}
	
	public void quitarIngrediente(Ingrediente i){
		this.ingredientes.remove(i);
	}
	
	public void ponerBase(Base b) {
		this.base = b;
		// Es lo mismo que : setBase(b);
	}
	
	public double calcularCostePizza() {
		double coste = base.getPrecio();
		for (int i=0; i<this.ingredientes.size(); i++) {
			coste += this.ingredientes.get(i).getPrecio(); 
		}
		return coste;
	}
	
	public boolean aptaCeliacos() {
		boolean apta = true;
		
		if (this.base.isGluten()) {
			apta = false;
		}
		else {
			int i = 0;
			while (apta && i<this.ingredientes.size()) {
				if(this.ingredientes.get(i).isGluten()) {
					apta = false;
				}
				i++;
			}
		}
		
		return apta;
	}
	@Override
	public String toString() {
		final NumberFormat formatter = new DecimalFormat("#0.00");
		
		String salida = "";
		return salida = salida + nombre_pizza + "  -  " 
						+ formatter.format(getPrecio()) + " €";
	}
	
	
	// Getters y Setters
	public String getNombre_pizza() {
		return nombre_pizza;
	}
	public void setNombre_pizza(String nombre_pizza) {
		this.nombre_pizza = nombre_pizza;
	}
	public Base getBase() {
		return base;
	}
	public void setBase(Base base) {
		this.base = base;
	}
	public Vector<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(Vector<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	public double getPrecio() {
		double precio = this.base.getPrecio();
		for (int i=0; i < this.ingredientes.size(); i++)
			precio = precio + this.ingredientes.get(i).getPrecio();
		return precio;
	}
	
}
