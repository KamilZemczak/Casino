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
    private static final List<Integer> deckCards = new ArrayList<Integer>();
    private static final List<Integer> gamerCards = new ArrayList<Integer>();
    private static final List<Integer> croupierCards = new ArrayList<Integer>();
    private static int games = 0;
    private static int won = 0;
    
    //**
    //Wykorzystanie Singletona
    //**
    public static BlackJack inst() {
        if(blackjack == null) {
            blackjack = new BlackJack();
        }
        return blackjack; 
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
        letsStop(gamer(),croupier());
    }
    
    public void lestStart() {
    //**
    //Rozdanie
    //**
         for(int i=0; i<4; i++) {
             for(int j=2;j<=11;j++) {
                deckCards.add(j);
                if (j==10) { 
                    for(int k=0;k<3;k++) deckCards.add(j); 
                }
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
    //**
    //Ruchy gracza
    //**
        public int gamer() {
            int sum = 0;
            String decision = "O";
            //czyszczenie ekranu
            System.out.println("RUCHY GRACZA");
            
            do {
                sum = 0;

    //**
    //Wyświetlenie kart gracza
    //**
                System.out.println("\nGracz ma te karty:");
                    for(int x: gamerCards) System.out.print(" "+x+" ");
                    System.out.println("\n");
                    
    //**
    //Wyświetlenie sumy kart
    //**
                    for(int x : gamerCards)
                        sum += x;
                    System.out.println("Suma kart to:" +sum);
                    
                    if(sum == 21) {
                        System.out.println("Gracz nie ma juz ruchow."); break; 
                    } else if(sum > 21&&gamerCards.contains(11)) ace();
                    else if (sum > 21) {
                        System.out.println("Gracz nie ma juz dostepnych ruchow"); break; 
                    } else {
                        do {
                            System.out.println("Jesli chcesz czekac nacisnij P.");
                            System.out.println("Jesli chcesz wziac sobie karte nacisnij K.");
                                    if(Gamer.getInst().getState()>80) System.out.println("Jesli chcesz podbic stawke nacisnij B (nie mozna dwa razy podbijac).");
                                    decision = input.next().toUpperCase();
                        } while(!decision.equals("P")&&!decision.equals("K")&&!decision.equals("B"));
                        
                        if(decision.equals("P")) break;
                        if(decision.equals("K")) hit(gamerCards);
                        if(decision.equals("B")) bid();
                    }
                    
            } while(!decision.equals("P"));
            return sum;
        }
        
    //**
    //Implementacja metody dodającą dodatkową karte do krupiera albo gracza
    //**              
        public void hit(List<Integer> name) {
            int index = rand.nextInt(deckCards.size());
            name.add(deckCards.get(index));
            deckCards.remove(index);
        }
        
    //**
    //Podbicie stawki
    //**    
        public void bid() {
            if (bidup == false) {
                System.out.println("\nZ konta zostaje pobrane $80, ktore zwieksza stawke");
                Bank.getInst().addMoney(80);
                Gamer.getInst().substractMoney(80);
                bidup = true;
            } else System.out.println("\nDwa razy nie mozesz podbijac stawek.");
                
        }
        
    //**
    //Zmieniająca wartość ace do jednego
    //**    
        public void ace() {
            System.out.println("\n\nSuma kart jest wieksza od 21, ale w kartach gracza znajduje sie as i jego wartosc zmienila sie na 1...");
            gamerCards.set(gamerCards.lastIndexOf(11), 1);
            System.out.println("Nacisnij dowolny klawisz by kontynuowac.");
            Scanner input = new Scanner(System.in);
            input.nextLine();
                                 
        }    
    

    //**
    //Implementacja krupiera
    //1. Jeśli suma kart jest mniejsze badz rowne 18 to krupier bierze nową karte
    //2. Jeśli suma jest wieksze 18 i nie ma asa w kartach kurier czeka
    //3. Jeśli suma powyżej 23 i jest as, to as zmienia wartość na 1    
    //**       
        public int croupier() {
            int sum;
            boolean var = false;
            System.out.println("Ruchy krupiera");
            
            do {
            sum = 0;
            System.out.println("Krupier ma nastepujace karty:");
            for(int x: croupierCards) System.out.print(" "+x+ " ");
            for(int x: croupierCards)
                sum += x;
            System.out.println("Suma kart krupiera to: "+sum);
            if(sum > 18)
                if (sum>23&&croupierCards.contains(11)) {
                    croupierCards.set(croupierCards.lastIndexOf(11), 1);
                    System.out.println("W kartach byl as, a suma kart przekroczyla 23, wiec w miejsce asa wstawiamy wartosc 1");
                } else var = true;
            else {
                hit(croupierCards); System.out.println("Krupier dobiera karte"); 
            }
        } while(var == false);
            
            return sum;
        }  
   
    //**
    //Finał
    //**       
        public void letsStop(int gamer, int croupier) {
            System.out.printf("Wynik gracza: %15d\n", gamer);
            System.out.printf("Wynik krupiera: %15d\n", croupier);
            if (gamer > 23)
                if (croupier > 23) System.out.println("Nikt nie wygral.");
                else System.out.println("Gracz przegral");
            else if (gamer == 23) {
                System.out.println("Gracz wygral Blackjacka");
                if (bidup == true) {
                    System.out.println("Dodatkowo gracz podwoil zaklad.");
                    System.out.println("Na konto gracza wplywa $240.");
                    Bank.getInst().substractMoney(240);
                    Gamer.getInst().addMoney(240);
                    won++;
                }
                
                else {
                    System.out.println("Na konto gracza wplywa $120.");
                    Bank.getInst().substractMoney(120);
                    Gamer.getInst().addMoney(120);
                    won++;
                }
            }
            else if ((23-gamer) < (23-croupier)) {
                System.out.println("Gracz wygral z krupierem.");
                System.out.println("Na konto gracza wplywa $100.");
                Bank.getInst().substractMoney(100);
                Gamer.getInst().addMoney(100);
                won++; 
            }
            else if ((23-gamer) == (23 - croupier)) {
                System.out.println("Remis.");
            } else System.out.println("Gracz przegral.");

    }           
}
