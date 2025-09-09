package io.github.kjarrio.pstypes.parsers.charid;

import io.github.kjarrio.pstypes.parsers.AbstractTerminologyParser;
import io.github.kjarrio.pstypes.utils.LineUtils;
import io.github.kjarrio.pstypes.dto.Pair;
import io.github.kjarrio.pstypes.dto.Range;
import java.io.File;
import java.util.*;

import static io.github.kjarrio.pstypes.parsers.TerminologyConstants.PI_END;

public class PITerminologyParser extends AbstractTerminologyParser {

    private final Map<String, Range> typeLocations = new HashMap<>();
    private final Map<String, List<Pair>> idPairs = new HashMap<>();

    public PITerminologyParser(File inputFile) {

        super(inputFile);

        this.cleanLines = LineUtils.removeCommentsAndEmptyLines(lines);
        this.cleanLines = LineUtils.removePreprocessorLines(cleanLines);

    }

    public void parse() {

        for (PITerminologyTypes type : PITerminologyTypes.getTypes()) {
            findRange(type);
        }

        for (PITerminologyTypes type : PITerminologyTypes.getTypes()) {
            String name = type.getName();
            Range range = typeLocations.get(name);
            String prefix = type.getPrefix();

            List<Pair> pairs = new ArrayList<>();

            for (int i = range.getStartLine(); i <= range.getEndLine(); i++) {
                String line = cleanLines.get(i);
                String trimmedLine = line.trim();
                String comment = "";
                String key = "";
                String value = "";
                if (trimmedLine.startsWith(prefix)) {
                    trimmedLine = trimmedLine.substring(prefix.length());
                }
                if (trimmedLine.contains("//")) {
                    comment = trimmedLine.substring(trimmedLine.indexOf("//"));
                    trimmedLine = trimmedLine.substring(0, trimmedLine.indexOf("//")).trim();
                    comment = comment.replace("//", "").trim();
                }
                if (trimmedLine.endsWith(",")) {
                    trimmedLine = trimmedLine.substring(0, trimmedLine.length() - 1);
                }
                if (trimmedLine.contains(" = ")) {
                    key = trimmedLine.substring(0, trimmedLine.indexOf(" = "));
                }
                if (trimmedLine.contains("'")) {
                    value = trimmedLine.substring(trimmedLine.indexOf("'"), trimmedLine.lastIndexOf("'")).replace("'", "");
                }
                if (!key.isEmpty() && !value.isEmpty()) {
                    Pair pair = new Pair(key, value);
                    if (!comment.isEmpty()) {
                        pair.setComment(comment);
                    }
                    pairs.add(pair);

                }

            }

            if (!pairs.isEmpty()) {
                idPairs.put(name, pairs);
            }

        }

    }

    private void findRange(PITerminologyTypes type) {

        Range range = new Range();

        int lineNr = 0;

        Boolean hasStartLine = false;

        for (String line : cleanLines) {
            if (line.trim().equals(type.getStart())) {
                range.setStartLine(lineNr+2);
                hasStartLine = true;
            } else if (hasStartLine && line.trim().contains(PI_END) && line.contains(type.getStart())) {
                range.setEndLine(lineNr-1);
                break;
            }
            lineNr++;
        }

        typeLocations.put(type.getName(), range);

    }

    public List<Pair> getPairs(String name) {
        return idPairs.get(name);
    }

}
