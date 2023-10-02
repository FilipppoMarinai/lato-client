package com.example;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    String nomeServer = "127.0.0.1";
    int portaServer = 6420;
    Socket socket;
    BufferedReader reader;
    String stringaInserita;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti(){
        System.out.println("Client in esecuzione");
        try{
            reader = new BufferedReader(new InputStreamReader((System.in)));
            socket = new Socket(nomeServer, portaServer);

            outVersoServer = new DataOutputStream(socket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
        }

        return socket;
    }

    public void comunica(){
        try{
            System.out.println("Inserisci la stringa da trasmettere al server:\n");
            stringaInserita = reader.readLine();
            System.out.println("invio la stringa al server e attendo");

            outVersoServer.writeBytes(stringaInserita + "\n");
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("risposta dal server " + "\n" + stringaRicevutaDalServer);
            System.out.println("chiudo la connessione");
            socket.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
        }
    }
}
