package jaas;
 
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
 
public class Main {
    public static void main(String[] args) throws LoginException {
        // 配置文件中查找 Sample 名字的 LoginModule，并指定 CallbackHandler
        LoginContext lc = new LoginContext("Sample", new MyCallbackHandler());
 
        try {
            lc.login();
            Subject subject = lc.getSubject();
            System.out.println(subject);
            System.out.println("Authentication succeeded!");
        } catch (LoginException le) {
            System.err.println("Authentication failed:" + le.getMessage());
        }
    }
}