import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Scanner;
import java.util.Arrays;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Isidoro
 */
public class BioSim extends JFrame {
    public static int tasa_mutacion = 20;
    public static int num_casillas = 100;
    public Casilla[][] tablero;
    public ArrayList<Criatura> p; //Lista donde se introducen todas las criaturas
        
    //Parte gráfica
    private JPanel contentPane;
    private JScrollPane scroll;
    
    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        BioSim juego = new BioSim(num_casillas);
        juego.setTitle("BioSim");
                
        System.out.println("BIOSIM 0.1");
        System.out.println("Prueba de generación de tablero");
        
        try {
            //Genera tablero con el tamaño de cada bioma
            juego.crear(1, 10);
            juego.crear(2, 10);
            juego.crear(3, 10);
            juego.crear(4, 30);
            juego.repaint();
            
        } catch (IOException ex) {
            Logger.getLogger(BioSim.class.getName()).log(Level.SEVERE, null, ex);
        }
        juego.clima(num_casillas, 20);
        
        //Imprime el tablero
        for (int i = 0; i < num_casillas; i++) {
            for (int j = 0; j < num_casillas; j++) {
                System.out.print(juego.tablero[i][j]);
            }
            System.out.println("X");
        }
        
        //Prueba de generacion de ADN
        juego.poblar();
        juego.mostrarADN();
               
        juego.setVisible(true);
        
    }
    
    public void crear(int tipo, int tam) throws IOException {
        for (int b = 1; b < (num_casillas/4); b++) {
            int semX = (int) Math.floor(Math.random()*num_casillas);
            int semY = (int) Math.floor(Math.random()*num_casillas);
            this.tablero[semX][semY].setTipo(tipo);
            this.propagar(semX, semY, tipo, tam);
        }
    }
    
    public void propagar(int x, int y, int tipo, int tam) throws IOException {
        boolean continua;
        int propaga;
        int dirX;
        int dirY;
        do{
            propaga = (int) Math.floor(Math.random()*tam);
            if (propaga > 0) {
                if ((x+1)>(num_casillas-1) || (x-1) < 0) {
                    continua = false;
                } else if((y+1)>(num_casillas-1) || (y-1) < 0) {
                    continua = false;
                } else {
                    this.tablero[x+1][y].setTipo(tipo);
                    this.tablero[x][y+1].setTipo(tipo);
                    this.tablero[x-1][y].setTipo(tipo);
                    this.tablero[x][y-1].setTipo(tipo);
                    continua = true;
                    dirX = (int) Math.floor(Math.random()*3);
                    dirY = (int) Math.floor(Math.random()*3);
                    switch(dirX) {
                        case 0: break;
                        case 1: x--;
                            break;
                        case 2: x++;
                            break;
                    }
                    switch(dirY) {
                        case 0: break;
                        case 1: y--;
                            break;
                        case 2: y++;
                            break;
                    }
                }
            } else {continua = false;}
        } while(continua);
    }
    
    public void clima(int casillas, int temp_ini) {
        for (int i = 0; i < casillas; i++) {
            for (int j = 0; j < casillas; j++) {
                switch(this.tablero[i][j].getTipo()) {
                    case 0: this.tablero[i][j].setTemp(temp_ini);
                        break;
                    case 1: this.tablero[i][j].setTemp(temp_ini+3);
                        break;
                    case 2: this.tablero[i][j].setTemp(temp_ini-5);
                        break;
                    case 3: this.tablero[i][j].setTemp(temp_ini-10);
                        break;
                    case 4: this.tablero[i][j].setTemp(temp_ini);
                        break;
                }
            }
        }
    }
    
    public void poblar() {
        p = new ArrayList<Criatura>();
        for (int u = 0; u<10; u++) {
            p.add(new Criatura());
        }
    }
    
    public void mostrarADN() {
        Iterator<Criatura> it_p = p.iterator();
        Scanner key = new Scanner(System.in);
        while(it_p.hasNext()) {
            System.out.println(it_p.next());
        }
        System.out.println("Elige la criatura padre a reproducir");
        int num = key.nextInt();
        Criatura padre = p.get(num-1);
        System.out.println("Elige la criatura madre a reproducir");
        num = key.nextInt();
        Criatura madre = p.get(num-1);
        p.add(new Criatura(padre, madre));
        p.add(new Criatura(padre, madre));
        p.add(new Criatura(padre, madre));
        p.add(new Criatura(padre, madre));
        p.add(new Criatura(padre, madre));
        System.out.println(p.get(p.size()-5));
        System.out.println(p.get(p.size()-4));
        System.out.println(p.get(p.size()-3));
        System.out.println(p.get(p.size()-2));
        System.out.println(p.get(p.size()-1));
    }
    
    public BioSim(int casillas) {
        //Variables del Jframe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout());
        contentPane.setVisible(true);
        scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(contentPane);
        setBounds(0,0,800,600);
        
        
        //Inicio del tablero y rellena la matriz
        this.tablero = new Casilla[casillas][casillas];
        for (int i = 0; i < casillas; i++) {
            for (int j = 0; j < casillas; j++) {
                this.tablero[i][j] = new Casilla(20, 0);
            }
        }
        
    }
    @Override
     public void paint (Graphics g)
    {
        super.paint(g);
        //Las casillas son de 25x25 pixels
        for (int i = 0; i < num_casillas; i++) {
            for (int j = 0; j < num_casillas; j++) {
                g.drawImage(tablero[i][j].getImage(), (i*25)+15, (j*25)+15, null);
            }
        }
        
    }
      
}


//Imprime temperatura
        /*for (int i = 0; i < num_casillas; i++) {
            for (int j = 0; j < num_casillas; j++) {
                System.out.print(juego.tablero[i][j].getTemp()+".");
            }
            System.out.println("X");
        }*/
        
        /*System.out.println("Prueba de generación de genes");
        System.out.println("¿Cuántos genes quiere generar?");
        int num = key.nextInt();
        Gen[] adn = new Gen[num];
        for (int i = 1; i<num; i++) {
            adn[i] = new Gen();
            System.out.println(i +"- "+ adn[i]);
        }
        
        System.out.println("");
        System.out.println("Genes Mutados");
        Gen[] adn_muta = new Gen[num];
        for (int o = 1; o<num; o++) {
            adn_muta[o] = new Gen(adn[o], tasa_mutacion);
            System.out.println(o +"- "+ adn[o] + " m: "+ adn_muta[o]);
        }
        
        System.out.println("¿Qué genes quiere combinar?");
        int gen1 = key.nextInt();
        int gen2 = key.nextInt();
        Gen nuevo_gen = new Gen(adn[gen1], adn[gen2]);
        System.out.println("Gen combinado: "+ nuevo_gen);*/
        
        