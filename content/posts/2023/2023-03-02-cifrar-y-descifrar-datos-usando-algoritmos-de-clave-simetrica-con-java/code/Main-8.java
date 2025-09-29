package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    ...

    public static String calculateHmac(SecretKey key, String text) throws Exception {
        Mac mac = Mac.getInstance("HMACSHA256");
        mac.init(key);
        byte[] bytes = mac.doFinal(text.getBytes());
        return HexFormat.of().formatHex(bytes);
    }
}

