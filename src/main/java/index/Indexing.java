package index;



import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by archit on 28/1/17.
 */
public class Indexing {
    public static void main(String[] args)throws Exception{
        List<String> words=new ArrayList<String>();


        BufferedReader bufferedReader=new BufferedReader(new FileReader("wordlist"));
        String t;
        System.out.println("READING FILE");

        while((t=bufferedReader.readLine())!=null)
            words.add(t.trim());

        System.out.println("READING FILE DONE");

        Directory indexDirectory = FSDirectory.open(new File(Constants.INDEX_DIR));
        IndexWriter indexWriter=new IndexWriter(indexDirectory,
                new StandardAnalyzer(Version.LUCENE_36, Collections.EMPTY_SET),true,
                IndexWriter.MaxFieldLength.UNLIMITED);


        for(String x:words){
            Document document = new Document();
            Field word = new Field("Word", x, Field.Store.YES,Field.Index.ANALYZED);
            document.add(word);
            indexWriter.addDocument(document);
        }
        indexWriter.close();

    }
}
