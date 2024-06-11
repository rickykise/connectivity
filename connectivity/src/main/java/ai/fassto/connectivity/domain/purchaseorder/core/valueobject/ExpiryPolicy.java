package ai.fassto.connectivity.domain.purchaseorder.core.valueobject;

public record ExpiryPolicy(
        Boolean managed,

        /** expiryDateTolerance
         *  If the managed field is true, this is the Expiry date Tolerance for shipping to a customer.
         *  How many days left before expiry can Fassto allow at a minimum.
         */
        Integer expiryDateTolerance,

        /** expiryDateTolerance
         *  If the managed field is true, this is the Expiry date Tolerance for alarm to a customer.
         *  How many days left before expiry can Fassto allow at a minimum.
         */
        Integer expiryDateAlarmTolerance
) {
}
