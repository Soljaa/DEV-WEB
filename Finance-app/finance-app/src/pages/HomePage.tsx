import TransactionsTable from "../components/transactionsTable";
import TransactionForm from "../components/transactionForm";
import { Button } from "../components/ui/button";
import { ReaderIcon } from "@radix-ui/react-icons";
import { Link } from 'react-router-dom'; 

export default function HomePage() {
  return (
    <div>
      <TransactionsTable />
      <TransactionForm />
      <Link to="/report">
        <Button className="size-12 fixed bottom-6 right-20 p-3 bg-blue-500 text-white rounded-full shadow-lg hover:bg-blue-600 transition-colors">
          <ReaderIcon className="size-24" />
        </Button>
      </Link>
    </div>
  );
}
