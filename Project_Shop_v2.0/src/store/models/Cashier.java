package store.models;

public class Cashier {
    private int id;
    private String name;
    private double monthlySalary;

    public Cashier(int id, String name, double monthlySalary) {
        this.id = id;
        this.name = name;
        this.monthlySalary = validateSalary(monthlySalary);
    }
    //Validate salary
    private double validateSalary(double monthlySalary){
        if(monthlySalary >= 0){
            return monthlySalary;
        }
        else{
            return 0;
        }
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return monthlySalary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double monthlySalary) {
        this.monthlySalary = validateSalary(monthlySalary);
    }

    @Override
    public String toString(){
        return "id = " + this.id + " - " + this.name + " with salary: " + this.monthlySalary;
    }
}
