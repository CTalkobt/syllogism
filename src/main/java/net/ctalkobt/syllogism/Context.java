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
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.apache.commons.collections4.map.MultiValueMap;


/****************************************************************************
 * Context holds the known Memes / Associations of Syllogisms that have been
 * defined.
 *
 * @author Craig.Taylor
 ***************************************************************************/
public class Context {
    private final MultiMap<Meme, KeyValue<Equivalence, Meme>> memeAssociations = new MultiValueMap<>();

    /************************************************************************
     * Defines a syllogism for a given equivalence type.
     *
     * @param memeKey
     * @param equivalence
     * @param memeValue
     ***********************************************************************/
    public void addSyllogism(Meme memeKey, Equivalence equivalence, Meme memeValue)
    {
       memeAssociations.put(memeKey, new DefaultKeyValue<>(equivalence, memeValue));
    }

    /************************************************************************
     * Determines if a given syllogism for an equivalence type is valid.
     *
     * @param memeKey
     * @param equivalence
     * @param memeValue
     * @return
     ***********************************************************************/
    public boolean interrogate(Meme memeKey, Equivalence equivalence, Meme memeValue)
    {
        Collection<KeyValue<Equivalence, Meme>> memeRelations = (Collection<KeyValue<Equivalence, Meme>>) memeAssociations.get(memeKey);

        if (memeRelations == null || memeRelations.isEmpty())
        {
            return false;
        }

        return memeRelations
            .stream()
            .anyMatch((KeyValue<Equivalence, Meme> kv) ->
                (kv.getKey().equals(equivalence) && kv.getValue().equals(memeValue)) ||
                (interrogate(kv.getValue(), equivalence, memeValue))
            );
    }

    /************************************************************************
     * Instantiates a new Meme.
     *
     * @param text
     * @return
     ***********************************************************************/
    public Meme createMeme(String text)
    {
        Meme m = new Meme(text);
        return m;
    }
}
