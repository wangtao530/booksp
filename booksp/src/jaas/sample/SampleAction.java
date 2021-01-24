package jaas.sample;

import java.io.File;
import java.io.IOException;
import java.security.PrivilegedAction;

/**
 * This is a Sample PrivilegedAction implementation, designed to be
 * used with the Sample application.
 *
 */
public class SampleAction implements PrivilegedAction {

    /**
     * This Sample PrivilegedAction performs the following operations:
     * <ul>
     *   <li>Access the System property, <i>java.home</i></li>
     *   <li>Access the System property, <i>user.home</i></li>
     *   <li>Access the file, <i>foo.txt</i></li>
     * </ul>
     *
     * @return <code>null</code> in all cases.
     *
     * @exception SecurityException if the caller does not have permission
     *          to perform the operations listed previously.
     */
    public Object run() {
        System.out.println("\nYour java.home property: "
                            +System.getProperty("java.home"));

        System.out.println("\nYour user.home property: "
                            +System.getProperty("user.home"));
        File f = new File("foo.txt");
        System.out.print("\nfoo.txt does ");
        if (!f.exists())
            System.out.print("not ");
        System.out.println("exist in the current working directory.");
        return null;
    }
}
