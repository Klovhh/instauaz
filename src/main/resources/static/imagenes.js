$(document).ready(function(){
    mostrarImagenes();
});

async function mostrarImagenes() {
    // Realizar una solicitud GET al servidor para obtener las imágenes del usuario actual
    const request = await fetch('api/imagenes/find/' + localStorage.id, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json',
            'Authorization': localStorage.token
        }
    });

    // Obtener las imágenes como respuesta en formato JSON
    const imagenes = await request.json();

    console.log(imagenes);

    // Recorrer cada imagen y mostrarla en la página
    for (let imagen of imagenes) {
        var foto = imagen.imagen_url;
        var comentario = imagen.imagen_info;

        var salida = '';
        salida += '<div class="col">';
        salida += '<div class="card h-100" style="margin: 10px">';
        salida += '<img src="' + foto + '" class="card-img-top" alt="Skyscrapers"/>';
        salida += '<div class="card-body">';
        salida += '<h5 class="card-title">Comentario:</h5>';
        salida += '<p class="card-text">' + comentario + '</p>';
        salida += '</div>';
        salida += '<div class="card-footer">';
        salida += '<a onClick="editarImagen(' + imagen.imagen_id + ')" class="btn btn-primary" style="background-color: #C29118; border-color: #9C7414">Editar</a>';
        salida += '<a onClick="eliminarImagen(' + imagen.imagen_id + ')" class="btn btn-primary" style="background-color: #D90707; border-color: #B50606; right: 10px; position:absolute">Eliminar</a>';
        salida += '</div>';
        salida += '</div>';
        salida += '</div>';

        // Agregar la salida generada al elemento con el ID "main"
        document.querySelector("#main").innerHTML += salida;
    }
}

async function eliminarImagen(imagen_id) {
    if (!confirm('¿Desea eliminar esta imagen?')) {
        return;
    }

    // Realizar una solicitud DELETE al servidor para eliminar la imagen especificada
    const request = await fetch('api/imagenes/delete/' + imagen_id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json',
            'Authorization': localStorage.token
        }
    });

    // Recargar la página después de eliminar la imagen
    location.reload();
}

async function editarImagen(imagen_id) {
    if (!confirm('¿Desea editar esta imagen?')) {
        return;
    }

    // Obtener los nuevos datos de la imagen a través de un cuadro de diálogo
    let info = datos();

    // Realizar una solicitud POST al servidor para actualizar la imagen especificada con los nuevos datos
    const request = await fetch('api/imagenes/update/' + imagen_id, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': localStorage.token
        },
        body: `info=${encodeURIComponent(info)}`
    });

    alert('El comentario se guardó correctamente');
    // Recargar la página después de editar la imagen
    location.reload();
}

function datos() {
    // Solicitar al usuario que ingrese el nuevo comentario de la imagen
    let dat = prompt("Por favor ingresa el nuevo comentario:");
    return dat;
}

