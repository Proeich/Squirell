package PROG2_SS2018.Aufgabe2.Utils.Engine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandScanner {

    public CommandScanner(){}

    public String read(){

        System.out.println("Bitte Geben sie einen Befehl ein: " + '\n'
        + "Help für Liste von Befehlen!");

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

            String k = sc.next();
            sb.append(k).append(" ");
            k = sc.next();
            sb.append(k).append(" ");

        return sb.toString();

    }

    public String[] readN(){
        PrintStream ps = new PrintStream(System.out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
           String k = br.readLine();
           String[] p = k.split(" ");
            return p;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getCommandN(String[] strings){
        return strings[0];
    }

    public String[] getArgsN(String[] strings){

        String[] buf = new String[strings.length-1];

        for(int i = 1;i>=strings.length-1;i++){
            buf[i-1] = strings[i];
        }
        return buf;
    }

    public String getCommand(String string){
        String[] n = string.split(" ");
        return n[0];
    }

    public String getArgs(String string){
        StringBuilder sb = new StringBuilder();

        int index = 0;
        for(String k : string.split(" ")){
            if(index == 0){
                index++;
                continue;
            }
            sb.append(k).append(" ");
        }

        return sb.toString();
    }


}
