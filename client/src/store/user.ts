import { atom } from 'recoil';
import { IAuth, IMember } from 'types/auth';

export const authState = atom<IAuth | null>({
	key: ' authState',
	default: null,
});

export const memberInfoState = atom<IMember | null>({
	key: 'memberInfoState',
	default: null,
});
