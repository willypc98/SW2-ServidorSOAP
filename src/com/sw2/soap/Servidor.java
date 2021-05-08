package com.sw2.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "MarcaService")
public class Servidor {
@WebMethod(operationName = "hello")
public String hello(@WebParam(name = "name") String txt) {
return "Hello " + txt + " !";
}
}
