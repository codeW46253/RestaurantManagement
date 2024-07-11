/**
 * To manage a restaurant customers
 * flow and managing counters
 * 
 * @author Zul Iskandar
 * @version 1.0 [July 11th, 2024]
 */
import java.util.*;

public class RestaurantManagement {
    // Read All Customer Information
    private CustomerDetailReader custDetail;

    // Inputs
    private Scanner terminalInput;

    private String custName;
    private String custId;
    private int    tableNumber;
    private CustomerInformation customerInformation;
    private OrderInformation orderInformation;

    // List
    private LinkedList<CustomerInformation> customerInformations;

    private Queue<CustomerInformation> counter1CustList;
    private Queue<CustomerInformation> counter2CustList;
    private Queue<CustomerInformation> counter3CustList;

    private Stack<CustomerInformation> completeStack;

    public RestaurantManagement() {
        custDetail = new CustomerDetailReader("customer.txt");
        customerInformations = new LinkedList<CustomerInformation>();
        customerInformations = custDetail.getCustomerList();

        counter1CustList = new LinkedList<CustomerInformation>(); 
        counter2CustList = new LinkedList<CustomerInformation>();
        counter3CustList = new LinkedList<CustomerInformation>();

        completeStack = new Stack<CustomerInformation>();

        terminalInput = new Scanner(System.in);

        boolean runtime = true;
        while (runtime) {
            System.out.print(
                "Restaurant Management \n" +
                "                      \n" +
                "Management Option:    \n" +
                "1) [L]oad Customer    \n" +
                "2) [C]heck Counters   \n" +
                "3) Check C[o]mpleted  \n" +
                "Enter >>> "
            );
            String enteredKey = terminalInput.nextLine();

            if (enteredKey.equalsIgnoreCase("L")) {
                loadCustomer();
            }

            if (enteredKey.equalsIgnoreCase("C")) {
                checkCounter();
            }

            if (enteredKey.equalsIgnoreCase("O")) {
                checkComplete();
            }
        }
    }

    private void displayCustomerInfo() {
        System.out.println("Loaded Customer Detail:");
        System.out.println("Name        : " + custName);
        System.out.println("Id          : " + custId);
        System.out.println("Table Number: " + tableNumber);

        System.out.println("Order Detail");
        System.out.println("Order Id: " + orderInformation.getId());
        for (ItemInformation itemInformation : orderInformation.getItemList()) {
            System.out.println(String.format("Item Name: %20s, %3d ", itemInformation.getItemName(), itemInformation.getQuantity()));
        }

        if (customerInformation.getOrder().getTotalQuantity() > 5) {
            counter3CustList.add(customerInformation);
            customerInformation = null;
        }
        else {
            if (counter1CustList.size() >= 5) {
                counter2CustList.add(customerInformation);
                customerInformation = null;
            }
            else {
                counter1CustList.add(customerInformation);
                customerInformation = null;
            }
        }
    }

    private void loadCustomer() {
        if (customerInformation == null) {
            if (!customerInformations.isEmpty()) {    
                CustomerInformation infoBuffer = customerInformations.pop();

                custName    = infoBuffer.getName();
                custId      = infoBuffer.getId();
                tableNumber = infoBuffer.getTableNumber();

                orderInformation = new OrderInformation(infoBuffer.getOrder());

                customerInformation = new CustomerInformation(custId, custName, tableNumber, orderInformation);

                displayCustomerInfo();

            } else System.out.println("Done");
        } else displayCustomerInfo();

    }

    private void checkCounter() {
        boolean checking = true;
        while (checking) {
            System.out.print(
                "Check Counter  \n" +
                "1) Counter [1] \n" +
                "2) Counter [2] \n" +
                "3) Counter [3] \n" +
                "4) Exit    [4] \n" +
                "Enter >>> "
            );
            String enteredKey = terminalInput.nextLine();

            if (enteredKey.equals("1")) {
                boolean runCounter1 = true;
                while (runCounter1) {
                    System.out.print(
                        "Counter 1 Action:  \n" +
                        "1) [D]isplay       \n" +
                        "2) [P]ass          \n" +
                        "4) [E]xit          \n" +
                        "Enter >>> "
                    );
                    String enteredActionKey = terminalInput.nextLine();

                    if (enteredActionKey.equalsIgnoreCase("D")) {
                        System.out.println("Counter 1:");
                        for (CustomerInformation custInfo : counter1CustList) {
                            System.out.println(String.format("Name: %30s | Total Price: %5.2f | Total Item: %5d",custInfo.getName(), custInfo.getOrder().getGrandPrice(), custInfo.getOrder().getTotalQuantity()));
                        }
                    }

                    if (enteredActionKey.equalsIgnoreCase("P")) {
                        if (!counter1CustList.isEmpty()) completeStack.add(counter1CustList.poll());
                    }

                    if (enteredActionKey.equalsIgnoreCase("E")) {
                        runCounter1 = false;
                    }
                }
            }

            if (enteredKey.equals("2")) {
                boolean runCounter2 = true;
                while (runCounter2) {
                    System.out.print(
                        "Counter 2 Action:  \n" +
                        "1) [D]isplay       \n" +
                        "2) [P]ass          \n" +
                        "4) [E]xit          \n" +
                        "Enter >>> "
                    );
                    String enteredActionKey = terminalInput.nextLine();

                    if (enteredActionKey.equalsIgnoreCase("D")) {
                        System.out.println("Counter 2:");
                        for (CustomerInformation custInfo : counter2CustList) {
                            System.out.println(String.format("Name: %30s | Total Price: %5.2f | Total Item: %5d",custInfo.getName(), custInfo.getOrder().getGrandPrice(), custInfo.getOrder().getTotalQuantity()));
                        }
                    }

                    if (enteredActionKey.equalsIgnoreCase("P")) {
                        if (!counter1CustList.isEmpty()) completeStack.add(counter1CustList.poll());
                    }

                    if (enteredActionKey.equalsIgnoreCase("E")) {
                        runCounter2 = false;
                    }
                }
            }

            if (enteredKey.equals("3")) {
                boolean runCounter3 = true;
                while (runCounter3) {
                    System.out.print(
                        "Counter 3 Action:  \n" +
                        "1) [D]isplay       \n" +
                        "2) [P]ass          \n" +
                        "4) [E]xit          \n" +
                        "Enter >>> "
                    );
                    String enteredActionKey = terminalInput.nextLine();

                    if (enteredActionKey.equalsIgnoreCase("D")) {
                        System.out.println("Counter 3:");
                        for (CustomerInformation custInfo : counter3CustList) {
                            System.out.println(String.format("Name: %30s | Total Price: %5.2f | Total Item: %5d",custInfo.getName(), custInfo.getOrder().getGrandPrice(), custInfo.getOrder().getTotalQuantity()));
                        }
                    }

                    if (enteredActionKey.equalsIgnoreCase("P")) {
                        if (!counter1CustList.isEmpty()) completeStack.add(counter1CustList.poll());
                    }

                    if (enteredActionKey.equalsIgnoreCase("E")) {
                        runCounter3 = false;
                    }
                }
            }

            if (enteredKey.equals("4")) checking = false;
        }
    }

    private void checkComplete() {
        System.out.println("Complete:");
        for (CustomerInformation custInfo : completeStack) {
            System.out.println(String.format("Name: %30s | Total Price: %5.2f | Total Item: %5d",custInfo.getName(), custInfo.getOrder().getGrandPrice(), custInfo.getOrder().getTotalQuantity()));
        }
    }
}
