/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import modelo.Usuario;

/**
 *
 * @author USUARIO
 */
@WebService(serviceName = "OperacionesCliente")
public class OperacionesCliente {

    private ArrayList<Usuario> usuariosRegistrados = new ArrayList<>();

    @WebMethod(operationName = "Deposito")
    public double Deposito(@WebParam(name = "saldo") double saldo, @WebParam(name = "valor") double valor) {
        if (valor <= 0) {
            return -1.0; // Valor inválido, no se permite realizar la operación
        }

        // Sumar el valor al saldo existente
        return saldo + valor;
    }

    @WebMethod(operationName = "Retiro")
    public double Retiro(@WebParam(name = "saldo") double saldo, @WebParam(name = "valor") double valor) {
        if (valor <= 0 || valor > saldo) {
            return -1.0; // Valor inválido o saldo insuficiente, no se permite realizar la operación
        }

        // Restar el valor del saldo existente
        return saldo - valor;
    }

    
    //METODO LOGIN
    
    public boolean login(
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "contrasena") String contrasena) {

        // Lógica para verificar las credenciales del usuario
        for (Usuario u : usuariosRegistrados) {
            if (u.getUsuario().equals(usuario) && u.getContrasena().equals(contrasena)) {
                return true; // Inicio de sesión exitoso
            }
        }

        return false; // Credenciales inválidas
    }

    
    // METODO DE REGISTRO DE USUARIO
    
    
    @WebMethod(operationName = "RegistrarUsuario")
    public Boolean RegistrarUsuario(
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "contrasena") String contrasena,
            @WebParam(name = "repetirContrasena") String repetirContrasena,
            @WebParam(name = "saldo") double saldo) {

        // Verificar si la contraseña y la repetición de la contraseña coinciden
        if (!contrasena.equals(repetirContrasena)) {
            return false; // Las contraseñas no coinciden
        }

        // Verificar si el usuario ya está registrado
        for (Usuario u : usuariosRegistrados) {
            if (u.getUsuario().equals(usuario)) {
                return false; // El usuario ya está registrado
            }
        }

        // Crear un nuevo objeto Usuario y agregarlo al ArrayList
        Usuario nuevoUsuario = new Usuario(usuario, contrasena, saldo);
        usuariosRegistrados.add(nuevoUsuario);

        // Devolver true si el registro fue exitoso
        return true;
    }

}
