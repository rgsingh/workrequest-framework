package com.rgsinfotech.bootstrap;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class Bootstrap {

    public static void main(String[] args) throws Exception {

        /*
            Assume your application has a "home" directory
            with /classes and /lib beneath it, where you can put
            loose files and jars.

            Thus,

            /usr/local/src/APP
            /usr/local/src/APP/classes
            /usr/local/src/APP/lib
         */

        String HOME = "/usr/local/src/YOURAPP";
        String CLASSES = HOME + "/classes";
        String LIB = HOME + "/lib";

        // add the classes dir and each jar in lib to a List of URLs.
        List<URL> urls = new ArrayList<URL>();
        urls.add(new File(CLASSES).toURI().toURL());
        for (File f : new File(LIB).listFiles()) {
            urls.add(f.toURI().toURL());
        }

        // feed your URLs to a URLClassLoader!
        ClassLoader classloader =
                new URLClassLoader(
                        urls.toArray(new URL[0]),
                        ClassLoader.getSystemClassLoader().getParent());

        // relative to that classloader, find the main class
        // you want to bootstrap, which is the first cmd line arg
        Class<?> mainClass = classloader.loadClass(args[0]);
        Method main = mainClass.getMethod("main",
                new Class[]{args.getClass()});

        // well-behaved Java packages work relative to the
        // context classloader.  Others don't (like commons-logging)
        Thread.currentThread().setContextClassLoader(classloader);

        // you want to prune the first arg because its your main class.
        // you want to pass the remaining args as the "real" args to your main
        String[] nextArgs = new String[args.length - 1];
        System.arraycopy(args, 1, nextArgs, 0, nextArgs.length);
        main.invoke(null, new Object[] { nextArgs });
    }

}