package piuk.website;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized()");

        try {
            SharedCoin.restore();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedCoin.ourWallet.tidyTheWallet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed()");

        try {
            SharedCoin.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}