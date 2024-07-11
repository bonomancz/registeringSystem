package cz.bonoman.service;

import java.io.*;
import java.util.ArrayList;

public class FileSystem {
    private final String PERSON_TXT_FILE;
    private final File PERSON_FILE;

    public FileSystem(){
        this.PERSON_TXT_FILE = "./appData/dataPersonId.txt";
        this.PERSON_FILE = new File(PERSON_TXT_FILE);
    }

    public ArrayList<String> getFilesContentList() throws StorageDataException{
        ArrayList<String> filesContentList = new ArrayList<>();
        try {
            if(isDataStorageAvailable()) {
                filesContentList = new ArrayList<>(this.fileRead(PERSON_FILE));
            }
        }catch(IOException e){
            throw new StorageDataException("DataHandler::getFilesContentList(): fileRead exception.");
        }
        return filesContentList;
    }

    private boolean isDataStorageAvailable() throws StorageDataException {
        boolean isDataStorageAvailable = true;
        try {
            if (!this.isReadable(this.PERSON_FILE)) {
                isDataStorageAvailable = false;
            }
        }catch(IOException e){
            isDataStorageAvailable = false;
            throw new StorageDataException("DataHandler::isDataStorageAvailable(): " + e.getMessage());
        }
        return isDataStorageAvailable;
    }

    private boolean isWriteable(File inputFile) throws IOException {
        boolean isWriteable = true;
        if (!inputFile.canWrite()) {
            isWriteable = false;
            throw new IOException("isWriteable(): soubor " + inputFile + " není k dispozici pro zápis.");
        }
        return isWriteable;
    }

    private boolean isReadable(File inputFile) throws IOException{
        boolean isReadable = true;
        if (!inputFile.canRead()) {
            isReadable = false;
            throw new IOException("isReadable(): soubor " + inputFile + " není k dispozici pro čtení.");
        }
        return isReadable;
    }

    private void fileClear(File file) throws IOException{
        try(PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)))){
            outputWriter.print("");
        }catch(IOException e){
            throw new IOException("fileClear(): " + e.getMessage());
        }
    }

    private ArrayList<String> fileRead(File file) throws IOException{
        ArrayList<String> fileContent = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                fileContent.add(line);
            }
        }catch(IOException e){
            throw new IOException("fileRead(): " + e.getMessage());
        }
        return fileContent;
    }

    private void prepareDataFiles() throws IOException{
        try {
            this.fileCreate(PERSON_FILE);
        }catch(IOException e){
            throw new IOException("prepareDataFiles(): " + e.getMessage());
        }
    }

    private void fileCreate(File file) throws IOException{
        try {
            if(!file.exists()) {
                file.createNewFile();
            }else{
                this.fileClear(file);
            }
        }catch(IOException e){
            throw new IOException("fileCreate(): " + e.getMessage());
        }
    }

    public File getScifiFile(){return this.PERSON_FILE;}
}
