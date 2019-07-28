package ri.b2w.digital.swapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArgumentSwitcher {

    private static final API apiCalls = new API();
    private GetRequestRepository repository = new GetRequestRepository(apiCalls);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Printer printer = new Printer();

    public JsonArray switcher(String searchquery) {

        JsonObject jsonObject = repository.getAll("planets", searchquery);
        JsonArray planetresults = jsonObject.getAsJsonArray("results");
//        printer.printDetailsPlanets(planetresults);
        
        return planetresults;
               
    }
}
