package ru.aleskovets.aoc.puzzle.impl.day2;

import org.springframework.stereotype.Component;
import ru.aleskovets.aoc.puzzle.impl.PuzzleImpl;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class Day2 extends PuzzleImpl {

    @Override
    public void run(Path path) throws Exception {
        int paper = Files.lines(path)
                .map(Box::new)
                .map(Box::requiredSquare)
                .reduce((sum, square)-> sum + square)
                .orElse(0);
        logger.info("Paper required: " + paper);

        int ribbon = Files.lines(path)
                .map(Box::new)
                .map(Box::requiredPerimeter)
                .reduce((sum, perimeter)-> sum + perimeter)
                .orElse(0);
        logger.info("Ribbon required: " + ribbon);
    }
}