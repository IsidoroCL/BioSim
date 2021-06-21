import java.util.ArrayList;

public class Criatura extends Thread {
    /**
     * Atributos de la criatura
     */
    ArrayList<Gen> ADN;
    int x; //Posición X
    int y; //Posición Y
    boolean sex; //True femenino - False masculino
    int energia; //Energía que tiene y gasta al hacer acciones
    int umbral; //Umbral de energía antes de buscar comida
    

    public ArrayList<Gen> getADN() {
        return ADN;
    }
    
    /**
     * Criatura constructor vacio.
     * Genera ADN al azar.
     */
    public Criatura() {
        //Genera ADN al azar
        ADN = new ArrayList<Gen>();
        int tam_adn = (int) Math.floor((Math.random()*20)+5);
        for (int i = 0; i < tam_adn; i++) {
            ADN.add(new Gen());
        }
    }
    
    /**
     * Criatura constructor con dos progenitores
     * @param padre
     * @param madre
    */
    public Criatura(Criatura padre, Criatura madre) {
        ADN = new ArrayList<Gen>();
        Gen x;
        int rnd;
        for (int i = 0; i < madre.ADN.size(); i++) {
            rnd = (int) Math.floor(Math.random()*2);
            if (rnd == 0) {
                if ((padre.ADN.size()-1) >= i) {
                    x = padre.ADN.get(i);
                } else {
                x = madre.ADN.get(i);
                }     
            } else {
                x = madre.ADN.get(i);
            }
            ADN.add(x);
        }
    }

    @Override
    public String toString() {
        String dataADN = "{";
        for (int j = 0; j < ADN.size(); j++) {
            dataADN = dataADN + "-";
            //Transformamos los números en las base de ADN
            switch(ADN.get(j).getA()) {
                case 0: dataADN += "A";
                    break;
                case 1: dataADN += "C";
                    break;
                case 2: dataADN += "T";
                    break;
                case 3: dataADN += "G";
                    break;
            }
            switch(ADN.get(j).getB()) {
                case 0: dataADN += "A";
                    break;
                case 1: dataADN += "C";
                    break;
                case 2: dataADN += "T";
                    break;
                case 3: dataADN += "G";
                    break;
            }
            switch(ADN.get(j).getC()) {
                case 0: dataADN += "A";
                    break;
                case 1: dataADN += "C";
                    break;
                case 2: dataADN += "T";
                    break;
                case 3: dataADN += "G";
                    break;
            }
            
        }
        dataADN += "}";
        return dataADN;
    }
    
    /*
    Añadir metodo run
    1- check energía
    2- comida 
        2.1- if comida en la casilla se alimenta (accion=true)
        2.2- else (metodo buscar comida)=devuelve casilla comida
    3- reproducirse
        3.1- if pareja en la casilla se reproduce (accion=true)
        3.2- else (metodo buscar pareja)=devuelve casill pareja
    4- moverse (if accion = false)
        mueve su movimiento a la casilla
    */
    
    @Override
    public void run() {
        
    }
    
}
