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
        salida += '<img src="' + foto + '" class="card-img-top" alt="'+comentario+'"/>';
        salida += '<div class="card-body">';
        salida += '<h5 class="card-title">Comentario:</h5>';
        salida += '<p class="card-text">' + comentario + '</p>';
        salida += '</div>';
        salida += '<div class="card-footer">';
        salida += '<a onClick="mostrarModal('+imagen.imagen_id+')" class="btn btn-primary" style="background-color: #C29118; border-color: #9C7414">Editar</a>';
        salida += '<a onClick="eliminarImagen(' + imagen.imagen_id + ')" class="btn btn-primary" style="background-color: #D90707; border-color: #B50606; right: 10px; position:absolute">Eliminar</a>';
        salida += '</div>';
        salida += '</div>';
        salida += '</div>';

        // Agregar la salida generada al elemento con el ID "main"
        document.querySelector("#main").innerHTML += salida;
    }
}

function eliminarImagen(imagen_id) {
    // Mostrar un modal de confirmación antes de eliminar la imagen
    $("#modal3").modal('show');
    var button = document.getElementById('borrarPubli');
    button.addEventListener('click', async function() {
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
    });
}

async function editarImagen(imagen_id) {
    // Obtener los nuevos datos de la imagen a través de un cuadro de diálogo
    let info = retrieveFormData();

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

    // Ocultar el modal de edición y mostrar un mensaje de confirmación
    $('#modal1').modal('hide');
    $("#modal2").modal('show');
    var button = document.getElementById('cerrarModal');
    button.addEventListener('click', function() {
        location.reload();
    });
}

function mostrarModal(imagen_id) {
    // Mostrar el modal de edición y asignar el evento click al botón "Guardar"
    $("#modal1").modal('show');
    var button = document.getElementById('btnGuardar');
    button.addEventListener('click', function() {
        console.log('Button pressed!');
        editarImagen(imagen_id);
    });
}

$('#modal1').on('hidden.bs.modal', function () {
    // Restablecer el valor del campo de texto del modal después de cerrarlo
    var modalElement = document.querySelector('#modal1');
    $('#modal1').modal('hide');
    var inputElement = modalElement.querySelector('#typeText');
    inputElement.value = '';
});

function retrieveFormData() {
    // Obtener el valor del campo de texto del modal
    let inputValue = document.getElementById("typeText").value;
    return inputValue;
}
