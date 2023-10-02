package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    //nome o indirizzo IP del server
    String nomeServer = "127.0.0.1";
    //porta del server
    int portaServer = 6420;
    //istanzio un socket
    Socket socket;
    //input da tastiera
    BufferedReader reader;
    //stinga che inserisce l'utente
    String stringaInserita;
    //stringa ricevuta dal server come risposta
    String stringaRicevutaDalServer;
    //instaznio un canale di comunicazione per mandare i dati al socket
    DataOutputStream outVersoServer;
    //instazio un canale in cui prendo in input ciò che viene dal server
    BufferedReader inDalServer;

    public Socket connetti(){
        System.out.println("Client in esecuzione");
        try{
            //creo un bufferedReader per prendere in input una stringa
            reader = new BufferedReader(new InputStreamReader((System.in)));
            //creo un socket con il nome del server e la porta del server
            socket = new Socket(nomeServer, portaServer);

            //creo il canale di comunicazione per mandare dati al server
            outVersoServer = new DataOutputStream(socket.getOutputStream());
            //creo un canale in cui prendo in input i dati dal server
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

            //mando al server sotto forma di byte la stringa ricevuta dall'utente
            outVersoServer.writeBytes(stringaInserita + "\n");
            //prendo in input la stringa di risposta dal server
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
