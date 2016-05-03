package com.andrespedes.ocpjp7.nio;

import java.nio.file.*;
import java.util.Iterator;
class PathInfo {
    public static void main(String[] args) {
        Path aFilePath = Paths.get("D:\\dir\\file.txt");
        Iterator<Path> paths = aFilePath.iterator();
        while (paths.hasNext()) {
            System.out.print(paths.next() + " ");
        }
    }
}
