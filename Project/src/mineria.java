// PROYECTO FINAL MINERÍA DE DATOS por Lucía Sánchez Manjarrez

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class mineria {
	private Random random;
	private final String[] niveles = {"Niño", "Joven", "Adulto", "Anciano"};
	private final String[] dias = {"LunesViernes", "SabadoDomingo"};
	private final String[] turnos = {"Matutino", "Vespertino", "Nocturno"};
	private final String[] eventos = {"Social", "Familiar", "Negocio", "Deportivo"};
	private final String[] tiposLugarEvento = {"Cerrado", "AireLibre", "SemiAbierto"};
	private final String[] generos = {"Masculino", "Femenino"};
	
	private mineria() {
		try {
			random = new Random();
            String route = "../data.arff";
            String content = "@relation covid\n"
            		+ "@attribute Edad numeric \n"
            		+ "@attribute Nivel {Niño, Joven, Adulto, Anciano} \n"
            		+ "@attribute Dia {LunesViernes, SabadoDomingo} \n"
            		+ "@attribute Turno {Matutino, Vespertino, Nocturno} \n"
            		+ "@attribute Hora numeric \n"
            		+ "@attribute Evento {Social, Familiar, Negocio, Deportivo} \n"
            		+ "@attribute TipoLugarEvento {Cerrado, AireLibre, SemiAbierto} \n"
            		+ "@attribute NoPersonas numeric \n"
            		+ "@attribute SanaDistancia {Si, No} \n"
            		+ "@attribute UsoCubrebocas {Si, No} \n"
            		+ "@attribute Genero {Masculino, Femenino} \n"
            		+ "@attribute SeContagio {Si, No} \n\n"
            		+ "@data\n";
            File file = new File(route);
            
            // if doesn't exist, create file
            if (!file.exists()) {
                file.createNewFile();
            }
            
            int noExamples = random.nextInt(100000) + 100000;
            int edad = 1;
            for(int i=0; i<noExamples; i++) {
            	if(i%1000 == 0)
            	System.out.println((i + 1) + " / " + noExamples);
            	edad = random.nextInt(100) + 1;
            	content+= edad + ", " +									// edad
            			(edad < 13? niveles[0] : 
            				edad < 18? niveles[1] : 
            					edad < 70? niveles[2] : 
            						niveles[3]) + 	 ", " +				// nivel
            			(dias[random.nextInt(1)]) +  ", " +				// dia
            			(turnos[random.nextInt(3)]) + ", " + 			// turno
                    	(random.nextInt(24) + 1) + ", " + 				// hora
            			(eventos[random.nextInt(4)]) + ", " +			// evento
            			(tiposLugarEvento[random.nextInt(3)]) + ", " +	// tipo de lugar
                    	(random.nextInt(99) + 2) + ", " + 				// no personas
                    	(random.nextBoolean()? "Si" : "No") + ", " +	// sana distancia
                    	(random.nextBoolean()? "Si" : "No") + ", " +	// usó cubrebocas
                    	(generos[random.nextInt(2)]) + ", " +			// genero
                    	(random.nextBoolean()? "Si" : "No") +			// se contagió
            			"\n";	
            }
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static void main(String [] args) {
		new mineria();
	}
}