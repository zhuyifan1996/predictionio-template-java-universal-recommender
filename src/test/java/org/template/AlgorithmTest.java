package org.template;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AlgorithmTest {
    Algorithm algorithm;
    Gson gson;
    // copied from AlgorithmParamsTest
    String paramsJson = "{\n" +
            "\t\"appName\": \"handmade\",\n" +
            "\t\"indexName\": \"urindex\",\n" +
            "\t\"typeName\": \"items\",\n" +
            "\t\"recsModel\": null,\n" +
            "\t\"eventNames\": [\"purchase\", \"view\"],\n" +
            "\t\"blacklistEvents\": [\"delete\"],\n" +
            "\t\"maxQueryEvents\": \"4\",\n" +
            "\t\"maxEventsPerEventType\": \"4\",\n" +
            "\t\"maxCorrelaatorsPerEeventType\": \"4\",\n" +
            "\t\"num\": \"4\",\n" +
            "\t\"userBias\": \"5\",\n" +
            "\t\"itemBias\": \"5\",\n" +
            "\t\"returnSelf\": \"false\",\n" +
            "\"fields\": [{\n" +
            "\t\t\"name\": \"item1\",\n" +
            "\"values\": [\"val1\", \"val2\"]," +
            "\t\t\"bias\": 2.0\n" +
            "\t}]," +
            "\"rankings\": [{\n" +
            "\t\t\"name\": \"nameValue\",\n" +
            "\t\t\"backFill\": \"\",\n" +
            "\"eventNames\": [\"purchase\", \"view\"]," +
            "\"offsetDate\": \"2017-08-15T11:28:45.114-07:00\",\n" +
            "\t\t\"endDate\": \"2017-08-15T11:28:45.114-07:00\"," +
            "\t\t\"duration\": \"\"\n" +
            "\t}]," +
            "\t\"availableDateName\": \"available\",\n" +
            "\t\"expireDateName\": \"expires\",\n" +
            "\t\"dateName\": \"date\",\n" +
            "\t\"indicators\": [{\n" +
            "\t\t\"name\": \"nameValue\",\n" +
            "\t\t\"maxItemsPerUser\": 4,\n" +
            "\t\t\"maxCorrelatorsPerItem\": 4,\n" +
            "\t\t\"minLLR\": 4.3\n" +
            "\t}],\n" +
            "\t\"seed\": \"4\"\n" +
            "}";

    @Before
    public void init() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(new DateRangeTypeAdapterFactory());
        this.gson = Converters.registerDateTime(builder).create();

        AlgorithmParams ap = gson.fromJson(paramsJson, AlgorithmParams.class);
        algorithm = new Algorithm(ap);
    }

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

    @Test
    public void testBuildQueryShould1(){
        System.out.println("[testBuildQueryShould1]");
        ArrayList<Algorithm.BoostableCorrelators> boostables = new ArrayList<>();
        Query query = new Query("user1", 1f, "item1",1f,
                new ArrayList<Field>(), null, null, new ArrayList<String>(),
                false, 20, new ArrayList<String>(), false);
        System.out.println(algorithm.buildQueryShould(query, boostables));
    }

    @Test
    public void realTestCalcPop(){

    }

}