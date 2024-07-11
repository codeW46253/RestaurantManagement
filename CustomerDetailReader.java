/**
 * To manage Customer Detail Reader from
 * a file
 * 
 * @author Zul Iskandar
 * @version 1.0 [July 10th, 2024] - create CustomerDetailReader
 * @version 1.01 [July 11th] - add time reader
 */
import java.io.*;
import java.util.*;

public class CustomerDetailReader {
    // Attributes
    // File Reader
    private FileReader fileReader;
    private BufferedReader buffer;
    private StringTokenizer tokenizer;

    private OrderInformation orderInformation;
    private LinkedList<CustomerInformation> customerDetails;

    // Constructor
    /** Read File Content and convert to an accesssible
     * LinkedList
     * 
     * @param filename - A file no read from
     */
    public CustomerDetailReader(String filename) {
        customerDetails = new LinkedList<CustomerInformation>();
        orderInformation    = new OrderInformation();

        try {
            // read file
            fileReader = new FileReader(filename);
            buffer     = new BufferedReader(fileReader);

            // Collect all customer informations
            String line = null;
            while ((line = buffer.readLine()) != null) {
                tokenizer = new StringTokenizer(line, "|");

                // Format: custName | custId | tableNumber | orderItemId1 | orderItemQuantity1 | orderItemId2 | orderItemQuantity2 | ... [v1.0]
                // Format: custName | custId | tableNumber | time_hour | time_minute | orderItemId1 | orderItemQuantity1 | orderItemId2 | orderItemQuantity2 | ... 
                String custName    = tokenizer.nextToken();
                String custId      = tokenizer.nextToken();
                String tableNumber = tokenizer.nextToken();

                String time_hour   = tokenizer.nextToken();
                String time_minute = tokenizer.nextToken();

                // Get Order Information
                orderInformation = new OrderInformation();
                while (tokenizer.hasMoreTokens()) {
                    Menu checkMenu = new Menu();
                    String          itemId       = tokenizer.nextToken();
                    ItemInformation gatheredItem = checkMenu.getItemById(itemId);

                    orderInformation.setTime(new TimeHandling(
                        Integer.parseInt(time_hour),
                        Integer.parseInt(time_minute),
                        0,
                        0,
                        0,
                        0,
                        true
                    ));
                    orderInformation.addItemOrder(new ItemInformation(gatheredItem.getItemName(), gatheredItem.getItemPrice(), Integer.parseInt(tokenizer.nextToken())));
                }
                
                customerDetails.add(new CustomerInformation(custId, custName, Integer.parseInt(tableNumber), orderInformation));
            }
        }
        catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getLocalizedMessage());
        }
        catch (IOException ioe) {
            System.out.println(ioe.getLocalizedMessage());
        }

    }

    // Accessor
    /** Get All Customer Detail */
    public LinkedList<CustomerInformation> getCustomerList() {
        return customerDetails;
    }

    /** Find Customer Info by using Id
     * 
     * @param id - Customer Id
     */
    public CustomerInformation getCustomerInfoById(String id) {
        int index = 0;
        boolean idExist = false;
        for (CustomerInformation customerDetail : customerDetails) {
            if (customerDetail.getId().equals(id)) {
                index = customerDetails.indexOf(customerDetail);
                idExist |= true;
                break;
            }
        }

        if (idExist) return customerDetails.get(index);
        return new CustomerInformation();
    }

    /** Find Customer Info by using Name
     * 
     * @param name - Customer Name
     */
    public CustomerInformation getCustomerInfoByName(String name) {
        int index = 0;
        boolean nameExist = false;
        for (CustomerInformation customerDetail : customerDetails) {
            if (customerDetail.getName().equals(name)) {
                index = customerDetails.indexOf(customerDetail);
                nameExist |= true;
                break;
            }
        }

        if (nameExist) return customerDetails.get(index);
        return new CustomerInformation();
    }
    
}
