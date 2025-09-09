package io.github.kjarrio.pstypes.utils;

import java.util.ArrayList;
import java.util.List;
import static io.github.kjarrio.pstypes.parsers.TerminologyConstants.*;

public class LineUtils {

    public static String fixName(String name) {
        if (startsWithNumber(name)) {
            return "_"+name;
        }
        return name;
    }

    public static Boolean startsWithNumber(String name) {
        char chr = name.charAt(0);
        return (
                chr == '0' || chr == '1' || chr == '2' || chr == '3' || chr == '4' || chr == '5' ||
                chr == '6' || chr == '7' || chr == '8' || chr == '9'
        );
    }

    public static List<String> filterDefines(List<String> lines) {
        List<String> result = new ArrayList<>();

        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("#define ")) {
                if (line.equals("#define __PIStringTerminology_h__")) continue;
                result.add(line);
            }
        }

        return result;

    }

    public static List<String> removeCommentsAndEmptyLines(List<String> lines) {

        List<String> result = new ArrayList<>();

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;
            if (trimmed.equals(COMMENT_WARNING)) continue;
            if (trimmed.startsWith(COMMENT_START)) continue;
            if (trimmed.startsWith(COMMENT_MULTI_START)) continue;
            if (trimmed.startsWith(COMMENT_MULTI_MIDDLE)) continue;
            if (trimmed.startsWith(COMMENT_MULTI_END)) continue;
            result.add(line);
        }

        return result;

    }

    public static List<String> removePreprocessorLines(List<String> lines) {

        List<String> result = new ArrayList<>();

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;
            if (trimmed.startsWith(PRE_PROCESSOR)) continue;
            result.add(line);
        }

        return result;

    }


}
