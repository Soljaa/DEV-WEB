import axios from "axios";
import { URL_AUTH, URL_BASE } from "../utils/const";
import User from "../interfaces/user";
import TokenResponse from "../interfaces/tokenResponse";
import CustomError from "../utils/CustomError";

const useAPIAuth = () => {
  const axiosInstance = axios.create({
    baseURL: URL_BASE,
  });

  const login = (user: User) =>
    axiosInstance
      .post<TokenResponse>(URL_AUTH + "/login", user)
      .then((res) => res.data)
      .catch((error) => {
        if (error.response) {
          if (error.response.data.errorCode === 422) {
            throw new CustomError(
              error.response.data.message,
              error.response.data.errorCode,
              Object.values(error.response.data.map)
            );
          }
          throw new CustomError(
            error.response.data.message,
            error.response.data.errorCode
          );
        } else if (error.request) {
          throw error;
        } else {
          throw error;
        }
      });

  return { login };
};

export default useAPIAuth;
