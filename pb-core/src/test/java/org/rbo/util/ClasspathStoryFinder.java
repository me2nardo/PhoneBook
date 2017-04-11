package org.rbo.util;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import static org.apache.commons.io.FileUtils.listFiles;

/**
 * A class to help us find stories (files) across a classpath with many roots.  This is especially important
 * when finding files when executed from a Gradle test context.
 */
public class ClasspathStoryFinder {

    private static final Logger LOG = LoggerFactory.getLogger(ClasspathStoryFinder.class);

    public static List<String> findFilenamesThatMatch(String aFilenameWithWildcards) {
        List<String> filenames = new ArrayList<String>();
        for (File file : findFilesThatMatch(aFilenameWithWildcards)) {
            filenames.add(file.toURI().toString());
        }
        return filenames;
    }

    private static Collection<File> findFilesThatMatch(String aFilenameWithWildcards) {
        WildcardFileFilter regexFileFilter = new WildcardFileFilter(aFilenameWithWildcards);
        List<File> rootDirsToSearchFrom = getRootDirs();
        LOG.debug("Searching for stories called {} in {}", aFilenameWithWildcards, rootDirsToSearchFrom);

        List<File> ret = new ArrayList<File>() ;
        for (File f : rootDirsToSearchFrom) {
            ret.addAll(listFiles(f, regexFileFilter, DirectoryFileFilter.DIRECTORY)) ;
        }
        return ret ;
    }

    private static List<File> getRootDirs() {
        List<File> ret = new ArrayList<File>() ;
        try {
            Enumeration<URL> roots = ClasspathStoryFinder.class.getClassLoader().getResources("") ;
            while(roots.hasMoreElements()) {
                ret.add(new File(roots.nextElement().getFile())) ;
            }
        } catch(IOException ioe) {
            LOG.error("Failed to derive classpath from Class Loader", ioe) ;
        }
        return ret ;
    }
}
