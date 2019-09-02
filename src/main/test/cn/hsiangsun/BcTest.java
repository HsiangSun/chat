package cn.hsiangsun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class BcTest {

    @Test
    public void test1(){
        String salt = BCrypt.gensalt();
        String hashpw = BCrypt.hashpw("1314", salt);
        System.out.println(hashpw + "-->" +hashpw.length() );
    }

}
