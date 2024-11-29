import axios from "axios";
import { URL_TRANSACTIONS, URL_BASE } from "../utils/const";
import useUserStore from "../store/userStore";
import PostTransaction from "../interfaces/postTransaction";
import { useState, useEffect, useCallback } from "react";

const useAPIPostTransaction = () => {
    const userId = useUserStore((s) => s.userId);
  const userToken = useUserStore((s) => s.userToken);

  const axiosInstance = axios.create({
    baseURL: URL_BASE,
  });

  const config = {
    headers: { Authorization: `Bearer ${userToken}` },
  };

  const addTransaction = async (newTransaction: PostTransaction) => {
    try {
      const response = await axiosInstance.post(URL_TRANSACTIONS + `/create/${userId}`, newTransaction, config);
      if (response.status === 200) {
        console.log('Nova transação adicionada:', response.data);
        refreshTransactions();
      }
    } catch (error) {
      console.log(error)
    }
  };

  const refreshTransactions = useCallback(() => {
    console.log('Recarregar transações após adição.');
    window.location.reload()
  }, []);

  return { addTransaction };
};

export default useAPIPostTransaction;
