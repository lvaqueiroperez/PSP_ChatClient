package psp_chatclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClienteHIlos extends Thread {

    public void run() {

        try {
            //ESPERAMOS POR MENSAJES DEL SERVER:
            //ACCEDEMOS AL MISMO SOCKET !!!
            while (true) {
                DataInputStream dis = new DataInputStream(UI_ChatCliente2.clienteSocket.getInputStream());

                String msg = dis.readUTF();
                UI_ChatCliente2.txtArea1.setText(UI_ChatCliente2.txtArea1.getText() + "\n" + msg);

            }

        } catch (IOException ex) {
            Logger.getLogger(ChatClienteHIlos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
