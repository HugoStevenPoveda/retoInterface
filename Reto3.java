package reto3;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
* Recomendaciones Generales:
*
*    -> El método run() funcionará como nuestro método principal
*    -> No declarar objetos de tipo Scanner, utilizar el método read() para solicitar datos al usuario.
*    -> Si requiere utilizar varias clases, estas NO deben ser tipo public.
*/
class Reto3 {

	/**
	 * Este debe ser el único objeto de tipo Scanner en el código
	 */
	private final Scanner scanner = new Scanner(System.in);

	/**
	 * Este método es usado para solicitar datos al usuario
	 * 
	 * @return Lectura de la siguiente linea del teclado
	 */
	public String read() {
		return this.scanner.nextLine();
	}

	/**
	 * método principal
	 */
	public void run() {
		/*
		 * solución propuesta
		 */
		// construcion Base datos
		Productos Manzanas = new Productos(1, "Manzanas", 8000.0, 65);
		Productos Limones = new Productos(2, "Limones", 2300.0, 15);
		Productos Granadilla = new Productos(3, "Granadilla", 2500.0, 38);
		Productos Arandanos = new Productos(4, "Arandanos", 9300.0, 55);
		Productos Tomates = new Productos(5, "Tomates", 2100.0, 42);
		Productos Fresas = new Productos(6, "Fresas", 4100.0, 3);
		Productos Helado = new Productos(7, "Helado", 4500.0, 41);
		Productos Galletas = new Productos(8, "Galletas", 500.0, 8);
		Productos Chocolates = new Productos(9, "Chocolates", 3500.0, 806);
		Productos Jamon = new Productos(10, "Jamon", 15000.0, 10);

		BaseDatosProductos baseDatosProductos = new BaseDatosProductos();
		baseDatosProductos.agregar(Manzanas);
		baseDatosProductos.agregar(Limones);
		baseDatosProductos.agregar(Granadilla);
		baseDatosProductos.agregar(Arandanos);
		baseDatosProductos.agregar(Tomates);
		baseDatosProductos.agregar(Fresas);
		baseDatosProductos.agregar(Helado);
		baseDatosProductos.agregar(Galletas);
		baseDatosProductos.agregar(Chocolates);
		baseDatosProductos.agregar(Jamon);


		// fin de base datos

		String operacion = read();
		String datosProducto = read();
		String[] elementosProductos = datosProducto.split(" ");
		Integer codigoProducto = Integer.parseInt(elementosProductos[0]);
		String nombreProducto = elementosProductos[1];
		Double precioProducto = Double.parseDouble(elementosProductos[2]);
		Integer inventarioProducto = Integer.parseInt(elementosProductos[3]);
		Productos producto = new Productos(codigoProducto, nombreProducto, precioProducto, inventarioProducto);
		Boolean existenciaProducto = baseDatosProductos.verificarExistencia(producto);

		switch (operacion) {
		case "ACTUALIZAR":
			if (existenciaProducto) {
				baseDatosProductos.actualizar(producto);
				baseDatosProductos.generarInforme();
			}

			else {
				System.out.println("ERROR");
			}

			break;
		case "BORRAR":
			if (existenciaProducto) {
				baseDatosProductos.eliminar(producto);
				baseDatosProductos.generarInforme();
			} else {
				System.out.println("ERROR");
			}

			break;
		case "AGREGAR":
			if (!existenciaProducto) {

				baseDatosProductos.agregar(producto);
				baseDatosProductos.generarInforme();
			} else {
				System.out.println("ERROR");
			}

			break;
		}

		
         
	}
}

class Productos {
	Integer codigo;
	String nombre;
	Double precio;
	Integer inventario;

	public Productos(Integer codigo, String nombre, Double precio, Integer inventario) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.inventario = inventario;

	}

	public Integer mostrarId() {
		return codigo;

	}

	public Double mostrarPrecio() {
		return precio;
	}

	public Integer mostrarInventario() {
		return inventario;
	}

	public String toString() {
		return nombre;
	}
}

class BaseDatosProductos {
	// agregar, actualizar y eliminar

	HashMap<Integer, Productos> listaProductos = new HashMap<Integer, Productos>();
	HashMap<Integer, Double> listaPrecios = new HashMap<Integer, Double>();

	public void crearListaPrecio() {
		for (Integer codigo : listaProductos.keySet()) {
			listaPrecios.put(codigo, listaProductos.get(codigo).mostrarPrecio());
		}
	}

	public Boolean verificarExistencia(Productos producto) {

		if (listaProductos.containsKey(producto.mostrarId())) {
			return true;
		} else {
			return false;
		}

	}

	public void agregar(Productos producto) {

		listaProductos.put(producto.mostrarId(), producto);

	}

	public void actualizar(Productos producto) {

		listaProductos.replace(producto.mostrarId(), producto);

	}

