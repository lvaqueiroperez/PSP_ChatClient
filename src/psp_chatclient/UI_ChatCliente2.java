/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp_chatclient;

//MANDAREMOS LOS MENSAJES DE ESTA CLASE A LA OTRA???
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static psp_chatclient.UI_ChatCliente1.ip;
import static psp_chatclient.UI_ChatCliente1.puerto;

public class UI_ChatCliente2 extends javax.swing.JFrame {

    //VARIABLES
    //La clase Socket nos deja hacer variables socket public static 
    //donde podemos instanciar un objeto DESPUÉS
    public static Socket clienteSocket;

    /**
     * Creates new form UI_Cliente2
     */
    public UI_ChatCliente2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblInfo1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea1 = new javax.swing.JTextArea();
        txtMensaje = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnConectarse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblInfo1.setText("Conectado a...");

        txtArea1.setColumns(20);
        txtArea1.setRows(5);
        jScrollPane1.setViewportView(txtArea1);

        btnEnviar.setText("ENVIAR");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        jLabel1.setText("Client Chat");

        btnConectarse.setText("CONECTARSE");
        btnConectarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 351, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(btnEnviar)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnConectarse)
                    .addComponent(lblInfo1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnConectarse))
                .addGap(19, 19, 19)
                .addComponent(lblInfo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviar)
                    .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarseActionPerformed
        
        try {
            
            System.out.println("***** CREANDO SOCKET CLIENTE *****");
            
            clienteSocket = new Socket();
            
            System.out.println("***** ESTABLECIENDO CONEXIÓN CON EL SERVIDOR *****");
            //REALIZAMOS EL BIND,RECOGEMOS LA IP Y PUERTO DEL SERVER DE LA OTRA CLASE
            InetSocketAddress addr = new InetSocketAddress(UI_ChatCliente1.ip, UI_ChatCliente1.puerto);
            //NOS CONECTAMOS AL SERVER Y ASÍ PODREMOS USAR ESTA VARIABLE EN OTRAS CLASES ESTANDO YA CONECTADA Y OPERATIVA
            clienteSocket.connect(addr);

            //ENVIAMOS EL NICKNAME,IP Y PUERTO AL SERVER
            DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());
            dos.writeUTF(UI_ChatCliente1.nickname);
            dos.writeUTF(UI_ChatCliente1.ip);
            //LO ENVIAMOS COMO STRING DE MOMENTO
            dos.writeUTF(Integer.toString(UI_ChatCliente1.puerto));

            //DESDE UN HILO NOS PONEMOS A LA ESPERA DE MENSAJES DESDE EL SERVER
            ChatClienteHIlos h = new ChatClienteHIlos();
            h.start();
            
        } catch (IOException ex) {
            Logger.getLogger(UI_ChatCliente1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnConectarseActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        
        try {
            
            if (txtMensaje.getText().equals("/bye")) {
                
                System.out.println("SALIENDO DEL CLIENTE");
                //PARA SALIR DEL PROGRAMA:

                System.exit(0);
                
            } else {
                //ENVIAMOS MENSAJES AL SERVIDOR JUNTO AL NICKNAME DEL CLIENTE
                DataOutputStream os = new DataOutputStream(clienteSocket.getOutputStream());
                
                os.writeUTF(UI_ChatCliente1.nickname + ": " + txtMensaje.getText());
                //LIMPIAMOS LA CELDA
                txtMensaje.setText("");
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(UI_ChatCliente2.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_btnEnviarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI_ChatCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_ChatCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_ChatCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_ChatCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_ChatCliente2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectarse;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInfo1;
    public static javax.swing.JTextArea txtArea1;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
}
