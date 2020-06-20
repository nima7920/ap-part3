package Logic_menus;

public class LoginState extends State {

     int login(String username, String password) {
        return playerHandler.login(username, password);
    }
     int signup(String username,String password){
        return playerHandler.sign_up(username,password);

    }

     long getPlayerID(){

        return playerHandler.getId();
    }
}
