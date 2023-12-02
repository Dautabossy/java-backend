package se.idioti.example.sqlite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

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

	public String getMyUnicorns(){
		List<Unicorn> unicorns = storage.fetchUnicorns();
		String jsonArray = gson.toJson(unicorns);

		// Denna ska returera en lista av Unicorns.

		return jsonArray;
	}

	public String getUnicorn(int id){
		return gson.toJson(storage.fetchUnicorn(id));
	}

	public void addUnicorn(Unicorn unicorn){

		storage.addUnicorn(unicorn);
	};

	public static void main(String[] args) throws Exception {

		APIRunner runner = new APIRunner();
		Javalin app = Javalin.create(config -> {});
		// A demonstration of how to use co	de within an endpoint

		app.get("/", ctx -> {

			String xxx = runner.getMyUnicorns();
			ctx.html(xxx);
		});  // Alltså metoden ovan
		app.get("/{id}", ctx -> {
			String id = ctx.pathParam("id");



			ctx.json(runner.getUnicorn(Integer.parseInt(id)));

		});
		app.post("/", ctx -> {

			runner.createUnicorn(ctx); });

		app.put("/{id}",ctx -> {

		});

		app.delete("/{id}", ctx -> {

		});

		// Run the server on port 5000
		app.start(5000);
	}

	private void createUnicorn(Context ctx) {
		Unicorn unicorn = gson.fromJson(ctx.body(), Unicorn.class);
		storage.addUnicorn(unicorn);
	}

}
