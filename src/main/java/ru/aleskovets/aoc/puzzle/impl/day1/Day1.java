package ru.aleskovets.aoc.puzzle.impl.day1;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import ru.aleskovets.aoc.puzzle.impl.PuzzleImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Component
public class Day1 extends PuzzleImpl {

    public void run(Path path) throws Exception {
        int floor = Files.lines(path)
                .collect(Collectors.joining(""))
                .chars()
                .mapToObj(i -> (char)i)
                .map((chr) -> chr == '(' ? 1 : -1)
                .reduce((currentFloor, action) -> currentFloor + action)
                .orElse(0);
        logger.info("Floor: " + floor);


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
    }
}
