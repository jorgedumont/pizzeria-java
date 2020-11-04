package pruebas;
import java.util.Date;
import java.util.Vector;

import modelo.Base;
import modelo.Ingrediente;
import modelo.Orden;
import modelo.Pizza;

public class Prueba {

	public static void main(String[] args) {
		// Bases e ingredientes
		Base fina_gf = new Base("fina sin gluten", false, 15, "mediana");
		Base normal = new Base("normal", true, 5, "mediana");

		Ingrediente tomate = new Ingrediente("tomate", false, 1.5);
		Ingrediente queso = new Ingrediente("queso", true, 4);
		Ingrediente cebolla = new Ingrediente("cebolla", false, 2.5);
		
		// 2 pizzas...
		//Vector<Ingrediente> ingredientes_triste_sg = new Vector<Ingrediente>();
		//ingredientes_triste_sg.add(cebolla);
		Pizza triste_gf = new Pizza("triste sin gluten", fina_gf); //, ingredientes_triste_sg);
		triste_gf.ponerIngrediente(cebolla);
		
		
		Vector<Ingrediente> ingredientes_felices = new Vector<Ingrediente>();
		ingredientes_felices.add(cebolla);
		ingredientes_felices.add(queso);
		ingredientes_felices.add(tomate);
		ingredientes_felices.add(queso);
		Pizza feliz = new Pizza("feliz doble queso", normal, ingredientes_felices);
		
		System.out.println("Precio pizza triste gf: " + triste_gf.calcularCostePizza());
		System.out.println("Precio pizza feliz :D : " + feliz.calcularCostePizza());
		System.out.println();
		System.out.println("¿Apta celiacos? triste gf: " + triste_gf.aptaCeliacos());
		System.out.println("¿Apta celiacos? feliz :D : " + feliz.aptaCeliacos());
		System.out.println();
		
		// Creamos el vector de pizzas
		Vector<Pizza> pizzas_pedido = new Vector<Pizza>();
		pizzas_pedido.add(feliz);
		pizzas_pedido.add(feliz);
		pizzas_pedido.add(feliz);
		pizzas_pedido.add(triste_gf);
		
		// Creamos una orden
		Orden orden = new Orden(
				"Javier Sánchez", 
				"C/ Tajo SN", 
				666666666, 
				new Date(), 
				pizzas_pedido, 
				false);
		
		orden.eliminarUltimaPizza();
		
		System.out.println("El coste del pedido: "+ orden.calcularCostePedido());
	}

}
