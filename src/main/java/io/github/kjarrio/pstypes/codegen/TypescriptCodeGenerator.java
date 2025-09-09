package io.github.kjarrio.pstypes.codegen;

import io.github.kjarrio.pstypes.utils.LineUtils;
import io.github.kjarrio.pstypes.dto.Pair;

import java.io.File;
import java.util.*;

public class TypescriptCodeGenerator extends AbstractCodeGenerator implements CodeGenerator {

    public TypescriptCodeGenerator(File outputFolder) {
        this.setOutputFolder(outputFolder);
    }

    @Override
    public void generate(String className, List<Pair> pairs) {

        lines.clear();

        lines.add("class "+className+" {");

        String appFunction = "charIDToTypeID";

        if (className.equals("PsString")) {
            appFunction = "stringIDToTypeID";
        }

        final String start = "    public static readonly ";

        for (Pair pair : pairs) {
            String comment = "";
            if (pair.hasComment()) comment = " // " + pair.getComment();
            lines.add(start + LineUtils.fixName(pair.getKey())+": number = app."+appFunction+"('"+pair.getValue()+"');" + comment);
        }

        lines.add("}");

        save(className+".ts");

    }

}
