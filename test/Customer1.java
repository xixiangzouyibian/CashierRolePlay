import org.junit.BeforeClass;
import org.martin.Orders;

public class Customer1 {
    private Orders orders;

    @BeforeClass
    private void init() {
        orders = new Orders();
        orders.bulkScan("apple");
        orders.bulkScan("milk,milk,milk");
        orders.bulkScan("cola zero");
    }

    private void testScan() {

    }
}
