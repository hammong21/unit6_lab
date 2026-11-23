import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
 * Unit6Project JUnit tests – updated to use JUnit 4 imports.
 */
public class Unit6ProjectTest {

    @Test
    public void removeMissingItemReturnsFalse() {
        // Create a fresh inventory instance
        Unit6Project inventory = new Unit6Project();

        // Add a single known SKU
        inventory.addOrUpdateItem("P100", 15);
        int sizeBefore = inventory.size();

        // Attempt to remove a SKU that was never added
        boolean removed = inventory.removeItem("P999");

        // Verify that removal failed
        assertFalse("removeItem should return false for a missing SKU", removed);

        // Verify that the inventory size did not change
        assertEquals("Inventory size must remain unchanged after a failed removal",
                     sizeBefore, inventory.size());
    }
}
