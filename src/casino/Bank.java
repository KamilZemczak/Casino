package casino;

public class Bank {
    
    private static Bank inst;
    private int state = 100000;
    
    public static Bank getInst() {
        if(inst == null)
            inst = new Bank();
        return inst;
    }
    
    public int getMoney() {
        return state;
    }
    
    public void addMoney(int x) {
        state += x;
    }
    
    public void substractMoney(int x) {
        state -= x;
    }
    
}
