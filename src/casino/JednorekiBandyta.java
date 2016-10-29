package casino;

import java.util.Random;
import java.util.Scanner;

class JednorekiBandyta {
    
    private static JednorekiBandyta jednorekibandyta;
    private int a, b, c;
    
    public static JednorekiBandyta inst() {
        if(jednorekibandyta = null) {
            jednorekibandyta = new JednorekiBandyta();
        }
        return jednorekibandyta;
    }
    
    public void gameJednorekiBandyta() {
    //**
    //Automatyczna zapłata za grę
    //**
    
    Bank.getInst().addMoney(40);
    Gamer.getInst().substractMoney(40);
    Casino.cls();
    
    //**
    //Wnętrze gry
    //**
    Random rand = new Random();
    a = rand.nextInt(6)+1;
    b = rand.nextInt(6)+1;
    c = rand.nextInt(6)+1;
    
    System.out.printf("Wylosowane liczby to:\n %30s %30s %30s\n\n",a,b,c);
    //System.out.println("Wylosowane liczby:"a+" "+b+" "+c);
    
    if(a == b && a == c) {
    System.out.println("Wygrales! $100 trafia na Twoje konto");
    Bank.getInst().substractMoney(100);
    Gamer.getInst().addMoney(100);
} else {
    System.out.println("Niestey przegrales, sprobuj jeszcze raz - na pewno sie uda.");
}
    
}
    
}
