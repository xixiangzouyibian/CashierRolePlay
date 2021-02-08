import org.junit.Before;
import org.junit.Test;
import org.martin.Orders;

public class Customer1 {
    private Orders orders;

    @Before
    public void init() {
        orders = new Orders();
        orders.bulkScan("apple,apple");
        orders.bulkScan("cola");
        orders.bulkScan("cola zero");
        orders.bulkScan("eggs");
    }

    @Test
    public void testCancel1() {
        System.out.println(orders.toString());
        orders.cancel("milk", 1);
        System.out.println(orders.toString());
    }

    @Test
    public void testCancel2() {
        System.out.println(orders.toString());
        orders.cancel("milk", null);
        System.out.println(orders.toString());
    }

    @Test
    public void testDiscount() {
        System.out.println(orders.toString());
        orders.discount("apple", "5%");
        orders.discount("cola", "10%");
        System.out.println(orders.toString());
    }

    @Test
    public void testWrongDiscount() {
        System.out.println(orders.toString());
        orders.discount("apple", "120%");
        orders.discount("cola", "10%");
        System.out.println(orders.toString());
    }

    @Test
    public void testSpecialOffers() {
        System.out.println(orders.toString());
        orders.bulkScan("apple"); // add 1 more apple
        System.out.println(orders.toString());
    }

    @Test
    public void testCrossProductDiscount1() {
        System.out.println(orders.toString());
        orders.bulkScan("milk"); // add  more milk, not free to give
        System.out.println(orders.toString());
    }

    @Test
    public void testCrossProductDiscount2() {
        System.out.println(orders.toString());
        orders.bulkScan("milk, milk"); // add 2 more milks, 1 free to give
        System.out.println(orders.toString());
    }

    @Test
    public void testCrossProductDiscount3() {
        System.out.println(orders.toString());
        orders.bulkScan("milk,milk,milk"); // add 2 more milks, should be same as above
        System.out.println(orders.toString());
    }
}
