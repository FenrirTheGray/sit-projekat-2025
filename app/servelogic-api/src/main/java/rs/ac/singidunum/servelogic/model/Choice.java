package rs.ac.singidunum.servelogic.model;

public class Choice {

    private int amount;

    public Choice(){}
    public Choice(int amount){
        this.amount = amount;
    }
   
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() { return null; }
    public void setProduct(Product p) { }

    public double getTotal(){
        return 0.0;
    }
}
