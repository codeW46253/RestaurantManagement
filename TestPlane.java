/**
 * To test all class by checking their
 * input and output
 * 
 * @author Zul Iskandar
 * @version 1.0 [July 10, 2024]
 */

public class TestPlane {
    public TestPlane() {}

    public void testTimeHandling() {
        TimeHandling timeHandling = new TimeHandling();
        
        // Test Date Formats
        System.out.println("Test Date Formats:");
        timeHandling.setDate(2024, 7, 10);
        System.out.println("Format: DD_MM_YYYY " + timeHandling.getFormatedDate(TimeHandling.DD_MM_YYYY));
        System.out.println("Format: MN_DD_YYYY " + timeHandling.getFormatedDate(TimeHandling.MN_DD_YYYY));
        System.out.println("Format: MM_DD_YYYY " + timeHandling.getFormatedDate(TimeHandling.MM_DD_YYYY));
        System.out.println("Format: YYYY_MM_DD " + timeHandling.getFormatedDate(TimeHandling.YYYY_MM_DD));
        
        // Test 24-Hour Format
        System.out.println("\nTest 24-Hour Formats:");
        timeHandling.setTime(19, 20, 0, true);
        System.out.println("Format: HH_MM_24 " + timeHandling.getFormatedTime(TimeHandling.HH_MM_24));
        System.out.println("Format: HH_MM_SS_24 " + timeHandling.getFormatedTime(TimeHandling.HH_MM_SS_24));

        // Test Convert to 12-Hour Format
        System.out.println("\nTest Convert to 12-Hour Format:");
        timeHandling.switch24To12Format();
        System.out.println("Format: HH_MM_12 " + timeHandling.getFormatedTime(TimeHandling.HH_MM_12));
        System.out.println("Format: HH_MM_SS_12 " + timeHandling.getFormatedTime(TimeHandling.HH_MM_SS_12));

        // Test Convert to 24-Hour Format
        System.out.println("\nTest Convert to 24-Hour Format:");
        timeHandling.switch12To24Format();
        timeHandling.setPM(true);
        System.out.println("Format: HH_MM_24 " + timeHandling.getFormatedTime(TimeHandling.HH_MM_24));
        System.out.println("Format: HH_MM_SS_24 " + timeHandling.getFormatedTime(TimeHandling.HH_MM_SS_24));
    }

    public void testItemInformation() {
        ItemInformation itemInformation = new ItemInformation();

        // Test Set Item
        System.out.println("Test Set Item:");
        itemInformation.setItem("Test Item", 99.99, 4);
        System.out.println("Name: " + itemInformation.getItemName());
        System.out.println("Price:" + itemInformation.getItemPrice());
        System.out.println("Quantity: " + itemInformation.getQuantity());
        System.out.println("Total Price: " + itemInformation.getTotalPrice());
    }

    public void testOrderInformation() {
        OrderInformation orderInformation = new OrderInformation();
        TimeHandling timeHandling = new TimeHandling();

        timeHandling.setTime(21, 37, 0, true);

        // Order Information test
        orderInformation.setId("testId");
        orderInformation.setTime(timeHandling);

        System.out.println(orderInformation.getId());
        System.out.println(orderInformation.getTime().getFormatedTime(TimeHandling.HH_MM_24));

        // Item Order Test
        // Test Add Item
        System.out.println("Test Add:");
        orderInformation.addItemOrder(new ItemInformation("Test Item 1", 99.99, 99));
        orderInformation.addItemOrder(new ItemInformation("Test Item 2", 99.99, 99));
        orderInformation.addItemOrder(new ItemInformation("Test Item 3", 99.99, 99));

        for (ItemInformation item : orderInformation.getItemList()) {
            System.out.println(item.toString());
        }

        // Test Add Duplicate Item different details
        System.out.println("\nTest Add Duplicate:");
        orderInformation.addItemOrder(new ItemInformation("Test Item 1", 9.99, 9));
        
        for (ItemInformation item : orderInformation.getItemList()) {
            System.out.println(item.toString());
        }

        // Test Remove Item
        System.out.println("\nTest Remove:");
        orderInformation.removeItemOrder("Test Item 1");

        for (ItemInformation item : orderInformation.getItemList()) {
            System.out.println(item.toString());
        }

        // Test Edit Item
        System.out.println("\nTest Edit:");
        orderInformation.editItemQuantity("Test Item 2", 9);
        orderInformation.editItemPrice("Test Item 2", 9.99);
        orderInformation.editItemName("Test Item 2", "Test Item 4");

        orderInformation.editItemQuantity("Test Item 3", 9);
        orderInformation.editItemPrice("Test Item 3", 9.99);

        for (ItemInformation item : orderInformation.getItemList()) {
            System.out.println(item.toString());
        }

        // Test List and Grand Price
        System.out.println("\nTest List & Grand Price:");
        for (String itemName : orderInformation.getItemNameList()) {
            System.out.println(itemName);
        }
        System.out.println(orderInformation.getGrandPrice());
    }

    public void testCustomerInformation() {
        CustomerInformation customerInformation = new CustomerInformation();
        OrderInformation orderInformation = new OrderInformation();
        TimeHandling timeHandling = new TimeHandling();

        // Set Order Details
        timeHandling.setTime(23, 35, 0, true);

        orderInformation.setId("test01");
        orderInformation.setTime(timeHandling);

        orderInformation.addItemOrder(new ItemInformation("Test 1", 9.99, 1));
        orderInformation.addItemOrder(new ItemInformation("Test 2", 9.99, 1));
        orderInformation.addItemOrder(new ItemInformation("Test 3", 9.99, 1));

        // Test Customer information
        customerInformation.setId("testC01");
        customerInformation.setName("Admin");
        customerInformation.setTableNumber(0);

        customerInformation.setOrder(orderInformation);

        // Test Detail Retreival
        System.out.println("Test Detail Retreival:");
        System.out.println("Id: " + customerInformation.getId());
        System.out.println("Name: " + customerInformation.getName());
        System.out.println("Table Number: " + customerInformation.getTableNumber());
        System.out.println("Order Id: " + customerInformation.getOrder().getId());
        System.out.println("Grand Price: " + customerInformation.getOrder().getGrandPrice());
    }

    public void testMenu() {
        Menu menu = new Menu();
        
        // Test Get Full Menu
        System.out.println("Test Get Menu:");
        System.out.println(menu.getMenu());

        // Test Find Item using Id
        System.out.println("\nTest Find Item using Id:");
        System.out.println(menu.getItemById("BF01"));

        // Test Find Item using Name
        System.out.println("\nTest Find Item using Id:");
        System.out.println(menu.getItemByName("Test Item"));
    }

    public void testCustomerDetailReader() {
        CustomerDetailReader reader = new CustomerDetailReader("customerTestList.txt");

        // Test Get All Customer Detail
        System.out.println("Test Get All Customer Detail:");
        System.out.println(reader.getCustomerList());

        // Test Find Customer using Id
        System.out.println("\nTest Find Customer using Id:");
        System.out.println(reader.getCustomerInfoById("TC01"));

        // Test Find Customer using Name
        System.out.println("\nTest Find Customer using Name:");
        System.out.println(reader.getCustomerInfoByName("Test Customer 05"));

    }
    
}
