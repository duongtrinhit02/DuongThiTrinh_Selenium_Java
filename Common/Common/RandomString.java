package Common;

import java.util.Random;

/**
 * RandomString
 * Generate random data for test cases
 */
public class RandomString {

   
    public static final Random random = new Random();

   
    public static final Integer NUMBER_EMAIL = 12;

    
    public static final String CHARACTOR =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    
    public static final String CHARACTOR_NUMBER = "0123456789";


    // ===== RANDOM STRING =====
    public static String generateRandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length is invalid.");
        }

        StringBuilder sBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTOR.length());
            sBuilder.append(CHARACTOR.charAt(index));
        }

        return sBuilder.toString();
    }


    // ===== RANDOM NUMBER STRING =====
    public static String generateRandomNumberString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length is invalid.");
        }

        StringBuilder sBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTOR_NUMBER.length());
            sBuilder.append(CHARACTOR_NUMBER.charAt(index));
        }

        return sBuilder.toString();
    }


    // ===== RANDOM EMAIL =====
    public static String generateRandomEmail(String domain) {
        return generateRandomString(NUMBER_EMAIL) + "@" + domain;
    }

    public static String generateRandomEmail() {
        return generateRandomString(NUMBER_EMAIL) + "@gmail.com";
    }
}
