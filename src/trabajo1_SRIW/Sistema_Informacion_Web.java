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
	static JLabel label1, label2, label3, label4, labelI5, labelI6;
	static JTextField text1, textI2, textI3;
	static JTextArea text_area1, text_area2, text_area3;
	static JScrollPane scroll_tarea;
	static JRadioButton mayor, menor;
	static JButton boton_filtros, boton_stat, boton_igualatr;
	static ButtonGroup vf, group;
	static JPanel panel2;
	static JRadioButton nombre_banda = null;
	static JRadioButton año_banda = null;
	static JRadioButton nombre_disq = null;
	static JRadioButton pagina_web = null;
	static JRadioButton localizacion = null;
	static JRadioButton fecha_nac = null;
	static JRadioButton nacionalidad = null;
	static JRadioButton nombre = null;
	
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
	
	// Creacion de la ventana
	public static void main(String[] args) {
		
		Sistema_Informacion_Web ventana = new Sistema_Informacion_Web();	

	}
	
	// Oyente para el menu superior
	class OyenteMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Obtenemos la etiqueta del boton que se acciona
			String comandoAccion = e.getActionCommand();
			//System.out.println(comandoAccion);
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
					
					labelI5 = new JLabel();
					labelI5.setText("Instancia de la entidad(Reemplazar espacios por '_'): ");
					panel.add(labelI5);
					
					textI2 = new JTextField();
					textI2.setText("Michael_Peter_Balzary");
					panel.add(textI2);
					
					labelI6 = new JLabel();
					labelI6.setText("Atributo común: ");
					panel.add(labelI6);
					
					textI3 = new JTextField();
					textI3.setText("Estadounidense");
					panel.add(textI3);
					
					boton_igualatr = new JButton();
					boton_igualatr.setText("Buscar");
					boton_igualatr.addActionListener(new OyenteBotonIgualatr());
					panel.add(boton_igualatr);
					
					text_area3 = new JTextArea(20, 50);
					text_area3.setText("");
					scroll_tarea = new JScrollPane(text_area3);
					scroll_tarea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
					scroll_tarea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
					panel.add(scroll_tarea);
					
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
					}
					
					panel2 = new JPanel();
					panel2.setSize(400, 200);
					panel2.setVisible(true);
					panel.add(panel2);
					
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
	
	// Oyente para los Items del Combobox
	class OyenteItem implements ItemListener {
		
		// Este metodo se llama solamente si ha sido seleccionado un nuevo item
		public void itemStateChanged(ItemEvent ie) {{
			String s = (String) ie.getItem();
			//System.out.println(s);
			//System.out.println("----------------------");
			
			// Query principal de entidades con sus atributos
			Ejecutar_Query(s);
			
			if (s.equals("Persona(Indirecto)")) {
				
			}else {
				// Relaciones entre las instancias
				RelacionesInstancias(s);
				
				// Instancias equivalentes
				InstanciasEquivalentes(s);
			}
			
			
			
		}

	}

		// INSTANCIAS EQUIVALENTES
		private void InstanciasEquivalentes(String s) {
			
			FileManager.get().addLocatorClassLoader(Sistema_Informacion_Web.class.getClassLoader());
			Model model = FileManager.get().loadModel("src/bandas.owl");
			
			/*// Probar que el modelo carga ok
			model.write(System.out);*/
			
			
			// String de la consulta
		
			String queryString = 
					"PREFIX rock: <http://www.bandasderock.com#> " +
					"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
					"SELECT ?instancia ?sameAs WHERE { " +
					"	?instancia owl:sameAs ?sameAs; " +
					"	a rock:" + s + " " +
					"} ";
			
			/*// Probar string bien formada
			System.out.println(queryString);*/
			
			Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
			QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
			
			text_area1.append("\n");
			text_area1.append("INSTANCIAS EQUIVALENTES\n");
			text_area1.append("INSTANCIA ------------ EQUIVALENCIA\n");
			text_area1.append("--------------------------------------------------------\n");
			
			try {
				ResultSet results = qexec.execSelect();
				while (results.hasNext()) {
					QuerySolution soln = results.nextSolution();
					Resource instancia = soln.getResource("instancia");
					Resource sameas = soln.getResource("sameAs");
					text_area1.append(instancia.getURI().toString() + " ------------ " + sameas.getURI().toString() + "\n");
				}
			} finally {
				qexec.close();
			}
	}
}
	
	public class OyenteItem2 implements ItemListener {

		@Override
		// Este metodo se llama solamente si ha sido seleccionado un nuevo item
				public void itemStateChanged(ItemEvent ie) {{
					String s = (String) ie.getItem();
					//System.out.println(s);
					//System.out.println("----------------------");
					
					OpcionesAtributos(s);
					
					
				}

			}
		
		// QUERY PARA LAS ESTADISTICAS
		private void OpcionesAtributos(String s) {
			
			if (s == "Banda_de_rock") {
				
				panel2.removeAll();		
				
				nombre_banda = new JRadioButton();
				año_banda = new JRadioButton();
				año_banda.setText("Año_de_formacion");
				nombre_banda.setText("Nombre");
				año_banda.setActionCommand("Año_de_formacion");
				nombre_banda.setActionCommand("Nombre");
				nombre_banda.setSelected(true);
				
				group = new ButtonGroup();
				group.add(nombre_banda);
				group.add(año_banda);
				
				panel2.add(nombre_banda);
				panel2.add(año_banda);
				
				boton_stat = new JButton();
				boton_stat.setText("Contar");
				boton_stat.addActionListener(new OyenteBotonStat());
				panel2.add(boton_stat);
				text_area2 = new JTextArea(20, 50);
				text_area2.setText("");
				scroll_tarea = new JScrollPane(text_area2);
				scroll_tarea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
				scroll_tarea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
				panel2.add(scroll_tarea);
				
				panel2.revalidate();
				panel2.repaint();
				
			}
			else if (s == "Disquera") {
				
				panel2.removeAll();
				
				nombre_disq = new JRadioButton();
				pagina_web = new JRadioButton();
				localizacion = new JRadioButton();
				nombre_disq.setText("Nombre");
				pagina_web.setText("Pagina_web");
				localizacion.setText("Localizacion");
				nombre_disq.setActionCommand("Nombre");
				pagina_web.setActionCommand("Pagina_web");
				localizacion.setActionCommand("Localizacion");
				nombre_disq.setSelected(true);
				
				group = new ButtonGroup();
				group.add(nombre_disq);
				group.add(pagina_web);
				group.add(localizacion);
				
				panel2.add(nombre_disq);
				panel2.add(pagina_web);
				panel2.add(localizacion);
				
				boton_stat = new JButton();
				boton_stat.setText("Contar");
				boton_stat.addActionListener(new OyenteBotonStat());
				panel2.add(boton_stat);
				
				text_area2 = new JTextArea(20, 50);
				text_area2.setText("");
				scroll_tarea = new JScrollPane(text_area2);
				scroll_tarea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
				scroll_tarea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
				panel2.add(scroll_tarea);
				
				panel2.revalidate();
				panel2.repaint();
			}
			else if (s == "Persona") {

				panel2.removeAll();
				
				fecha_nac = new JRadioButton();
				nacionalidad = new JRadioButton();
				nombre = new JRadioButton();
				fecha_nac.setText("Fecha_de_nacimiento");
				nacionalidad.setText("Nacionalidad");
				nombre.setText("Nombre");
				fecha_nac.setActionCommand("Fecha_de_nacimiento");
				nacionalidad.setActionCommand("Nacionalidad");
				nombre.setActionCommand("Nombre");
				nombre.setSelected(true);
				
				group = new ButtonGroup();
				group.add(fecha_nac);
				group.add(nacionalidad);
				group.add(nombre);
				
				panel2.add(fecha_nac);
				panel2.add(nacionalidad);
				panel2.add(nombre);
				
				boton_stat = new JButton();
				boton_stat.setText("Contar");
				boton_stat.addActionListener(new OyenteBotonStat());
				panel2.add(boton_stat);
				
				text_area2 = new JTextArea(20, 50);
				text_area2.setText("");
				scroll_tarea = new JScrollPane(text_area2);
				scroll_tarea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
				scroll_tarea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
				panel2.add(scroll_tarea);
				
				panel2.revalidate();
				panel2.repaint();
				
			} 
			else if (s == "Manager") {
				
				panel2.removeAll();
				
				fecha_nac = new JRadioButton();
				nacionalidad = new JRadioButton();
				nombre = new JRadioButton();
				fecha_nac.setText("Fecha_de_nacimiento");
				nacionalidad.setText("Nacionalidad");
				nombre.setText("Nombre");
				fecha_nac.setActionCommand("Fecha_de_nacimiento");
				nacionalidad.setActionCommand("Nacionalidad");
				nombre.setActionCommand("Nombre");
				nombre.setSelected(true);
				
				group = new ButtonGroup();
				group.add(fecha_nac);
				group.add(nacionalidad);
				group.add(nombre);
				
				panel2.add(fecha_nac);
				panel2.add(nacionalidad);
				panel2.add(nombre);
				
				boton_stat = new JButton();
				boton_stat.setText("Contar");
				boton_stat.addActionListener(new OyenteBotonStat());
				panel2.add(boton_stat);
				
				text_area2 = new JTextArea(20, 50);
				text_area2.setText("");
				scroll_tarea = new JScrollPane(text_area2);
				scroll_tarea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
				scroll_tarea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
				panel2.add(scroll_tarea);
				
				panel2.revalidate();
				panel2.repaint();
				
			} else {

				panel2.removeAll();
								
				fecha_nac = new JRadioButton();
				nacionalidad = new JRadioButton();
				nombre = new JRadioButton();
				fecha_nac.setText("Fecha_de_nacimiento");
				nacionalidad.setText("Nacionalidad");
				nombre.setText("Nombre");
				fecha_nac.setActionCommand("Fecha_de_nacimiento");
				nacionalidad.setActionCommand("Nacionalidad");
				nombre.setActionCommand("Nombre");
				nombre.setSelected(true);
				
				group = new ButtonGroup();
				group.add(fecha_nac);
				group.add(nacionalidad);
				group.add(nombre);
				
				panel2.add(fecha_nac);
				panel2.add(nacionalidad);
				panel2.add(nombre);
				
				boton_stat = new JButton();
				boton_stat.setText("Contar");
				boton_stat.addActionListener(new OyenteBotonStat());
				panel2.add(boton_stat);
				
				text_area2 = new JTextArea(20, 50);
				text_area2.setText("");
				scroll_tarea = new JScrollPane(text_area2);
				scroll_tarea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
				scroll_tarea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
				panel2.add(scroll_tarea);
				
				panel2.revalidate();
				panel2.repaint();
				
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
			//System.out.println(queryString2);
			
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
	
	// Oyente para la query de las estadisticas de los atributos 
	class OyenteBotonStat implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			FileManager.get().addLocatorClassLoader(Sistema_Informacion_Web.class.getClassLoader());
			Model model = FileManager.get().loadModel("src/bandas.owl");
			
			// Mirar Seleccion
			//System.out.println("Seleccion: " + group.getSelection().getActionCommand());
			
			String queryString = 
					"PREFIX rock: <http://www.bandasderock.com#> " +
					"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
					"SELECT DISTINCT ?atr (COUNT(DISTINCT ?instance) AS ?num_inst) WHERE { " +
					"	?class a owl:Class . " +
					"	?instance a ?class . " +
					"	?atr a owl:DatatypeProperty . " +
					"	?instance ?atr ?valor  " +
					"} " +
					"GROUP BY (?atr) ";
			
			// Probar string bien formada
			//System.out.println(queryString);
			
			Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
			QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
			
			text_area2.setText("");
			text_area2.setText("ATRIBUTO SELECCIONADO ------ NUMERO DE INSTANCIAS\n");
			text_area2.append("---------------------------------------------------------------\n");
			
			try {
				ResultSet results = qexec.execSelect();
				while (results.hasNext()) {
					QuerySolution soln = results.nextSolution();
					Resource atr = soln.getResource("atr");
					Literal num_inst = soln.getLiteral("num_inst");
					
					//System.out.println("Seleccion Nombre: " + group.getSelection().getActionCommand().equals("Nombre"));
					//System.out.println("Seleccion Año: " + group.getSelection().getActionCommand().equals("Año_de_formacion"));
					
					
					if (group.getSelection().getActionCommand().equals("Nombre")) {
						if (atr.getURI().toString().equals("http://www.bandasderock.com#Nombre")) {
							text_area2.append(atr.getURI().toString() + " ------ " + num_inst.getString() + "\n");
						}
					}
					if (group.getSelection().getActionCommand().equals("Año_de_formacion")) {
						if (atr.getURI().toString().equals("http://www.bandasderock.com#Año_de_formacion")) {
							text_area2.append(atr.getURI().toString() + " ------ " + num_inst.getString() + "\n");
						}
					}
					if (group.getSelection().getActionCommand().equals("Pagina_web")) {
						if (atr.getURI().toString().equals("http://www.bandasderock.com#Pagina_web")) {
							text_area2.append(atr.getURI().toString() + " ------ " + num_inst.getString() + "\n");
						}
					}
					if (group.getSelection().getActionCommand().equals("Localizacion")) {
						if (atr.getURI().toString().equals("http://www.bandasderock.com#Localizacion")) {
							text_area2.append(atr.getURI().toString() + " ------ " + num_inst.getString() + "\n");
						}
					}
					if (group.getSelection().getActionCommand().equals("Nacionalidad")) {
						if (atr.getURI().toString().equals("http://www.bandasderock.com#Nacionalidad")) {
							text_area2.append(atr.getURI().toString() + " ------ " + num_inst.getString() + "\n");
						}
					}
					if (group.getSelection().getActionCommand().equals("Fecha_de_nacimiento")) {
						if (atr.getURI().toString().equals("http://www.bandasderock.com#Fecha_de_nacimiento")) {
							text_area2.append(atr.getURI().toString() + " ------ " + num_inst.getString() + "\n");
						}
					}
					
					//text_area2.append(atr.getURI().toString() + " ------ " + num_inst.getString() + "\n");
					
				}
			} finally {
				qexec.close();
			}
			
		}
	}
	
class OyenteBotonIgualatr implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			FileManager.get().addLocatorClassLoader(Sistema_Informacion_Web.class.getClassLoader());
			Model model = FileManager.get().loadModel("src/bandas.owl");
			
			/*// Probar que el modelo carga ok
			model.write(System.out);*/
			
			
			// String de la consulta
		
			String queryString = 
					"PREFIX rock: <http://www.bandasderock.com#> " +
					"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
					"SELECT DISTINCT ?entidad ?instancia2 ?atributo WHERE { " +
					"	?entidad a owl:Class . " +
					"	?instancia a ?entidad . " +
					"	?instancia2 a ?entidad FILTER(?instancia != ?instancia2) . " +
					"	?datatype a owl:DatatypeProperty . " +
					" 	?instancia ?datatype ?atributo . " +
					"	?instancia2 ?datatype ?atributo . " +
					"} ";
			
			/*// Probar string bien formada
			System.out.println(queryString);*/
			
			Query query = QueryFactory.create(queryString); // Crear un objeto para consulta
			QueryExecution qexec = QueryExecutionFactory.create(query, model); // Ejecutar la consulta SPARQL
			
			text_area3.setText("Instancias que tienen el mismo valor en el atributo '"+ textI3.getText() + "' que la instancia '" + textI2.getText() + "':\n");
			text_area3.append("INSTANCIA\n");
			text_area3.append("--------------------------------------------------------\n");
			
			try {
				ResultSet results = qexec.execSelect();
				while (results.hasNext()) {
					QuerySolution soln = results.nextSolution();
					Resource instancia = soln.getResource("instancia2");
					Literal atr = soln.getLiteral("atributo");
					
					//System.out.println(atr.getURI().toString());
					
					if (atr.getString().equals(textI3.getText())) {
						text_area3.append(instancia.getURI().toString() + "\n");
					}
				}
			} finally {
				qexec.close();
			}
		}
	}
}
