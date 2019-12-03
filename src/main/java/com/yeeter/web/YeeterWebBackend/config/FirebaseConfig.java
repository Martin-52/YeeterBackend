package com.yeeter.web.YeeterWebBackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseAuth getAuthInstance() {
        return FirebaseAuth.getInstance();
    }
   
    @Bean
    public FirebaseDatabase getDbInstance() {
        return FirebaseDatabase.getInstance();
    }

    @PostConstruct
    public FirebaseApp init() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials
                        .fromStream(new ClassPathResource("/yeeter-backend.json").getInputStream()))
                .setDatabaseUrl("https://yeeter-backend.firebaseio.com/")
                .build();
        
        return FirebaseApp.initializeApp(options);
    }
}