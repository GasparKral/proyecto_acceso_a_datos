package es.acceso_a_datos.modelos.records;

public record OpcionesDeEscena(Par<Integer, Integer> tamaño, Par<Integer, Integer> posicion, Boolean maximizado,
        Boolean redimensionable) {

}
