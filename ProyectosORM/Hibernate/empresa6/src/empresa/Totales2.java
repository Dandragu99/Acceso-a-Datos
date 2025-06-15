/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

/**
 *
 * @author Damiansu
 */
public class Totales2 {
    private Byte numero;
    private Long cuenta;
    private Double media;
    private String nombre;

    public Totales2(Byte numero, Long cuenta, Double media, String nombre) {
        this.numero = numero;
        this.cuenta = cuenta;
        this.media = media;
        this.nombre = nombre;
    }

    public Byte getNumero() {
        return numero;
    }

    public void setNumero(Byte numero) {
        this.numero = numero;
    }

    public Long getCuenta() {
        return cuenta;
    }

    public void setCuenta(Long cuenta) {
        this.cuenta = cuenta;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
