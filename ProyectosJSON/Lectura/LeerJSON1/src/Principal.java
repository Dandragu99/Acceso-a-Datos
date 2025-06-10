
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dragu
 */
public class Principal {
    public static void main(String[] args) {
        try {
            // Para leer un archivo json como texto
            String contenido = new String(Files.readAllBytes(Paths.get("datos.json")));
            
            
            
            
            
            
        } catch (Exception e) {
        }
        
        
        
    }
}
