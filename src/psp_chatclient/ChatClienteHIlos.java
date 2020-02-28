package psp_chatclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClienteHIlos extends Thread {

    public void run() {

        try {
            //ESPERAMOS POR MENSAJES DEL SERVER:
            //ACCEDEMOS CON EL MISMO SOCKET DE LA CLASE ANTERIOR !!!
            while (true) {

                DataInputStream dis = new DataInputStream(UI_ChatCliente2.clienteSocket.getInputStream());

                String msg = dis.readUTF();
                //CAMBIAMOS EL ESTADO SI EL SERVER NOS DICE QUE ESTÁ LLENO
                if (msg.equals("SERVER LLENO")) {
                    UI_ChatCliente2.estado = 0;
                }

                UI_ChatCliente2.txtArea1.setText(UI_ChatCliente2.txtArea1.getText() + "\n" + msg);

            }

        } catch (IOException ex) {
            //UTILIZAMOS ESTA EXCEPCIÓN PARA SABER SI EL SERVER SE HA DESCONECTADO
            UI_ChatCliente2.txtArea1.setText(UI_ChatCliente2.txtArea1.getText() + "\n***** EL SERVER SE HA DESCONECTADO, CERRANDO PROGRAMA *****");
            try {
                sleep(3000);
            } catch (InterruptedException ex1) {
                Logger.getLogger(ChatClienteHIlos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.exit(0);
        }

    }

}
