package com.example.workouttracker.datastructure;
/**
 * Author: Philippe Nadon
 * Author: Yusheng Liu
 *
 * This is the file that provide methods for saving and loading data from files
 *
 * Yusheng: I build this file base on the understanding of Phil's code and more than 80% of this
 * file's authority belong to Phil
 */

import com.google.gson.Gson;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Json {
    public static void saveToJson(Context context, Object obj, String fileName) {
        Gson gson = new Gson();
        String strObj = gson.toJson(obj);
        try {
            FileOutputStream fos = new FileOutputStream(
                    new File(context.getFilesDir() + "/" + fileName)
            );
            fos.write(strObj.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T loadFromJson(Context context, Class<T> classOfT, String fName) {
        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream inputStream = new FileInputStream(
                    new File(context.getFilesDir() + "/" + fName)
            );
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream)
            );
            String bufferLine;

            while ((bufferLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(bufferLine);
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = stringBuilder.toString();
        return gson.fromJson(json, classOfT);
    }
}
