interface Transaction {
    amount: number;
    description: string;
    frequencyType: "OCCASIONAL" | "REGULAR";
    transactionDate: string;
    transactionType: "EXPENSE" | "INCOME";
  }
  
  export default Transaction;
  