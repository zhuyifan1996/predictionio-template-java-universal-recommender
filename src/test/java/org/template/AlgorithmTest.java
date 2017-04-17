package org.template;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AlgorithmTest {
    @Test
    public void testGson1(){
        System.out.println("[TestGson1]");
        String shouldScore =
                "{\n"+
                        "   \"constant_score\": {\n" +
                        "  \"filter\": {\n" +
                        "  \"match_all\": {}\n"+
                        "},\n"+
                        "  \"boost\": 0\n"+
                        "  }\n"+
                        "}";
        System.out.println(new JsonParser().parse(shouldScore).getAsJsonObject());
    }

    @Test
    public void testGson2(){
        System.out.println("[TestGson2]");
        ArrayList<Integer> testL = new ArrayList<>();
        testL.add(1);
        testL.add(89);
        System.out.println(new Gson().toJson(testL));
    }

}