package io.github.kjarrio.pstypes.parsers.stringid;

import io.github.kjarrio.pstypes.dto.Pair;
import io.github.kjarrio.pstypes.parsers.AbstractTerminologyParser;
import io.github.kjarrio.pstypes.utils.LineUtils;
import java.io.File;
import java.util.*;

public class PIStringTerminologyParser extends AbstractTerminologyParser {

    private final List<Pair> pairs = new ArrayList<>();
    private List<String> cleanLines;

    public PIStringTerminologyParser(File inputFile) {

        super(inputFile);

        this.cleanLines = LineUtils.removeCommentsAndEmptyLines(lines);
        this.cleanLines = LineUtils.filterDefines(cleanLines);

    }

    public void parse() {

        for (String line : cleanLines) {
            line = line.trim().replace("#define ", "");
            String[] split = line.split(" ");
            if (split.length != 2) continue;
            String key = split[0];
            String value = split[1].replace("\"", "");
            if (key.startsWith("k")) {
                key = key.substring(1);
            }
            String firstChar = key.substring(0, 1);
            if (firstChar.toLowerCase().equals(firstChar)) {
                firstChar = firstChar.toUpperCase();
                key = firstChar + key.substring(1);
            }
            if (key.endsWith("Str")) {
                key = key.substring(0, key.length() - 3);
            }
            Pair pair = new Pair(key, value);
            pairs.add(pair);
        }

    }

    public List<Pair> getPairs() {
        return pairs;
    }


}
