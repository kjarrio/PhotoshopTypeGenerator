package io.github.kjarrio.pstypes.parsers;

import java.util.List;

public class TerminologyConstants {

    public static final String FILE_CHAR_ID = "PITerminology.h";
    public static final String URL_CHAR_ID = "https://raw.githubusercontent.com/AdobeDocs/photoshop-cpp-sdk/refs/heads/main/pluginsdk/photoshopapi/photoshop/PITerminology.h";

    public static final String FILE_STRING_ID = "PIStringTerminology.h";
    public static final String URL_STRING_ID = "https://raw.githubusercontent.com/AdobeDocs/photoshop-cpp-sdk/refs/heads/main/pluginsdk/photoshopapi/photoshop/PIStringTerminology.h";

    public static final List<String> CHAR_ID_NAMES = List.of("PsClass", "PsEnum", "PsEvent", "PsForm", "PsKey", "PsType", "PsUnit");
    public static final String STRING_ID_NAME = "PsString";

    public static final String PI_CLASSES = "enum PIClasses";
    public static final String PI_ENUMS = "enum PIEnums";
    public static final String PI_EVENTS = "enum PIEvents";
    public static final String PI_FORMS = "enum PIForms";
    public static final String PI_KEYS = "enum PIKeys";
    public static final String PI_TYPES = "enum PITypes";
    public static final String PI_UNITS = "enum PIUnits";

    public static final String PI_END = "};";

    public static final String COMMENT_WARNING = "/////// DO NOT ADD ANYTHING HERE - SEE WARNING AT TOP OF FILE ///////////";
    public static final String COMMENT_START = "//";
    public static final String COMMENT_MULTI_START = "/**";
    public static final String COMMENT_MULTI_MIDDLE = "* ";
    public static final String COMMENT_MULTI_END = "*/";

    public static final String PRE_PROCESSOR = "#";

    public static final String PI_CLASSES_PREFIX = "class";
    public static final String PI_ENUMS_PREFIX = "enum";
    public static final String PI_EVENTS_PREFIX = "event";
    public static final String PI_FORMS_PREFIX = "form";
    public static final String PI_KEYS_PREFIX = "key";
    public static final String PI_TYPES_PREFIX = "type";
    public static final String PI_UNITS_PREFIX = "unit";


}
