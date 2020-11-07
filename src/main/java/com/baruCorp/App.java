package com.baruCorp;

import com.baruCorpGUI.SuscriptorGUI;
import com.baruCorpModel.Mensaje;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.zeromq.SocketType;
import org.zeromq.ZConfig;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static String[] argument;
    public static int confirmaSub1;
    public static ZMQ.Socket subscriptor;
    public static ZMQ.Socket subscriptor1;
    public static ZMQ.Socket subscriptor2;
    public static void main( String[] args )
    {
        argument=args;
        new SuscriptorGUI().setVisible(true);
        
       
    }
    
    /*
    En este metodo se suscribe y se conectan varios sockets a diferentes
    publicadores para recibir los mensajes y luego retornarlos a la
    interfaz grafica
    */

    public static ArrayList <Mensaje> artistaMensajes(String numArtista) {
        String[] args=argument;
        ArrayList<Mensaje> mensajes=new ArrayList<Mensaje>();
        
        try(ZContext context = new ZContext()){
            
            
            
            switch(numArtista){
                case "1":
                    
                    if(confirmaSub1==1){
                        
                    
                    subscriptor= context.createSocket(SocketType.SUB);
                    subscriptor1= context.createSocket(SocketType.SUB);
                    subscriptor2= context.createSocket(SocketType.SUB);
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    /*
                    PARA CONECTAR CON OTRAS MAQUINAS DES-COMENTAR LA OTRAS IP
                    */
                    
                    subscriptor.connect("tcp://localhost:5557");
                    subscriptor1.connect("tcp://localhost:5558");
                    subscriptor2.connect("tcp://localhost:5559");
                    //subscriptor.connect("tcp://192.168.0.19:5557");
                    //subscriptor1.connect("tcp://192.168.0.19:5558");
                    //subscriptor2.connect("tcp://192.168.0.19:5559");
                    
                    /*
                    **********************************************************
                    */
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    String filter = (args.length > 0) ? args[0] : "10002";

                    subscriptor.subscribe(filter.getBytes(ZMQ.CHARSET));
                    subscriptor1.subscribe(filter.getBytes(ZMQ.CHARSET));
                    subscriptor2.subscribe(filter.getBytes(ZMQ.CHARSET));
                    //subscriptor2.unsubscribe(filter.getBytes(ZMQ.CHARSET));
                    
                   while(!Thread.currentThread().isInterrupted()){
                    
                   
                    
                    String string= subscriptor.recvStr(0).trim();
                    Mensaje stringHecho1=recibirMensajes(string);
                    
                    String string2= subscriptor1.recvStr(0).trim();
                    Mensaje stringHecho2=recibirMensajes(string2);
                    
                    String string3= subscriptor2.recvStr(0).trim();
                    Mensaje stringHecho3=recibirMensajes(string3);
                    
                    
                    mensajes.add(stringHecho1);
                    mensajes.add(stringHecho2);
                    mensajes.add(stringHecho3);
                    return mensajes;
                }
                    }
                    return mensajes;
                
                
                //break;
                
                case "4":
                    confirmaSub1=1;
                
                break;
                default:
                subscriptor.connect("tcp://localhost:5556");
            }
            
            
           return mensajes;
        } 
         catch (Exception e) {
            System.out.println(e);
            return mensajes;
        }
            
    }
    
    
    /*
    Este metodo separa cada campo del String enviado y luego crea y retorna un objeto
    de la clase mensaje
    */
    
    public static Mensaje recibirMensajes(String string){
        
        StringTokenizer sscanf=new StringTokenizer(string,"\n");
                
                int zipcode=Integer.valueOf(sscanf.nextToken());
                String fecha=sscanf.nextToken();
                String artista = sscanf.nextToken();
                String mensaje=sscanf.nextToken();
                
                
                Mensaje m=new Mensaje(zipcode,fecha,artista,mensaje);
        
        return m;
    }

 
}
