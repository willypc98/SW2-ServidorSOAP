package com.sw2.soap;

import java.io.File;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pojos.Marca;
import pojos.Producto;


@WebService(serviceName = "MarcaService")
public class Servidor {


Marca marca = new Marca();
	
/*
    private String sCarpAct = System.getProperty("user.dir");
    
    private String ruta2 = System.getProperty("user.home");
    
	private File carpeta = new File(sCarpAct);
	private String ruta = carpeta.getPath();
	*/
	private String directorio= null;
	private String directorioCliente= null;
	
	@WebMethod(operationName = "hello")
	public String hello(@WebParam(name = "name") String txt) {
	return "Hello " + txt + " !";
	}
	
	private void crearCarpetaDeTrabajo(){
		
		String directorio = System.getProperty("user.home");
		String directorioCliente = System.getProperty("user.home");
		File carpeta = new File(directorio + "/Servidor");
		carpeta.mkdir();
		File carpeta2 = new File(directorio + "/Cliente");
		carpeta2.mkdir();
		this.directorio=carpeta.getAbsolutePath();
		this.directorioCliente=carpeta2.getAbsolutePath();
	}
	
	private Marca marshallerMarca(String nombreMarca, Producto producto) throws JAXBException {
		
		Marca marca = new Marca();
		marca.setNombre(nombreMarca);
		
		if(producto==null) {
			
		}
		else {	
			marca.addProducto(producto);
		}	
		//Hacemos el Marshalling
		JAXBContext context = JAXBContext.newInstance(Marca.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		File XMLfile = new File(directorio + "/"+ nombreMarca + ".xml");
		m.marshal(marca, XMLfile);
		return marca;
	}
	
private Marca unmarshallerMarca(String nombreMarca) throws JAXBException {
		
		Marca marca = null;
		
		//Hacemos el Unmarshalling
		JAXBContext context = JAXBContext.newInstance(Marca.class);
		Unmarshaller u = context.createUnmarshaller();
		
		File XMLfile = new File( directorio + "/" + nombreMarca + ".xml");

		marca = (Marca) u.unmarshal(XMLfile);
		return marca;
	}
	
	
	@WebMethod(operationName = "crearMarca")
	public Marca crearMarca(@WebParam(name = "nombreMarca") String nombreMarca) throws JAXBException {
		
		if(directorio==null)
		{
			crearCarpetaDeTrabajo();
		}
			
		Producto producto = null;
		
		
		return marshallerMarca(nombreMarca,producto);
		
	}
	
	@WebMethod(operationName = "crearProducto")
	public Marca crearProducto(@WebParam(name = "nombreMarca") String nombreMarca,@WebParam(name = "nombreProducto") Producto producto) throws JAXBException{
		
		if(directorio==null)
		{
			crearCarpetaDeTrabajo();
		}
		return marshallerMarca(nombreMarca,producto);
	}
	
	
	@WebMethod(operationName = "devolverMarca")
	public Marca devolverMarca(String nombreMarca) throws JAXBException {
		
		if(directorio==null)
		{
			crearCarpetaDeTrabajo();
		}
		
		return unmarshallerMarca(nombreMarca);
		
	}
	
	@WebMethod(operationName = "devolverProducto")
	public Producto devolverProducto(@WebParam(name = "nombreMarca") String nombreMarca,@WebParam(name = "nombreProducto") String nombreProducto) throws JAXBException{
		
		if(directorio==null)
		{
			crearCarpetaDeTrabajo();
		}
		Marca marca= devolverMarca(nombreMarca);
		Producto producto= marca.getProducto(nombreProducto);
		return producto;
	}
	
	@WebMethod(operationName = "exportarMarca")
	public void exportarMarca(@WebParam(name = "nombreMarca") String nombreMarca) throws JAXBException {
		
		if(directorio==null)
		{
			crearCarpetaDeTrabajo();
		}
		
		Marca marca = devolverMarca(nombreMarca);
       
		
        JAXBContext context = JAXBContext.newInstance(Marca.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE); 
        File XMLfile = new File(directorioCliente + "/" + nombreMarca + ".xml");
        m.marshal(marca, XMLfile);
        
    }
	
	@WebMethod(operationName = "exportarProducto")
	public void exportarProducto(@WebParam(name = "nombreMarca") String nombreMarca, @WebParam(name = "nombreProducto") String nombreProducto) throws JAXBException {
		
		if(directorio==null)
		{
			crearCarpetaDeTrabajo();
		}
		
		Producto producto = devolverProducto(nombreMarca,nombreProducto);
       
        JAXBContext context = JAXBContext.newInstance(Producto.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE); 
        File XMLfile = new File(directorioCliente + "/" + nombreProducto + ".xml");
        m.marshal(producto, XMLfile);
        
    }
	
	@WebMethod(operationName = "importarMarca")
    public void importarMarca(@WebParam(name = "nombreMarca") String nombreMarca) throws JAXBException {
    	

		if(directorio==null)
		{
			crearCarpetaDeTrabajo();
		}
		
		
		JAXBContext context = JAXBContext.newInstance(Marca.class);
    	Unmarshaller u = context.createUnmarshaller();
    	File fichero = new File(directorioCliente + "/" + nombreMarca + ".xml" );
        Marca marca= (Marca) u.unmarshal(fichero);
        
    	JAXBContext context2 = JAXBContext.newInstance(Marca.class); 
        Marshaller m = context2.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        
        File XMLfile = new File(directorio + "/" + nombreMarca + ".xml" );
        m.marshal(marca, XMLfile);
    }
	
	@WebMethod(operationName = "importarProducto")
    public void importarProducto(@WebParam(name = "nombreProducto") String nombreProducto) throws JAXBException {
    	

		if(directorio==null)
		{
			crearCarpetaDeTrabajo();
		}
		
		
		JAXBContext context = JAXBContext.newInstance(Producto.class);
    	Unmarshaller u = context.createUnmarshaller();
    	File fichero = new File(directorioCliente + "/" + nombreProducto + ".xml" );
        Producto producto= (Producto) u.unmarshal(fichero);
        Marca marca = new Marca();
        marca.addProducto(producto);
        
    	JAXBContext context2 = JAXBContext.newInstance(Marca.class); 
        Marshaller m = context2.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        
        File XMLfile = new File(directorio + "/" + nombreProducto + ".xml" );
        m.marshal(marca, XMLfile);
    }
}
