import java.util.HashMap;
import java.util.Map;

/**
 * Unit6Project
 * 
 * Greg Hammond, CMSC 315
 * 
 * Inventory lookup tool using a HashMap.
 *
 * You will practice:
 * - Storing inventory items by SKU
 * - Updating item quantities
 * - Looking up quantities
 * - Checking whether an SKU exists
 * - Removing inventory items
 *
 * -----------------------------------------------------------------
 * How the HashMap stores inventory data
 * -----------------------------------------------------------------
 * The HashMap uses the SKU as the key and the quantity
 * as the value.  When a key is added, Java computes the
 * keys hash code, reduces it to an index in a bucket array,
 * and stores the key/value pair there.  Because the hash code is
 * deterministic, the same SKU will always be mapped to the same bucket,
 * giving O(1) average‑time for insert, lookup, update and removal.
 *
 * -----------------------------------------------------------------
 * How SKU lookups work
 * -----------------------------------------------------------------
 * The containsKey, get and remove methods of HashMap internally
 * compute the hash of the supplied SKU, locate the correct bucket, and
 * then perform a quick equality check against the keys stored in that
 * bucket.  
 *
 * -----------------------------------------------------------------
 * How items are updated or removed
 * -----------------------------------------------------------------
 * - addOrUpdateItem uses put().  If the SKU is already present,
 *   put overwrites the old quantity otherwise it inserts a new entry.
 * - removeItem invokes remove.  The method returns the previous
 *   value, which we use to decide whether the removal succeeded.
 *
 * -----------------------------------------------------------------
 * Why hash tables are efficient for inventory systems
 * -----------------------------------------------------------------
 * Inventory operations are typically read‑heavy. You need to check the
 * stock level of a product many times.  A hash table offers
 * constant-time performance for frequent lookups, regardless of
 * how many different SKUs you store.  This makes it far faster than a
 * linear list or an array where you would have to search sequentially.
 */

public class Unit6Project {

    /** The internal map that holds SKU → quantity pairs. */
    private Map<String, Integer> inventory = new HashMap<>();

    /**
     * Adds a new SKU with its quantity, or updates the quantity if the SKU already exists.
     *
     * @param sku      
     * @param quantity 
     */
    public void addOrUpdateItem(String sku, int quantity) {
        // HashMap.put returns the previous value (or null if none).  We don't need that
        // information here; we simply store the new quantity, which overwrites any existing entry.
        inventory.put(sku, quantity);
    }

    /**
     * Returns the quantity stored for the given SKU.
     *
     * @param sku 
     * @return the quantity or null if the SKU is not present
    */
    public Integer getQuantity(String sku) {
        // HashMap.get returns null when the key is absent, matching the required contract.
        return inventory.get(sku);
    }

    /**
     * Checks whether the specified SKU exists in the inventory.
     *
     * @param sku the SKU to test
     * @return {@code true} if the SKU is present; {@code false} otherwise
     */
    public boolean containsSku(String sku) {
        // containsKey offers O(1) existence checking.
        return inventory.containsKey(sku);
    }

    /**
     * Removes the item identified by the supplied SKU.
     *
     * @param sku the SKU to remove
     * @return {@code true} if an entry was removed; {@code false} if the SKU was not found
     */
    public boolean removeItem(String sku) {
        // We use the result of remove to decide the boolean result.
        return inventory.remove(sku) != null;
    }

    /**
     * Returns the total number of distinct SKUs currently stored.
     *
     * @return the size of the inventory map
     */
    public int size() {
        return inventory.size();
    }

    public static void main(String[] args) {

        Unit6Project app = new Unit6Project();

        app.addOrUpdateItem("P100", 15);
        app.addOrUpdateItem("P200", 9);
        app.addOrUpdateItem("P100", 20); // update existing SKU

        System.out.println("Inventory size: " + app.size());
        System.out.println("P100 quantity: " + app.getQuantity("P100"));
        System.out.println("P200 quantity: " + app.getQuantity("P200"));
        System.out.println("Contains P300? " + app.containsSku("P300"));
        System.out.println("Contains P100? " + app.containsSku("P100"));
        System.out.println("Removing P200: " + app.removeItem("P200"));
        System.out.println("Inventory size after removal: " + app.size());
    }
}