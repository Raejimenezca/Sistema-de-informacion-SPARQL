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
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

public class Sistema_Informacion_Web extends JFrame {

	static Container panel;
	static JComboBox cbx1, cbx2;
	static JLabel label1, label2, label3, label4;
	static JTextField text1;
	static JTextArea text_area1;
	static JScrollPane scroll_tarea;
	static JRadioButton mayor, menor;
	static JButton boton_filtros;
	static ButtonGroup vf;
	
	public Sistema_Informacion_Web() {
		super("Sistema de información web");
		panel = getContentPane();
		panel.setLayout(new FlowLayout());
		
		// Manejo del menu de la ventana
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu); // La barra de menus de esta ventana
		
		// Definicion del menu
		JMenu menu1 = new JMenu("Archivo");
		barraMenu.add(menu1); // Se aÃ±ade el menu1 a la barraMenu
		
		// Se adiciona un elemento de menu
		JMenuItem salir = new JMenuItem("Salir del programa");
		menu1.add(salir); // Se aÃ±ade el elemento al menu1 (Archivo)
		// Inserta una linea de separacion en el menu1 (Archivo)
		menu1.add(new JSeparator());
		
		// Se adiciona un elemento de menu que sera un submenu de menu1 (Archivo)
		JMenu opciones = new JMenu("Abrir archivo");
		
		// Se adicionan dos elementos de menu para el JMenu abrir
		JMenuItem opcion1 = new JMenuItem("Consulta de instancias");
		opciones.add(opcion1); // Se aÃ±ade el menu a las opciones
		JMenuItem opcion2 = new JMenuItem("Instancia con igual valor de atributo");
		opciones.add(opcion2); 
		JMenuItem opcion3 = new JMenuItem("Estadisticas por atributo");
		opciones.add(opcion3); 
		JMenuItem opcion4 = new JMenuItem("Relacion entre instancias");
		opciones.add(opcion4); 
		
		// Se aÃ±ade al submenu como elemento del menu
		menu1.add(opciones);
		
