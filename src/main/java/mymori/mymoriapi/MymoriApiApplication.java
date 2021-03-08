package mymori.mymoriapi;

import mymori.mymoriapi.repository.CardRepository;
import mymori.mymoriapi.repository.GameRepository;
import mymori.mymoriapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MymoriApiApplication {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    CardRepository cardRepository;

    public static void main(String[] args) {
        SpringApplication.run(MymoriApiApplication.class, args);
    }

}
