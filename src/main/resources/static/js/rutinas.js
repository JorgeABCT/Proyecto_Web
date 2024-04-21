/* La siguiente función se utiliza para activar la cantidad de elementos seleccionados
 * En el carrito de compras utilizando un llamado "ajax" */
function addCartListado(idPlatillo) {
    var url = '/carrito/agregar';
    url = url + '/' + idPlatillo;
    $("#agregadoCarrito").load(url);
}
/*
 function addCartVisualizar(idPlatillo) {
 debugger;
 var valorText = document.getElementsByName("CambiosPlatillo")[0].value;
 console.log("El valor del textarea es: " + valorText);
 var detalleCodificado = encodeURIComponent(valorText);
 var url = '/carrito/agregarV?id_platillo=';
 url += idPlatillo;
 url += '&detalle=';
 url += detalleCodificado;
 $("#agregadoCarrito").load(url);
 }*/

function addCartVisualizar(idPlatillo) {
    //debugger;
    var valorText = document.getElementsByName("CambiosPlatillo")[0].value;
    console.log("El valor del textarea es: " + valorText);
    var detalleCodificado = encodeURIComponent(valorText);
    //var url = '/carrito/agregarV/' + idPlatillo + '/' + detalleCodificado;
    var url = '/carrito';
    if (detalleCodificado !== "") {
        url = url + '/agregarV/' + idPlatillo + '/' + detalleCodificado;
    } else {
        url = url + '/agregar/' + idPlatillo;
    }
    $("#agregadoCarrito").load(url);
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result).height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
}


