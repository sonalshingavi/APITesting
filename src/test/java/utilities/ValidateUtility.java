package utilities;

import java.math.BigDecimal;
import java.util.regex.Pattern;
import models.Order;
import org.testng.asserts.SoftAssert;

public class ValidateUtility {

    // Validate email format using regex
    public static boolean isValidEmail(String email) {
        // Regex pattern for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Validate Order fields with SoftAssert
    public static void validateOrder(Order order) {
        if (order == null) {
            return;
        }
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotNull(order.getID(), "Order ID is null");
        softAssert.assertNotNull(order.getName(), "Name is null");
        softAssert.assertFalse(order.getName().trim().isEmpty(), "Name is empty or whitespace");
        softAssert.assertNotNull(order.getEmail(), "Email is null");
        softAssert.assertFalse(order.getEmail().trim().isEmpty(), "Email is empty or whitespace");
        softAssert.assertTrue(isValidEmail(order.getEmail()), "Email format is invalid");
        softAssert.assertTrue(order.getPrice() != null && order.getPrice().compareTo(BigDecimal.ZERO) > 0, "Price should be greater than 0");
        softAssert.assertNotNull(order.getTypeId(), "TypeId is null");
        softAssert.assertNotNull(order.getQuantity(), "Quantity is null");
        softAssert.assertTrue( order.getQuantity() > 0, "Quantity should be greater than 0");

        softAssert.assertAll();
    }
}
