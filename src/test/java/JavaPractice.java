import org.junit.jupiter.api.Test;

public class JavaPractice {

    @Test
    public void firstJavaCode() {
        Double distance = 135.7;
        Double fuelAmount = 20.00;

        double ticketMonthlyPrice = 50.00;
        int rideCount = 80;

        int myRidesCount = 2;

        double consumption = fuelAmount / distance * 100;

        double pricePerRide = calculatePricePerRide(rideCount);

        double myPricePerRide = calculatePricePerRide(myRidesCount);

        System.out.println("Current fuel consumption is " + consumption +
                " but using Trolley it costs " + pricePerRide + " Eur" + " per ride." +
                " I spend " + myPricePerRide + " Eur" + " per ride.");

        int a = 15;
        int b = 10;

        String c = "15";
        String d = "10";

        System.out.println(a + b);
        System.out.println(c + d);
    }

    private double calculatePricePerRide(int ridesCount) {
        final double PRICE = 50.00;

        double pricePerRide = PRICE / ridesCount;
        return pricePerRide;
    }
}
