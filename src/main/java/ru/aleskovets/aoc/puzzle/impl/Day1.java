package ru.aleskovets.aoc.puzzle.impl;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class Day1 extends PuzzleImpl {

    public void run(Path path) throws Exception {
        int floor = Files.lines(path)
                .mapToInt((parenthesis) -> parenthesis == '(' ? 1 : -1)
                .reduce((currentFloor, action) -> );
        String input = IOUtils.toString(Files.newInputStream(path), "UTF-8");
        int result = 0;
        boolean basementFound = false;
        for(int i=0; i < input.length(); i++) {
            if (input.charAt(i) == '(' ) {
                result++;
            } else if (input.charAt(i) == ')' ) {
                result--;
            }
            if(!basementFound && result == -1) {
                logger.info("At basement: " + (i+1));
                basementFound = true;
            }
        }
        logger.info("Result: " + result);
    }
}
