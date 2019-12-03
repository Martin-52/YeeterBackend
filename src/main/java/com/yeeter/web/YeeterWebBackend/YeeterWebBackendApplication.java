package com.yeeter.web.YeeterWebBackend;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class YeeterWebBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(YeeterWebBackendApplication.class, args);
		
		try {
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials
							.fromStream(new ClassPathResource("/yeeter-backend.json").getInputStream()))
					.setDatabaseUrl("https://yeeter-backend.firebaseio.com/")
					.build();
			
			FirebaseApp defaultApp = FirebaseApp.initializeApp(options);
			FirebaseDatabase defaultDatabase = FirebaseDatabase.getInstance(defaultApp);
			FirebaseAuth fireAuth = FirebaseAuth.getInstance(defaultApp);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
