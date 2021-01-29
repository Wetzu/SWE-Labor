package rummy.logic;


import java.util.ArrayList;
import java.util.Collections;

public class OffenerStapel extends Stapel{
    /*
    Created by:
        Johannes Essig
        info@johannes-essig.de
    Version 1.0
    created on 29/01/2021 at 16:22
    */
    private ArrayList<Karte> karten;


    public OffenerStapel(Karte karte) {
        karten = new ArrayList<Karte>();
        karten.add(karte);
    }


    public void AddCard(Karte karte){
        karten.add(karte);
    }

    public Karte ShowTopCard(){
        return karten.get(karten.size() - 1);
    }

    public Karte GetCard(){
        return karten.remove(karten.size() - 1);
    }
}
