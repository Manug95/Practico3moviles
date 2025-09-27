package com.example.practico3.modelos;

import androidx.annotation.Nullable;

public class Producto implements Comparable<Producto> {
    private String codigo;
    private double precio;
    private String descripcion;

    public Producto(String codigo, double precio, String descripcion) {
        this.codigo = codigo;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Producto)) return false;
        Producto p = (Producto) obj;
        return this.codigo.equalsIgnoreCase(p.getCodigo());
        //return this.codigo.toLowerCase().equals(p.getCodigo().toLowerCase());
        //return  this.codigo.hashCode() == p.hashCode();
    }

    @Override
    public int hashCode() {
        return this.codigo.toLowerCase().hashCode();
    }


    @Override
    public int compareTo(Producto o) {
        return this.descripcion.compareToIgnoreCase(o.descripcion);
    }

    public static String validarPrecio(String precio) {
        try {
            double precioDouble = Double.parseDouble(precio);
            if (precioDouble < 0) return "*El precio no puede ser negativo\n";
        } catch (NumberFormatException e) {
            return "*El precio debe ser un número\n";
        }
        return "";
    }

    public static String validarCodigo(String codigo) {
        if (codigo == null) return "*El código no puede estar vacío\n";
        if (codigo.isEmpty()) return "*El código no puede estar vacío\n";
        return "";
    }

    public static String validarDescripcion(String descripcion) {
        if (descripcion == null) return "*La descripción no puede estar vacía\n";
        if (descripcion.isEmpty()) return "*La descripción no puede estar vacía\n";
        return "";
    }
}
