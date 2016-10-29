package casino;

import java.util.Scanner;

public class Casino {
    public static Scanner input = new Scanner(System.in);
    String decision;
    int cash;
    
    public Casino() {
        //**
        //Wiadomość informacyjna
        //**
        System.out.println("KRÓTKI SYMULATOR KASYNA W RAMACH PROJEKTU NA NJPO2");
        do {
            System.out.print("Podaj dokładną wartość Twoich pieniędzy: ");
            cash = input.nextInt();
            if (cash < 40) System.out.println("Najtansza gra kosztuje 40$, musisz isc pozyczyc od kogos pieniadze!");
        } while (cash < 40);
        
        Gamer.getInst().setState(cash);
        
        do {
            do {
                System.out.println("Naciśnij klawisz 'C' by ujrzeć ile masz pieniędzy.");
                System.out.println("Naciśnij klawisz 'B' by grać w BlackJacka ($80).");
                System.out.println("Naciśnij klawisz 'J' by grać w JednorekiegoBandyte ($40).");
                System.out.println("Naciśnij klawisz 'W' by wyjść z gry.");
                decision = input.next().toUpperCase();
            } while (!decision.equals("W")&&!decision.equals("B")&&!decision.equals("J")&&!decision.equals("C"));
            if (decision.equals("B"))
                if (Gamer.getInst().getState()>=80) BlackJack.inst().gameBlackJack();
                else System.out.println("Brak wystarczajacych funduszy na gre w BlackJacka");
            if (decision.equals("J"))
                if (Gamer.getInst().getState()>=40) JednorekiBandyta.inst().gameJednorekiBandyta();
                else System.out.println("Brak wystarczajacych funduszy na gre w JednorekiegoBandyte");
            if (decision.equals("S"))
            {
                System.out.println("Stan konta:"+Gamer.getInst().getState());
            }
            
        } while (Gamer.getInst().getState()>40 && !dec.equals("Q"));
        
        }
    }

   

