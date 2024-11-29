interface Transaction {
  amount: number;
  description: string;
  frequencyType: "OCCASIONAL" | "REGULAR";
  id: number;
  transactionDate: string;
  transactionType: "EXPENSE" | "INCOME";
}

export default Transaction;
