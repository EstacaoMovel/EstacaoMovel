package pt.isec.gps.tp.utils;

import pt.isec.gps.tp.modelo.dados.Notificacao;
import pt.isec.gps.tp.modelo.dados.NotificacaoToWrite;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class WriteArrObject {

    public static void writeAnObject(ArrayList<NotificacaoToWrite> notificacoes) {
        try {
            new FileOutputStream("notificacoes.txt").close();

            FileOutputStream writeData = new FileOutputStream("notificacoes.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(notificacoes);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            return;
        }
    }

    public static ArrayList<NotificacaoToWrite> readData() {
        try{

            FileInputStream readData = new FileInputStream("notificacoes.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            ArrayList<NotificacaoToWrite> people2 = (ArrayList<NotificacaoToWrite>) readStream.readObject();
            readStream.close();
            System.out.println(people2.toString());
            return people2;
        }catch (Exception e) {
            return null;

        }
    }
}
