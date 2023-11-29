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

	public static void main(String[] args) throws Exception {
		APIRunner runner = new APIRunner(); //AAAAAA
		Javalin app = Javalin.create(config -> {})
				// A demonstration of how to use code within an endpoint
				.get("/", ctx -> { ctx.html("Hello, World!"); })
				// Run the server on port 5000
				.start(5000);
	}

}
