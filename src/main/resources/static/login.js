$(document).ready(function() {
    // Función que se ejecuta cuando el documento HTML ha sido completamente cargado
});

async function iniciarSesion() {
    // Función para iniciar sesión

    // Obtener los datos del formulario de inicio de sesión
    let datos = {};
    datos.nom_usuario = document.getElementById('txtNombreUsuario').value;
    datos.con_usuario = document.getElementById('txtPassword').value;

    // Realizar una solicitud GET al servidor para obtener la lista de usuarios
    const usuariosRequest = await fetch('api/usuarios', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    });

    // Obtener la lista de usuarios como respuesta en formato JSON
    const usuarios = await usuariosRequest.json();

    // Validar que se hayan ingresado los datos de inicio de sesión
    if (datos.nom_usuario !== '' && datos.con_usuario !== '') {
        // Realizar una solicitud POST al servidor para iniciar sesión
        const request = await fetch('api/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        // Obtener la respuesta del servidor en formato de texto
        const respuesta = await request.text();

        // Buscar el ID del usuario en la lista de usuarios
        for (usuario of usuarios) {
            if (datos.nom_usuario == usuario.nom_usuario) {
                datos.usu_id = usuario.id_usuario;
            }
        }

        // Validar la respuesta del servidor
        if (respuesta !== 'FAIL') {
            // Si la respuesta es exitosa, almacenar el token, el nombre de usuario y el ID del usuario en el almacenamiento local
            localStorage.token = respuesta;
            localStorage.usuario = datos.nom_usuario;
            localStorage.id = datos.usu_id;

            // Redirigir al usuario a la página de inicio
            window.location.href = 'inicio.html';
        } else {
            // Si la respuesta es fallida, mostrar un mensaje de error
              $("#modal3").modal('show');
        }
    } else {
        // Validar los campos de inicio de sesión
        if (datos.nom_usuario === '') {
              $("#modal1").modal('show');

        }else{
        if (datos.con_usuario === '') {
              $("#modal2").modal('show');

        }}
        return;
    }
}
