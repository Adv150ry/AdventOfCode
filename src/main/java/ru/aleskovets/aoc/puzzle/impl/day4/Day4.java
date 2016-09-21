package ru.aleskovets.aoc.puzzle.impl.day4;

import ru.aleskovets.aoc.puzzle.impl.PuzzleImpl;
import ru.aleskovets.aoc.util.MD5;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 extends PuzzleImpl {

    @Override
    public void run(Path path) throws Exception {
        String inputCommands = Files.lines(path)
                .collect(Collectors.joining(""));

        String result = "";
        for (int i=0; i<10000000; i++) {
            String input = inputCommands + i;
            byte[] message = IntStream.range(0, input.length())
                    .collect(ByteArrayOutputStream::new, (baos, j) -> baos.write((byte) j),
                            (baos1, baos2) -> baos1.write(baos2.toByteArray(), 0, baos2.size()))
                    .toByteArray();
            String hash = MD5.toHexString(MD5.computeMD5(message));
            if (!hash.startsWith("00000")) return;

            result = hash;
        }
    }
}
