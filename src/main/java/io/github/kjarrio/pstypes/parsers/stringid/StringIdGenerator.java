package io.github.kjarrio.pstypes.parsers.stringid;

import io.github.kjarrio.pstypes.codegen.CodeGenerator;
import java.io.File;

import static io.github.kjarrio.pstypes.parsers.TerminologyConstants.STRING_ID_NAME;

public class StringIdGenerator {

    private final PIStringTerminologyParser parser;

    public StringIdGenerator(File inputFile) {
        parser = new PIStringTerminologyParser(inputFile);
        parser.parse();
    }

    public void generateCode(CodeGenerator codeGen) {
        codeGen.generate(STRING_ID_NAME, parser.getPairs());
    }

}
