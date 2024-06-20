package com.whz.ldap.demo;

import org.junit.Test;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

/**
 * @author 盖伦
 * @date 12/31/2021 6:01 PM
 */
public class LdapOperationTest {

    // Login DN：cn=admin,dc=example,dc=com
    // Password：123456

    private final static String LDAP_URL = "ldap://127.0.0.1:389/";

    private final static String LOGIN_DN = "cn=admin,dc=example,dc=com";

    private final static String PASSWORD = "123456";

    @Test
    public void test() {
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        // 用户名: cn,ou,dc 分别：用户，组，域
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=example,dc=com");
        // 用户密码: cn的密码
        env.put(Context.SECURITY_CREDENTIALS, "123456");
        // url格式：协议://ip:端口/组,域
        // 直接连接到域或者组上面
        env.put(Context.PROVIDER_URL, "ldap://127.0.0.1:389/");
        // LDAP工厂
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        // 验证的类型："none", "simple", "strong"
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        try {

            Attribute objectClass = new BasicAttribute("objectClass");
            objectClass.add("top");
            objectClass.add("person");
            objectClass.add("inetOrgPerson");
            objectClass.add("organizationalPerson");

            Attributes entry = new BasicAttributes();
            entry.put(objectClass);
            // 注：只能添加objectClass中定义的属性
            entry.put(new BasicAttribute("cn", "si"));
            entry.put(new BasicAttribute("sn", "Li"));
            entry.put(new BasicAttribute("uid", "lisi"));
            entry.put(new BasicAttribute("userPassword", "123456"));

            LdapContext ldapContext = new InitialLdapContext(env, null);
            ldapContext.createSubcontext("cn=lisi,dc=example,dc=com", entry);

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

}