package casino;

import java.util.Scanner;

public class CasinoGame {
    public static Scanner input = new Scanner(System.in);
    String decision;
    int money;
    
    public CasinoGame() {
        //**
        //Wiadomość informacyjna
        //**
        System.out.println("KRÓTKI SYMULATOR KASYNA W RAMACH PROJEKTU NA NJPO2.");
        
        //**
        //Sprawdzenie pieniędzy od gracza
        //**
        do {
            System.out.print("Podaj dokładną wartość Twoich pieniędzy:");
            money = input.nextInt();
            if (money < 40) System.out.println("Najtansza gra kosztuje 40$, musisz isc pozyczyc od kogos pieniadze!");
        } while (money < 40);
        
        Gamer.getInst().setState(money);
        
        //**
        //Menu
        //**
        do {
            do {
                System.out.println("Nacisnij klawisz 'C' by ujrzec ile masz pieniedzy.");
                System.out.println("Nacisnij klawisz 'B' by grać w BlackJacka ($80).");
                System.out.println("Nacisnij klawisz 'J' by grać w JednorekiegoBandyte ($40).");
                System.out.println("Nacisnij klawisz 'W' by wyjsc z gry.");
                decision = input.next().toUpperCase();
            } while (!decision.equals("W")&&!decision.equals("B")&&!decision.equals("J")&&!decision.equals("C"));
            if (decision.equals("B"))
                if (Gamer.getInst().getState()>=80) BlackJack.inst().gameBlackJack();
                else System.out.println("Brak wystarczajacych funduszy na gre w BlackJacka");
            if (decision.equals("J"))
                if (Gamer.getInst().getState()>=40) JednorekiBandyta.inst().gameJednorekiBandyta();
                else System.out.println("Brak wystarczajacych funduszy na gre w JednorekiegoBandyte");
            if (decision.equals("C"))
            {
                System.out.println("Stan konta:"+Gamer.getInst().getState());
            }
            
        } while (Gamer.getInst().getState()>40 && !decision.equals("W"));
        
        
        //**
        //Końcowe informacje dla użytkownika
        //**
        System.out.println("Rozegranych gier w BlackJacka:" +BlackJack.inst().getGames());
        if(BlackJack.inst().getGames() > 0) System.out.printf("Stosunek zwyciestw: %3.5f\n",BlackJack.inst().getBilance());
        
        System.out.println("Rozegranych gier w JednorekiegoBandyte:" +JednorekiBandyta.inst().getGames());
        if(JednorekiBandyta.inst().getGames() > 0) System.out.printf("Stosunek zwyciestw: %3.5f\n",JednorekiBandyta.inst().getBilance());
        
        System.out.println("Bilans: "+(1000000-Bank.getInst().getMoney()));
        
        }
    }

   

