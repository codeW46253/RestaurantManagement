/**
 * To manage Customer Information gathered
 * from RestaurantManagement subroutine
 * 
 * @author Zul Iskandar
 * @version 1.0 [July 10th, 2024]
 */

class CustomerInformation {
    // Attributes
    private String custId;
    private String custName;
    private int    tableNumber;

    private OrderInformation orderInformation;

    // Constructor
    /** Set to default value */
    public CustomerInformation() { // No Param Constructor
        custId      = null;
        custName    = null;
        tableNumber = 0;

        orderInformation = new OrderInformation();
    }

    /** Set Customer information
     * 
     * @param id          - Set Customer ID
     * @param name        - Set Customer Name
     * @param tableNumber - Set Sellected Table Number
     */
    public CustomerInformation(
        String id,
        String name,
        int tableNumber) {
        this.custId      = id;
        this.custName    = name;
        this.tableNumber = tableNumber;

        this.orderInformation = new OrderInformation();
    }

    /** Set Customer information
     * 
     * @param id          - Set Customer ID
     * @param name        - Set Customer Name
     * @param tableNumber - Set Sellected Table Number
     * 
     * @param orderInformation - Set OrderInformation
     */
    public CustomerInformation(
        String id,
        String name,
        int tableNumber,
        OrderInformation orderInformation) {
        this.custId      = id;
        this.custName    = name;
        this.tableNumber = tableNumber;

        this.orderInformation = orderInformation;
    }

    // Modifier
    /** Set Customer Id
     * 
     * @param id - Customer Id
    */
    public void setId(String id) {
        this.custId = id;
    }

    /** Set Customer Name
     * 
     * @param name - Customer Name
    */
    public void setName(String name) {
        this.custName = name;
    }

    /** Set Sellected Table Number
     * 
     * @param tableNumber - Sellected Table Number
    */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /** Set Order Information
     * 
     * @param orderinformation - Order Information
    */
    public void setOrder(OrderInformation orderInformation) {
        this.orderInformation = orderInformation;
    }

    /** Set Order Information
     * 
     * @param orderId   - Order Id
     * @param orderTime - Order Time
    */
    public void setOrder(String orderId, TimeHandling orderTime) {
        this.orderInformation = new OrderInformation(orderId, orderTime);
    }

    // Accessor
    /** Get Customer Id */
    public String getId() {
        return custId;
    }

    /** Get Customer Name */
    public String getName() {
        return custName;
    }

    /** Get Sellected Table */
    public int getTableNumber() {
        return tableNumber;
    }

    /** Get Order Information */
    public OrderInformation getOrder() {
        return orderInformation;
    }

    // toString
    /** Return the class as String */
    public String toString() {
        return String.format("{ CustomerInformation: { Id: %s, Name: %s, Table: %d, Order Information: %s } }",
        custId,
        custName,
        tableNumber,
        orderInformation.toString());
    }
}
