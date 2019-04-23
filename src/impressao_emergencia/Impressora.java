package impressao_emergencia;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;


public final class Impressora {
    // variavel estatica porque será utilizada por inumeras threads

private static PrintService impressora;

public Impressora() {
    detectaImpressoras();
}
// O metodo verifica se existe impressora conectada e a
// define como padrao.

public void detectaImpressoras() {
    try {
        DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);
        for (PrintService p : ps) {
            if (p.getName() != null && p.getName().contains("Itv_Recursos_Humanos")) {
                System.out.println("Impressora Selecionada: " + p.getName());
                System.out.println("Impressora encontrada: " + p.getName());
                impressora = p;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public synchronized boolean imprime(String texto) {
    // se nao existir impressora, entao avisa usuario
    // senao imprime texto
    if (impressora == null) {
        String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.";
        System.out.println(msg);
    } else {
        System.out.println(texto);
        try {
            DocPrintJob dpj = impressora.createPrintJob();
            InputStream stream = new ByteArrayInputStream(texto.getBytes());
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(stream, flavor, null);
            dpj.print(doc, null);
            
            return true;
        } catch (PrintException e) {
            e.printStackTrace();
        }
    }
    return false;
}

public synchronized boolean imprime(byte[] bytes) {
    // se nao existir impressora, entao avisa usuario
    // senao imprime texto
    if (impressora == null) {
        String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.";
        System.out.println(msg);
    } else {
        System.out.println(bytes);
        try {
            DocPrintJob dpj = impressora.createPrintJob();
            InputStream stream = new ByteArrayInputStream(bytes);
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(stream, flavor, null);
            dpj.print(doc, null);
            return true;
        } catch (PrintException e) {
            e.printStackTrace();
        }
    }
    return false;
    }
}