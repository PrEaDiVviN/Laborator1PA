package classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class DynamicClassLoader extends URLClassLoader {
    public DynamicClassLoader() {
        super(new URL[0], ClassLoader.getSystemClassLoader());
    }
    @Override
    public void addURL(URL url) {
        super.addURL(url); //addUrl is protected in the superclass
    }
}