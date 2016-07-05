package ru.aleskovets.aoc.puzzle.impl.day2;

import java.util.stream.IntStream;

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

    int side1Square() {
        return w*l;
    }

    int side2Square() {
        return w*h;
    }

    int side3Square() {
        return l*h;
    }

    int side1Perimeter() {
        return 2*(w + l);
    }

    int side2Perimeter() {
        return 2*(w + h);
    }

    int side3Perimeter() {
        return 2*(l + h);
    }

    int volume() {
        return  l * w * h;
    }

    int surface() {
        return 2*side1Square() + 2*side2Square() + 2*side3Square();
    }

    int minSquare() {
        return IntStream.of(side1Square(), side2Square(), side3Square()).min().orElse(0);
    }

    int minPerimeter() {
        return IntStream.of(side1Perimeter(), side2Perimeter(), side3Perimeter()).min().orElse(0);
    }

    int requiredSquare() {
        return surface() + minSquare();
    }

    int requiredPerimeter() {
        return volume() + minPerimeter();
    }
}

