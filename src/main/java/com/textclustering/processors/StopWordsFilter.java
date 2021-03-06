package com.textclustering.processors;

import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StopWordsProcessor stores a set of known english stopwords to filter from documents.
 * Created with IntelliJ IDEA.
 * User: george
 * Date: 11/21/13
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class StopWordsFilter {
    private Set<String> m_stopWords = new HashSet<String>();
    private StringBuilder regexPattern = new StringBuilder();
    private File m_stopWordsFile;

    /**
     * Constructor takes a file that is a list of stopwords to be read into the set.
     * @param path
     */
    public void StopWordsFilter(File path) throws FileNotFoundException {
        m_stopWordsFile = path;
        loadStopWords(path);
    }

    public void loadStopWords(File path) throws FileNotFoundException {
        FileReader reader = new FileReader(path);
        LineIterator lineIterator = new LineIterator(reader);

        while(lineIterator.hasNext()){
            String tmp = lineIterator.nextLine();
            m_stopWords.add(tmp);
        }
    }

    /**
     * Method takes a string of input and filters out the stopwords in the documents
     */
    public String filterStopWords(String doc){
        StringBuilder filteredResult = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(doc," ");

        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken().toLowerCase();
            if(!m_stopWords.contains(token)){
                filteredResult.append(token+" ");
            }
        }

        return filteredResult.toString().trim();
    }
}