	public void eliminar(Productos producto) {

		listaProductos.remove(producto.mostrarId());

	}

	
	public Integer[] mayorMenor() {
		crearListaPrecio();
        // objeto me permite tener un aaray a agregarle un valor a mayor y menor
		Object[] preciosLista = listaPrecios.values().toArray();

		// almacena la llave producto mayor y la llave producto menor
		// primer llave mayor luego llave menor
		Integer[] KeysMayorMenor = new Integer[2];

		Double mayor;
		Double menor;
		Integer keyMayor = 0, keyMenor = 0;
		menor = (Double) preciosLista[0];
		mayor = (Double) preciosLista[0];

		for (Double precio : listaPrecios.values()) {
			if (precio > mayor)
				mayor = precio;
			if (precio < menor)
				menor = precio;
		}
		for (Integer key : listaPrecios.keySet()) {
			if (listaPrecios.get(key) == menor)
				keyMenor = key;
			if (listaPrecios.get(key) == mayor)
				keyMayor = key;
		}

		KeysMayorMenor[0] = keyMayor;
		KeysMayorMenor[1] = keyMenor;

		return KeysMayorMenor;

	}

	public void imprimeMayorMenor() {
		Integer[] mayorMenor = mayorMenor();
		System.out.print(listaProductos.get(mayorMenor[0]));
		System.out.print(" ");
		System.out.print(listaProductos.get(mayorMenor[1]));

	}

	public Double PromedioPrecio() {
		
		Double suma = (double) 0;
		Double promedio = (double) 0;
		int cantidaProductos = listaProductos.size();
		for (int key : listaProductos.keySet()) {
			suma += listaProductos.get(key).mostrarPrecio();
		}
		promedio = suma / cantidaProductos;
		// permite mostrar con solo un decimal
		String resultado = String.format("%.1f", promedio);
		resultado = resultado.replace(",", ".");
		promedio = Double.parseDouble(resultado);
		return promedio;
	}

	public void imprimirPromedio() {
		System.out.print(PromedioPrecio());

	}

	public Double totalInventario() {
		Double suma = (double) 0;
		Double precioProducto = (double) 0;
		Integer inventarioProducto = 0;
		Double producto = (double) 0;

		for (int key : listaProductos.keySet()) {
			precioProducto = listaProductos.get(key).mostrarPrecio();
			inventarioProducto = listaProductos.get(key).mostrarInventario();
			producto = precioProducto * inventarioProducto;
			suma += producto;
		}
		String resultado = String.format("%.1f", suma);
		resultado = resultado.replace(",", ".");
		suma = Double.parseDouble(resultado);

		return suma;
	}

	public void imprimirTotalInventario() {
		System.out.print(totalInventario());
	}
	
   public Integer[] ordenarPrecios() {
	   crearListaPrecio();
	   Integer[] keysProductosMayores = new Integer[3];
	   Integer keyProductoUno = 0;
	   Integer keyProductoDos = 0;
	   Integer keyProductoTres = 0;
	   Object[] preciosLista = listaPrecios.values().toArray();
	   Arrays.sort(preciosLista);
	   Double precioProductoUno = (Double) preciosLista[preciosLista.length-1];
	   Double precioProductoDos = (Double) preciosLista[preciosLista.length-2];
	   Double precioProductoTres = (Double) preciosLista[preciosLista.length-3];
	   for (Integer key:listaPrecios.keySet()) {
		   if(listaPrecios.get(key)==precioProductoUno) keyProductoUno = key;
		   if(listaPrecios.get(key)==precioProductoDos) keyProductoDos = key;
		   if(listaPrecios.get(key)==precioProductoTres) keyProductoTres = key;
	   }
	   keysProductosMayores[0]=keyProductoUno;
	   keysProductosMayores[1]=keyProductoDos;
	   keysProductosMayores[2]=keyProductoTres;
	   
	   return keysProductosMayores;
	   
		   
   }
   
   public void inprimirOrdenarPrecios() {
	   Integer[] listaPreciosMayores = ordenarPrecios();
	   Integer precioUno = listaPreciosMayores[0];
	   Integer precioDos = listaPreciosMayores[1];
	   Integer precioTres = listaPreciosMayores[2];
	   
	   System.out.print(listaProductos.get(precioUno));
	   System.out.print(" ");
	   System.out.print(listaProductos.get(precioDos));
	   System.out.print(" ");
	   
	   System.out.print(listaProductos.get(precioTres));
	  
	   
   }
		   
	   
	   
	   
	   
	   
	   
	
	
	
	
	
	
	

	public void generarInforme() {
		imprimeMayorMenor();
		System.out.print(" ");
		imprimirPromedio();
		System.out.print(" ");
		imprimirTotalInventario();
		System.out.println(" ");
		inprimirOrdenarPrecios();

	}

}
