package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends javax.swing.JFrame {

    static Socket s;
    static ObjectInputStream oin;
    static ObjectOutputStream oout;

    static DataInputStream din;
    static DataOutputStream dout;


    public Client() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        msg_area = new JPanel();
        msg_area.setSize(800, 600);
        msg_text = new JTextField();
        msg_send = new JButton();

        int[][] arr = new int[15][5];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = (int) Math.round((Math.random() * 3));
            }
        }



        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client");
        setName("Client"); // NOI18N

        //msg_area.setColumns(20);
        //msg_area.setRows(5);
        jScrollPane1.setViewportView(msg_area);

        msg_send.setLabel("Send");
        msg_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(msg_send, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)))
                                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(msg_send, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void msg_sendActionPerformed(java.awt.event.ActionEvent evt) {

        try{
            String msgout = "";
            msgout = msg_text.getText().trim();
            dout.writeUTF(msgout);
        }
        catch(Exception e){

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });

        try{
            ImageIcon hero = new ImageIcon("bodybuilder.png");
            ImageIcon enemy = new ImageIcon("target.png");
            ImageIcon wall = new ImageIcon("brickwall.png");

            s = new Socket("127.0.0.1",1201);

            oin = new ObjectInputStream(s.getInputStream());
            oout = new ObjectOutputStream(s.getOutputStream());

            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            int[][] msgin = new int[15][5];

            while(msgin!=null){
                msgin = (int[][]) oin.readObject();
                //msg_area.setText(msg_area.getText().trim()+"\n Server:\t"+msgin);
                msg_area.setLayout(new GridLayout(15,5));
                for(int i = 0; i < 15; i++){
                    for(int j = 0; j < 5; j++){
                        if(msgin[i][j] == 1)
                            msg_area.add(new JLabel(hero));
                        if(msgin[i][j] == 2)
                            msg_area.add(new JLabel(enemy));
                        if(msgin[i][j] == 3)
                            msg_area.add(new JLabel(wall));
                        if(msgin[i][j] == 0)
                            msg_area.add(new JLabel());
                    }
                }
            }
        }catch(Exception e){

        }
    }

    // Variables declaration - do not modify
    private JScrollPane jScrollPane1;
    private static JPanel msg_area;
    private JButton msg_send;
    private JTextField msg_text;
    // End of variables declaration
}
