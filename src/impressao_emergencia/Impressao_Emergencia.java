/*
    CLASSE QUE POSIBILITA IMPRESSÃO EM UMA IMPRESSORA LOCAL 
    PRÉ DEFINIDA QUE ESTEJA COMO IMPRESSORA PADRÃO DO COMPUTAODR
*/

package impressao_emergencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;

public class Impressao_Emergencia {
     
   
    
    public static void main(String[] args)  {
    ///DEFINIÇÕES PRÉ CONSULTA NO BANCO DE DADOS    
    
    String newline = System.getProperty("line.separator");///VARIAVEL PARA PULAR LINHA
    int pessoas = 0; //SETA A QUANTIDADE DE PESSOAAS
    int l1 = 0;
    int l2 = 0;
    int l3 = 0;
    int l5 = 0;
    int l6 = 0;
    int l7 = 0;
    
    /**DATA ATUAL DO SYSTEMA**/
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

    //CRIA VARIAVEL DE CONEXÃO SQL
    String connectionUrl = "jdbc:sqlserver://SERVIDORAQUI:1433;databaseName=BANCODEDADOSAQUI;user=USUARIOAQUI;password=SENHAQUI";

        
    ///POPOULA ARRAY DE OBJETOS COM RESULTADO DA CONSULTA
    try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
       String SQL = "SELECT * NO BANCO DE DADOS";
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








    Impressora printer = new Impressora();

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
     }
         

    

    }
    
     
     
}    

