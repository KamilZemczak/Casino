package casino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {
    
    private static BlackJack blackjack;
    private static boolean bidup = true;
    private static Scanner input = new Scanner (System.in);
    private static Random rand = new Random();
    private static List<Integer> deckCards = new ArrayList<Integer>();
    private static List<Integer> gamerCards = new ArrayList<Integer>();
    private static List<Integer> croupierCards = new ArrayList<Integer>();
    private static int games = 0;
    private static int won = 0;
    
    //**
    //Wykorzystanie Singletona
    //**
    public static BlackJack inst() {
        if(blackjack == null) {
            blackjack = new BlackJack();
        }
        return blackjack; }
    
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
    
    public void gameBlackJack() {
        
    //**
    //Automatyczna zapłata za grę
    //**
        Bank.getInst().addMoney(80);
        Gamer.getInst().substractMoney(80);;
        games++;
        
    //**
    //Gra
    //**
        lestStart();
        score(gamer(),croupier());
    }
    
    public void lestStart() {
    //**
    //Rozdanie
    //**
        for(int i=0; i < 4; i++) {
            for(int j=2; j<=11; j++) {
                deckCards.add(j);
                if (j==10) {
                    for(int k=0; k < 3; k++) deckCards.add(j);
                }
            }
            gamerCards.clear();
            croupierCards.clear();
            bidup = false;
            
    //**
    //Rozdanie dwóch kart krupierowi i graczu
    //**
            int index;
            for(int i=0; i < 2; i++) {
                index = rand.nextInt(deckCards.size());
                gamerCards.add(deckCards.get(index));
                deckCards.remove(index);
            }
            
            for(int i=0; i < 2; i++) {
                index = rand.nextInt(deckCards.size());
                croupierCards.add(deckCards.get(index));
                deckCards.remove(index);
            }
            
    //**
    //Wyświetlenie informacji o kartach
    //**
            System.out.printf("Karty gracza: %10d %10d\n", gamerCards.get(0), gamerCards.get(1));
            System.out.printf("Karta krupiera: %10d", croupierCards.get(0));
            System.out.println("Nacisnij dowolny klawisz by kontynuowac.");
            Scanner input = new Scanner(System.in);
            input.nextLine();
        }
    }        
        //ruchy gracza        
        //ruchy gracza
        public int gamer() {
            int part_sum = 0;
            Sring dec = "O";
            //czyszczenie ekranu
            System.out.println("RUCHY GRACZA");
            
            
            do {
                part_sum = 0;
                //wyswietleniekork
                System.out.println("\n\n\nGracz ma te karty:");
                    for(int x: gamerCards) System.out.print(" "+x+" ");
                    System.out.println("\n");
                    
                    //suma kart
                    for(int x : gamerCards)
                        part_sum+=x;
                    System.out.println("Suma kart to "+part_sum);
                    
                    if(part_sum == 21) {
                        System.out.println("Gracz nie ma juz ruchow"); break; 
                    } else if(part_sum > 21&&gamerCards.contains(11)) ace();
                    else if (part_sum > 21) {
                        System.out.println("Gracz nie ma juz dostepnych ruchow"); break; 
                    } else {
                        do {
                            System.out.println("Prosze wybrac cos:");
                            System.out.println("P - czekaj");
                            System.out.println("H - dobierz kartke")
                                    if(Gamer.getInst().getState()>80) System.out.println("B - podbij stawke");
                                    decision = input.next().toUpperCase();
                        } while(!decision.equals("P")&&!decision.equals("H")&&!.decision.equaals("B"));
                        
                        if(decision.equals("P")) break;
                        if(decision.equals("H")) hit(gamerCards);
                        if(decision.equals("B")) bid();
                    }
                    
            } while(!decision.equals("P"));
            return part_sum;
        }
        
        public void bid() {
            if (bidup == false) {
                System.out.println("\nZ konta zostaje pobrane $60, ktore zwieksza stawke");
                Bank.getInst().addMoney(80);
                Gamer.getInst().substractMoney(60);
                bidup = true;
            } else System.out.println("\nJuz raz podwoiles stawke - nie mozesz tego zrobic ponownie.");
                
        }
        
        public int croupier() {
            int part_sum;
            boolean var = false;
            System.out.println("Ruchy krupiera");
            
            do {
            part_sum = 0;
            System.out.println("Krupier ma nastepujace karty:");
            for(int x: croupierCards) System.out.print(" "+x+ " ");
            System.out.println("\n");
            for(int x: croupierCards)
                part_sum += x;
            System.out.println("Suma kart krupiera to: "+part_sum);
            if(part_sum > 16)
                if (part_sum>21&&croupierCards.contains(11)) {
                    croupierCards.set(croupierCards.lastIndexOf(11), 1);
                    System.out.println("W kartach byl as, a suma kart przekroczyla 21, wiec w miejsce asa wstawiamy wartosc 1");
                } else var = true;
            else {
                hit(croupierCards); System.out.println("Krupier dobiera karte"); 
            }
        } while(var == false);
            
            return part_sum;
            
        public void hit(List<Integer> name) {
            int index = rand.nextInt(deckCards.size());
            name.add(deckCards.get(index));
            deckCards.remove(index);
        }
        
        public void ace() {
            System.out.println()("\n\nSuma kart jest wieksza od 21, ale w kartach gracza znajduje sie as\n"
                                 +"Jego wartosc zmienila sie na 1...");
            gamerCards.set(gamerCards.lastIndexOf(11), 1);
            System.out.println("nacisnij aby kontynowac");
            input.nextLine();
                                 
        }
        
        public void score(int gamer, int croupier) {
            System.out.printf("  WYNIK GRACZA: %15d\n", gamer);
            System.out.printf("WYNIK KRUPIERA: %15d\n\n", croupier);
            if (gamer > 21)
                if (croupier > 21) System.out.println("Nikt nie wygral.");
                else System.out.println("Gracz przegral");
            else if (gamer == 21) {
                System.out.println("Gracz wygral blackjacka");
                if (bidup == true) {
                    System.out.println("Dodatkowo gracz podwoil zaklad");
                    System.out.println("Na konto gracza wyplaa 24");
                    Bank.getInst().substractMoney(240);
                    Gamer.getInst().addMoney(240);
                    won++;
                }
                
                else {
                    System.out.println("Na konto gracza wplywa 120");
                    Bank.getInst().substractMoney(120);
                    Gamer.getInst().addMoney(120);
                    won++;
                }
            }
            else if ((21-gamer) < (21-croupier)) {
                System.out.println("Gracz wygral z krupierem!!!");
                System.out.println("Na konto gracza wplywa $100!!!");
                Bank.getInst().substractMoney(100);
                Gamer.getInst().addMoney(100);
                won++; 
            }
            else if ((21-gamer) == (21 - croupier)) {
                System.out.println("REMIS");
            } else System.out.println("gracz przegral");
        
        
    }
            
    
}
