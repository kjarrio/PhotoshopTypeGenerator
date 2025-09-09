package io.github.kjarrio.pstypes.parsers;

import io.github.kjarrio.pstypes.utils.DownloadHelper;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.*;

public class CmdLineArgs {

    private final String VERSION = "0.1-SNAPSHOT";
    private final Options options = new Options();
    private final String[] args;
    private Boolean valid = false;
    private File outputFolder = null;
    private File inputFolder = null;

    private File charTermsFile = null;
    private File stringTermsFile = null;
    private Boolean generateTS = false;
    private Boolean generateJava = false;

    public CmdLineArgs(String[] args) {
        this.args = args;
        this.createOptions();
    }

    private void createOptions() {
        options.addOption("l", "lang", true, "Target language (java/ts)");
        options.addOption("h", "help", false, "Print out help and exit");
        options.addOption("v", "version", false, "Print out version and exit");
        options.addOption("o", "output", true, "Output folder");
        options.addOption("i", "input", true, "Input folder containing Terminology files from cpp");
    }

    public Boolean validate() {

        DefaultParser parser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(options, args, true);
        } catch (ParseException e) {
            printError(e.getMessage());
            return false;
        }

        assert commandLine != null;

        if (commandLine.getOptionValue('v') != null) {
            printTitleAndVersion();
            return false;
        }

        if (commandLine.getOptionValue('h') != null) {
            printTitleAndVersion();
            printHelp();
            return false;
        }

        Boolean hasLanguage = commandLine.getOptionValue('l') != null;
        String language = "";
        if (!hasLanguage) {
            this.printTitleAndVersion();
            this.printError("No language provided");
            printHelp();
            return false;
        } else {

            language = commandLine.getOptionValue('l').toLowerCase().trim();

            if (language.equals("all")) {
                generateTS = true;
                generateJava = true;
            } else if (language.equals("ts")) {
                generateTS = true;
                generateJava = false;
            } else if (language.equals("java")) {
                generateTS = false;
                generateJava = true;
            } else {
                this.printTitleAndVersion();
                this.printError("Invalid option for lang: " + language);
                printHelp();
                return false;
            }


        }

        Boolean hasOutput = !commandLine.getOptionValue('o', "").isEmpty();
        Boolean hasInput = !commandLine.getOptionValue('i', "").isEmpty();
        Boolean tryDownload = false;
        String outputValue = commandLine.getOptionValue("o", System.getProperty("user.dir"));
        String inputValue = commandLine.getOptionValue("i", ".");
        this.outputFolder = new File(outputValue);;

        if (!hasOutput) {
            printError("Warning: No output folder was chosen. Using current directory.");
        }

        if (!outputFolder.exists() || !outputFolder.isDirectory()) {
            printError("Unable to write to output folder: " + outputFolder.getAbsolutePath());
            return false;
        }

        if (!hasInput) {
            printError("Warning: No input folder selected, downloading latest version or using internal files.");
        } else {

            inputFolder = new File(inputValue);
            if (!inputFolder.exists()) {
                printError("Input folder " + inputFolder.getAbsolutePath() + " does not exist.");
                return false;
            } else if (!inputFolder.isDirectory()) {
                printError("Input folder " + inputFolder.getAbsolutePath() + " is not a directory.");
                return false;
            }

            if (inputFolder.exists() && inputFolder.isDirectory()) {

                List<File> charTermsFiles = new ArrayList<>();
                charTermsFiles.add(new File(inputFolder, TerminologyConstants.FILE_CHAR_ID));
                charTermsFiles.add(new File(inputFolder, "pluginsdk/photoshopapi/photoshop/" + TerminologyConstants.FILE_CHAR_ID));
                charTermsFiles.add(new File(inputFolder, "photoshopapi/photoshop/" + TerminologyConstants.FILE_CHAR_ID));
                charTermsFiles.add(new File(inputFolder, "photoshop/" + TerminologyConstants.FILE_CHAR_ID));

                for (File file : charTermsFiles) {
                    if (file.exists() && file.isFile()) {
                        charTermsFile = new File(file.getAbsolutePath());
                        break;
                    }
                }

                if (charTermsFile != null) {
                    stringTermsFile = new File(charTermsFile.getParentFile(), TerminologyConstants.FILE_STRING_ID);
                    if (!stringTermsFile.exists() || !stringTermsFile.isFile()) {
                        printError("Unable to locate " + TerminologyConstants.FILE_STRING_ID + " in " + inputFolder);
                        return false;
                    }
                } else {
                    printError("Unable to locate " + TerminologyConstants.FILE_CHAR_ID + " in " + inputFolder);
                    return false;
                }

            }

        }

        if (charTermsFile == null || !charTermsFile.isFile()) {
            printMessage("Downloading: " + TerminologyConstants.URL_CHAR_ID);
            charTermsFile = DownloadHelper.downloadToTempDirectory(TerminologyConstants.URL_CHAR_ID, TerminologyConstants.FILE_CHAR_ID);
            printMessage("Downloading: " + TerminologyConstants.URL_STRING_ID);
            stringTermsFile = DownloadHelper.downloadToTempDirectory(TerminologyConstants.URL_STRING_ID, TerminologyConstants.FILE_STRING_ID);
            tryDownload = true;
        }

        if ((charTermsFile == null || !charTermsFile.isFile()) || (stringTermsFile == null || !stringTermsFile.isFile())) {
            if (tryDownload) {
                printError("Unable to download " + TerminologyConstants.FILE_CHAR_ID + " or " + TerminologyConstants.FILE_STRING_ID + " from " + inputFolder);
            } else {
                printError("Unable to locate " + TerminologyConstants.FILE_CHAR_ID + " or " + TerminologyConstants.FILE_STRING_ID + " from " + inputFolder);
            }
            this.valid = false;
            return false;
        }

        Boolean valid1 = charTermsFile != null && charTermsFile.exists() && charTermsFile.isFile();
        Boolean valid2 = stringTermsFile != null && stringTermsFile.exists() && stringTermsFile.isFile();
        Boolean valid3 = generateJava || generateTS;
        Boolean valid4 = outputFolder != null && outputFolder.exists() && outputFolder.isDirectory();

        this.valid = valid1 && valid2 && valid3 && valid4;

        return valid;

    }

    public Boolean isValid() {
        return valid;
    }

    public File getOutputFolder() {
        return outputFolder;
    }

    public File getCharTermsFile() {
        return charTermsFile;
    }

    public File getStringTermsFile() {
        return stringTermsFile;
    }

    public Boolean getGenerateTS() {
        return generateTS;
    }

    public Boolean getGenerateJava() {
        return generateJava;
    }

    private void printTitleAndVersion() {
        printMessage(
            "PhotoshopTypeGenerator",
            "Version: " + VERSION
        );
    }

    public void printHelp() {
        printMessage(
                "Example usage:",
                "ptg -l java|ts -i /home/downloads/api/photoshopapi/photoshop -o /user/home/output ",
                "Command Line Options:",
                "-l or --lang=java|ts|all     Target language (java/ts/all) Required",
                "-o or --output=PATH          Output folder for generated code. Current working directory will be used no value is provided.",
                "-i or --input=PATH           Input folder containing Terminology files from the C++ API reference.",
                "                             If left empty, latest version will be downloaded and used, or included",
                "                             files included in this version, which may be outdated.",
                "-h or --help                 Print this help and exit",
                "-v or --version              Print version and exit"
        );
    }

    private void printMessage(String... msg) {
        for (String out : msg) System.out.println(out);
    }

    private void printError(String... msg) {
        for (String out : msg) System.err.println(out);
    }

}
