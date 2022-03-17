package obs.selenium;

public class Utility {

    public static String randomString(int n)
    {
      //  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"+ "abcdefghijklmnopqrstuvxyz";
        String AlphaNumericString ="suryasomaraj";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    public static void main(String[] args)
    {

        int n =10;
        System.out.println(Utility.randomString(n));
    }
}




