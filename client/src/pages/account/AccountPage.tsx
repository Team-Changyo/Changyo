import React, { useEffect, useState } from 'react';
import PageLayout from 'layouts/common/PageLayout';
import AccountPageLayout from 'layouts/page/account/AccountPageLayout';
import AccountTotalSummary from 'components/organisms/account/AccountTotalSummary';
import AccountFilterList from 'components/organisms/account/AccountFilterList';
import AccountList from 'components/organisms/account/AccountList';
import MainTabNavbar from 'components/organisms/common/MainTabNavbar';
import { useNavigate } from 'react-router-dom';
import UnderlineButton from 'components/atoms/common/UnderlineButton';
import { findAllAccountApi } from 'utils/apis/account';
import { isAxiosError } from 'axios';
import { toast } from 'react-hot-toast';
import { IAccount } from 'types/account';
import AccountListSkeleton from 'components/atoms/skeleton/AccountListSkeleton';
import LargeMoneySkeleton from 'components/atoms/skeleton/LargeMoneySkeleton';

function AccountPage() {
	const [selectedCode, setSelectedCode] = useState('000'); // 은행구분
	const [accountList, setAccountList] = useState<IAccount[]>([]);
	const [totalMoney, setTotalMoney] = useState(0);
	const [bankList, setBankList] = useState<string[]>([]);
	const navigate = useNavigate();
	const [isLoading, setIsLoading] = useState(true);

	const fetchAccountData = async () => {
		try {
			const response = await findAllAccountApi();

			if (response.status === 200) {
				const arr: string[] = [];
				response.data.data?.accountDetailResponses.forEach((el: IAccount) => {
					arr.push(el?.bankCode);
				});

				const set = new Set(arr);
				setBankList([...set]);
				setAccountList(response.data.data.accountDetailResponses);
				setTotalMoney(response.data.data.accountTotalBalance);
				setTimeout(() => {
					setIsLoading(false);
				}, 300);
			}
		} catch (error) {
			if (isAxiosError(error)) {
				toast.error(error.response?.data.message);
			}
		}
	};

	useEffect(() => {
		fetchAccountData();
	}, []);

	return (
		<PageLayout>
			<AccountPageLayout
				Navbar={
					<MainTabNavbar
						tabName="내 계좌"
						navBtn={<UnderlineButton text="계좌 등록하기" handleClick={() => navigate('register')} type="Primary" />}
					/>
				}
				AccountSummary={
					isLoading ? (
						<LargeMoneySkeleton />
					) : (
						<AccountTotalSummary accountCnt={accountList.length} totalMoney={totalMoney} />
					)
				}
				AccountFilterList={
					<AccountFilterList bankCodes={bankList} selectedCode={selectedCode} setSelectedCode={setSelectedCode} />
				}
				AccountList={
					isLoading ? <AccountListSkeleton /> : <AccountList accountList={accountList} selectedCode={selectedCode} />
				}
			/>
		</PageLayout>
	);
}

export default AccountPage;
