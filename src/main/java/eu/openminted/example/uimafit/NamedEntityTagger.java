package eu.openminted.example.uimafit;

import java.util.Arrays;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

@TypeCapability(inputs="eu.openminted.example.uimafit.NamedEntity.NamedEntity")
public class NamedEntityTagger
    extends JCasAnnotator_ImplBase
{
    private List<String> dictionary = Arrays.asList("John", "Fred", "Lilly");

    @Override
    public void process(JCas aJCas)
        throws AnalysisEngineProcessException
    {
        for (Token t : JCasUtil.select(aJCas, Token.class)) {
            if (dictionary.contains(t.getCoveredText())) {
                new NamedEntity(aJCas, t.getBegin(), t.getEnd()).addToIndexes();
            }
        }
    }
}
