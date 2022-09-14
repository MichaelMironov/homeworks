package tests;

import components.PersonalBasket;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pages.MainPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PurchaseTransactionTest extends BaseTest {

    @Test
    @Order(1)
    void batteryBuyingTest() {

        MainPage
                .toProductCatalog()
                .selectBatterySection()
                .buyBattery()
                .purchaseCount(1);

    }

}
