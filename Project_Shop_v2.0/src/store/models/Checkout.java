package store.models;

public class Checkout {
    private int id;
    private Cashier cashier;

    public Checkout(int id) {
        this.id = id;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public boolean isAvailable() {
        try{
            this.cashier.getName();
            return true;
        }
        catch (NullPointerException ex){
            return false;
        }
    }

    @Override
    public String toString(){

        try{
            return "Checkout " + this.id + " with cashier: " + this.cashier.getName();
        }
        catch (NullPointerException ex){
            return "Checkout " + this.id + " with no cashier";
        }
    }
}
