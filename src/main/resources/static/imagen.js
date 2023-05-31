$(document).ready(function(){
    mostrarImagenes();
});

async function mostrarImagenes(){
    const request = await fetch('imagenes/find/'+localStorage.id,{

        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'applicaton/json'
        }
    });

    const imagenes = await request.json();

    console.log(imagenes);

