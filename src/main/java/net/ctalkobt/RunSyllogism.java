package net.ctalkobt;

import net.ctalkobt.syllogism.Equivalence;
import net.ctalkobt.syllogism.Meme;
import net.ctalkobt.syllogism.Context;
import net.ctalkobt.syllogism.SyllogismStore;

/**
 *
 * @author Craig.Taylor
 */
public class RunSyllogism
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Context context = new SyllogismStore();

        Meme mMan = context.createMeme("Man");
        Meme mMammel = context.createMeme("Mammel");
        Meme mAnimal = context.createMeme("Animal");

        context.addDefinition(mMan, Equivalence.EQUALITY, mMammel);
        context.addDefinition(mMammel, Equivalence.EQUALITY, mAnimal);

        System.err.println(context.interrogate(mMan, Equivalence.EQUALITY, mMammel));

        System.out.println( context.interrogate(mMan, Equivalence.EQUALITY, mAnimal));

    }

}
