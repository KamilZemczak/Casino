package casino;

public class Gamer {
    
    private static Gamer inst;
    private static int state;
    
    public static Gamer getInst() {
        if(inst == null)
            inst = new Gamer();
        return inst;
    }

    /**
     * @return the state
     */
    public static int getState() {
        return state;
    }

    /**
     * @param aState the state to set
     */
    public static void setState(int aState) {
        state = aState;
    }
    
    public void addMoney(int x) {
        state += x;
    }
    
    public void substractMoney(int x) {
        state -= x;
    }
    
    
    
}
