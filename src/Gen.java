
import static java.lang.Math.random;


/**
 *
 * @author Isidoro
 */
public class Gen {
    int a, b, c; //Gen formado por tres números

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    /**
     * Constructor sin argumentos para crear el gen inicial al azar
     */
    public Gen() {
        this.a = (int) Math.floor(Math.random()*4);
        this.b = (int) Math.floor(Math.random()*4);
        this.c = (int) Math.floor(Math.random()*4);
    }
    
    /**
     * Recombina a un gen nuevo a partir de dos
     * @param x
     * @param y
     */
    public Gen(Gen x, Gen y) {
        int aleatorio1 = (int) Math.floor(Math.random()*100);
        double par = aleatorio1 % 2;
        if (par == 0) {
            this.a = x.getA();
        } else if (par != 0) {
            this.a = y.getA();
        }
        
        int aleatorio2 = (int) Math.floor(Math.random()*100);
        par = aleatorio2 % 2;
        if (par == 0) {
            this.b = x.getB();
        } else if (par != 0) {
            this.b = y.getB();
        }
        
        int aleatorio3 = (int) Math.floor(Math.random()*100);
        par = aleatorio3 % 2;
        if (par == 0) {
            this.c = x.getC();
        } else if (par != 0) {
            this.c = y.getC();
        }
                
    }
    
    /**
     * Gen que se copia y puede mutar
     * @param x
     * @param mutacion 
     */
    public Gen(Gen x, int mutacion) {
        this.a = x.getA();
        this.b = x.getB();
        this.c = x.getC();
        int aleatorio = (int) Math.floor(Math.random()*100);
        if (aleatorio <= mutacion) {
            int aleatorio2 = (int) (Math.floor(Math.random()*3))+1;
            switch (aleatorio2) {
                case 0: this.a = (int) Math.floor(Math.random()*4);
                    break;
                case 1: this.b = (int) Math.floor(Math.random()*4);
                    break;
                case 2: this.c = (int) Math.floor(Math.random()*4);
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Gen(" + a + ", " + b + ", " + c + ')';
    }
    
}
