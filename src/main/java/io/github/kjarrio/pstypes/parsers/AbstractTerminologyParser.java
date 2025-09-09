package io.github.kjarrio.pstypes.parsers;

import io.github.kjarrio.pstypes.dto.Pair;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public abstract class AbstractTerminologyParser {

    protected final File inputFile;
    protected final List<String> lines;
    protected List<String> cleanLines;

    protected AbstractTerminologyParser(File inputFile) {

        this.inputFile = inputFile;

        try {
            lines = FileUtils.readLines(inputFile, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public abstract void parse();

    public File getInputFile() {
        return inputFile;
    }

    public List<String> getLines() {
        return lines;
    }

}
