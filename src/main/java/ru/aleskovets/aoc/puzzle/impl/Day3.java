package ru.aleskovets.aoc.puzzle.impl;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class Day3 extends PuzzleImpl {

    @Override
    public void run(Path path) throws Exception {
        new BufferedReader(new InputStreamReader(Files.newInputStream(path)));
    }
}
