package BS;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class BingoServer {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        int flag = 0;
        int[] sorteados = new int[11];
        for(int i = 0; i<=10; i++) {
            sorteados[i]=0;
        }
        System.out.println("COMEÇOU NO SENDER");
        try {
            while(true) {
                flag = 0;
                InetAddress group = InetAddress.getByName("230.0.0.0");
                MulticastSocket multicastSock = new MulticastSocket();
                int sorteado = new Random().nextInt(11);
                System.out.println("Sorteando----> "+ sorteado + "=====" + sorteados[sorteado]);
                if(sorteados[sorteado]==1) {
                    System.out.println("JÁ FOI SORTEADO");
                    continue;
                }else {
                    sorteados[sorteado] = 1;
                    System.out.println("ENVIANDO===>" + sorteado);
                    String msg = Integer.toString(sorteado);
                    DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), group, 3456);
                    multicastSock.send(packet);
                    multicastSock.close();
                }
                for(int i = 0; i<=10; i++) {
                    if(sorteados[i]==0) {
                        flag = 1;
                    }
                }
                if(flag==0) {
                    System.out.println("Já sorteou todo mundo");
                    break;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Deu ruim");
        }
    }

}