package io.github.kjarrio.pstypes.codegen;

import io.github.kjarrio.pstypes.dto.Pair;
import io.github.kjarrio.pstypes.utils.LineUtils;

import java.io.File;
import java.util.List;

public class JavaCodeGenerator extends AbstractCodeGenerator implements CodeGenerator {

    public JavaCodeGenerator(File outputFolder) {
        this.setOutputFolder(outputFolder);
    }

    @Override
    public void generate(String className, List<Pair> pairs) {

        lines.clear();

        lines.add("public class "+className+" {");

        final String start = "    public static final String ";

        for (Pair pair : pairs) {
            String comment = "";
            if (pair.hasComment()) comment = " // " + pair.getComment();

            lines.add(start + LineUtils.fixName(pair.getKey())+" = \""+pair.getValue()+"\";" + comment);
        }

        lines.add("}");

        save(className+".java");

    }
}
