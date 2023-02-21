import java.math.BigInteger;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        //Pido los datos
        System.out.println("LOGIN:");
        System.out.println("Introduzca su nombre:");
        String username= sc.nextLine();
        System.out.println("Introduzca su contraseña");
        String password= sc.nextLine();

        //Los encripto
        byte[] datosUsuario=EncriptarMensaje.getDigest(username + " " + password);

        //Obtengo la cadena encriptada y la paso a un array de bytes
        String datosHexadecimal=String.format("%064x", new BigInteger(1, datosUsuario));
        byte [] datosUsuarioBytes= datosHexadecimal.getBytes();

        //La comparo con el contenido del fichero
        boolean login= EncriptarMensaje.compararResumenes(datosUsuarioBytes);
        if (login){
            System.out.println("Se ha realizado el login correctamente");
        }else {
            System.out.println("Login erroneo");
        }
    }
}
