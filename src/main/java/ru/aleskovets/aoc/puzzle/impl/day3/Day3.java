package ru.aleskovets.aoc.puzzle.impl.day3;

import org.springframework.stereotype.Component;
import ru.aleskovets.aoc.puzzle.impl.PuzzleImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class Day3 extends PuzzleImpl {

    private static int currentX = 0;
    private static int currentY = 0;

    private static int currentRoboX = 0;
    private static int currentRoboY = 0;

    private static Coordinate calculateSantaCoordinate(char direction) {
        switch (direction) {
            case '>':
                ++currentX;
                break;
            case '^':
                ++currentY;
                break;
            case '<':
                --currentX;
                break;
            case 'v':
                --currentY;
                break;
        }
        return new Coordinate(currentX, currentY);
    }

    private static Coordinate calculateRoboCoordinate(char direction) {
        switch (direction) {
            case '>':
                ++currentRoboX;
                break;
            case '^':
                ++currentRoboY;
                break;
            case '<':
                --currentRoboX;
                break;
            case 'v':
                --currentRoboY;
                break;
        }
        return new Coordinate(currentRoboX, currentRoboY);
    }

    @Override
    public void run(Path path) throws Exception {
        String inputCommands = Files.lines(path)
                .collect(Collectors.joining(""));
        String test1 = "^v^v^v^v^v";
        String test2 = "^>v<";
        String test3 = "^v";

        Stream<Coordinate> santa = IntStream.range(0, inputCommands.length())
                .filter(i -> i % 2 == 0)
                .map(inputCommands::charAt)
                .mapToObj(i -> (char)i)
                .map(Day3::calculateSantaCoordinate);

        Stream<Coordinate> robo = IntStream.range(0, inputCommands.length())
                .filter(i -> i % 2 != 0)
                .map(inputCommands::charAt)
                .mapToObj(i -> (char)i)
                .map(Day3::calculateRoboCoordinate);

        long countWithRobo = Stream.concat(Stream.of(new Coordinate(0, 0)), Stream.concat(santa, robo))
                .distinct()
                .count();
        logger.info("Count distinct houses with robo: " + countWithRobo);
    }
}
