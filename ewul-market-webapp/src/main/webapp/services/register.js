import axios from "axios";

const registerUrl = "/api/register";

function register(username, email, password) {
  return axios.post(registerUrl, { username: username, email: email, password: password }).then((result) => result.data);
}

export default register;
