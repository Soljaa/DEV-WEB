import useAPIGetTransactions from "../hooks/useAPIGetTransactions";
import useAPIDeleteTransactions from "../hooks/useAPIDelete";
import Transaction from "../interfaces/transaction";
import {
  TriangleDownIcon,
  TriangleUpIcon,
  ReaderIcon,
  TrashIcon,
  Pencil2Icon,
} from "@radix-ui/react-icons";
import { useState } from "react";

const formatAmount = (amount: number, type: string) => {
  const formattedAmount = amount.toLocaleString("pt-BR", {
    style: "currency",
    currency: "BRL",
  });
  return type === "EXPENSE" ? `-${formattedAmount}` : formattedAmount;
};

const formatDate = (date: string) => {
  const [year, month, day] = date.split("-");
  return `${day}/${month}/${year}`;
};

const translateTransactionType = (type: string) => {
  switch (type) {
    case "EXPENSE":
      return "Gasto";
    case "INCOME":
      return "Renda";
    default:
      return type;
  }
};

export default function transactionTable() {
  const {
    transactions,
    currentPage,
    totalPages,
    setCurrentPage,
    sortField,
    setSortField,
    sortDirection,
    setSortDirection,
    getTransactions,
  } = useAPIGetTransactions();

  const handlePreviousPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
      getTransactions(currentPage - 1, 10, sortField, sortDirection);
    }
  };

  const { deleteTransaction } = useAPIDeleteTransactions();
  const [isDeleting, setIsDeleting] = useState(false);

  const deleteRow = (id: Number) => {
    setIsDeleting(true);
    try {
      deleteTransaction(id);
      getTransactions(1, 10);
      setCurrentPage(1);
    } finally {
      setIsDeleting(false);
    }
  };

  const handleNextPage = () => {
    if (currentPage < totalPages) {
      setCurrentPage(currentPage + 1);
      getTransactions(currentPage + 1, 10, sortField, sortDirection);
    }
  };

  const handleSort = (field: string) => {
    if (sortField === field) {
      const newSortDirection = sortDirection === "asc" ? "desc" : "asc";
      setSortDirection(newSortDirection);
      getTransactions(currentPage, 10, field, newSortDirection);
    } else {
      setSortField(field);
      setSortDirection("asc");
      getTransactions(currentPage, 10, field, "asc");
    }
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Transações</h1>
      <div className="overflow-y-auto max-h-[620px]">
        <table className="min-w-full divide-y divide-gray-200 rounded-md">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                <div className="flex items-center gap-2">
                  ID
                  {sortField !== "id" ? (
                    <div onClick={() => handleSort("id")}>
                      <TriangleUpIcon /> <TriangleDownIcon />
                    </div>
                  ) : sortDirection === "asc" ? (
                    <div onClick={() => handleSort("id")}>
                      <TriangleUpIcon />
                    </div>
                  ) : (
                    <div onClick={() => handleSort("id")}>
                      <TriangleDownIcon />
                    </div>
                  )}
                </div>
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                <div className="flex items-center gap-2">
                  Descrição
                  {sortField !== "description" ? (
                    <div onClick={() => handleSort("description")}>
                      <TriangleUpIcon /> <TriangleDownIcon />
                    </div>
                  ) : sortDirection === "asc" ? (
                    <div onClick={() => handleSort("description")}>
                      <TriangleUpIcon />
                    </div>
                  ) : (
                    <div onClick={() => handleSort("description")}>
                      <TriangleDownIcon />
                    </div>
                  )}
                </div>
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                <div className="flex items-center gap-2">
                  Valor
                  {sortField !== "amount" ? (
                    <div onClick={() => handleSort("amount")}>
                      <TriangleUpIcon /> <TriangleDownIcon />
                    </div>
                  ) : sortDirection === "asc" ? (
                    <div onClick={() => handleSort("amount")}>
                      <TriangleUpIcon />
                    </div>
                  ) : (
                    <div onClick={() => handleSort("amount")}>
                      <TriangleDownIcon />
                    </div>
                  )}
                </div>
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                <div className="flex items-center gap-2">
                  Data
                  {sortField !== "transactionDate" ? (
                    <div onClick={() => handleSort("transactionDate")}>
                      <TriangleUpIcon /> <TriangleDownIcon />
                    </div>
                  ) : sortDirection === "asc" ? (
                    <div onClick={() => handleSort("transactionDate")}>
                      <TriangleUpIcon />
                    </div>
                  ) : (
                    <div onClick={() => handleSort("transactionDate")}>
                      <TriangleDownIcon />
                    </div>
                  )}
                </div>
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                <div className="flex items-center gap-2">
                  Tipo
                  {sortField !== "transactionType" ? (
                    <div onClick={() => handleSort("transactionType")}>
                      <TriangleUpIcon /> <TriangleDownIcon />
                    </div>
                  ) : sortDirection === "asc" ? (
                    <div onClick={() => handleSort("transactionType")}>
                      <TriangleUpIcon />
                    </div>
                  ) : (
                    <div onClick={() => handleSort("transactionType")}>
                      <TriangleDownIcon />
                    </div>
                  )}
                </div>
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                <div className="flex items-center gap-2">
                  Frequência
                  {sortField !== "frequencyType" ? (
                    <div onClick={() => handleSort("frequencyType")}>
                      <TriangleUpIcon /> <TriangleDownIcon />
                    </div>
                  ) : sortDirection === "asc" ? (
                    <div onClick={() => handleSort("frequencyType")}>
                      <TriangleUpIcon />
                    </div>
                  ) : (
                    <div onClick={() => handleSort("frequencyType")}>
                      <TriangleDownIcon />
                    </div>
                  )}
                </div>
              </th>
              <th> </th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200 text-white">
            {transactions.map((transaction: Transaction) => (
              <tr
                key={transaction.id}
                className={
                  transaction.transactionType === "EXPENSE"
                    ? "bg-red-500"
                    : "bg-green-500"
                }
              >
                <td className="px-6 py-4 whitespace-nowrap">
                  {transaction.id}
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  {transaction.description}
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  {formatAmount(
                    transaction.amount,
                    transaction.transactionType
                  )}
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  {formatDate(transaction.transactionDate)}
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  {translateTransactionType(transaction.transactionType)}
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  {transaction.frequencyType === "OCCASIONAL"
                    ? "Ocasional"
                    : "Regular"}
                </td>
                <td>
                  <div className="flex gap-4">
                    {isDeleting ? (
                      "Loading..."
                    ) : (
                      <TrashIcon
                        className="size-6 cursor-pointer"
                        onClick={() =>deleteRow(transaction.id)}
                      />
                    )}
                    <Pencil2Icon className="size-5 cursor-pointer" />
                    <ReaderIcon className="size-6 cursor-pointer" />
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="flex justify-between mt-4">
        <button
          onClick={handlePreviousPage}
          disabled={currentPage === 1}
          className="px-4 py-2 bg-blue-500 text-white rounded disabled:opacity-50"
        >
          Página Anterior
        </button>
        <span>
          Página {currentPage} de {totalPages}
        </span>
        <button
          onClick={handleNextPage}
          disabled={currentPage === totalPages}
          className="px-4 py-2 bg-blue-500 text-white rounded disabled:opacity-50"
        >
          Próxima Página
        </button>
      </div>
    </div>
  );
}
