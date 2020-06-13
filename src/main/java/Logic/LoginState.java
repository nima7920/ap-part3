package Logic;

public class LoginState extends State {

     int login(String username, String password) {
        return currentPlayer.login(username, password);
    }
     int signup(String username,String password){
        return currentPlayer.sign_up(username,password);

    }

     long getPlayerID(){

        return currentPlayer.getId();
    }
}
