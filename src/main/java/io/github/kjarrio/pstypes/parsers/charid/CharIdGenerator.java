package io.github.kjarrio.pstypes.parsers.charid;

import io.github.kjarrio.pstypes.codegen.CodeGenerator;
import io.github.kjarrio.pstypes.codegen.JavaCodeGenerator;
import io.github.kjarrio.pstypes.codegen.TypescriptCodeGenerator;
import io.github.kjarrio.pstypes.dto.Pair;
import io.github.kjarrio.pstypes.parsers.TerminologyConstants;
import java.io.File;
import java.util.List;

public class CharIdGenerator {

    private final PITerminologyParser parser;

    public CharIdGenerator(File inputFile) {
        parser = new PITerminologyParser(inputFile);
        parser.parse();
    }

    public void generateCode(CodeGenerator codeGen) {
        for (String name : TerminologyConstants.CHAR_ID_NAMES) {
            codeGen.generate(name, parser.getPairs(name));
        }
    }

}
