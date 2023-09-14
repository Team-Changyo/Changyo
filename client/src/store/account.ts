import { atom } from 'recoil';
import { IAccount } from 'types/account';

export const accountListState = atom<IAccount[]>({
	key: 'accountListState',
	default: [],
});
