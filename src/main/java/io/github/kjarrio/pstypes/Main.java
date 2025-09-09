package io.github.kjarrio.pstypes;

import io.github.kjarrio.pstypes.codegen.CodeGenerator;
import io.github.kjarrio.pstypes.codegen.JavaCodeGenerator;
import io.github.kjarrio.pstypes.codegen.TypescriptCodeGenerator;
import io.github.kjarrio.pstypes.parsers.CmdLineArgs;
import io.github.kjarrio.pstypes.parsers.charid.CharIdGenerator;
import io.github.kjarrio.pstypes.parsers.stringid.StringIdGenerator;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        CmdLineArgs cmdLineArgs = new CmdLineArgs(args);
        Boolean valid = cmdLineArgs.validate();
        if (!valid || !cmdLineArgs.isValid()) {
            System.exit(1);
        }

        CharIdGenerator charIdGenerator = new CharIdGenerator(cmdLineArgs.getCharTermsFile());
        StringIdGenerator stringIdGenerator = new StringIdGenerator(cmdLineArgs.getStringTermsFile());

        List<CodeGenerator> codeGenerators = new ArrayList<>();

        if (cmdLineArgs.getGenerateTS()) codeGenerators.add(new TypescriptCodeGenerator(cmdLineArgs.getOutputFolder()));
        if (cmdLineArgs.getGenerateJava()) codeGenerators.add(new JavaCodeGenerator(cmdLineArgs.getOutputFolder()));

        for (CodeGenerator codeGenerator : codeGenerators) {
            charIdGenerator.generateCode(codeGenerator);
            stringIdGenerator.generateCode(codeGenerator);
        }

    }
}
