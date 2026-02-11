package Common;

import Railway.*;
import Account.Account;
import Constant.Constant;
import Mail.RandomMail;
import Menu.MenuRailway;

public class AccountHelper {

    /**
     * Create & activate a new Railway account
     */
    public static Account createAndActivateAccount() {

        System.out.println("Create Mail account");
        Account account = Account.generalAccount();

        // ===== CREATE TEMP MAIL =====
        RandomMail randomMail = new RandomMail();
        randomMail.open();
        randomMail.setGuerrillaMail(account.getEmail());
        account.setEmail(randomMail.getGmail());

        // ===== REGISTER =====
        HomePage homePage = new HomePage();
        WindowManager.newTab(Constant.WINDOW_TAB_RAILWAY);
        Utilities.getUrl(Constant.RAIWAY_URL);
        RegisterPage registerPage =
            (RegisterPage) homePage.navigateMenu(MenuRailway.REGISTER);
        registerPage.register(account);

        // ===== ACTIVATE =====
        WindowManager.switchToTab(Constant.WINDOW_TAB_MAIL);
        Utilities.refreshWindow();
        String activeLink = randomMail.getLinkActive();
        WindowManager.switchToTab(Constant.WINDOW_TAB_RAILWAY);
        Utilities.getUrl(activeLink);
        return account;
    }
}
