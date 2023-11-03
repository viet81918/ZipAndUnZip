/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controller;

import Model.UnzipFile;
import Model.ZipFile;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author conch
 */
public class ZipUnZip {

    Scanner sc = new Scanner(System.in);

    public void zipFile() {
        System.out.print("Enter the path to the folder containing files to be zipped: ");
        String sourcePath = sc.nextLine();
        System.out.print("Enter the name of the zip file: ");
        String zipFileName = sc.nextLine();
        System.out.print("Enter the path where the zip file should be saved: ");
        String zipFilePath = sc.nextLine();
        ZipFile file = new ZipFile(zipFilePath, sourcePath, zipFileName);
        boolean result = compressTo(file);
        if (result) {
            System.out.println("File zipped successfully.");
        } else {
            System.out.println("Failed to zip the file.");
        }
    }

    public void unzipFile() {
        System.out.print("Enter the path to the zip file: ");
        String zipFilePath = sc.nextLine();
        System.out.print("Enter the path where the files should be extracted: ");
        String extractPath = sc.nextLine();
        UnzipFile file = new UnzipFile(zipFilePath, extractPath);
        boolean result = extractTo(file);
        if (result) {
            System.out.println("File unzipped successfully.");
        } else {
            System.out.println("Failed to unzip the file.");
        }
    }

    public boolean compressTo(ZipFile file) {
        try {
            try (FileOutputStream fos = new FileOutputStream(file.getDestionation() + File.separator + file.getName() + ".zip"); ZipOutputStream zipOut = new ZipOutputStream(fos)) {

                File fileToZip = new File(file.getSource());
                zipFile(fileToZip, fileToZip.getName(), zipOut);

            }
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws Exception {
        if (fileToZip.isHidden()) {
            return;
        }

        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
            }

            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }

        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }

    public boolean extractTo(UnzipFile file) {
        try {
            File destDir = new File(file.getSource());
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            try (FileInputStream fis = new FileInputStream(file.getDestionation()); ZipInputStream zipIn = new ZipInputStream(fis)) {

                ZipEntry entry = zipIn.getNextEntry();
                while (entry != null) {
                    String filePath = file.getSource() + File.separator + entry.getName();
                    if (!entry.isDirectory()) {
                        extractFile(zipIn, filePath);
                    } else {
                        File dir = new File(filePath);
                        dir.mkdirs();
                    }
                    zipIn.closeEntry();
                    entry = zipIn.getNextEntry();
                }

            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }
}
