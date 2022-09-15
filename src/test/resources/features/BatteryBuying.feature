Feature: Products buying

  Scenario: Buying a battery
    Given i open "https://www.dns-shop.ru/"
    When hover on "Аксессуары и услуги"
    Then submenu appeared
    Then click on "Батарейки и аккумуляторы"
    And click on "Батарейки"
    And click on "Купить"
    And click on "В корзине"
    Then number of items in the cart - 1

  Scenario: Deleting products from cart
    Given i open "https://www.dns-shop.ru/cart/"
    When number of items in the cart - 1
    Then click on "Удалить"
    Then given message "Коризна пуста"
