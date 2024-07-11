/**
 * To manage Item Information for
 * OrderInformation Items handling
 * 
 * @author Zul Iskandar
 * @version 1.0 [July 7th, 2024]
 */

public class ItemInformation {
    // Attributes
    private String itemName;
    private double itemPrice;
    private int    quantity;

    private double totalPrice;

    // Constructor
    /** Set to default value */
    public ItemInformation() { //No Param Constructor
        itemName  = null;
        itemPrice = 0.0;
        quantity  = 0;

        totalPrice = itemPrice * quantity;
    }

    /** Set Item Information */
    public ItemInformation(String itemName, double itemPrice, int quantity) {
        this.itemName  = itemName;
        this.itemPrice = itemPrice;
        this.quantity  = quantity;
        calcTotalPrice();
    }

    // Modifier
    /** Set Item Information */
    public void setItem(String itemName, double itemPrice, int quantity) {
        this.itemName  = itemName;
        this.itemPrice = itemPrice;
        this.quantity  = quantity;
        calcTotalPrice();
    }

    /** Set Item Name */
    public void setItemName(String itemName) {
        this.itemName  = itemName;
    }

    /** Set Item Price */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
        calcTotalPrice();
    }

    /** Set Item Quantity */
    public void setItemQuantity(int quantity) {
        this.quantity = quantity;
        calcTotalPrice();
    }

    /** Calculate Total Price */
    private void calcTotalPrice() {
        this.totalPrice = this.itemPrice * this.quantity;
    }

    // Accessor
    /** Get Item Name */
    public String getItemName() {
        return itemName;
    }

    /** Get Item Price */
    public double getItemPrice() {
        return itemPrice;
    }

    /** Get Item Quantity */
    public int getQuantity() {
        return quantity;
    }

    /** Get Total Price */
    public double getTotalPrice() {
        calcTotalPrice();
        return totalPrice;
    }

    // toString
    /** Return the class as String */
    public String toString() {
        return String.format("{ ItemInformation: { Name: %s, Price: %.2f, Quantity: %d, Total Price: %.2f } }",
        itemName,
        itemPrice,
        quantity,
        totalPrice);
    }
}
