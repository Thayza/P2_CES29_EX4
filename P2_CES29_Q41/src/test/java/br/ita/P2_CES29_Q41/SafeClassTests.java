package br.ita.P2_CES29_Q41;

import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Before;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;

/**
* Java comment.
** Java comment
*/
public class SafeClassTests {
    /**
    * Java comment.
    ** Java comment
    */
    private SafeClass safety = null;
    /**
    * Java comment.
    ** Java comment
    */
    private BufferedWriter bw = null;
    /**
    * @param filename
    ** filename The filename
    * @param data
    ** data The data
    */
    private void criarArquivo(final String filename, final String data) {
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            bw.write(data);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    /**
    * @param filename
    ** filename The filename
    */
    private void deletarArquivoCriado(final String filename) {
        try {
            File file = new File(filename);
            file.delete();
         } catch (Exception e) {
            e.printStackTrace();
         }
    }
    /**
    * @param input
    ** input The input
    */
    private void entradaConsole(final String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
    /**
    * Java comment.
    ** Java comment
    */
     @Before
    public void setup() {
       safety = new SafeClass();
    }
    /**
    * Java comment.
    ** Java comment
    */
    @Test
    public void arquivoComNomeInavlido() {
        System.out.print("Teste: Nome inválido\n");
        try {
            safety.safeMethod("arquivo/.txt");  // caractere inválido /
            fail("Esperado: Exception.class");
        } catch (Exception e) { }
    }
    /**
    * Java comment.
    ** Java comment
    */
    @Test
    public void comandoInvalido() {
        System.out.print("\nTeste: Comando inválido\n");
        criarArquivo("entrada.txt", "qualquercoisa");
        entradaConsole("A");
        try {
            safety.safeMethod("entrada.txt");
            fail("Esperado: Exception.class");
        } catch (Exception e) { }
        deletarArquivoCriado("entrada.txt");
    }
    /**
    * Java comment.
    ** Java comment
    */
    @Test
    public void lerAqruivoNaoExistenteComandoR() {
        deletarArquivoCriado("entrada.txt");
        System.out.print("\nTest: Arquivo Inexistente\n");
        entradaConsole("R");
        try {
            safety.safeMethod("entrada.txt");
            fail("Esperado: Exception.class");
        } catch (Exception e) { }
    }
    /**
    * Java comment.
    ** Java comment
    */
    @Test
    public void lerArquivoExistente() {
        deletarArquivoCriado("entrada.txt");
        System.out.print("\nTeste: Ler arquivo\n");
        criarArquivo("entrada.txt", "qualquercoisa");
        entradaConsole("R");
        try {
            safety.safeMethod("entrada.txt");
        } catch (Exception e) {
             deletarArquivoCriado("entrada.txt");
       }
    }
    /**
    * Java comment.
    ** Java comment
    */
    @Test
    public void esceverArquivoExistente() {
        System.out.print("\nTeste: Escrever arquivo\n");
        criarArquivo("entrada.txt", "qualquercoisa");
        entradaConsole("W");
        try {
            safety.safeMethod("entrada.txt");
        } catch (Exception e) { }
            deletarArquivoCriado("entrada.txt");
    }
}
