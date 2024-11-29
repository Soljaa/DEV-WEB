import axios from "axios";
import { URL_TRANSACTIONS, URL_BASE } from "../utils/const";
import useUserStore from "../store/userStore";

const useAPIDeleteTransaction = () => {
  const userToken = useUserStore((s) => s.userToken);

  const axiosInstance = axios.create({
    baseURL: URL_BASE,
  });

  const config = {
    headers: { Authorization: `Bearer ${userToken}` },
  };

  const deleteTransaction = async (id: Number) => {
    try {
      const response = await axiosInstance.delete(URL_TRANSACTIONS + `/delete/${id}`, config);
    } catch (error) {
      console.log(error)
    }
  };

  return { deleteTransaction };
};

export default useAPIDeleteTransaction;
