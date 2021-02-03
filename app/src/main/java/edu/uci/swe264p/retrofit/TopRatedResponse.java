package edu.uci.swe264p.retrofit;

import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

/**
 * @author Mandy Tsai
 */
public class TopRatedResponse {
    private List<LinkedTreeMap> results;

    public TopRatedResponse(List<LinkedTreeMap> results) {
        this.results = results;
    }

    public List<LinkedTreeMap> getResults()  { return results; }
}
