package net.ctalkobt.syllogism;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.apache.commons.collections4.map.MultiValueMap;


/****************************************************************************
 *
 * @author Craig.Taylor
 ***************************************************************************/
public class Context {
    private final MultiMap<Meme, KeyValue<Equivalence, Meme>> memeAssociations = new MultiValueMap<>();

    public void addDefinition(Meme memeKey, Equivalence equivalence, Meme memeValue)
    {
       memeAssociations.put(memeKey, new DefaultKeyValue<>(equivalence, memeValue));
    }

    public boolean interrogate(Meme memeKey, Equivalence equivalence, Meme memeValue)
    {
        boolean result = false;
        Collection<KeyValue<Equivalence, Meme>> memeRelations = (Collection<KeyValue<Equivalence, Meme>>) memeAssociations.get(memeKey);

        if (memeRelations == null || memeRelations.isEmpty())
        {
            return false;
        }

        result = memeRelations
            .stream()
            .anyMatch((KeyValue<Equivalence, Meme> kv) ->
                (kv.getKey().equals(equivalence) && kv.getValue().equals(memeValue)) ||
                (interrogate(kv.getValue(), equivalence, memeValue))
            );

        return result;
    }

    public Meme createMeme(String text)
    {
        Meme m = new Meme(text);
        return m;

    }
}
