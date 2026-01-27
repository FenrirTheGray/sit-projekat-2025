package rs.ac.singidunum.servelogic.model;

public enum OrderStatus {
    CREATED, //kreirana ceka odobrenje
    ACCEPTED, //odobrena
    REJECTED, //odbijena
    IN_PROGRESS, //aktivno se radi na porudzbini
    READY, //spremna za preuzimanje
    DONE //preuzeto
}
