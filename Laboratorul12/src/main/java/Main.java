import classloader.DynamicClassLoader;
import frameworktest.FrameworkTest;
import information.InformationClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        File path = new File(args[0]);
        Class clasa = null;
        try {
            if (path.exists()) {
                URL url = path.toURI().toURL();
                DynamicClassLoader loader= new DynamicClassLoader();
                loader.addURL(url);
                String[] paths = args[0].split("\\\\");
                for (int i = 0; i < paths.length; i++) {
                    boolean continua = true;
                    try {
                        String newPath = "";
                        for (int j = i; j < paths.length; j++) {
                            if(j>i)
                                newPath = newPath + ".";
                            newPath = newPath + paths[j];
                        }
                        newPath = newPath.substring(0,newPath.length()-6);
                        clasa = loader.loadClass(newPath);
                        InformationClass infoClass = new InformationClass(clasa);
                        infoClass.setMethods();
                        infoClass.showMethods();
                        continua = false;
                    }
                    catch (ClassNotFoundException e) {
                        //e.printStackTrace();
                    }
                    if(!continua) {
                        System.out.println("Done");
                        break;
                    }
                }
                FrameworkTest.Testing(clasa);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
