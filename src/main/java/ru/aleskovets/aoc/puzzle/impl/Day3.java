package ru.aleskovets.aoc.puzzle.impl;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class Day3 extends PuzzleImpl {

    private int currentX = 0;
    private int currentY = 0;

    private class CoordinateChange {

        CoordinateChange(String direction) {

        }
    }

    private class Coordinate {

        private final int x;
        private final int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }


        Coordinate(String direction) {
            switch (direction) {
                case ">":
                    currentX++;
                    break;
                case "^":
                    currentY++;
                    break;
                case "<":
                    currentX--;
                    break;
                case "v":
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
    }

    @Override
    public void run(Path path) throws Exception {
        Stream<Coordinate> input = new BufferedReader(new InputStreamReader(Files.newInputStream(path)))
                .lines()
                .map(Coordinate::new);
        long count = Stream.concat(Stream.of(new Coordinate(0, 0)), input)
                .distinct()
                .count();

        logger.info("Count distinct houses: " + count);
    }
}
