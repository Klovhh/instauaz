package com.campusjalpa.instauaz.controllers;

import com.campusjalpa.instauaz.models.Usuario;
import com.campusjalpa.instauaz.models.UsuarioRepository;
import com.campusjalpa.instauaz.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Clase Controller para la autenticación del usuario
public class AuthController {

    //llamamos al objeto de tipo UsuarioDAO
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JWTUtil jwtUtil;



    //Método para verificar el nombre de usuario y la contraseña
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        //para saber si el usuario se inició sesión correctamente o no
        Usuario usuarioLogueado = usuarioService.verificarUsuario(usuario);

        if(usuarioLogueado != null){
            //se crea el JWT
            //se genera el token en base al id del usuario y a su nombre de usuario

            //devuelve el token
            return jwtUtil.create(String.valueOf(usuarioLogueado.getId_usuario()), usuarioLogueado.getNom_usuario());
        }//fin del if
        //Si no, manda FAIL para indicar que el usuario no pudo iniciar sesión
        return "FAIL";
    }//fin del método login

}//fin de la clase AuthController