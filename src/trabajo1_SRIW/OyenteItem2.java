package trabajo1_SRIW;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OyenteItem2 implements ItemListener {

	@Override
	// Este metodo se llama solamente si ha sido seleccionado un nuevo item
			public void itemStateChanged(ItemEvent ie) {{
				String s = (String) ie.getItem();
				//System.out.println(s);
				//System.out.println("----------------------");
				
				Ejecutar_Query2(s);
				
				
			}

		}

	private void Ejecutar_Query2(String s) {
		if (s == "") {
			
		}
		else if (s == "") {
			
		}
		else if (s == "") {
			
		} else {
			
		}
		
	}

}
