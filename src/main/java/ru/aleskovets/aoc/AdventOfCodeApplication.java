package ru.aleskovets.aoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.aleskovets.aoc.puzzle.Puzzle;

import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class AdventOfCodeApplication {

	private static final String cookie = "session=53616c7465645f5f621f587e1179afd9f7bbc41242cb882358415d2e4c70845ab68818f67919cba1c63951b3a40c0964; _ga=GA1.2.271644391.1466421906";

    @Bean
    public Logger logger(){
        return LoggerFactory.getLogger("ApplicationOutput");
    }

	public static void main(String[] args) throws Exception {
        ApplicationContext app = SpringApplication.run(AdventOfCodeApplication.class, args);

		int day = Integer.parseInt(args[0]);
        Path path = Paths.get("src/main/resources/inputs/Day" + day);
        if (!Files.exists(path)) {
            URL url = new URL("http://adventofcode.com/day/" + day + "/input");
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Cookie", cookie);

            Files.createDirectories(path.getParent());
            Files.copy(connection.getInputStream(), path);
        }
        Class dayClass = Class.forName("ru.aleskovets.aoc.puzzle.impl.day" + day + ".Day" + day);
        Puzzle puzzle = (Puzzle) app.getBean(dayClass);

        puzzle.run(path);
    }
}