/*
 * Decompiled with CFR 0.152.
 */
package com.chef.mod.checker;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;


public class JavaGetUrl {
    private static String version;
    public static String[] extraInfoArray;
    private static String minecraft_version;

    public JavaGetUrl(String minecraft_version) {
        JavaGetUrl.minecraft_version = minecraft_version;
    }

    public static String getVersion() {
        return version;
    }

    public static void main(Subject subject) {
        version = null; // Reset antes de começar
        extraInfoArray = null;

        try {
            URL url = new URL("http://update.minetronic.com/versions/Master_Chef_latest_version-" + minecraft_version + ".txt");
            InputStream is = url.openStream();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));

            List<String> lines = new ArrayList<String>();
            String line;

            while ((line = dis.readLine()) != null) {
                lines.add(line);
            }

            dis.close();

            if (lines.isEmpty()) return;

            if (subject == Subject.VERSION) {
                version = lines.get(0);
            } else if (subject == Subject.EXTRA_INFO && lines.size() > 1) {
                extraInfoArray = lines.subList(1, lines.size()).toArray(new String[0]);
            }

        } catch (Exception e) {
            e.printStackTrace();
            version = "0.0.0";
            extraInfoArray = new String[0];
        }
    }

    public static enum Subject {
        VERSION,
        EXTRA_INFO;
    }
}

