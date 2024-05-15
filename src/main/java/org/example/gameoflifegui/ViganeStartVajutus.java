package org.example.gameoflifegui;

public class ViganeStartVajutus extends Exception{
    private static String error = "Mäng juba jookseb";
    public ViganeStartVajutus(){
        super(error);
    }
}
