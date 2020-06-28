import org.junit.jupiter.api.Test;

public class FirstHomework {
    final Double creditTermInYears = 10.0;
    final int loanAmount = 20000;
    final Double percentPerYear = 7.5;

    @Test
    public void calculateCredit() {
        Double totalPayout = calculateTotalPayout(loanAmount, creditTermInYears, percentPerYear);
        System.out.println("If you take " + loanAmount + " EUR in Mortgage credit with " + percentPerYear +
                "% per year, in the end you will pay " + totalPayout + " EUR in total.");
    }

    private Double calculateTotalPayout(Integer loanAmount, Double creditTermInYears, Double percentPerYear) {
        return loanAmount * (1 + creditTermInYears * percentPerYear / 100);
    }

    @Test
    public void calculateChars() {

        String countAllCharsInThisString = "  Can you     count all chars in this useless sentence?";
        int charAmount = 0;

        for (int i = 0; i < countAllCharsInThisString.length(); i++) {
            if (Character.isLetter(countAllCharsInThisString.charAt(i)))
                charAmount++;
        }
        String[] result = countAllCharsInThisString.trim().replaceAll(" +", " ").split(" ");
        System.out.println("Total amount of symbol in this string is " + countAllCharsInThisString.length() + ".");
        System.out.println("Total amount of char in this string is " + charAmount + ".");
        System.out.println("String has " + result.length + " words.");
    }
}
