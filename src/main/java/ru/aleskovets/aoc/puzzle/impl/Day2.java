package ru.aleskovets.aoc.puzzle.impl;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

@Component
public class Day2 extends PuzzleImpl {

    class Box {

        private final int l;
        private final int w;
        private final int h;

        Box(int l, int w, int h) {
            this.l = l;
            this.w = w;
            this.h = h;
        }

        Box(String boxDimensions) {
            String[] dimensions = boxDimensions.split("x");
            this.l = Integer.parseInt(dimensions[0]);
            this.w = Integer.parseInt(dimensions[1]);
            this.h = Integer.parseInt(dimensions[2]);
        }

        int side1Perimeter() {
            return w*l;
        }

        int side2Perimeter() {
            return w*h;
        }

        int side3Perimeter() {
            return l*h;
        }

        int surface() {
           return 2*side1Perimeter() + 2*side2Perimeter() + 2*side3Perimeter();
        }

        int minPerimeter() {
            return IntStream.of(side1Perimeter(), side2Perimeter(), side3Perimeter()).min().orElse(0);
        }

        int requiredPerimeter() {
            return surface() + minPerimeter();
        }
    }

    @Override
    public void run(InputStream inputStream) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
        int result = buffer.lines()
                .map(Box::new)
                .map(Box::requiredPerimeter)
                .reduce((sum, perimeter)-> sum + perimeter)
                .orElse(0);
        logger.info("Result: " + result);
    }
}