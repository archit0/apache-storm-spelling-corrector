package index;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by archit on 28/1/17.
 */
public class Searching {
    static QueryParser queryParser;
    static Query query;
    static IndexSearcher indexSearcher;
    private static JLanguageTool langTool;

    public static void main(String[] args)throws Exception{


        load();
        String sentence[]="My countri this is a grl and I am a very good buoy. ".split(" ");

        int total=0;
        for(String x:sentence) {
            boolean ans=searchWord(x);
            if(ans)total++;

        }
        System.out.println(total+"/"+sentence.length);





    }

    public static boolean searchWord(String word){
        try{
            word = word.replaceAll("[^\\w\\s]","").toLowerCase();

        String query = word+"";
        TopDocs hits = search(query);

            List<String> ans=new ArrayList<String>();
        for (ScoreDoc scoreDoc : hits.scoreDocs) {

            Document doc = getDocument(scoreDoc);
            String alt = doc.get("Word");
            ans.add(alt.toLowerCase());
        }
        if(ans.indexOf(word)!=-1)
            return true;
        return false;
        }
        catch (Exception e){
            return false;
        }
    }

    public static String suggestion(String wrongWord){try {
        List<RuleMatch> matches = langTool.check(wrongWord);
        String ans = "";
        for (RuleMatch match : matches) {
            ans += match.getSuggestedReplacements() + " ";
        }
        return ans;
    }
    catch (Exception e){
        return "";
    }
    }

    public static void load()throws Exception{
        Directory indexDirectory = FSDirectory.open(new File(Constants.INDEX_DIR));
        indexSearcher = new IndexSearcher(indexDirectory);

        queryParser = new QueryParser(Version.LUCENE_36,
                "Word",
                new StandardAnalyzer(Version.LUCENE_36, Collections.EMPTY_SET));
        langTool = new JLanguageTool(new BritishEnglish());


    }
    private static TopDocs search(String searchQuery)
            throws IOException, ParseException {
        query = queryParser.parse(searchQuery);
        //query= new FuzzyQuery(new Term("Word", searchQuery), 10);

        return indexSearcher.search(query, 100);
    }

    public static Document getDocument(ScoreDoc scoreDoc)
            throws CorruptIndexException, IOException{
        return indexSearcher.doc(scoreDoc.doc);
    }
}
