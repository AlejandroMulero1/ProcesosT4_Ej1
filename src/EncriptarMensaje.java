import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class EncriptarMensaje {
    public static byte[] getDigest(String datos){
        byte [] mensajeBytes;
        byte [] resumen=null;
        try {
            // Convierto el mensaje introducido por el usuario en un array de bytes
            mensajeBytes = datos.getBytes("UTF-8");

            // Creo una instancia de MessageDigest con el algoritmo SHA-256
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");

            // Reinicio el objeto por si contiene datos
            algoritmo.reset();

            // Añado el mensaje del cual quiero calcular su hash
            algoritmo.update(mensajeBytes);

            // Genero el resumen
            resumen = algoritmo.digest();
        }
        // Controlo las posibles excepciones
        catch (UnsupportedEncodingException exception){
            System.err.println("Se ha producido el siguiente error al obtener los bytes del mensaje:" + exception);
        }
        catch (NoSuchAlgorithmException exception){
            System.err.println("Se ha producido el siguiente error al obtener el algoritmo de codificacion:" + exception);
        }
        return resumen;
    }
    public static boolean compararResumenes(byte [] datosUsuario){
        String contenidoFichero="";
        BufferedReader br;
        boolean encontrado=false;
        try {
            br = new BufferedReader(new FileReader("src/usuarios.cre"));
            Scanner sc = new Scanner(br);
            //Recorro el fichero
            while (sc.hasNext() && !encontrado) {
                contenidoFichero=sc.nextLine();
                //Obtengo un array de bytes del contenido del fichero
                byte [] registro=contenidoFichero.getBytes();

                //Los comparo con el isEqual
                if (MessageDigest.isEqual(datosUsuario, registro)){
                    encontrado=true;
                }
            }
            br.close();

            //Si se produce alguna excepcion se manda el mensaje de error
        } catch (FileNotFoundException e) {
            System.err.println("Ha ocurrido el siguiente error co el archivo: " + e);
        } catch (IOException e){
            System.err.println("Se ha producido el siguiente error con el fichero: " + e);
        }
        return encontrado;
    }
}
