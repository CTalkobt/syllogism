/****************************************************************************
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ***************************************************************************/
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
