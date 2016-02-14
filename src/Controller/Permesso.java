package Controller;

/**
 * Created by nicola on 14/02/16.
 */
public enum Permesso {
    Admin,  //utente con i massimi privilegi che amministra il programma
    TL,     // Utente con i massimi privilegi nel team corse
    GL,     //utente capogruppo
    US,     //utente semplice
    NR      //utente non registrato
}
