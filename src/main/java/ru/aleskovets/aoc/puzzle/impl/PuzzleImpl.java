package ru.aleskovets.aoc.puzzle.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.aleskovets.aoc.puzzle.Puzzle;

public abstract class PuzzleImpl implements Puzzle {

    @Autowired
    protected Logger logger;
}
