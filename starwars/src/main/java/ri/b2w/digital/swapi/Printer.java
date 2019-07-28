package ri.b2w.digital.swapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Printer {

    public Printer() {
    }

    API api = new API();
    GetRequestRepository repository = new GetRequestRepository(api);

    public void printDetailsPlanets(JsonArray planetresults) {

        if (planetresults.size() != 0)
            for (int i = 0; i < planetresults.size(); i++) {
                JsonObject planet = planetresults.get(i).getAsJsonObject();
                System.out.println("Planet : " + planet.get("name"));
                System.out.println("Rotation Period : " + planet.get("rotation_period"));
                System.out.println("Orbital Period : " + planet.get("orbital_period"));
                System.out.println("Diameter : " + planet.get("diameter"));
                System.out.println("Gravity : " + planet.get("gravity"));
                System.out.println("Terrain : " + planet.get("terrain"));
                System.out.println("Surface water : " + planet.get("surface_water"));
                System.out.println("Population : " + planet.get("population"));

                JsonArray residents = planet.getAsJsonArray("residents");
                System.out.println("");
                System.out.println("Residents :");
                System.out.println("");
                printSubCall("name", residents);
                System.out.println("");

                JsonArray films = planet.getAsJsonArray("films");
                System.out.println("");
                System.out.println("Films :");
                System.out.println("");
                printSubCall("title", films);
                System.out.println("");
            }
        else {
            System.out.println("Your search didn't get any results");
        }
    }

    //prints the underlying api calls in the array of the original call.
    private void printSubCall(String entity, JsonArray jsonArray) {
        if (jsonArray.size() != 0) {
            for (int j = 0; j < jsonArray.size(); j++) {
                JsonElement character = jsonArray.get(j);
                String uri = character.getAsString();
                JsonObject response = repository.innerRequest(uri);
                System.out.println(response.get(entity));
            }
        } else {
            System.out.println("nothing here");
        }
    }
}
