/**
 * Handling Item and Menu
 * 
 * @author Zul Iskandar
 * @version 1.0 [July 11th, 2024]
 */
import java.util.*;
import java.io.*;

public class Menu {
    // Attributes
    private LinkedList<String> itemIds;
    private LinkedList<ItemInformation> itemInfos;

    private FileReader reader;
    private BufferedReader buffer;

    public Menu() {
        itemIds = new LinkedList<>();
        itemInfos = new LinkedList<>();

        try {
            reader = new FileReader("menu.txt");
            buffer = new BufferedReader(reader);

            String line = null;
            while ((line = buffer.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "|");

                String itemId    = tokenizer.nextToken();
                String itemName  = tokenizer.nextToken();
                double itemPrice = Double.parseDouble(tokenizer.nextToken());

                itemIds.add(itemId);
                itemInfos.add(new ItemInformation(itemName, itemPrice, 1));
            }
        }
        catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe) {

        }
    }

    public LinkedList<ItemInformation> getMenu() {
        return itemInfos;
    }

    public ItemInformation getItemById(String id) {
        int index = -1;
        boolean idExist = false;
        for (String itemId : itemIds) {
            index += 1;
            if (itemId.equals(id)) {
                idExist |= true;
                break;
            }
        }

        if (idExist) return itemInfos.get(index);
        return new ItemInformation();
    }

    public ItemInformation getItemByName(String name) {
        int index = 0;
        boolean nameExist = false;
        for (ItemInformation testItem : itemInfos) {
            if (testItem.getItemName().equals(name)) {
                index = itemInfos.indexOf(testItem);
                nameExist |= true;
                break;
            }
        }

        if (nameExist) return itemInfos.get(index);
        return new ItemInformation();
    }
}
