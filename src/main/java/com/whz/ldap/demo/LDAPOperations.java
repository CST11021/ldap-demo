/*
 * Author: Janki Shah
 * Date: 11/01/2018
 */

package com.whz.ldap.demo;

import javax.naming.directory.DirContext;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @Author 盖伦
 * @Date 2023/9/6
 */
public class LDAPOperations {

    private static final Logger logger = Logger.getLogger(LDAPOperations.class.getName());

    public static Scanner sc = new Scanner(System.in);

    static CommonUtility commonUtility = new CommonUtility();

    /**
     * cn=admin,dc=example,dc=com
     * 123456
     *
     * @param args
     */
    public static void main(String args[]) {
        commonUtility.setLoggingManager();

        logger.info("Class Name: ldapOperationsTest, Method: main()");

        ConnectionUitility connectionUitility = new ConnectionUitility();
        DirContext directoryContext = connectionUitility.ldapBinding();
        if (directoryContext == null) {
            System.out.println("BINDING FAILED");
            return;
        }

        System.out.println("BINDING SUCCESSFUL");
        boolean repeat = true;
        while (repeat) {
            System.out.println("Enter DN of entry to be modified, e.g: ou=department,dc=example,dc=com");
            String dn = sc.next();

            System.out.println("Select an operation to perform:");
            System.out.println("1.Add 2.Modify 3.Delete 4.Search 5.Lookup 6.Exit");
            int operationToPerform = sc.nextInt();
            switch (operationToPerform) {
                case 1:
                    AddUtility addUtility = new AddUtility();
                    addUtility.addAction(directoryContext, dn);
                    break;
                case 2:
                    ModifyUtility modifyUtility = new ModifyUtility();
                    modifyUtility.modifyAction(directoryContext, dn);
                    break;
                case 3:
                    DeleteUtility deleteUtility = new DeleteUtility();
                    deleteUtility.deleteAction(directoryContext, dn);
                    break;
                case 4:
                    SearchUtility searchUtility = new SearchUtility();
                    searchUtility.searchAction(directoryContext);
                    break;
                case 5:
                    LookupUtility lookupUtility = new LookupUtility();
                    lookupUtility.lookupAction(directoryContext, dn);
                    break;
                case 6:
                    connectionUitility.ldapUnBinding(directoryContext);
                    System.out.println("Thank you");
                    repeat = false;
                    break;
            }
        }
    }
}
