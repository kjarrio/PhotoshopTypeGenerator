package io.github.kjarrio.pstypes.utils;

import java.util.HashMap;
import java.util.Map;

public class ActionTypes {

    private final Map<String, String> name2type = new HashMap<>();
    private final Map<String, String> type2name = new HashMap<>();

    public ActionTypes() {
        add("Bookmark", "bkmk");
        add("XPlatFileSpec", "xpfs");
        add("Boolean", "bool");
        add("Char", "TEXT");
        add("Text", "TEXT");
        add("SInt16", "shor");
        add("SInt32", "long");
        add("UInt32", "magn");
        add("SInt64", "comp");
        add("UInt64", "ucom");
        add("IEEE32BitFloatingPoint", "sing");
        add("IEEE64BitFloatingPoint", "doub");
        add("128BitFloatingPoint", "ldbl");
        add("DecimalStruct", "decm");
        add("ObjectSpecifier", "obj ");
        add("AEList", "list");
        add("AERecord", "reco");
        add("AppleEvent", "aevt");
        add("EventRecord", "evrc");
        add("True", "true");
        add("False", "fals");
        add("Alias", "alis");
        add("Enumerated", "enum");
        add("Type", "type");
        add("AppParameters", "appa");
        add("Property", "prop");
        add("FSS", "fss ");
        add("FSRef", "fsrf");
        add("Keyword", "keyw");
        add("SectionH", "sect");
        add("WildCard", "****");
        add("ApplSignature", "sign");
        add("QDRectangle", "qdrt");
        add("Fixed", "fixd");
        add("ProcessSerialNumber", "psn ");
        add("ApplicationURL", "aprl");
        add("Null", "null");
        add("Extended", "exte");
        add("LongFloat", "doub");
        add("Path", "Pth ");
    }

    private void add(String name, String type) {
        name2type.put(name, type);
        type2name.put(type, name);
    }

}
