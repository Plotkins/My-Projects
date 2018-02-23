package Model;

import java.io.BufferedReader;

public class FileTuple{
    public String fileName;
    public BufferedReader buffer;
    public FileTuple(String f,BufferedReader b){
        fileName=f;
        buffer=b;
    }
}
