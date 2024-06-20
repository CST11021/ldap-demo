/*
 * Author: Janki Shah
 * Date: 12/01/2018
 */

package com.whz.ldap.demo;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import java.util.Scanner;
import java.util.logging.Logger;

public class DeleteUtility {

    public static Scanner sc = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(DeleteUtility.class.getName());

    public void deleteAction(DirContext context, String dn) {

        CommonUtility commonUtility = new CommonUtility();
        commonUtility.setLoggingManager();

        logger.info("Class Name: DeleteUtility, Method: delete()");

        System.out.println("Note subtree of entered entry will be deleted along.");

        LookupUtility lookupUtility = new LookupUtility();
        Object lookedupObject = lookupUtility.lookupAction(context, dn);

        if (lookedupObject != null) {
            try {
                context.destroySubcontext(dn);
                System.out.println("Subtree deleted successfully");
            } catch (NamingException exception) {
                System.out.println("Exception Occured while deleting object.");
                logger.warning("Class Name: DeleteUtility, Method: delete(), Exception: " + exception);
            }
        } else {
            System.out.println("Entered object does not exist Can't be deleted.");
        }
    }

}
