import { Button } from '../components/ui/button';
import { Input } from '../components/ui/input';
import { Label } from '../components/ui/label';
import { useState } from 'react';
import { z } from 'zod';
import PostTransaction from '../interfaces/postTransaction';
import useAPIPutTransaction from '../hooks/useAPIPostTransaction';

interface FormModalProps {
  closeModal: () => void;
}

const schema = z.object({
  amount: z.number().min(1, { message: "O valor deve ser maior que zero."}),
  description: z.string().min(1, { message: "A descrição deve ser informada."}),
  frequencyType: z.enum(["OCCASIONAL", "REGULAR"]),
  transactionDate: z.string().min(1, { message: "A data deve ser informada."}),
  transactionType: z.enum(["EXPENSE", "INCOME"]),
});

const FormModal: React.FC<FormModalProps> = ({ closeModal }) => {
  const [formData, setFormData] = useState<PostTransaction>({
    amount: 0,
    description: '',
    frequencyType: 'OCCASIONAL',
    transactionDate: '',
    transactionType: 'EXPENSE',
  });

  const [formErrors, setFormErrors] = useState<{ [key: string]: string | null }>({
    amount: null,
    description: null,
    frequencyType: null,
    transactionDate: null,
    transactionType: null,
  });

  const { addTransaction } = useAPIPutTransaction();

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const validatedData = schema.parse(formData);
      console.log('Dados do formulário:', validatedData);
      addTransaction(validatedData);
      closeModal();
    } catch (error) {
      if (error instanceof z.ZodError) {
        console.error('Erro de validação:', error.errors);
        const newErrors: { [key: string]: string | null } = {};
        error.errors.forEach((err) => {
          const path = err.path.join('.');
          if (!newErrors[path]) {
            newErrors[path] = err.message;
          }
        });
        setFormErrors(newErrors);
      }
    }
  };

  const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: name === 'amount' ? parseFloat(value) : value,
    });

    setFormErrors({
      ...formErrors,
      [name]: null,
    });
  };

  return (
    <div className="p-4">
      <div className="grid gap-2 py-4">
        <div className="grid grid-cols-4 items-center gap-4">
          <Label htmlFor="amount" className="text-right">
            Valor
          </Label>
          <Input id="amount" name="amount" type="number" value={formData.amount.toString()} onChange={handleChange} className="col-span-3" />
        </div>
        {formErrors.amount && (
          <div className="text-xs text-center text-red-500 ml-12">{formErrors.amount}</div>
        )}
        <div className="grid grid-cols-4 items-center gap-4">
          <Label htmlFor="description" className="text-right">
            Descrição
          </Label>
          <Input id="description" name="description" value={formData.description} onChange={handleChange} className="col-span-3" />
        </div>
        {formErrors.description && (
          <div className="text-xs text-center text-red-500 ml-12">{formErrors.description}</div>
        )}
        <div className="grid grid-cols-4 items-center gap-4">
          <Label htmlFor="transactionDate" className="text-right">
            Data da Transação
          </Label>
          <Input id="transactionDate" name="transactionDate" type="date" value={formData.transactionDate} onChange={handleChange} className="col-span-3" />
        </div>
        {formErrors.transactionDate && (
          <div className="text-xs text-center text-red-500 ml-12">{formErrors.transactionDate}</div>
        )}
        <div className="grid grid-cols-4 items-center gap-4">
          <Label htmlFor="transactionType" className="text-right">
            Tipo de Transação
          </Label>
          <select id="transactionType" name="transactionType" value={formData.transactionType} onChange={handleChange} className="col-span-3">
            <option value="EXPENSE">Despesa</option>
            <option value="INCOME">Rendimento</option>
          </select>
        </div>
        {formErrors.transactionType && (
          <div className="text-xs text-center text-red-500 ml-12">{formErrors.transactionType}</div>
        )}
        <div className="grid grid-cols-4 items-center gap-4">
          <Label htmlFor="frequencyType" className="text-right">
            Frequência
          </Label>
          <select id="frequencyType" name="frequencyType" value={formData.frequencyType} onChange={handleChange} className="col-span-3">
            <option value="OCCASIONAL">Ocasional</option>
            <option value="REGULAR">Regular</option>
          </select>
        </div>
        {formErrors.frequencyType && (
          <div className="text-xs text-center text-red-500 ml-12">{formErrors.frequencyType}</div>
        )}
      </div>
      <div className="flex justify-end space-x-2">
        <Button type="submit" onClick={handleSubmit}>
          Enviar
        </Button>
      </div>
    </div>
  );
};

export default FormModal;
