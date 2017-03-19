package eu.openminted.example.uimafit;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.JCasFactory.createText;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;
import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.jcas.JCas;
import org.junit.Assert;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;

public class NamedEntityTaggerTest
{
    @Test
    public void test()
        throws Exception
    {
        JCas document = createText("John likes Lilly. That makes Fred jealous.", "en");
        AnalysisEngineDescription segmenter = createEngineDescription(BreakIteratorSegmenter.class);
        AnalysisEngineDescription tagger = createEngineDescription(NamedEntityTagger.class);
        
        runPipeline(document, segmenter, tagger);
        
        List<NamedEntity> entities = new ArrayList<>(select(document, NamedEntity.class));
        Assert.assertEquals("John", entities.get(0).getCoveredText());
        Assert.assertEquals("Lilly", entities.get(1).getCoveredText());
        Assert.assertEquals("Fred", entities.get(2).getCoveredText());
    }
}
