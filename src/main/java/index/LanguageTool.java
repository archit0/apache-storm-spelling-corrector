package index;

import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

import java.util.List;

/**
 * Created by archit on 28/1/17.
 */
public class LanguageTool {
    public static void main(String[] args)throws Exception{
        JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
// comment in to use statistical ngram data:
//langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
        List<RuleMatch> matches = langTool.check("I olny am a huamn");
        for (RuleMatch match : matches) {
            System.out.println("Potential error at characters " +
                    match.getFromPos() + "-" + match.getToPos() + ": " +
                    match.getMessage());
            System.out.println("Suggested correction(s): " +
                    match.getSuggestedReplacements());
        }
    }
}
