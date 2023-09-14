import { useEffect } from 'react';
import { isAxiosError } from 'axios';
import { toast } from 'react-hot-toast';
import { useRecoilState } from 'recoil';
import { accountListState } from 'store/account';
import { findAllAccountApi } from 'utils/apis/account';

function useAccountList() {
	const [accountList, setAccountList] = useRecoilState(accountListState);

	const fetchData = async () => {
		try {
			const response = await findAllAccountApi();
			console.log(response);
			if (response.status === 200) {
				setAccountList(response.data.data.accountDetailResponses);
			}
		} catch (error) {
			console.error(error);
			if (isAxiosError(error)) {
				toast.error(error.response?.data.message);
			}
		}
	};

	useEffect(() => {
		fetchData();
	}, []);

	return accountList;
}

export default useAccountList;
