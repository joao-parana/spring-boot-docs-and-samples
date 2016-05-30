package sample.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import com.github.scribejava.apis.TrelloApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuthService;
import static com.github.scribejava.core.model.SignatureType.*;

// FIXME: https://github.com/scribejava/scribejava/issues/663

public class TestSetup {
	String PROTECTED_RESOURCE_URL = "https://trello.com/1/members/me";

	public static void main(String[] args) {
		System.out.println("SomaBoot: Set up integration test environment");
		try {
			new TestSetup();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SomaBootRuntimeException e) {
			e.printStackTrace();
		}
	}

	public TestSetup() throws IOException, SomaBootRuntimeException {
		Properties properties = getSetupProperties();
		String apiKey = properties.getProperty("api_key");
		String apiSecret = properties.getProperty("api_secret");
		OAuthService trelloService = new ServiceBuilder().apiKey(apiKey).apiSecret(apiSecret)
				.signatureType(QueryString).build(TrelloApi.instance());
		System.out.println("api_key = " + apiKey + ", api_secret = " + apiSecret);
		System.out.println(trelloService.getVersion());
		System.out.println(trelloService.getConfig().getCallback());
		//

		// SomaBoot somaBoot = new SomaBoot();
		// Scanner scanner = new Scanner(System.in);
		//
		// System.out.println("Follow this URL to authorise yourself on SomaBoot
		// oAuth");

		System.out.println("=== Trello's OAuth Workflow ===");
		System.out.println();

		// Obtain the Request Token
		System.out.println("Fetching the Request Token...");
		final OAuth1RequestToken requestToken = ((OAuth10aService) trelloService).getRequestToken();
		System.out.println("Got the Request Token!");
		System.out.println("requestToken = " + requestToken);

		System.out.println("Now go and authorize ScribeJava here:");
		String authorizationUrl = ((OAuth10aService) trelloService).getAuthorizationUrl(requestToken);
		System.out.println(authorizationUrl);
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		final Scanner in = new Scanner(System.in);
		final String oauthVerifier = in.nextLine();
		System.out.println();

		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		final OAuth1AccessToken accessToken = ((OAuth10aService) trelloService).getAccessToken(requestToken,
				oauthVerifier);
		System.out.println("Got the Access Token!");
		System.out.println("(if your curious it looks like this: " + accessToken + ", 'rawResponse'='"
				+ accessToken.getRawResponse() + "')");
		System.out.println();

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL,
				((OAuth10aService) trelloService));
		((OAuth10aService) trelloService).signRequest(accessToken, request);
		final Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getBody());

		System.out.println();
		System.out.println("Thats it man! Go and build something awesome with ScribeJava! :)");
		in.close();
	}

	public Properties getSetupProperties() {
		String propertiesFile = "src/test/resources/setup.properties";
		File currentDir = new File(".");
		String absPath = currentDir.getAbsolutePath();
		System.out.println(absPath.substring(0, absPath.length() - 1) + propertiesFile);
		// "src/test/resources/setup.properties";
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(propertiesFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(properties);
		return properties;
	}
}
