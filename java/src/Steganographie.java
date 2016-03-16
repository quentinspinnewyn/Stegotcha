package mainPackage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Alexandre on 16/03/2016.
 *
 */
public class Steganographie {

    String os;
    String p;
    Runtime runtime;

    public Steganographie() {
        os = System.getProperty("os.name");
        runtime = Runtime.getRuntime();
        if (os.contains("Windows")) {
            p="\\";
        } else {
            p="/";
        }
    }

    protected void insert(String embedTxt, String cover, String passphrase) {
        System.out.println("Starting Insertion");
        File tmp = new File ("resources"+p+"embedMsg.txt");
        File coverFile = new File ("resources"+p+cover);
        try {
            FileWriter fw = new FileWriter(tmp);
            fw.write(embedTxt);
            fw.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du message : "+e.getMessage());
        }
        String[] cmd;
        if (tmp.length()<cover.length()) {
            System.out.println("Embed text has a correct length.");
            cmd = new String[]{"", "embed", "-ef resources" + p + "embedMsg.txt", "-cf " + "resources" + p + "cover", "-p " + passphrase, "-z 9"};

            /** TODO:
             *  Vérifier si taille embed > taille cover
             *  si oui : augmenter compression
             *  Traitement message d'erreur !
             */

            if (os.contains("Windows")) {
                System.out.println("Windows OS detected");
                cmd[0] = "resources" + p + "steghide" + p + "steghide.exe";
            } else {
                System.out.println("Other OS detected");
                cmd[0] = "steghide";
            }

            try {
                Process process = runtime.exec(cmd);
                process.waitFor();
            } catch (InterruptedException e) {
                System.out.println("Embed execution has been interrupted");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Embed execution failed");
                e.printStackTrace();
            }
        } else {
            System.out.println("Secret Message too big !");
        }
        System.out.println("Insertion ended");
    }

    protected String extract(String target, String passphrase) {
        System.out.println("Starting Extraction");
        File tmp = new File ("resources"+p+"embedMsg.txt");
        String[] cmd = new String[]{"","extract", "-sf resources"+p+target, "-xf resources"+p+"extractMsg.txt", "-p "+passphrase};
        if (os.contains("Windows")) {
            System.out.println("Windows OS detected");
            cmd[0]="resources"+p+"steghide"+p+"steghide.exe";
        } else {
            System.out.println("Other OS detected");
            cmd[0]="steghide";
        }

        try {
            Process process = runtime.exec(cmd);
            process.waitFor();
        } catch (InterruptedException e) {
            System.out.println("Extract execution has been interrupted");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Extract execution failed");
            e.printStackTrace();
        }
        String result="";
        try {
            FileReader fr = new FileReader(tmp);
            int c = fr.read();
            while (c != -1) {
                result+=(char)c;
                c = fr.read();
            }
            fr.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du message : "+e.getMessage());
        }

        /** TODO:
         *  Traitement message d'erreur !
         */
        System.out.println("Exctraction correclty ended");
        tmp.delete();
        return result;
    }

}

