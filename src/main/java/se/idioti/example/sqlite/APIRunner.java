package se.idioti.example.sqlite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;

/**
 * This demonstrates how to expose the storage through a REST API using Spark.
 * 
 * @author "Johan Holmberg, Malmö university"
 * @since 1.0
 */
public class APIRunner {

	private Storage storage = null;
	private Gson gson = null;

	public APIRunner() {
		try {
			storage = new Storage();
			storage.setup();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		// Set a decent date format
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}

	public void getMyUnicorns(){
	storage.fetchUnicorns();

	// Denna ska returera en lista av Unicorns.
	}

	public void getUnicorn(int id){
		storage.fetchUnicorn(id);
		
	}

	public static void main(String[] args) throws Exception {
		APIRunner runner = new APIRunner();
		Javalin app = Javalin.create(config -> {});
		// A demonstration of how to use co	de within an endpoint

		app.get("/", ctx -> {

			runner.getMyUnicorns();});  // Alltså metoden ovan
		app.get("/{id}", ctx -> {
			ctx.html("Du gav mig ett id: ");
		});
		app.post("/", ctx -> {

		});
		app.put("/{id}",ctx -> {

		});

		app.delete("/{id}", ctx -> {

		});

		// Run the server on port 5000
		app.start(5000);
	}

}
