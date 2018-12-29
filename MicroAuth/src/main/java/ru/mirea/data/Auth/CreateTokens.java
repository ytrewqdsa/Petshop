package ru.mirea.data.Auth;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

class CreateTokens{

    private static void fill_Payload(){

        String header = new JSONObject()
                .put("alg", "HS256")
                .put("typ", "JWT").toString();
        System.out.println(header);
        String eH = Base64.getEncoder().encodeToString(header.getBytes());
        System.out.println(eH);
        String payload = new JSONObject()
                .put("sub", "162606")
                .put("name", "Alex")
                .put("admin", true).toString();
        System.out.println(payload);
        String eP = Base64.getEncoder().encodeToString(payload.getBytes());
        System.out.println(eP);
        String s = eH + "." + eP;
        String signature = Base64.getEncoder().encodeToString(s.getBytes());
        System.out.println(signature);
        byte[] bytes = Base64.getDecoder().decode(s.getBytes());
        String decodedString = new String(bytes);
        System.out.println(decodedString);
    }

    public static void main(String []args) {
        fill_Payload();
    }
}