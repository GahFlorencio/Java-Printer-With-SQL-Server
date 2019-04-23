/*
CLASSE QUE CONVERTE TODOS OS ACENTUOS EM LETRAS SEM ACENTUOS
*/
package impressao_emergencia;


public class AsciiUtils {
  
  ///EQUALIZAÇÃO DE  LETRAS MATRIZ A PARA A   
  private static final String PLAIN_ASCII = "AaEeIiOoUuAaEeIiOoUuYyAaEeIiOoUuYyAaOoNnAaEeIiOoUuYyAaCcOoUu";
  private static final String UNICODE = "ÀàÈèÌìÒòÙùÁáÉéÍíÓóÚúÝýÂâÊêÎîÔôÛûŶŷÃãÕõÑñÄäËëÏïÖöÜüŸÿÅåÇçŐőŰű";
  
  
  //FUNÇÃO QUE CONVERTE OS ACENTUOS EM LETRAS NÃO ACENTUADAS
  public static String convertNonAscii(String s)
  {
    if (s == null) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    int n = s.length();
    for (int i = 0; i < n; i++)
    {
      char c = s.charAt(i);
      int pos = "ÀàÈèÌìÒòÙùÁáÉéÍíÓóÚúÝýÂâÊêÎîÔôÛûŶŷÃãÕõÑñÄäËëÏïÖöÜüŸÿÅåÇçŐőŰű".indexOf(c);
      if (pos > -1) {
        sb.append("AaEeIiOoUuAaEeIiOoUuYyAaEeIiOoUuYyAaOoNnAaEeIiOoUuYyAaCcOoUu".charAt(pos));
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }
    
}
