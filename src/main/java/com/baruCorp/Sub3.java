package com.baruCorp;


import com.baruCorpGUI.Suscriptor3GUI;
import com.baruCorpModel.Mensaje;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.zeromq.SocketType;
import org.zeromq.ZConfig;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

public class Sub3 {
    
    public static String[] argument;
    public static int confirmaSub1;
    public static ZMQ.Socket subscriptor;
    public static ZMQ.Socket subscriptor1;
    public static ZMQ.Socket subscriptor2;
    
    public static void main( String[] args )
    {
        argument=args;
            new Suscriptor3GUI().setVisible(true);
           
        
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
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    /*
                    PARA CONECTAR CON OTRAS MAQUINAS DES-COMENTAR LA OTRAS IP
                    */
                    subscriptor.connect("tcp://localhost:5557");
                    //subscriptor.connect("tcp://192.168.0.19:5557");
                    /*
                    **********************************************************
                    */
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    String filter = (args.length > 0) ? args[0] : "10004";

                    subscriptor.subscribe(filter.getBytes(ZMQ.CHARSET));
                    
                    
                    //subscriptor2.unsubscribe(filter.getBytes(ZMQ.CHARSET));
                    
                   while(!Thread.currentThread().isInterrupted()){
                    
                   
                    
                    String string= subscriptor.recvStr(0).trim();
                    Mensaje stringHecho1=recibirMensajes(string);
                    
                    
                    
                    mensajes.add(stringHecho1);
                    
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
