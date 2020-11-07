package com.baruCorp;

import java.util.Random;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Publi3 {

    public static void main(String[] args)
    {
         //Establece el ambiente o contexto
        try(ZContext context = new ZContext()){
            //Crea un socket tipo PUB
            ZMQ.Socket publisher= context.createSocket(SocketType.PUB);
            //Ata el socket a u  puerto
            publisher.bind("tcp://*:5559");
            publisher.bind("ipc://weather2");
            
            //Inicializa los numeros al azar
            Random srandom= new Random(System.currentTimeMillis());
            String[] mensajes={"Nuevo Album!","Sencillo Nuevo!","Concierto Pronto en tu ciudad","Mira la nueva entrevista del artista",
                                "Publicacion del arstista:Hola a todooos :D","Mercancia disponible"};

            
            while(!Thread.currentThread().isInterrupted()){
                //ZIPCODE
                int zipcode;
                zipcode=10000+srandom.nextInt(10000);
                

                //EL MENSAJE 
                String mensaje=mensajes[srandom.nextInt(mensajes.length)];

                //EL ARTISTA
                String artista="Bon Jovi";

                //Fecha y hora en tiempo real a todo momento
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();
                String date= dtf.format(now);  
                

                //Crea el mensaje en cadena de caracteres
                String update =String.format(
                    "%05d\n %s \n %s \n %s", zipcode,date,artista,mensaje
                    );

                //SE ENVIA EL MENSAJE A LO SUSCRIPTORES
                publisher.send(update,0);
            }
            
        }

        
    }
 
}
