import type { Transaction } from '../types/Transaction';
import type { Category } from '../types/Category';
import api from '../services/api';

interface Props {
  transactions: Transaction[];
  categories: Category[];
  onChange: () => void;
  onEdit: (tx: Transaction) => void;
}

export default function TransactionList({ transactions, categories, onChange, onEdit }: Props) {
    const handleDelete = async (id: number) => {
        await api.delete(`/transactions/${id}`);
        onChange();
    };

    return (
        <div className="bg-white rounded-lg shadow p-4">
            <h2 className="text-lg font-bold text-blue-700 mb-4">Transações</h2>
            <ul className="flex flex-col gap-3">
                {transactions.map((t) => (
                    <li
                        key={t.id}
                        className={`flex flex-col md:flex-row md:items-center justify-between gap-2 p-4 rounded border ${
                            t.type === 1 ? 'bg-green-50 border-green-200' : 'bg-red-50 border-red-200'
                        }`}
                    >
                        <div className="flex-1">
                            <p className="font-semibold text-gray-800">
                                {t.description} <span className="text-xs text-gray-500">({categories.find((cat) => cat.id == t.category)?.name})</span>
                            </p>
                            <p className="text-sm text-gray-600">{t.date} - R$ {t.value.toFixed(2)}</p>
                        </div>
                        <div className="flex gap-2 mt-2 md:mt-0">
                            <button
                                onClick={() => onEdit(t)}
                                className="px-3 py-1 rounded bg-blue-500 text-white hover:bg-blue-600 transition text-sm"
                            >
                                Editar
                            </button>
                            <button
                                onClick={() => handleDelete(t.id)}
                                className="px-3 py-1 rounded bg-red-500 text-white hover:bg-red-600 transition text-sm"
                            >
                                Excluir
                            </button>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}