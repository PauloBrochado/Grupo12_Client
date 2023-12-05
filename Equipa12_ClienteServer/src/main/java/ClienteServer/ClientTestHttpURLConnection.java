package ClienteServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Path;

import com.google.gson.Gson;

import stand.Cliente;
import stand.Carro;

public class ClientTestHttpURLConnection {

	public static void main(String[] args) {

		getCarro();
		getId(0);
		
	}

	private static void getCarro() {
		HttpURLConnection conn = null;

		try {

			URL url = new URL("http://localhost:8080/Equipa12/Carro/getCarro");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.connect();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			Gson gson = new Gson();
			List<Carro> carr = Arrays.asList(gson.fromJson(br, Carro[].class));
			System.out.println("Output JSON from Server .... \n");
			for (Carro car : carr) {
				System.out.println(car);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	private static void getId(int id) {
		HttpURLConnection conn = null;

		try {

			URL url = new URL("http://localhost:8080/Equipa12/carro/getId/" + Integer.toString(id));
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.connect();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			Gson gson = new Gson();
			Carro car = gson.fromJson(br, Carro.class);
			System.out.println("Output JSON from Server .... \n");
			System.out.println(car);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
