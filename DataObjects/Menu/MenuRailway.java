package Menu;



import Railway.FAQPage;
import Railway.GeneralPage;
import Railway.HomePage;
import Railway.LoginPage;

import Railway.RegisterPage;


public enum MenuRailway {

    HOME("Home", HomePage.class),
   
    FAQ("FAQ", FAQPage.class),
    REGISTER("Register", RegisterPage.class),
    LOGIN("Login", LoginPage.class);
	

    private final String displayText;
    private final Class<? extends GeneralPage> pageClass;

    MenuRailway(String displayText, Class<? extends GeneralPage> pageClass) {
        this.displayText = displayText;
        this.pageClass = pageClass;
    }

    public String getText() {
        return displayText;
    }
    
    public Class<? extends GeneralPage> getPageClass() {
    	return this.pageClass;
    }
}
