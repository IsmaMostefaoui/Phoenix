package com.umons.model;

import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.*;
 //copier coller sur http://pastebin.com/xVq6Wa19
 
public class Audio implements Runnable{
        static String path = "";
        static boolean isSet = false;
 
        public Audio(String pPath){
                path = pPath;
                isSet = true;
        }
       
        public void run() {
                if(isSet){
                        SourceDataLine line;
                        File file;
                        AudioInputStream audioInputStream;
                        AudioFormat audioFormat;
                        file = new File(path);
                        try {
                                audioInputStream = AudioSystem.getAudioInputStream(file);}
                        catch (UnsupportedAudioFileException e) {
                                e.printStackTrace();
                                return;}
                        catch (IOException e){
                                e.printStackTrace();
                                return;}
                        audioFormat = audioInputStream.getFormat();
                        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                        try {
                                line = (SourceDataLine) AudioSystem.getLine(info);}
                        catch (LineUnavailableException e){
                                e.printStackTrace();
                                return;}
                        try {
                                line = (SourceDataLine) AudioSystem.getLine(info);
                                line.open(audioFormat);
                        }
                                catch (LineUnavailableException e1) {
                                        e1.printStackTrace();
                                        return;}
                        line.start();
                        try {
                                byte bytes[] = new byte[5];
                                int bytesRead = 0;
                                while((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1){
                                        line.write(bytes, 0, bytesRead);
                                }
                        }
                        catch (IOException e) {
                                e.printStackTrace();
                                return;}
                }
        }
}
