package net.ctalkobt;

import net.ctalkobt.syllogism.Equivalence;
import net.ctalkobt.syllogism.Meme;
import net.ctalkobt.syllogism.Context;

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
        Context context = new Context();

        Meme mMan = context.createMeme("Man");
        Meme mMammel = context.createMeme("Mammel");
        Meme mAnimal = context.createMeme("Animal");
        Meme mInanimate = context.createMeme("Inanimate");

        context.addDefinition(mMan, Equivalence.EQUALITY, mMammel);
        context.addDefinition(mMammel, Equivalence.EQUALITY, mAnimal);

        /** Should return true */
        System.err.println(context.interrogate(mMan, Equivalence.EQUALITY, mMammel));

        /** Should return true */
        System.out.println( context.interrogate(mMan, Equivalence.EQUALITY, mAnimal));

        /** Should return false */
        System.out.println( context.interrogate(mMan, Equivalence.EQUALITY, mInanimate));
    }

}
