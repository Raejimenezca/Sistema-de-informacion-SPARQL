package trabajo1_SRIW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class Sistema_Informacion_Web extends JFrame {

	Container panel;
	JComboBox cbx1;
	JLabel label1;
	JTextField text1;
	JTextArea text_area1;
	JScrollPane scroll_tarea;
	
	public Sistema_Informacion_Web() {
		super("Sistema de informaci�n web");
		panel = getContentPane();
		panel.setLayout(new FlowLayout());
		
		// Manejo del menu de la ventana
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu); // La barra de menus de esta ventana
		
		// Definicion del menu
		JMenu menu1 = new JMenu("Archivo");
		barraMenu.add(menu1); // Se a�ade el menu1 a la barraMenu
		
		// Se adiciona un elemento de menu
		JMenuItem salir = new JMenuItem("Salir del programa");
		menu1.add(salir); // Se a�ade el elemento al menu1 (Archivo)
		// Inserta una linea de separacion en el menu1 (Archivo)
		menu1.add(new JSeparator());
		
		// Se adiciona un elemento de menu que sera un submenu de menu1 (Archivo)
		JMenu opciones = new JMenu("Abrir archivo");
		
		// Se adicionan dos elementos de menu para el JMenu abrir
		JMenuItem opcion1 = new JMenuItem("Consulta de instancias");
		opciones.add(opcion1); // Se a�ade el menu a las opciones
		JMenuItem opcion2 = new JMenuItem("Instancia con igual valor de atributo");
		opciones.add(opcion2); 
		JMenuItem opcion3 = new JMenuItem("Estadisticas por atributo");
		opciones.add(opcion3); 
		JMenuItem opcion4 = new JMenuItem("Relacion entre instancias");
		opciones.add(opcion4); 
		
		// Se a�ade al submenu como elemento del menu
		menu1.add(opciones);
		
		// Se asignan los oyentes para las opciones 1 y 2, queda pendiente el oyente para opciones
		opcion1.addActionListener(new OyenteMenu());
		opcion2.addActionListener(new OyenteMenu());
		opcion3.addActionListener(new OyenteMenu());
		opcion4.addActionListener(new OyenteMenu());
		salir.addActionListener(new OyenteMenu());
		
		
		setSize(600, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
	
	public static void main(String[] args) {
		
		Sistema_Informacion_Web ventana = new Sistema_Informacion_Web();		

	}
	
	class OyenteMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Obtenemos la etiqueta del boton que se acciona
			String comandoAccion = e.getActionCommand();
			if (e.getSource() instanceof JMenuItem) {
				if("Consulta de instancias".equals(comandoAccion)) {
					panel.revalidate();
					if (cbx1 != null) {
						
					} else {
						cbx1 = new JComboBox();
						cbx1.addItem("Banda_de_rock");
						cbx1.addItem("Disquera");
						cbx1.addItem("Manager");
						cbx1.addItem("Miembro");
						panel.add(cbx1);
						cbx1.addItemListener(new OyenteItem());
						text_area1 = new JTextArea(5, 20);
						text_area1.setText("");
						scroll_tarea = new JScrollPane(text_area1);
						panel.add(text_area1);
					}
				}		
				else if("Instancia con igual valor de atributo".equals(comandoAccion)) {
					panel.removeAll();
					System.out.println("Opcion2");
				}
				else if("Estadisticas por atributo".equals(comandoAccion)) {
					panel.removeAll();
					System.out.println("Opcion3");
				}
				else if("Relacion entre instancias".equals(comandoAccion)) {
					panel.removeAll();
					System.out.println("Opcion4");
				}
				else if("Salir del programa".equals(comandoAccion)) {
					System.exit(0);
				}
							
			}
					
		}
	}
	
	class OyenteItem implements ItemListener {
		
		// Este metodo se llama solamente si ha sido seleccionado un nuevo item
		public void itemStateChanged(ItemEvent ie) {{
			String s = (String) ie.getItem();
			//System.out.println(s);
			//System.out.println("----------------------");
			
			Ejecutar_Query(s);
	}

}

		private void Ejecutar_Query(String str) {
			
			FileManager.get().addLocatorClassLoader(Sistema_Informacion_Web.class.getClassLoader());
			Model model = FileManager.get().loadModel("src/bandas.owl");
			
			/*// Probar que el modelo carga ok
			model.write(System.out);*/
			
			
			// String de la consulta
			if (str.equals("Banda_de_rock")) {
				String queryString = 
						"PREFIX rock: <http://www.bandasderock.com#> " +
						"SELECT ?nombre ?a�o WHERE { " +
						"	?banda a rock:" + str +" . " +
						"	?banda rock:Nombre ?nombre; " +
						"		rock:A�o_de_formacion ?a�o " +
						"} ";
				
				/*// Probar string bien formada
				System.out.println(queryString);*/
				
				Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
				QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
				
				text_area1.setText("");
				text_area1.setText("NOMBRE ------------ A�O FORMACION\n");
				
				try {
					ResultSet results = qexec.execSelect();
					while (results.hasNext()) {
						QuerySolution soln = results.nextSolution();
						Literal nombre = soln.getLiteral("nombre");
						Literal a�o = soln.getLiteral("a�o");
						text_area1.append(nombre.getString() + " ------------ " + a�o.getInt() + "\n");
					}
				} finally {
					qexec.close();
				}
			}
			else if (str.equals("Disquera")) {
				String queryString = 
						"PREFIX rock: <http://www.bandasderock.com#> " +
						"SELECT ?nombre ?web ?local WHERE { " +
						"	?disquera a rock:" + str +" . " +
						"	?disquera rock:Nombre ?nombre; " +
						"		rock:Pagina_web ?web . " +
						"	OPTIONAL { ?disquera rock:Localizacion ?local } " +
						"} ";
				
				/*// Probar string bien formada
				System.out.println(queryString);*/
				
				Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
				QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
				
				text_area1.setText("");
				text_area1.setText("NOMBRE ------- PAGINA WEB ------- LOCALIZACION\n");
			
				try {
					ResultSet results = qexec.execSelect();
					while (results.hasNext()) {
						QuerySolution soln = results.nextSolution();
						Literal nombre = soln.getLiteral("nombre");
						Literal web = soln.getLiteral("web");
						Literal localizacion = soln.getLiteral("local");
						if (localizacion != null) {
							text_area1.append(nombre.getString() + " ------- " + web.getString() + " ------- " + localizacion.getString() + "\n");
						}
						else {
							text_area1.append(nombre.getString() + " ------- " + web.getString() + " ------- " + "NULL" + "\n");
						}
						
					}
				} finally {
					qexec.close();
				}
			}
			else {
				String queryString = 
						"PREFIX rock: <http://www.bandasderock.com#> " +
						"SELECT ?nombre ?fecha ?nacionalidad WHERE { " +
						"	?persona a rock:" + str +" . " +
						"	?persona rock:Nombre ?nombre; " +
						"		rock:Fecha_de_nacimiento ?fecha . " +
						"	?persona rock:Nacionalidad ?nacionalidad " +
						"} ";
				
				/*// Probar string bien formada
				System.out.println(queryString);*/
				
				Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
				QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
				
				text_area1.setText("");
				text_area1.setText("NOMBRE ------ FECHA NACIMIENTO ------ NACIONALIDAD\n");
				
				try {
					ResultSet results = qexec.execSelect();
					while (results.hasNext()) {
						QuerySolution soln = results.nextSolution();
						Literal nombre = soln.getLiteral("nombre");
						Literal fecha = soln.getLiteral("fecha");
						Literal nacionalidad = soln.getLiteral("nacionalidad");
						System.out.println(fecha);
						System.out.println(nacionalidad);
						text_area1.append(nombre.getString() + " ------ " + fecha.getDatatypeURI() + " ------ " + nacionalidad.getString() + "\n");
					}
				} finally {
					qexec.close();
				}
			}
		}
	
}
	
}