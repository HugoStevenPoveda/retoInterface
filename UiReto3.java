package reto3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import javax.swing.JTable;

public class UiReto3 {

	private JFrame frame;
	private JTable tableProductos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiReto3 window = new UiReto3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UiReto3() {
		initialize();
	}

	private void construirFrame() {
		frame = new JFrame();
		frame.setTitle("Inventario de Productos");
		frame.setBounds(100, 100, 500, 600);
		frame.getContentPane().setLayout(new BorderLayout(10, 10));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void contruirPanelAgregarProducto() {

		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(255, 255, 255));
		panelNorte.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 204), 5));
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(2, 2));

		// panel para titulo

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(255, 255, 204));
		panelNorte.add(panelTitulo, BorderLayout.NORTH);
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));

		// titulo de la app
		JLabel titulo = new JLabel();
		titulo.setForeground(new Color(102, 0, 102));
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		titulo.setText("!Bienvenidos a la APP inventario");
		panelTitulo.add(titulo);

		// panel controlar los label y textFiedl
		JPanel panelAgregarProducto = new JPanel();

		panelAgregarProducto.setBackground(new Color(255, 255, 153));
		panelAgregarProducto.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		panelNorte.add(panelAgregarProducto, BorderLayout.CENTER);
		panelAgregarProducto.setLayout(new GridLayout(3, 2, 2, 2));

		JLabel nombre = new JLabel("Nombre");
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel precio = new JLabel("Precio");
		precio.setHorizontalAlignment(SwingConstants.CENTER);
		precio.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel inventario = new JLabel("Invetario");
		inventario.setHorizontalAlignment(SwingConstants.CENTER);
		inventario.setFont(new Font("Tahoma", Font.BOLD, 14));
		JTextField campoNombre = new JTextField();
		JTextField campoPrecio = new JTextField();
		JTextField campoInventario = new JTextField();
		panelAgregarProducto.add(nombre);
		panelAgregarProducto.add(campoNombre);
		panelAgregarProducto.add(precio);
		panelAgregarProducto.add(campoPrecio);
		panelAgregarProducto.add(inventario);
		panelAgregarProducto.add(campoInventario);

		// panel de boton agregar producto
		JPanel panelBoton = new JPanel();
		panelBoton.setBackground(new Color(255, 255, 204));
		panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelNorte.add(panelBoton, BorderLayout.SOUTH);

		JButton agregarProducto = new JButton();
		agregarProducto.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		agregarProducto.setText("Agregar Producto");
		panelBoton.add(agregarProducto);

		// panel lateral para style en east

		JPanel panelIsquierda = new JPanel();
		panelIsquierda.setBackground(UIManager.getColor(new Color(255, 255, 204)));
		panelIsquierda.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelNorte.add(panelIsquierda, BorderLayout.EAST);
		panelIsquierda.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 204), 30));

		// panel lateral para style west
		JPanel panelDerecho = new JPanel();
		panelDerecho.setBackground(UIManager.getColor(new Color(255, 255, 204)));
		panelDerecho.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelNorte.add(panelDerecho, BorderLayout.WEST);
		panelDerecho.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 204), 30));

	}

	private void construirTablaProductos() {

		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new BorderLayout(5, 5));
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);

		// datos y cnombre de campos para la tabla
		String[] columnNombres = { "Nombre", "Precio", "Invetario" };
		Object[][] datosProductos = { { "Manzanas", 14.5, 4 }, { "Peras", 45.8, 7 }, { "Papas", 4.5, 5 } };

		// creacion de tabla
		tableProductos = new JTable(datosProductos, columnNombres);
		tableProductos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableProductos.getTableHeader().setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panelCenter.add(tableProductos.getTableHeader(), BorderLayout.PAGE_START);
		panelCenter.add(new JScrollPane(tableProductos), BorderLayout.CENTER);

		// panel izquierdo
		JPanel panelIsquierda = new JPanel();
		panelIsquierda.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCenter.add(panelIsquierda, BorderLayout.EAST);
		panelIsquierda.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 30));
		// Panel derecho
		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCenter.add(panelDerecho, BorderLayout.WEST);
		panelDerecho.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 30));
		panelCenter.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 7));

	}

	private void construirBotonesOperaciones() {

		JPanel panelSur = new JPanel();
		frame.getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton borrarProducto = new JButton("Borrar Producto");
		borrarProducto.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		JButton actualizarProducto = new JButton("Actualizar Producto");
		actualizarProducto.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		JButton informes = new JButton("Informes");
		informes.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		panelSur.add(borrarProducto);
		panelSur.add(actualizarProducto);
		panelSur.add(informes);
		panelSur.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 7));

	}



	private void initialize() {
		construirFrame();
		contruirPanelAgregarProducto();
		construirTablaProductos();
		construirBotonesOperaciones();
		

	}

}
