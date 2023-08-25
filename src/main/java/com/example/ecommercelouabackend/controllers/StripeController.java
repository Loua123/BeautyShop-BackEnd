package com.example.ecommercelouabackend.controllers;

import java.util.HashMap;
import java.util.Map;

import com.example.ecommercelouabackend.entities.CheckoutPayment;
import com.fasterxml.jackson.databind.ObjectMapper; // Import Jackson's ObjectMapper
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.bind.annotation.*;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@RestController
@CrossOrigin("*")
public class StripeController {
    // create an ObjectMapper object
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/payment")
    /**
     * Payment with Stripe checkout page
     *
     * @throws StripeException
     */
    public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
        // We initialize stripe object with the api key
        init();
        // We create a stripe session parameters
        SessionCreateParams params = SessionCreateParams.builder()
                // We will use the credit card payment method
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                        .builder().setName(payment.getName()).build())
                                                .build())
                                .build())
                .build();
        // create a stripe session
        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap<>();
        // We get the sessionId and we put it inside the response data; you can get more info from the session object
        responseData.put("id", session.getId());
        // We can return only the sessionId as a JSON String using Jackson's ObjectMapper
        try {
            return objectMapper.writeValueAsString(responseData);
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Handle the exception appropriately
        }
    }

    private static void init() {
        Stripe.apiKey = "sk_test_51NWNG5KWg2Q9YGOMTUUofcZRDBSUEpXdjVsbHm9Pnx4t20qxGgajN5EosdEgVMrgdXJbR5GMvFCTvIUuUsFx5nuS00tbU1E7hz";
    }
}
