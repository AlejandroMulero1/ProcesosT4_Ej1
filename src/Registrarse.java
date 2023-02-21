import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Registrarse {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("REGISTRARSE:");
        System.out.println("Introduzca su nombre:");
        String username= sc.next();

        System.out.println("Introduzca su contraseña");
        String password= sc.next();

        //Codifico los datos y los escribo en el fichero
        byte [] datosCodificados=EncriptarMensaje.getDigest(username + " " + password);
        String datosHexadecimal=String.format("%064x", new BigInteger(1, datosCodificados));
        escribirEnFichero(datosHexadecimal);
    }

    private static void escribirEnFichero(String datosCodificados){
        String filePath = "src/usuarios.cre";
        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(datosCodificados);
            bw.newLine();
            bw.close();
            System.out.println("Se ha registrado correctamente");
        }
        catch (IOException e){
            System.err.println("Se ha producido un error en el registro: "+ e);
        }
    }
}
