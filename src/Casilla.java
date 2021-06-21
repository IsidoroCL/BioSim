import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Isidoro
 */
public class Casilla {
    public int temp;
    public int comida;
    public int tipo;
    public BufferedImage image;
    /* tipo 
    0 llanura
    1 bosque
    2 colinas
    3 montañas
    4 agua    
    */
    
    public void setImage(BufferedImage i) {
        this.image = i;
    } 
    
    public BufferedImage getImage() {
        return image;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getComida() {
        return comida;
    }

    public void setComida(int comida) {
        this.comida = comida;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) throws IOException {
        this.tipo = tipo;
        switch(this.tipo) {
            case 0: this.image = ImageIO.read(new File("llanura.png"));
                break;
            case 1: this.image = ImageIO.read(new File("bosque.png"));
                break;
            case 2: this.image = ImageIO.read(new File("colinas.png"));
                break;
            case 3: this.image = ImageIO.read(new File("monta.png"));
                break;
            case 4: this.image = ImageIO.read(new File("mar.png"));
                break;
        }
    }
    
    
    /** Constructor vacio para la primera casilla
     * 
     */
    public Casilla() {
        this.temp = (int) Math.floor(Math.random()*30);
        this.comida = (int) Math.floor(Math.random()*4);
        this.tipo = (int) Math.floor(Math.random()*5);
        try {
            this.image = ImageIO.read(new File("llanura.png"));
        } catch (IOException ex) {
            Logger.getLogger(Casilla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Constructor que tiene en cuenta las casillas adyacentes
     * Casillas adyacentes
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public Casilla(Casilla a, Casilla b, Casilla c, Casilla d) {
        this.temp = ((a.getTemp()+b.getTemp()+c.getTemp()+d.getTemp())/4);
        this.tipo = ((a.getTipo()+b.getTipo()+c.getTipo()+d.getTipo())/4);
        this.comida = (int) Math.floor(Math.random()*4);
        int var_temp = (int) Math.floor(Math.random()*2);
        if (var_temp == 0) {this.temp -= (int) Math.floor(Math.random()*3);}
        if (var_temp == 1) {this.temp += (int) Math.floor(Math.random()*3);}
        int var_tipo = (int) Math.floor(Math.random()*2);
        if (var_temp == 1) {this.tipo = (int) Math.floor(Math.random()*5);}
    }
    
    public Casilla(Casilla a, Casilla b, Casilla c) {
        this.temp = ((a.getTemp()+b.getTemp()+c.getTemp())/3);
        this.tipo = ((a.getTipo()+b.getTipo()+c.getTipo())/3);
        this.comida = (int) Math.floor(Math.random()*4);
        int var_temp = (int) Math.floor(Math.random()*2);
        if (var_temp == 0) {this.temp -= (int) Math.floor(Math.random()*3);}
        if (var_temp == 1) {this.temp += (int) Math.floor(Math.random()*3);}
        int var_tipo = (int) Math.floor(Math.random()*2);
        if (var_temp == 1) {this.tipo = (int) Math.floor(Math.random()*5);}
    }
    
    public Casilla(Casilla a, Casilla b) {
        this.temp = ((a.getTemp()+b.getTemp())/2);
        this.tipo = ((a.getTipo()+b.getTipo())/2);
        this.comida = (int) Math.floor(Math.random()*4);
        int var_temp = (int) Math.floor(Math.random()*2);
        if (var_temp == 0) {this.temp -= (int) Math.floor(Math.random()*3);}
        if (var_temp == 1) {this.temp += (int) Math.floor(Math.random()*3);}
        int var_tipo = (int) Math.floor(Math.random()*2);
        if (var_temp == 1) {this.tipo = (int) Math.floor(Math.random()*5);}
    }
    
    public Casilla(Casilla a) {
        this.temp = a.getTemp();
        this.tipo = a.getTipo();
        this.comida = (int) Math.floor(Math.random()*4);
        int var_temp = (int) Math.floor(Math.random()*2);
        if (var_temp == 0) {this.temp -= (int) Math.floor(Math.random()*3);}
        if (var_temp == 1) {this.temp += (int) Math.floor(Math.random()*3);}
        int var_tipo = (int) Math.floor(Math.random()*2);
        if (var_temp == 1) {this.tipo = (int) Math.floor(Math.random()*5);}
    }
    
    public Casilla(int temp, int tipo) {
        this.temp = temp;
        this.tipo = tipo;
        this.comida = (int) Math.floor(Math.random()*4);
        try {
            this.image = ImageIO.read(new File("llanura.png"));
        } catch (IOException ex) {
            Logger.getLogger(Casilla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        String tipo_ascii = ".";
        switch (tipo) {
            case 0: tipo_ascii = "-";
                break;
            case 1: tipo_ascii = "+";
                break;
            case 2: tipo_ascii = "m";
                break;
            case 3: tipo_ascii = "M";
                break;
            case 4: tipo_ascii = "o";
                break;
        }
        return tipo_ascii;
    }
    
    
}
