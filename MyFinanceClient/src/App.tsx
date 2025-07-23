import { useEffect, useState } from 'react';
import TransactionList from './components/TransactionList';
import TransactionForm from './components/TransactionForm';
import Dashboard from './components/Dashboard';
import TransactionFilters from './components/TransactionFilters';
import api from './services/api';
import type { Transaction } from './types/Transaction';
import type { Category } from './types/Category';

function App() {
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [editTransaction, setEditTransaction] = useState<Transaction | null>(null);
  const [categories, setCategories] = useState<Category[]>([]);

  const fetchTransactions = async () => {
    const response = await api.get<Transaction[]>('/transactions/');
    setTransactions(response.data);
  };

  const fetchCategories = async () => {
    const response = await api.get<Category[]>('/transactions/category/');
    setCategories(response.data);
  }

  useEffect(() => {
    fetchTransactions();
  }, []);

  useEffect(() => {
    fetchCategories();
  }, []);

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-blue-200 flex flex-col items-center py-8 px-2">
      <div className="w-full max-w-3xl bg-white rounded-xl shadow-lg p-8">
        <h1 className="text-3xl font-bold text-blue-700 mb-8 text-center drop-shadow">Transações Bancárias</h1>
        <Dashboard categories={categories} />
        <TransactionFilters setTransactions={setTransactions} categories={categories} />
        <div className="my-8">
          <TransactionList
            transactions={transactions}
            categories={categories}
            onChange={fetchTransactions}
            onEdit={(tx) => setEditTransaction(tx)}
          />
        </div>
        <TransactionForm 
          onTransactionSave={fetchTransactions} 
          onCategorySave={fetchCategories} 
          editTransaction={editTransaction}
          setEditTransaction={setEditTransaction}
          categories={categories}
        />
      </div>
    </div>
  )
}

export default App
