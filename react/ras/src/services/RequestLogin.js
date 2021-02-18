
import axios from 'axios';

const  LOGIN_API_REQUST_BASE_URL = " http://localhost:8080/ras/loginRequest";

class LoginRequest{

    postLoginRequest(loginRequest){
        return axios.post(LOGIN_API_REQUST_BASE_URL,loginRequest);
    }
        
}

export default new LoginRequest()