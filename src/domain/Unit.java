package domain;

/**
 * Interfaz que define los comportamientos comunes entre las clases Store y Robot.
 * 
 * Ambos comparten características como posición, color, tipo y manejo de tenges.
 * 
 * @author (Diego Ortiz - Julio Mayorquin)
 * @version (1.0)
 */
public interface Unit {
    
    // --- Métodos relacionados con tipo ---
    String getType();
    void changeType(String newType);
    
    // --- Métodos relacionados con posición ---
    int getXPosition();
    int getYPosition();
    int getPosition();
    void changePositionX(int newXPosition);
    void changePositionY(int newYPosition);
    void moveTo(int x, int y);
    
    // --- Métodos relacionados con color ---
    String getColor();
    void changeColor(String color);
    
    // --- Métodos de visibilidad ---
    void makeVisible();
    void makeInvisible();
    
    // --- Métodos relacionados con tenges ---
    int getTenges();
    void setTenges(int tenges);
}
