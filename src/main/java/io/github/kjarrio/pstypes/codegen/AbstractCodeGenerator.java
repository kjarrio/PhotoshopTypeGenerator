package io.github.kjarrio.pstypes.codegen;

import io.github.kjarrio.pstypes.dto.Pair;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCodeGenerator implements CodeGenerator {

    protected final List<String> lines = new ArrayList<>();
    protected File outputFolder;

    @Override
    public void setOutputFolder(File outputFolder) {
        this.outputFolder = outputFolder;
    }

    @Override
    public abstract void generate(String className, List<Pair> pairs);

    protected Boolean save(String classFileName) {
        try {
            File outputFile = new File(outputFolder, classFileName);
            FileUtils.writeLines(outputFile, lines);
            if (outputFile.exists()) {
                System.out.println("File Written: " + outputFile.getAbsolutePath());
                return true;
            }
            return false;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

}
