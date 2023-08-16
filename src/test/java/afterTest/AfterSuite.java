package afterTest;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import test.Utility.BaseClass;

public class AfterSuite extends BaseClass{


        @AfterTest(alwaysRun = true)
        public void aftrtst() throws IOException, InterruptedException {

            System.out.println("************After test is getting executed*********");
            extent.flush();
            try {
                driver.quit();
            }catch(Exception e) {

            }

        }



        @BeforeTest(alwaysRun = true)
        public void beforetest() throws IOException, InterruptedException {
            System.out.println("************Before test is getting executed*********");

        }
    }




