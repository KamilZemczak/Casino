package casino;

import java.util.Random;
import java.util.Scanner;

class JednorekiBandyta {
    
    private static JednorekiBandyta jednorekiBandyta;
    public static int games = 0;
    public static int won = 0;
    private int a, b, c;
    
    //**
    //Wykorzystanie Singletona
    //**
    public static synchronized JednorekiBandyta inst() {
        if(jednorekiBandyta == null) {
            jednorekiBandyta = new JednorekiBandyta();
        }
        return jednorekiBandyta;
    } 
    
    //**
    //Stosunek wygranych
    //**
    public double getBilance() {
        return won/games;
    }
    
    //**
    //Wartość rozegranych gier
    //**
    public int getGames() {
        return games;
    }
    
    public void gameJednorekiBandyta() {
        
    //**
    //Automatyczna zapłata za grę
    //**
    Bank.getInst().addMoney(40);
    Gamer.getInst().substractMoney(40);
    games++;
    
    //**
    //Wnętrze gry
    //**
    Random rand = new Random();
    a = rand.nextInt(6)+1;
    b = rand.nextInt(6)+1;
    c = rand.nextInt(6)+1;
    
    System.out.printf("Wylosowane liczby to:\n %30s %30s %30s\n\n",a,b,c);
    
    if(a == b && a == c) {
    System.out.println("Wygrales! $100 trafia na Twoje konto.");
    Bank.getInst().substractMoney(100);
    Gamer.getInst().addMoney(100);
} else {
    System.out.println("Niestey przegrales, sprobuj jeszcze raz - na pewno sie uda.");
}
    System.out.println("Nacisnij dowolny klawisz by kontynuowac.");
    Scanner input = new Scanner(System.in);
    input.nextLine();
}
    
}
