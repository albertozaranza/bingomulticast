package BC;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class BingoClient {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        int sorteado;
        int[] cartela = new int[11];
        for(int i = 0; i<=10;i++) {
            cartela[i]=0;
        }
        cartela[1] = 1;
        cartela[2] = 1; 
        cartela[3] = 1;
        int flag = 0;
        try {
            System.out.println("COMEÇOU");
            InetAddress group = InetAddress.getByName("230.0.0.0");
            MulticastSocket mult = new MulticastSocket(3456);
            mult.joinGroup(group);
            while(true) {
                flag = 0;
                byte[] buffer = new byte[2];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                mult.receive(packet);
                //System.out.println(new String(buffer));

                String entrada = new String(buffer);
                System.out.println("TAMANHO" + entrada.length());
                entrada = entrada.trim();
                sorteado = Integer.parseInt(entrada);



                System.out.println("Sorteado-- "+ sorteado);
                cartela[sorteado] = 0;
                for(int i = 0; i<=10; i++) {
                    if(cartela[i]==1) {
                        flag = 1;
                        break;
                    }
                }
                if(flag==0) {
                    System.out.println("BATI!!!");
                    break;

                }

            }
            mult.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

}