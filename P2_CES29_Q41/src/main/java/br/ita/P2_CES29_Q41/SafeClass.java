package br.ita.P2_CES29_Q41;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
* Java comment.
** Java comment
*/
public class SafeClass {
    /**
    * @param file
    ** file The file
    * @throws Exception
    ** Exception The Exception
    */
    public final void safeMethod(final String file) throws Exception {
        /*nome do arquivo com um padrão*/
        Pattern pattern = Pattern.compile("[^A-Za-z0-9._]");
        Matcher matcher = pattern.matcher(file);
        if (matcher.find()) {
            System.out.print("Arquivo com nome inválido!!\n");
            throw new Exception();
        }
        final Scanner console = new Scanner(System.in);
        System.out.print("Digite a operacao desejada para realizar "
                + "no arquivo <R para ler um arquivo, "
                + "W para escrever em um arquivo>?   ");
        final String opr = console.nextLine();
        //try e catch para garantir funcionamento correto
        if (opr.equals("R")) {
            String sCurrentLine;
            BufferedReader br = null;
            System.out.printf("%s \n", opr);
            try {
                FileReader arq = new FileReader(file);
                br = new BufferedReader(arq);
                while ((sCurrentLine = br.readLine()) != null) {
                    matcher = pattern.matcher(sCurrentLine);
                    if (!matcher.find()) {
                        System.out.println(sCurrentLine);
                    } else {
                        br.close();  // fechar o buffer
                        throw new Exception();
                    }
                }
                try {
                    if (br != null) {
                        br.close();
                    }
                    } catch (IOException ex) {
                    ex.printStackTrace();
                 }
               arq.close();
               } catch (IOException e) {
               System.out.print("Arquivo Inexistente"
                    + "para leitura!!\n");
               throw new Exception();
            }
          } else if (opr.equals("W")) {
            System.out.printf("%s \n", opr);
            BufferedWriter buffWrite = null;
            try {
                FileWriter arq = new FileWriter(file);
                buffWrite = new BufferedWriter(arq);
                System.out.println("Escreva algo: ");
                buffWrite.append(console.nextLine() + "\n");
                try {
                   if (buffWrite != null) {
                       buffWrite.close();
                   }
                 } catch (IOException ex) {
                     ex.printStackTrace();
                 }
            } catch (IOException e) {
                 throw new Exception();
            }
        } else {
            System.out.printf("%s \n", opr);
            System.out.print("Comando Inválido!!\n");
            console.close();
            throw new Exception();
        }
        console.close();
     }
}
