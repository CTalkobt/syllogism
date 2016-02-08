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
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
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
    private final MultiMap<Term, KeyValue<Copula, Term>> memeAssociations = new MultiValueMap<>();

    /************************************************************************
     * Defines a syllogism for a given equivalence type.
     *
     * @param memeKey
     * @param equivalence
     * @param memeValue
     ***********************************************************************/
    public void addSyllogism(Term memeKey, Copula equivalence, Term memeValue)
    {
       memeAssociations.put(memeKey, new DefaultKeyValue<>(equivalence, memeValue));
    }

    /************************************************************************
     * Determines if a given syllogism for an equivalence type is valid.
     *
     * @param memeKey
     * @param equivalence
     * @param memeValue
     * @return result if known, otherwise optional.empty(). 
     ***********************************************************************/
    public Optional<Boolean> interrogate(Term memeKey, Copula equivalence, Term memeValue)
    {
        Collection<KeyValue<Copula, Term>> memeRelations = (Collection<KeyValue<Copula, Term>>) memeAssociations.get(memeKey);
        if (memeRelations == null || memeRelations.isEmpty())
        {
            return Optional.empty();
        }

        Optional<KeyValue<Copula, Term>> result = memeRelations
                .parallelStream()
                .findFirst()
                .filter((KeyValue<Copula, Term> kv) -> {
                    if (kv.getKey().equals(equivalence)
                            && kv.getValue().equals(memeValue)) {
                        return true;
                    } else {
                        Optional<Boolean> result1 = interrogate(kv.getValue(), equivalence, memeValue);
                        if (result1.isPresent()) {
                            return true;
                        }
                    }
                    return false;
            });

        if (result != null && result.isPresent())
        {
            return Optional.of(equivalence.getTruthEquivalency());
        }
        return Optional.empty();
    }

    /************************************************************************
     * Instantiates a new Meme.
     *
     * @param text
     * @return
     ***********************************************************************/
    public Term createMeme(String text)
    {
        Term m = new Term(text);
        return m;
    }
}
