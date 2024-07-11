/**
 * To manage Order for 
 * CustomerInformation order handling
 * 
 * @author Zul Iskandar
 * @version 1.0 [July 10th, 2024]
 */

import java.util.LinkedList;

class OrderInformation {
    // Atrributes
    private String       orderId;
    private TimeHandling orderTime;

    // Order items
    private LinkedList<ItemInformation> orderItem;

    // Constructor
    /** Set to default value */
    public OrderInformation() {
        orderItem = new LinkedList<ItemInformation>();

        orderId   = null;
        orderTime = new TimeHandling();
    }

    /** Set Order Information
     * 
     * @param id   - Set Order Id
     * @param time - Set Order time using TimeHandling
     */
    public OrderInformation(String id, TimeHandling time) {
        this.orderItem = new LinkedList<ItemInformation>();

        this.orderId   = id;
        this.orderTime = time;
    }

    /** Set Order Information
     * 
     * @param id   - Set Order Id
     * @param time - Set Order time using TimeHandling
     */
    public OrderInformation(String id, TimeHandling time, LinkedList<ItemInformation> orderItem) {
        this.orderItem = orderItem;

        this.orderId   = id;
        this.orderTime = time;
    }

    /** Set Order Information
     * 
     * @param oi - Pass other OrderInformation Object
     */
    public OrderInformation(OrderInformation oi) {
        this.orderItem = oi.orderItem;

        this.orderId   = oi.orderId;
        this.orderTime = oi.orderTime;
    }

    // Modifier
    /** Set Order Id
     * 
     * @param id - Order id
    */
    public void setId(String id) {
        this.orderId   = id;
    }

    /** Set Order Time
     * 
     * @param time - Order Time
    */
    public void setTime(TimeHandling time) {
        this.orderTime = time;
    }

    /** Add Item to item List
     * 
     * @param item - Item Information
    */
    public void addItemOrder(ItemInformation item) {
        // Check if item exist
        boolean itemExisted = false;
        for (ItemInformation testItem : orderItem) {
            if (testItem.getItemName().equals(item.getItemName())) {
                editItemPrice(testItem.getItemName(), item.getItemPrice());
                editItemQuantity(testItem.getItemName(), item.getQuantity());
                itemExisted |= true;
            }
        }

        // Add if item don't exist
        if (!itemExisted) orderItem.add(item);
    }

    /** Remove Item from item list
     * 
     * @param itemName - Item Name to remove from list
     */
    public void removeItemOrder(String itemName) {
        for (ItemInformation testItem : orderItem) {
            if (testItem.getItemName().equals(itemName)) {
                orderItem.remove(testItem);
                break;
            } 
        }
    }

    /** Edit Item Name
     * 
     * @param itemName_search - Item name provided to search in list
     * @param itemName_new    - New item name
     * 
     * @return 0 - Successfull
     * @return 1 - Item Not Exist
     */
    public int editItemName(String itemName_search, String itemName_new) {
        for (ItemInformation testItem : orderItem) {
            if (testItem.getItemName().equals(itemName_search)) {
                testItem.setItemName(itemName_new);
                return 0;
            } 
        }
        return 1;
    }

    /** Edit Item Name
     * 
     * @param itemName_search - Item name provided to search in list
     * @param itemPrice_new   - New item price
     * 
     * @return 0 - Successfull
     * @return 1 - Item Not Exist
     */
    public int editItemPrice(String itemName_search, double itemPrice_new) {
        for (ItemInformation testItem : orderItem) {
            if (testItem.getItemName().equals(itemName_search)) {
                testItem.setItemPrice(itemPrice_new);
                return 0;
            } 
        }

        return 1;
    }

    /** Edit Item Name
     * 
     * @param itemName_search - Item name provided to search in list
     * @param itemName_new    - New item name
     * 
     * @return 0 - Successfull
     * @return 1 - Item Not Exist
     */
    public int editItemQuantity(String itemName_search, int itemQuantity_new) {
        for (ItemInformation testItem : orderItem) {
            if (testItem.getItemName().equals(itemName_search)) {
                testItem.setItemQuantity(itemQuantity_new);
                return 0;
            } 
        }

        return 1;
    }

    // Accessor
    /** Get Order Id */
    public String getId() {
        return orderId;
    }

    /** Get Order Time */
    public TimeHandling getTime() {
        return orderTime;
    }

    /** Get Order List */
    public LinkedList<ItemInformation> getItemList() {
        return orderItem;
    }

    /** Get Item Name List */
    public String[] getItemNameList() {
        String[] nameList = new String[orderItem.size()];

        for (ItemInformation testItem : orderItem) {
            nameList[orderItem.indexOf(testItem)] = testItem.getItemName();
        }

        return nameList;
    }

    /** Get Item Price List */
    public double[] getItemPriceList() {
        double[] priceList = new double[orderItem.size()];

        for (ItemInformation testItem : orderItem) {
            priceList[orderItem.indexOf(testItem)] = testItem.getItemPrice();
        }

        return priceList;
    }

    /** Get Item Quantity List */
    public int[] getItemQuantityList() {
        int[] quantityList = new int[orderItem.size()];

        for (ItemInformation testItem : orderItem) {
            quantityList[orderItem.indexOf(testItem)] = testItem.getQuantity();
        }

        return quantityList;
    }

    /** Get Item Total Price List */
    public double[] getItemTotalPriceList() {
        double[] totalPriceList = new double[orderItem.size()];

        for (ItemInformation testItem : orderItem) {
            totalPriceList[orderItem.indexOf(testItem)] = testItem.getTotalPrice();
        }

        return totalPriceList;
    }

    /** Get Order Grand Price */
    public double getGrandPrice() {
        double grandPrice = 0.00;

        for (double totalPrice : getItemTotalPriceList()) {
            grandPrice += totalPrice;
        }

        return grandPrice;
    }

    /** Get Order Total Item */
    public int getTotalQuantity() {
        int totalQuantity = 0;

        for (int quantity : getItemQuantityList()) {
            totalQuantity += quantity;
        }

        return totalQuantity;
    }

    // toString
    /** Return the class as String */
    public String toString() {
        String itemListData = "";
        int index = -1;
        for (ItemInformation itemTest : orderItem) {
            index += 1;
            itemListData += itemTest.toString() + ((index == orderItem.size() - 1) ? "" : ",");
        }

        return String.format("{ OrderInformation: { Id: %s,  Item List: { %s }, Time: %s } }", orderId, itemListData, orderTime);
    }
}
