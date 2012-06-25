/* LanguageTool, a natural language style checker
 * Copyright (C) 2012 Marcin Miłkowski
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules.en;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.TestTools;
import org.languagetool.rules.RuleMatch;

public class MorfologikBritishSpellerRuleTest {

    @Test
    public void testMorfologikSpeller() throws IOException {
        MorfologikBritishSpellerRule rule =
                new MorfologikBritishSpellerRule (TestTools.getMessages("English"), Language.BRITISH_ENGLISH);

        RuleMatch[] matches;
        JLanguageTool langTool = new JLanguageTool(Language.BRITISH_ENGLISH);


        // correct sentences:
        assertEquals(0, rule.match(langTool.getAnalyzedSentence("This is an example: we get behaviour as a dictionary word.")).length);
        assertEquals(0, rule.match(langTool.getAnalyzedSentence("Why don't we speak today.")).length);
        //with doesn't
        assertEquals(0, rule.match(langTool.getAnalyzedSentence("He doesn't know what to do.")).length);
        assertEquals(0, rule.match(langTool.getAnalyzedSentence(",")).length);
        assertEquals(0, rule.match(langTool.getAnalyzedSentence("123454")).length);

        //incorrect sentences:

        matches = rule.match(langTool.getAnalyzedSentence("Behavior"));
        // check match positions:
        assertEquals(1, matches.length);
        assertEquals(0, matches[0].getFromPos());
        assertEquals(8, matches[0].getToPos());
        assertEquals("behaviour", matches[0].getSuggestedReplacements().get(0));

        assertEquals(1, rule.match(langTool.getAnalyzedSentence("aõh")).length);
        assertEquals(0, rule.match(langTool.getAnalyzedSentence("a")).length);

    }

}
