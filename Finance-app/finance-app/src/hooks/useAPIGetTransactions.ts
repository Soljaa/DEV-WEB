import axios from "axios";
import { URL_TRANSACTIONS, URL_BASE } from "../utils/const";
import CustomError from "../utils/CustomError";
import useUserStore from "../store/userStore";
import Transaction from "../interfaces/transaction";
import { useState, useEffect, useCallback } from "react";

const useAPIGetTransactions = () => {
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [sortField, setSortField] = useState<string | undefined>(undefined);
  const [sortDirection, setSortDirection] = useState<"asc" | "desc">("asc");
  const userId = useUserStore((s) => s.userId);
  const userToken = useUserStore((s) => s.userToken);

  const axiosInstance = axios.create({
    baseURL: URL_BASE,
  });

  const config = {
    headers: { Authorization: `Bearer ${userToken}` },
  };

  const getTransactions = useCallback(
    (page = 1, pageSize = 10, field?: string, dir?: "asc" | "desc") => {
      const offset = page - 1;
      let url = `${URL_TRANSACTIONS}/users/${userId}/pagination?offset=${offset}&pageSize=${pageSize}`;
      if (field && dir) {
        url += `&field=${field}&dir=${dir}`;
      }
      axiosInstance
        .get(url, config)
        .then((res) => {
          setTransactions(res.data.content);
          setTotalPages(res.data.totalPages);
        })
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
    },
    [userId, userToken]
  );

  useEffect(() => {
    getTransactions(currentPage, 10, sortField, sortDirection);
  }, [currentPage, sortField, sortDirection, getTransactions]);

  return { transactions, currentPage, totalPages, setCurrentPage, sortField, setSortField, sortDirection, setSortDirection, getTransactions };

};

export default useAPIGetTransactions;
