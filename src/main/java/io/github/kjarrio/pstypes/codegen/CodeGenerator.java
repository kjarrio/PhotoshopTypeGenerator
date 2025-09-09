package io.github.kjarrio.pstypes.codegen;

import io.github.kjarrio.pstypes.dto.Pair;
import java.io.File;
import java.util.List;

public interface CodeGenerator {

    void setOutputFolder(File outputFolder);
    void generate(String className, List<Pair> pairs);


}
