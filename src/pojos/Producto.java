package pojos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "producto")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "nombre", "tipo", "precio" , "descripcion"})

public class Producto implements Serializable{


    private static final long serialVersionUID = -2924448807956380505L;
    @XmlTransient
    private int id;
    @XmlAttribute
    private String fechaDePublicacion;
    @XmlElement
    private String nombre;
    @XmlElement
    private String tipo;
    @XmlElement
    private String descripcion;
    @XmlElement
    private double precio;


    @XmlTransient
    private Marca marca;

    public Producto() {
        super();
    }

    public Producto(int id,String fechaDePublicacion, String nombre, String tipo, String descripcion, double precio) {
        super();
        this.id = id;
        this.fechaDePublicacion = fechaDePublicacion;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion= descripcion;
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        if (id != other.id)
            return false;
        return true;
    }


    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public void setFechaDePublicacion(String fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", fechaDePublicacion=" + fechaDePublicacion
                + ", nombre=" + nombre + ", tipo=" + tipo +", descripcion="+ descripcion
                + ", precio=" + precio + "]";
    }


}