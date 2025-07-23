import { useState, useEffect } from 'react';
import api from '../services/api';
import type { Transaction, TransactionType } from '../types/Transaction';
import type { Category } from '../types/Category';

interface Props {
    onTransactionSave: () => void;
    onCategorySave: () => void;
    editTransaction?: Transaction | null;
    setEditTransaction: (tx: Transaction | null) => void;
    categories: Category[];
}

export default function TransactionForm({ onTransactionSave, onCategorySave, editTransaction, setEditTransaction, categories }: Props) {
    const [newCategory, setNewCategory] = useState<Boolean>(false)

    const [formCategoryData, setFormCategoryData] = useState<Omit<Category, 'id'>>({
        name: '',
    });
    const [formTransactionData, setFormTransactionData] = useState<Omit<Transaction, 'id'>>({
        description: '',
        value: 0,
        type: 1,
        category: 1,
        date: '',
    });

    useEffect(() => {
        if (editTransaction) {
            const { id, ...rest } = editTransaction;
            setFormTransactionData(rest);
        }
    }, [editTransaction]);

    const handleChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
    ) => {
        const { name, value } = e.target;
        if (value === '-1') {
            setNewCategory(true)
        }
        setFormTransactionData((prev) => ({
            ...prev,
            [name]: name === 'value' ? parseFloat(value) : value,
        }));
    };

    const handleCategoryChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
    ) => {
        const { name, value} = e.target;
        setFormCategoryData((prev) => ({
            ...prev,
            [name]: name === 'value' ? parseFloat(value) : value,
        }))
    }

    const handleTransactionSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        if (editTransaction) {
            await api.put(`/transactions/${editTransaction.id}`, formTransactionData);
        } else {
            await api.post('/transactions/', formTransactionData);
        }

        setFormTransactionData({
            description: '',
            value: 0,
            type: 1,
            category: 1,
            date: '',
        });

        onTransactionSave();
        setEditTransaction(null);
    }

    const handleCategorySubmit = async () => {
        await api.post(`/transactions/category/`, formCategoryData)

        setFormCategoryData({
            name: '',
        })

        onCategorySave();
        setNewCategory(false);
    }

    return (
        <form onSubmit={handleTransactionSubmit} className="bg-blue-50 rounded-lg shadow p-6 flex flex-col gap-4">
            <h2 className="text-xl font-semibold text-blue-700 mb-2 text-center">
                {editTransaction ? 'Editar Transação' : 'Nova Transação'}
            </h2>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <input
                    type="text"
                    name="description"
                    placeholder="Descrição"
                    value={formTransactionData.description}
                    onChange={handleChange}
                    required
                    className="border border-blue-200 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 bg-white"
                />
                <input
                    type="number"
                    name="value"
                    placeholder="Valor"
                    value={formTransactionData.value}
                    onChange={handleChange}
                    required
                    className="border border-blue-200 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 bg-white"
                />
                <select
                    name="type"
                    value={formTransactionData.type}
                    onChange={handleChange}
                    className="border border-blue-200 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 bg-white"
                >
                    <option value="1">Receita</option>
                    <option value="0">Despesa</option>
                </select>
                <select
                    name="category"
                    value={formTransactionData.category}
                    onChange={handleChange}
                    className="border border-blue-200 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 bg-white"
                >
                    {categories.map((cat) => (
                        <option key={cat.id} value={cat.id}>
                            {cat.name}
                        </option>
                    ))}
                    <option key="-1" value="-1">Nova categoria</option>
                </select>
                {newCategory && (
                    <div className="col-span-1 md:col-span-2 flex gap-2 items-center">
                        <input
                            type="text"
                            name="name"
                            placeholder="Categoria"
                            value={formCategoryData.name}
                            onChange={handleCategoryChange}
                            className="border border-blue-200 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 bg-white flex-1"
                        />
                        <button
                            type="button"
                            onClick={handleCategorySubmit}
                            className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition"
                        >
                            Criar nova categoria
                        </button>
                    </div>
                )}
                <input
                    type="date"
                    name="date"
                    value={formTransactionData.date}
                    onChange={handleChange}
                    required
                    className="border border-blue-200 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 bg-white"
                />
            </div>

            <button
                type="submit"
                className="mt-4 bg-blue-600 text-white px-6 py-2 rounded font-semibold hover:bg-blue-700 transition"
            >
                {editTransaction ? 'Atualizar' : 'Adicionar'}
            </button>
        </form>
    )
}