		// Se asignan los oyentes para las opciones 1 y 2, queda pendiente el oyente para opciones
		opcion1.addActionListener(new OyenteMenu());
		opcion2.addActionListener(new OyenteMenu());
		opcion3.addActionListener(new OyenteMenu());
		opcion4.addActionListener(new OyenteMenu());
		salir.addActionListener(new OyenteMenu());
		
		
		setSize(1400, 700);
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
			System.out.println(comandoAccion);
			if (e.getSource() instanceof JMenuItem) {
				if("Consulta de instancias".equals(comandoAccion)) {
					cbx1 = null;
					cbx2 = null;
					panel.removeAll();
					if (cbx1 != null) {
						
					} else {
						cbx1 = new JComboBox();
						cbx1.addItem("Banda_de_rock");
						cbx1.addItem("Disquera");
						cbx1.addItem("Manager");
						cbx1.addItem("Miembro");
						cbx1.addItem("Persona(Indirecto)");
						cbx1.addItemListener(new OyenteItem());
						panel.add(cbx1);
						text_area1 = new JTextArea(20, 50);
						text_area1.setText("");
						scroll_tarea = new JScrollPane(text_area1);
						scroll_tarea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
						scroll_tarea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
						panel.add(scroll_tarea);
					}
					
					panel.revalidate();
					panel.repaint();
				}		
				else if("Instancia con igual valor de atributo".equals(comandoAccion)) {
					cbx1 = null;
					cbx2 = null;
					panel.removeAll();
					System.out.println("Opcion2");	
					panel.revalidate();
					panel.repaint();
				}
				else if("Estadisticas por atributo".equals(comandoAccion)) {
					cbx1 = null;
					cbx2 = null;
					panel.removeAll();
					
					if (cbx2 != null) {
						
					} else {
						cbx2 = new JComboBox();
						cbx2.addItem("Banda_de_rock");
						cbx2.addItem("Disquera");
						cbx2.addItem("Manager");
						cbx2.addItem("Miembro");
						cbx2.addItem("Persona");
						cbx2.addItemListener(new OyenteItem2());
						panel.add(cbx2);
						text_area1 = new JTextArea(20, 50);
						text_area1.setText("");
						scroll_tarea = new JScrollPane(text_area1);
						scroll_tarea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
						scroll_tarea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
						panel.add(scroll_tarea);
					}
					
					panel.revalidate();
					panel.repaint();
				}
				else if("Relacion entre instancias".equals(comandoAccion)) {
					cbx1 = null;
					cbx2 = null;
					panel.removeAll();
					System.out.println("Opcion4");
					panel.revalidate();
					panel.repaint();
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
			if (s.equals("Persona(Indirecto)")) {
				
			}else {
				RelacionesInstancias(s);
			}			
		}

	}
}
		
		// CONSULTA DE INSTANCIAS Y FILTROS
		
		private void Ejecutar_Query(String str) {
			
			FileManager.get().addLocatorClassLoader(Sistema_Informacion_Web.class.getClassLoader());
			Model model = FileManager.get().loadModel("src/bandas.owl");
			
			/*// Probar que el modelo carga ok
			model.write(System.out);*/
			
			
			// String de la consulta
			if (str.equals("Banda_de_rock")) {
				
				if(label3 != null) {
					panel.remove(label3);
				}
				if(label4 != null) {
					panel.remove(label4);
				}
				if(label1 != null) {
					panel.remove(label1);
				}
				if(label2 != null) {
					panel.remove(label2);
				}
				if (mayor != null) {
					panel.remove(mayor);
				}
				if (menor!= null) {
					panel.remove(menor);
				}
				if (text1 != null) {
					panel.remove(text1);
				}
				if (boton_filtros != null) {
					panel.remove(boton_filtros);
				}
				
				
				String queryString = 
						"PREFIX rock: <http://www.bandasderock.com#> " +
						"SELECT ?nombre ?año WHERE { " +
						"	?banda a rock:" + str +" . " +
						"	?banda rock:Nombre ?nombre; " +
						"		rock:Año_de_formacion ?año " +
						"} ";
				
				/*// Probar string bien formada
				System.out.println(queryString);*/
				
				Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
				QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
				
				text_area1.setText("");
				text_area1.setText("INSTANCIAS DE 'Banda_de_rock':\n");
				text_area1.append("NOMBRE ------------ AÑO FORMACION\n");
				text_area1.append("--------------------------------------------------------\n");
				
				try {
					ResultSet results = qexec.execSelect();
					while (results.hasNext()) {
						QuerySolution soln = results.nextSolution();
						Literal nombre = soln.getLiteral("nombre");
						Literal año = soln.getLiteral("año");
						text_area1.append(nombre.getString() + " ------------ " + año.getInt() + "\n");
					}
				} finally {
					qexec.close();
				}
				
					label2 = new JLabel();
					label2.setText("Filtrar año de formacion ");
					panel.add(label2);
					
					mayor = new JRadioButton();
					mayor.setText("Mayor: ");
					mayor.setBounds(20, 30, 120, 30);
					
					menor = new JRadioButton();
					menor.setText("Menor: ");
					menor.setBounds(20, 30, 120, 30);
					
					vf = new ButtonGroup();
					vf.add(mayor);
					vf.add(menor);
					
					panel.add(mayor);
					panel.add(menor);
					
					text1 = new JTextField();
					text1.setText("2000");
					panel.add(text1);
					
					boton_filtros = new JButton();
					boton_filtros.setText("Filtrar");
					boton_filtros.addActionListener(new OyenteBoton1());
					panel.add(boton_filtros);
			}
			else if (str.equals("Persona(Indirecto)")) {
				
				if(label3 != null) {
					panel.remove(label3);
				}
				if(label4 != null) {
					panel.remove(label4);
				}
				if(label1 != null) {
					panel.remove(label1);
				}
				if(label2 != null) {
					panel.remove(label2);
				}
				if (mayor != null) {
					panel.remove(mayor);
				}
				if (menor!= null) {
					panel.remove(menor);
				}
				if (text1 != null) {
					panel.remove(text1);
				}
				if (boton_filtros != null) {
					panel.remove(boton_filtros);
				}
				
				
				String queryString = 
						"PREFIX rock: <http://www.bandasderock.com#> " +
						"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
						"SELECT ?nombre WHERE { " +
						"	?class a owl:Class . " +
						"	?class rdfs:subClassOf rock:Persona . " +
						"	?persona a ?class; " +
						"		rock:Nombre ?nombre " +
						"} ";
				
				/*// Probar string bien formada
				System.out.println(queryString);*/
				
				Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
				QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
				
				text_area1.setText("");
				text_area1.setText("INSTANCIAS QUE SON INDIRECTO:\n");
				text_area1.append("NOMBRE\n");
				text_area1.append("---------------------------------------------------------\n");
				
				try {
					ResultSet results = qexec.execSelect();
					while (results.hasNext()) {
						QuerySolution soln = results.nextSolution();
						Literal nombre = soln.getLiteral("nombre");
						text_area1.append(nombre.getString() + "\n");
					}
				} finally {
					qexec.close();
				}
			}
			else if (str.equals("Disquera")) {
				
				if(label3 != null) {
					panel.remove(label3);
				}
				if(label4 != null) {
					panel.remove(label4);
				}
				if(label1 != null) {
					panel.remove(label1);
				}
				if(label2 != null) {
					panel.remove(label2);
				}
				if (mayor != null) {
					panel.remove(mayor);
				}
				if (menor!= null) {
					panel.remove(menor);
				}
				if (text1 != null) {
					panel.remove(text1);
				}
				if (boton_filtros != null) {
					panel.remove(boton_filtros);
				}
				
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
				text_area1.setText("INSTANCIAS DE 'Disquera':\n");
				text_area1.append("NOMBRE ------- PAGINA WEB ------- LOCALIZACION\n");
				text_area1.append("------------------------------------------------------------------\n");
			
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
				
					label3 = new JLabel();
					label3.setText("Filtrar por parte del nombre: ");
					panel.add(label3);
					
					text1 = new JTextField();
					text1.setText("EMI");
					panel.add(text1);
					
					boton_filtros = new JButton();
					boton_filtros.setText("Filtrar");
					boton_filtros.addActionListener(new OyenteBoton2());
					panel.add(boton_filtros);		
			}
			else {
				
				if(label3 != null) {
					panel.remove(label3);
				}
				if(label4 != null) {
					panel.remove(label4);
				}
				if(label1 != null) {
					panel.remove(label1);
				}
				if(label2 != null) {
					panel.remove(label2);
				}
				if (mayor != null) {
					panel.remove(mayor);
				}
				if (menor!= null) {
					panel.remove(menor);
				}
				if (text1 != null) {
					panel.remove(text1);
				}
				if (boton_filtros != null) {
					panel.remove(boton_filtros);
				}

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
				if (str == "Manager") {
					text_area1.setText("INSTANCIAS DE 'Manager':\n");
				}
				if (str == "Miembro") {
					text_area1.setText("INSTANCIAS DE 'Miembro':\n");
				}
				text_area1.append("NOMBRE ------ FECHA NACIMIENTO ------ NACIONALIDAD\n");
				text_area1.append("-----------------------------------------------------------------\n");
				
				try {
					ResultSet results = qexec.execSelect();
					while (results.hasNext()) {
						QuerySolution soln = results.nextSolution();
						Literal nombre = soln.getLiteral("nombre");
						Literal fecha = soln.getLiteral("fecha");
						Literal nacionalidad = soln.getLiteral("nacionalidad");
						text_area1.append(nombre.getString() + " ------ " + fecha.getDatatypeURI() + " ------ " + nacionalidad.getString() + "\n");
					}
				} finally {
					qexec.close();
				}
				
					label4 = new JLabel();
					label4.setText("Filtrar por nacionalidad: ");
					panel.add(label4);
					
					text1 = new JTextField();
					text1.setText("Estadounidense");
					panel.add(text1);
					
					boton_filtros = new JButton();
					boton_filtros.setText("Filtrar");
					if (str.equals("Manager")) {
						boton_filtros.addActionListener(new OyenteBoton3());
					}
					if (str.equals("Miembro")) {
						boton_filtros.addActionListener(new OyenteBoton4());
					}
					panel.add(boton_filtros);
			}
		}
		
		// RELACIONES ENTRE INSTANCIAS

		private static void RelacionesInstancias(String st) {
			
			FileManager.get().addLocatorClassLoader(Sistema_Informacion_Web.class.getClassLoader());
			Model model = FileManager.get().loadModel("src/bandas.owl");
			
			// Relaciones con otras instancias
			String queryString2 = 
					"PREFIX rock: <http://www.bandasderock.com#> " +
					"SELECT ?relacion ?instanciar WHERE { " +
					"	?rel a rock:" + st +"; " +
					"	?relacion ?instanciar " +
					"} ";
			
			// Probar string bien formada
			System.out.println(queryString2);
			
			Query query2 = QueryFactory.create(queryString2); // Crear un objeto para consulta
			QueryExecution qexec2 = QueryExecutionFactory.create(query2, model); // Ejecutar la consulta SPARQL
			
			text_area1.append("\n");
			text_area1.append("RELACIONES CON OTRAS INSTANCIAS\n");
			text_area1.append("RELACION ------------ INSTANCIA\n");
			text_area1.append("---------------------------------------------------------\n");
			
			try {
				ResultSet results2 = qexec2.execSelect();
				while (results2.hasNext()) {
					QuerySolution soln = results2.nextSolution();
					Resource relacion = soln.getResource("relacion");
					//Literal relacion = soln.getLiteral("relacion");				
					
					try {
						Literal instancia = soln.getLiteral("instanciar");
						text_area1.append(relacion.getURI().toString() + " ------------ " + instancia.getString() + "\n");
					} catch (ClassCastException cce) {
						Resource instancia = soln.getResource("instanciar");
						text_area1.append(relacion.getURI().toString() + " ------------ " + instancia.getURI().toString() + "\n");
					}
					
					//System.out.println(relacion.getString());
					//System.out.println(instancia.getString());
					
				}
			} finally {
				qexec2.close();
			}
			
		}
	
	
	class OyenteBoton1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			FileManager.get().addLocatorClassLoader(Sistema_Informacion_Web.class.getClassLoader());
			Model model = FileManager.get().loadModel("src/bandas.owl");
			
			if (mayor.isSelected()) {
				String queryString = 
						"PREFIX rock: <http://www.bandasderock.com#> " +
						"SELECT ?nombre ?año WHERE { " +
						"	?banda a rock:Banda_de_rock . " +
						"	?banda rock:Nombre ?nombre; " +
						"		rock:Año_de_formacion ?año " +
						"	FILTER (?año > " + text1.getText() + ") " +
						"} ";
				
				/*// Probar string bien formada
				System.out.println(queryString);*/
				
				Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
				QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
				
				text_area1.setText("");
				text_area1.setText("NOMBRE ------------ AÑO FORMACION\n");
				text_area1.append("---------------------------------------------------------\n");
				
				try {
					ResultSet results = qexec.execSelect();
					while (results.hasNext()) {
						QuerySolution soln = results.nextSolution();
						Literal nombre = soln.getLiteral("nombre");
						Literal año = soln.getLiteral("año");
						text_area1.append(nombre.getString() + " ------------ " + año.getInt() + "\n");
					}
				} finally {
					qexec.close();
				}
			}
			if (menor.isSelected()) {
				String queryString = 
						"PREFIX rock: <http://www.bandasderock.com#> " +
						"SELECT ?nombre ?año WHERE { " +
						"	?banda a rock:Banda_de_rock . " +
						"	?banda rock:Nombre ?nombre; " +
						"		rock:Año_de_formacion ?año " +
						"	FILTER (?año < " + text1.getText() + ") " +
						"} ";
				
				/*// Probar string bien formada
				System.out.println(queryString);*/
				
				Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
				QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
				
				text_area1.setText("");
				text_area1.setText("NOMBRE ------------ AÑO FORMACION\n");
				text_area1.append("-------------------------------------------------------\n");
				
				try {
					ResultSet results = qexec.execSelect();
					while (results.hasNext()) {
						QuerySolution soln = results.nextSolution();
						Literal nombre = soln.getLiteral("nombre");
						Literal año = soln.getLiteral("año");
						text_area1.append(nombre.getString() + " ------------ " + año.getInt() + "\n");
					}
				} finally {
					qexec.close();
				}
			}
		}
	}
	
	class OyenteBoton2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			FileManager.get().addLocatorClassLoader(Sistema_Informacion_Web.class.getClassLoader());
			Model model = FileManager.get().loadModel("src/bandas.owl");
			
			String queryString = 
					"PREFIX rock: <http://www.bandasderock.com#> " +
					"SELECT ?nombre ?web ?local WHERE { " +
					"	?disquera a rock:Disquera . " +
					"	?disquera rock:Nombre ?nombre " +
					"	FILTER REGEX(?nombre, \"" + text1.getText() + "\", \"i\") ." +
					"	?disquera rock:Pagina_web ?web . " +
					"	OPTIONAL { ?disquera rock:Localizacion ?local } " +
					"} ";
			
			Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
			QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
			
			text_area1.setText("");
			text_area1.setText("NOMBRE ------- PAGINA WEB ------- LOCALIZACION\n");
			text_area1.append("-----------------------------------------------------------\n");
		
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
	}
	
	class OyenteBoton3 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			FileManager.get().addLocatorClassLoader(Sistema_Informacion_Web.class.getClassLoader());
			Model model = FileManager.get().loadModel("src/bandas.owl");
			
			String queryString = 
					"PREFIX rock: <http://www.bandasderock.com#> " +
					"SELECT ?nombre ?fecha ?nacionalidad WHERE { " +
					"	?persona a rock:Manager . " +
					"	?persona rock:Nombre ?nombre; " +
					"		rock:Fecha_de_nacimiento ?fecha . " +
					"	?persona rock:Nacionalidad ?nacionalidad " +
					"	FILTER REGEX(?nacionalidad, \"" + text1.getText() + "\", \"i\") ." +
					"} ";
			
			
			Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
			QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
			
			text_area1.setText("");
			text_area1.setText("NOMBRE ------ FECHA NACIMIENTO ------ NACIONALIDAD\n");
			text_area1.append("---------------------------------------------------------------\n");
			
			try {
				ResultSet results = qexec.execSelect();
				while (results.hasNext()) {
					QuerySolution soln = results.nextSolution();
					Literal nombre = soln.getLiteral("nombre");
					Literal fecha = soln.getLiteral("fecha");
					Literal nacionalidad = soln.getLiteral("nacionalidad");
					text_area1.append(nombre.getString() + " ------ " + fecha.getDatatypeURI() + " ------ " + nacionalidad.getString() + "\n");
				}
			} finally {
				qexec.close();
			}
		}
	}
	
	class OyenteBoton4 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			FileManager.get().addLocatorClassLoader(Sistema_Informacion_Web.class.getClassLoader());
			Model model = FileManager.get().loadModel("src/bandas.owl");
			
			String queryString = 
					"PREFIX rock: <http://www.bandasderock.com#> " +
					"SELECT ?nombre ?fecha ?nacionalidad WHERE { " +
					"	?persona a rock:Miembro . " +
					"	?persona rock:Nombre ?nombre; " +
					"		rock:Fecha_de_nacimiento ?fecha . " +
					"	?persona rock:Nacionalidad ?nacionalidad " +
					"	FILTER REGEX(?nacionalidad, \"" + text1.getText() + "\", \"i\") ." +
					"} ";
			
			
			Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
			QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
			
			text_area1.setText("");
			text_area1.setText("NOMBRE ------ FECHA NACIMIENTO ------ NACIONALIDAD\n");
			text_area1.append("------------------------------------------------------------------\n");
			
			try {
				ResultSet results = qexec.execSelect();
				while (results.hasNext()) {
					QuerySolution soln = results.nextSolution();
					Literal nombre = soln.getLiteral("nombre");
					Literal fecha = soln.getLiteral("fecha");
					Literal nacionalidad = soln.getLiteral("nacionalidad");
					text_area1.append(nombre.getString() + " ------ " + fecha.getDatatypeURI() + " ------ " + nacionalidad.getString() + "\n");
				}
			} finally {
				qexec.close();
			}
		}
	}
	
}
