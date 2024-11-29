import React from 'react';
import { Button } from '../components/ui/button';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from '../components/ui/dialog';
import { PlusIcon } from '@radix-ui/react-icons';
import FormModal from './formModal';

const FloatingButtonWithModal: React.FC = () => {
  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button
          className="size-12 fixed bottom-6 right-6 p-3 bg-blue-500 text-white rounded-full shadow-lg hover:bg-blue-600 transition-colors"
        >
          <PlusIcon className='size-24'/>
        </Button>
      </DialogTrigger>
      <DialogContent className="sm:max-w-[425px]" aria-describedby={undefined}>
        <DialogHeader>
          <DialogTitle>Nova Transação</DialogTitle>
        </DialogHeader>
        <FormModal closeModal={() => {}} />
      </DialogContent>
    </Dialog>
  );
};

export default FloatingButtonWithModal;
