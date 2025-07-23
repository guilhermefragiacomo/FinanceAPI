import type { Transaction } from '../types/Transaction';
import type { Summary } from '../types/Summary';
import { useState, useEffect } from 'react';
import api from '../services/api';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import type { Category } from '../types/Category';

ChartJS.register(ArcElement, Tooltip, Legend);

interface Props {
    categories: Category[];
}

export default function Dashboard({ categories }: Props) {
    const [categoryFilter, setCategoryFilter] = useState<Category>({
        id: -1,
        name: '',
    });
    const [summary, setSummary] = useState<Summary | null>(null);

    const fetchSummary = async () => {
        if (categoryFilter !== null && Number(categoryFilter.id) >= 0) {
            const response = await api.get<Summary>(`/transactions/summary/${categoryFilter.id}`);
            setSummary(response.data);
            console.log(response);
        } else {
            const response = await api.get<Summary>('/transactions/summary/');
            setSummary(response.data);
            console.log(response);
        }
    }

    useEffect(() => {
        fetchSummary();
    }, []);

    const handleChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
    ) => {
        const { name, value } = e.target;
        setCategoryFilter((prev) => ({
            ...prev,
            [name]: name === 'value' ? parseFloat(value) : value,
        }));
    };

    return (
        <div className="bg-blue-100 rounded-lg shadow flex flex-col md:flex-row items-center justify-between p-6 mb-8 gap-6">
            <div className="flex-1 flex flex-col gap-2">
                <h2 className="text-lg font-bold text-blue-700 mb-1">Resumo Financeiro</h2>
                <div className="flex flex-col gap-1 mb-4">
                    <span className="text-green-600 font-semibold">Receitas: <span className="font-bold">R$ {summary?.total_revenue.toFixed(2)}</span></span>
                    <span className="text-red-500 font-semibold">Despesas: <span className="font-bold">R$ {summary?.total_expense.toFixed(2)}</span></span>
                    <span className={
                        summary?.current_balance
                            ? 'text-blue-700 font-bold text-lg'
                            : 'text-red-700 font-bold text-lg'
                    }>
                        Saldo: R$ {summary?.current_balance.toFixed(2)}
                    </span>
                </div>
                <div className="flex flex-col md:flex-row gap-2 items-center">
                    <select
                    name="id"
                    value={categoryFilter.id}
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
                    <button
                        type="button"
                        onClick={fetchSummary}
                        className="bg-blue-600 text-white px-4 py-2 rounded font-semibold hover:bg-blue-700 transition"
                    >
                        Buscar Resumo por Categoria
                    </button>
                </div>
            </div>
        </div>
    );
}