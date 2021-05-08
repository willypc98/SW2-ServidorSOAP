package pojos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "marca")
@XmlAccessorType(XmlAccessType.FIELD)
public class Marca implements Serializable{


    private static final long serialVersionUID = -8956150563795146209L;
    @XmlElement
    private String Nombre;

    //@XmlElement(name = "producto")
    @XmlElementWrapper(name = "productos")
    private ArrayList<Producto> productos;

    public Marca() {
        super();
        productos = new ArrayList<>();
    }
    
    public Producto getProducto(String nombreProducto) {
    	Producto salida = null;
		for(Producto producto: productos) {
			if(producto.getNombre().equals(nombreProducto)) {
				salida = producto;
				break;
			}
		}
		return salida;
    }
    public String getNombre() {
        return Nombre;
    }


    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto e) {
        if(!productos.contains(e))
            productos.add(e);
    }

    public void removeProducto(Producto e) {
        productos.remove(e);
    }
}