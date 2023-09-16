import React, { useEffect, useState } from 'react';
import AccountSummary from 'components/organisms/account/AccountSummary';
import RemitHistoryFilterList from 'components/organisms/account/RemitHistoryFilterList';
import RemitHistoryListStack from 'components/organisms/account/RemitHistoryListStack';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import PageLayout from 'layouts/common/PageLayout';
import AccountDetailPageLayout from 'layouts/page/account/AccountDetailPageLayout';
import { useParams } from 'react-router-dom';
import { findAccountAllApi, findAccountInApi, findAccountOutApi } from 'utils/apis/account';
import { IDetailInfo } from 'types/account';
import LargeMoneySkeleton from 'components/atoms/skeleton/LargeMoneySkeleton';
import AccountHistoryListSkeleton from 'components/atoms/skeleton/AccountHIstoryListSkeleton';

function AccountDetailPage() {
	const [selectedMenu, setSelcetedMenu] = useState('0');
	const [detailInfo, setDetailInfo] = useState<IDetailInfo>();
	const { accountId } = useParams();
	const [isLoading, setIsLoading] = useState(true);

	const fetchData = async () => {
		try {
			if (accountId) {
				let response;
				switch (selectedMenu) {
					case '0': {
						response = await findAccountAllApi(accountId);
						break;
					}
					case '1': {
						response = await findAccountInApi(accountId);

						break;
					}
					case '2': {
						response = await findAccountOutApi(accountId);
						break;
					}
					default:
						return;
				}
				if (response.status === 200) {
					setDetailInfo(response.data.data);
					setTimeout(() => {
						setIsLoading(false);
					}, 300);
				}
				console.log(response);
			}
		} catch (error) {
			console.error(error);
		}
	};

	useEffect(() => {
		fetchData();
	}, [selectedMenu]);

	return (
		<PageLayout>
			<AccountDetailPageLayout
				Navbar={<SubTabNavbar text={detailInfo?.title as string} type="back" />}
				AccountSummary={
					isLoading ? (
						<LargeMoneySkeleton />
					) : (
						<AccountSummary
							bankCode={detailInfo?.bankCode as string}
							accountNumber={detailInfo?.accountNumber as string}
							totalMoney={detailInfo?.balance as number}
						/>
					)
				}
				RemitHistoryFilterList={
					<RemitHistoryFilterList selectedMenu={selectedMenu} setSelcetedMenu={setSelcetedMenu} />
				}
				RemitHistoryList={
					isLoading ? (
						<AccountHistoryListSkeleton />
					) : detailInfo ? (
						<RemitHistoryListStack detailInfo={detailInfo} />
					) : (
						<div>내역이 없습니다.</div>
					)
				}
			/>
		</PageLayout>
	);
}

export default AccountDetailPage;
