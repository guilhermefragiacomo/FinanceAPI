import { useState } from 'react';
import type { Transaction } from '../types/Transaction';
import api from '../services/api';
import type { Category } from '../types/Category';

interface Filter {
    type?: number;
    category?: number;
    month?: string;
}

interface Props {
    categories: Category[];
    setTransactions: (transactions: Transaction[]) => void;
}

export default function TransactionFilters({ setTransactions, categories }: Props) {
    const [formFilterData, setFormFilterData] = useState({
        type: -1,
        category: -1,
        month: 0,
    });
    
    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        fetchTransactions();
    }

    const fetchTransactions = async () => {
      let params: any = {}

      if (formFilterData.type !== -1) params.type = Number(formFilterData.type);
      if (formFilterData.category !== -1) params.category = Number(formFilterData.category);
      if (formFilterData.month >= 1) params.month = Number(formFilterData.month);

      if (Object.keys(params).length > 0) {
        const response = await api.get<Transaction[]>('/transactions/filter/', {params});
        console.log(params);
        console.log(response);
        setTransactions(response.data);
      } else {
        const response = await api.get<Transaction[]>('/transactions/');
        setTransactions(response.data);
      }

      setFormFilterData({
        type: -1,
        category: -1,
        month: 0,
      })
    };

    const handleChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
    ) => {
        const { name, value } = e.target;
        setFormFilterData((prev) => ({
            ...prev,
            [name]: name === 'value' ? parseFloat(value) : value,
        }));
    };

    return (
        <form
            onSubmit={handleSubmit}
            className="flex flex-col md:flex-row items-center gap-4 bg-blue-50 rounded-lg shadow p-4 mb-6"
        >
            <select
                name="type"
                value={formFilterData.type}
                onChange={handleChange}
                className="border border-blue-200 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 bg-white"
            >
                <option value="-1">Todos os tipos</option>
                <option value="1">Receitas</option>
                <option value="0">Despesas</option>
            </select>

            <select
                name="category"
                value={formFilterData.category}
                onChange={handleChange}
                className="border border-blue-200 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 bg-white"
            >
                {categories.map((cat) => (
                    <option key={cat.id} value={cat.id}>
                        {cat.name}
                    </option>
                ))}
                <option key="-1" value="-1">Todas as categorias</option>
            </select>

            <input
                name="month"
                type="number"
                value={formFilterData.month}
                onChange={handleChange}
                className="border border-blue-200 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 bg-white"
            />

            <button
                type="submit"
                className="bg-blue-600 text-white px-6 py-2 rounded font-semibold hover:bg-blue-700 transition"
            >
                Filtrar
            </button>
        </form>
    );
}