package com.company;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends javax.swing.JFrame {


    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    public Server() {
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */


        String msgin = "";
        try{

            ss = new ServerSocket(1201); //server starts at 1201 port number
            s = ss.accept();

            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while(!msgin.equals("exit")){
                msgin = din.readUTF();
                System.out.println(msgin);
               // msg_area.setText(msg_area.getText().trim()+"\n Client:\t"+msgin);
            }


        }catch(Exception e){

        }

    }

}
