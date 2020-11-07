package com.baruCorp;

import static com.baruCorp.App.argument;
import static com.baruCorp.App.confirmaSub1;
import static com.baruCorp.App.recibirMensajes;
import static com.baruCorp.App.subscriptor;
import static com.baruCorp.App.subscriptor1;
import static com.baruCorp.App.subscriptor2;
import com.baruCorpGUI.Suscriptor2GUI;
import com.baruCorpModel.Mensaje;
import java.util.ArrayList;
//import com.baruCorpGUI.SuscriptorGUI;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.zeromq.SocketType;
import org.zeromq.ZConfig;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

public class Sub2 {

    public static String[] argument;
    public static int confirmaSub1;
    public static ZMQ.Socket subscriptor;
    public static ZMQ.Socket subscriptor1;
    public static ZMQ.Socket subscriptor2;
    public static void main( String[] args )
    {
            argument=args;
            new Suscriptor2GUI().setVisible(true);
        
    }
    
    /*
    En este metodo se suscribe y se conectan varios sockets a diferentes
    publicadores para recibir los mensajes y luego retornarlos a la
    interfaz grafica
    */

    public static ArrayList<Mensaje> artistaMensajes(String numArtista) {
       String[] args=argument;
        ArrayList<Mensaje> mensajes=new ArrayList<Mensaje>();
        
        try(ZContext context = new ZContext()){
            
            
            
            switch(numArtista){
                case "1":
                    
                    if(confirmaSub1==1){
                        
                    
                    subscriptor= context.createSocket(SocketType.SUB);
                    subscriptor1= context.createSocket(SocketType.SUB);
                    
                    
                    
                    
                    
                    
                    
                    
                    /*
                    PARA CONECTAR CON OTRAS MAQUINAS DES-COMENTAR LA OTRAS IP
                    */
                    
                     
                    subscriptor.connect("tcp://localhost:5557");
                    subscriptor1.connect("tcp://localhost:5558");
                    //subscriptor.connect("tcp://192.168.0.19:5557");
                    //subscriptor1.connect("tcp://192.168.0.19:5558");
                    
                    /*
                    **********************************************************
                    */
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    String filter = (args.length > 0) ? args[0] : "10003";

                    subscriptor.subscribe(filter.getBytes(ZMQ.CHARSET));
                    subscriptor1.subscribe(filter.getBytes(ZMQ.CHARSET));
                    
                    //subscriptor2.unsubscribe(filter.getBytes(ZMQ.CHARSET));
                    
                   while(!Thread.currentThread().isInterrupted()){
                    
                   
                    
                    String string= subscriptor.recvStr(0).trim();
                    Mensaje stringHecho1=recibirMensajes(string);
                    
                    String string2= subscriptor1.recvStr(0).trim();
                    Mensaje stringHecho2=recibirMensajes(string2);
                    
                    
                    
                    mensajes.add(stringHecho1);
                    mensajes.add(stringHecho2);
                    
                    return mensajes;
                }
                    }
                    return mensajes;
                    /*
                while(!Thread.currentThread().isInterrupted()){
                    
                   
                    System.out.println("f");
                    String string= subscriptor.recvStr(0).trim();
                    
                    System.out.println(string); 
                }
                    */
                
                //break;
                case "2":
                subscriptor.connect("tcp://localhost:5558");
                break;
                case "3":
                subscriptor.connect("tcp://localhost:5559");
                break;
                case "4":
                    confirmaSub1=1;
                //recibirMensajes(subscriptor,subscriptor1,subscriptor2);
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
