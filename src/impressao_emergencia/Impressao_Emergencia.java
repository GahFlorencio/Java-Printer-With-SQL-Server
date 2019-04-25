/*
    CLASSE QUE POSIBILITA IMPRESSÃO EM UMA IMPRESSORA LOCAL 
    PRÉ DEFINIDA QUE ESTEJA COMO IMPRESSORA PADRÃO DO COMPUTAODR
*/

package impressao_emergencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Impressao_Emergencia {
 
    
    public static void main(String[] args) throws FileNotFoundException, IOException  {
        /***LEITURA DO ARQUIVO DE CONFIGURAÇÃO***/
        Properties config = new Properties();
        File file = new File(System.getProperty("user.dir").toString()+"/config.ini");
        if(!file.exists()){
            String textoQueSeraEscrito = "****** ARQUIVO DE CONIGURAÇÃO ********\n" +
                                        "\n" +
                                        "******SERVIDOR BANCO DE DADOS ******\n" +
                                        "db_server=SERVIDOR AQUI\n" +
                                        "db_port=PORTA AQUI\n" +
                                        "db_name=NOME BANCO AQUI\n" +
                                        "db_table=TABELA AQUI\n" +
                                        "db_user=USUARIO AQUI\n" +
                                        "db_pass=SENHA AQUI\n" +
                                        "\n" +
                                        "********IMPRESSORA********\n" +
                                        "printer_name= NOME IMPRESSORA AQUI\n" +
                                        "\n" +
                                        "";
		FileWriter arq;
		try {
			arq = new FileWriter(new File("config.ini"));
			arq.write(textoQueSeraEscrito);
			arq.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}         
        }
        file = new File(System.getProperty("user.dir").toString()+"/config.ini");      
       
        
         config.load(new FileInputStream(file));
         String db_server = config.getProperty("db_server").toString(); //SERVIDOR DO BANCO DE DADOS
         String db_port = config.getProperty("db_port").toString();//PORTA DO BANCO DE DADOS
         String db_name = config.getProperty("db_name").toString();//NOME BANCO DE DADOS
         String db_table = config.getProperty("db_table").toString();//TABELA OU VIEW DO BANCO DE DADOS
         String db_user = config.getProperty("db_user").toString();//USUARIO BANCO DE DADOS
         String db_pass = config.getProperty("db_pass").toString();//SENHA BANCO DE DADOS
         String printer_name = config.getProperty("printer_name").toString();//NOME DA IMPRESSORA BANCO DE DADOS
        
      
      
    ///DEFINIÇÕES PRÉ CONSULTA NO BANCO DE DADOS    
    
    String newline = System.getProperty("line.separator");///VARIAVEL PARA PULAR LINHA
     
    /**DATA ATUAL DO SISTEMA**/
    java.util.Date NDATA = new Date();
    String DATASYS = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(NDATA);

   
    /*CABEÇALHO  DAS PÁGINAS */
    String HAD = ""+newline+newline+"LISTA DE EMERGENCIA  DE A - D    - "+DATASYS+"\n";
    String HEH = ""+newline+newline+"LISTA DE EMERGENCIA  DE E - H    - "+DATASYS+"\n";
    String HIL = ""+newline+newline+"LISTA DE EMERGENCIA  DE I - L    - "+DATASYS+"\n";
    String HMP = ""+newline+newline+"LISTA DE EMERGENCIA  DE M - P    - "+DATASYS+"\n";
    String HQT = ""+newline+newline+"LISTA DE EMERGENCIA  DE Q - T    - "+DATASYS+"\n";
    String HUZ = ""+newline+newline+"LISTA DE EMERGENCIA  DE U - Z    - "+DATASYS+"\n";
    
    
    /*ARRAY DE STRINGS COLABORADORES */
    Vector AD = new Vector();
    Vector EH = new Vector();
    Vector IL = new Vector();
    Vector MP = new Vector();
    Vector QT = new Vector();
    Vector UZ = new Vector();
    

    /**PAGINAS POR GRUPO**/
    String CAD = "";
    String CEH = "";
    String CIL = "";
    String CMP = "";
    String CQT = "";
    String CUZ = ""; 
    
    /**resultado da impressão**/
    Integer resultado = 0;
   
    //CRIA VARIAVEL DE CONEXÃO SQL
    String connectionUrl = "jdbc:sqlserver://"+db_server+":"+db_port+";databaseName="+db_name+";user="+db_user+";password="+db_pass+"";

        
        
    ///POPOULA ARRAY DE OBJETOS COM RESULTADO DA CONSULTA
    try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
       String SQL = "SELECT * FROM "+db_table;
       ResultSet rs = stmt.executeQuery(SQL);


    //RODA TODA A ESTRUTURA E DESCOBRE QUAL INICIAL DO COLABORADOR
       while (rs.next()) {

          String inicial =  (rs.getString("NM_PESSOA")).substring(0,1);



          if(inicial.equals("A") ||inicial.equals("B") || inicial.equals("C") || inicial.equals("D") )
          {    
             String nome = rs.getString("NM_PESSOA");
              nome = String.format("%-50s", nome);
              String tpcredencial = rs.getString("DS_TIPO_credencial");
              tpcredencial = String.format("%-15s", tpcredencial);

              AD.add((nome+" | "+tpcredencial+newline));
          }
          
           if(inicial.equals("E") ||inicial.equals("F") || inicial.equals("G") || inicial.equals("H") )
          {    
             String nome = rs.getString("NM_PESSOA");
              nome = String.format("%-50s", nome);
              String tpcredencial = rs.getString("DS_TIPO_credencial");
              tpcredencial = String.format("%-15s", tpcredencial);

              EH.add(nome+" | "+tpcredencial+newline);
              
          }
           if(inicial.equals("I") ||inicial.equals("J") || inicial.equals("K") || inicial.equals("L") )
          {    
             String nome = rs.getString("NM_PESSOA");
              nome = String.format("%-50s", nome);
              String tpcredencial = rs.getString("DS_TIPO_credencial");
              tpcredencial = String.format("%-15s", tpcredencial);

              IL.add(nome+" | "+tpcredencial+newline);
              
          }
           if(inicial.equals("M") ||inicial.equals("N") || inicial.equals("O") || inicial.equals("P") )
          {    
             String nome = rs.getString("NM_PESSOA");
              nome = String.format("%-50s", nome);
              String tpcredencial = rs.getString("DS_TIPO_credencial");
              tpcredencial = String.format("%-15s", tpcredencial);

              MP.add(nome+" | "+tpcredencial+newline);
              
          }
           if(inicial.equals("Q") ||inicial.equals("R") || inicial.equals("S") || inicial.equals("T") )
          {    
             String nome = rs.getString("NM_PESSOA");
              nome = String.format("%-50s", nome);
              String tpcredencial = rs.getString("DS_TIPO_credencial");
              tpcredencial = String.format("%-15s", tpcredencial);

              QT.add(nome+" | "+tpcredencial+newline);
              
          }
           if(inicial.equals("U") ||inicial.equals("V") || inicial.equals("X") || inicial.equals("W") || inicial.equals("Y") || inicial.equals("Z"))
          {    
             String nome = rs.getString("NM_PESSOA");
              nome = String.format("%-50s", nome);
              String tpcredencial = rs.getString("DS_TIPO_credencial");
              tpcredencial = String.format("%-15s", tpcredencial);

              UZ.add(nome+" | "+tpcredencial+newline);
              
          }



       }

    }

    catch (SQLException e) {
       e.printStackTrace();
    }  








    Impressora printer = new Impressora(printer_name);

    Integer cont = 0;
    Integer contl= 0;
    Integer pg = 1;
    
     
    
        
    
    
    
    ////IMPRESSAO DO GRUPO DE A ATÉ D
     if ((AD.size())>0){
         CAD = HAD+"Página: "+pg+newline+newline;
         
         while(cont <  AD.size())
         {  
                       
             contl++;
             CAD = CAD+(AD.get(cont).toString());
             //System.out.print(CAD);
             if (contl == 50)
             {
                
                printer.imprime(AsciiUtils.convertNonAscii(CAD));
                pg++;
                contl=0;
                CAD = HAD+"Página: "+pg+newline+newline;
             }
             else
             { 
                 
                 if(cont+1 == AD.size() && contl<50)
                 {
                    
                    printer.imprime(AsciiUtils.convertNonAscii(CAD));
                    pg++;
                 }
             }
             cont++;
                                     
         }
         resultado++;
         
     }
     
     
     ///IMPRESSAO DO GRUPO DE E ATÉ  H
      cont = 0;
      contl= 0;
     
     if ((EH.size())>0){
         CEH = HEH+"Página: "+pg+newline+newline;
         
         while(cont <  EH.size())
         {  
                       
             contl++;
             CEH = CEH+(EH.get(cont).toString());
             //System.out.print(CAD);
             if (contl == 50)
             {
                
                printer.imprime(AsciiUtils.convertNonAscii(CEH));
                pg++;
                contl=0;
                CEH = HEH+"Página: "+pg+newline+newline;
             }
             else
             { 
                 
                 if(cont+1 == EH.size() && contl<50)
                 {
                    
                    printer.imprime(AsciiUtils.convertNonAscii(CEH));
                    pg++;
                 }
             }
             cont++;
                                     
         }
         resultado++;
     }
     
     
     ///IMPRESSAO DO GRUPO DE I ATÉ  L
      cont = 0;
      contl= 0;
     
     if ((IL.size())>0){
         CIL = HIL+"Página: "+pg+newline+newline;
         
         while(cont <  IL.size())
         {  
                       
             contl++;
             CIL = CIL+(IL.get(cont).toString());
             //System.out.print(CAD);
             if (contl == 50)
             {
                
                printer.imprime(AsciiUtils.convertNonAscii(CIL));
                pg++;
                contl=0;
                CIL = HIL+"Página: "+pg+newline+newline;
             }
             else
             { 
                 
                 if(cont+1 == IL.size() && contl<50)
                 {
                    
                    printer.imprime(AsciiUtils.convertNonAscii(CIL));
                    pg++;
                 }
             }
             cont++;
                                     
         }
         resultado++;
     }
     
     ///IMPRESSAO DO GRUPO DE M ATÉ  P
      cont = 0;
      contl= 0;
     
     if ((MP.size())>0){
         CMP = HMP+"Página: "+pg+newline+newline;
         
         while(cont <  MP.size())
         {  
                       
             contl++;
             CMP = CMP+(MP.get(cont).toString());
             //System.out.print(CAD);
             if (contl == 50)
             {
                
                printer.imprime(AsciiUtils.convertNonAscii(CMP));
                pg++;
                contl=0;
                CMP = HMP+"Página: "+pg+newline+newline;
             }
             else
             { 
                 
                 if(cont+1 == MP.size() && contl<50)
                 {
                    
                    printer.imprime(AsciiUtils.convertNonAscii(CMP));
                    pg++;
                 }
             }
             cont++;
                                     
         }
     resultado++;
     }
     
     ///IMPRESSAO DO GRUPO DE Q ATÉ  T
      cont = 0;
      contl= 0;
     
     if ((QT.size())>0){
         CQT = HQT+"Página: "+pg+newline+newline;
         
         while(cont <  QT.size())
         {  
                       
             contl++;
             CQT = CQT+(QT.get(cont).toString());
             //System.out.print(CAD);
             if (contl == 50)
             {
                
                printer.imprime(AsciiUtils.convertNonAscii(CQT));
                pg++;
                contl=0;
                CQT = HQT+"Página: "+pg+newline+newline;
             }
             else
             { 
                 
                 if(cont+1 == QT.size() && contl<50)
                 {
                    
                    printer.imprime(AsciiUtils.convertNonAscii(CQT));
                    pg++;
                 }
             }
             cont++;
                                     
         }
         resultado++;
     }
     ///IMPRESSAO DO GRUPO DE U ATÉ  Z
      cont = 0;
      contl= 0;
     
     if ((UZ.size())>0){
         CUZ = HUZ+"Página: "+pg+newline+newline;
         
         while(cont <  UZ.size())
         {  
                       
             contl++;
             CUZ = CUZ+(UZ.get(cont).toString());
             //System.out.print(CAD);
             if (contl == 50)
             {
                
                printer.imprime(AsciiUtils.convertNonAscii(CUZ));
                pg++;
                contl=0;
                CUZ = HUZ+"Página: "+pg+newline+newline;
             }
             else
             { 
                 
                 if(cont+1 == UZ.size() && contl<50)
                 {
                    
                    printer.imprime(AsciiUtils.convertNonAscii(CUZ));
                    pg++;
                 }
             }
             cont++;
                                     
         }
         resultado++;
     }
     /******VERIFICA SE IMPRIMIU CON ******/
    JOptionPane.showMessageDialog(null, "LISTA ENVIADA PARA IMPRESSÃO.");
    

    }
    
     
     
}    

