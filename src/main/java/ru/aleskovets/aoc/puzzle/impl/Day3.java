package ru.aleskovets.aoc.puzzle.impl;

import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class Day3 extends PuzzleImpl {

    private int currentX = 0;
    private int currentY = 0;

    private class Coordinate {

        private final int x;
        private final int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }


        Coordinate(char direction) {
            switch (direction) {
                case '>':
                    currentX++;
                    break;
                case '^':
                    currentY++;
                    break;
                case '<':
                    currentX--;
                    break;
                case 'v':
                    currentY--;
                    break;
            }
            this.x = currentX;
            this.y = currentY;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinate that = (Coordinate) o;

            if (x != that.x) return false;
            return y == that.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private static char invert(char direction) {
        switch (direction) {
            case '>':
                return '<';
            case '^':
                return 'v';
            case '<':
                return '>';
            case 'v':
                return '^';
            default:
                return direction;
        }
    }

    @Override
    public void run(Path path) throws Exception {
        Stream<Coordinate> input = Files.lines(path)
                .collect(Collectors.joining(""))
                .chars()
                .mapToObj(i -> (char)i)
                .map(Coordinate::new);
        long count = Stream.concat(Stream.of(new Coordinate(0, 0)), input)
                .distinct()
                .count();

        logger.info("Count distinct houses: " + count);

        currentX = 0;
        currentY = 0;
        String inputCommands = Files.lines(path)
                .collect(Collectors.joining(""));
        String test1 = "^v^v^v^v^v";
        String test2 = "^>v<";
        String test3 = "^v";

        Stream<Coordinate> santa = IntStream.range(0, inputCommands.length())
                .filter(i -> i % 2 == 0)
                .map(inputCommands::charAt)
                .mapToObj(i -> (char)i)
                .map(Coordinate::new);

        currentX = 0;
        currentY = 0;
        Stream<Coordinate> robo = IntStream.range(0, inputCommands.length())
                .filter(i -> i % 2 != 0)
                .map(inputCommands::charAt)
                .mapToObj(i -> (char)i)
                .map(Day3::invert)
                .map(Coordinate::new);

        long countWithRobo = Stream.concat(Stream.of(new Coordinate(0, 0)), Stream.concat(santa, robo))
                .distinct()
                .count();
        logger.info("Count distinct houses with robo: " + countWithRobo);
    }
}
