package io.github.kjarrio.pstypes.parsers.charid;

import java.util.List;

import static io.github.kjarrio.pstypes.parsers.TerminologyConstants.*;

public enum PITerminologyTypes {

    CLASS(PI_CLASSES, PI_CLASSES_PREFIX, "PsClass"),
    ENUM(PI_ENUMS, PI_ENUMS_PREFIX, "PsEnum"),
    EVENT(PI_EVENTS, PI_EVENTS_PREFIX, "PsEvent"),
    FORM(PI_FORMS, PI_FORMS_PREFIX, "PsForm"),
    KEY(PI_KEYS, PI_KEYS_PREFIX, "PsKey"),
    TYPE(PI_TYPES, PI_TYPES_PREFIX, "PsType"),
    UNIT(PI_UNITS, PI_UNITS_PREFIX, "PsUnit");

    private final String start;
    private final String prefix;
    private final String name;
    private final String end;

    PITerminologyTypes(String start, String prefix, String name) {
        this.start = start;
        this.prefix = prefix;
        this.name = name;
        this.end = PI_END + start;
    }

    public String getStart() {
        return start;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }

    public String getEnd() {
        return end;
    }

    public static List<PITerminologyTypes> getTypes() {
        return List.of(CLASS, ENUM, EVENT, FORM, KEY, TYPE, UNIT);
    }

}
