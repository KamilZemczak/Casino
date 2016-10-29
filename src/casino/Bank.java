package casino;

public class Bank {
    
    private static Bank inst;
    private int state = 1000000;
    
    //**
    //Wykorzystanie Singletona
    //**
    public static Bank getInst() {
        if(inst == null)
            inst = new Bank();
        return inst;
    }
    
    public int getMoney() {
        return state;
    }
    
    public void addMoney(int aMoney) {
        state += aMoney;
    }
    
    public void substractMoney(int aMoney) {
        state -= aMoney;
    }
    
}
