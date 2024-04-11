/* La siguiente función se utiliza para activar la cantidad de elementos seleccionados
 * En el carrito de compras utilizando un llamado "ajax" */
function addCartListado(idPlatillo) {
    var url = '/carrito/agregar';
    url = url + '/' + idPlatillo;
    $("#agregadoCarrito").load(url);
}

function addCartVisualizar(formulario, num) {
    var url = '/carrito/agregar';
    url = url + '/' + num;
    $("#agregadoCarrito").load(url);
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result).height(200);};
        reader.readAsDataURL(input.files[0]);
    }
}


