package casino;

public class Gamer {
    
    private static Gamer inst;
    private static int state;
    
    //**
    //Wykorzystanie Singletona
    //**
    public static Gamer getInst() {
        if(inst == null)
            inst = new Gamer();
        return inst;
    }

    /**
     * @return the state
     */
    public int getState() {
        return this.state;
    }

    /**
     * @param aState the state to set
     */
    public void setState(int aState) {
        this.state = aState;
    }
    
    public void addMoney(int aState) {
        state += aState;
    }
    
    public void substractMoney(int aState) {
        state -= aState;
    }
    
    
    
}
