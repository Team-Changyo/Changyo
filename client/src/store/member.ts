import { atom } from 'recoil';
import { IMember } from 'types/auth';

export const memberInfoState = atom<IMember | null>({
	key: 'memberInfoState',
	default: null,
});
