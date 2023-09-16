import React from 'react';
import { ReactComponent as QR } from 'assets/icons/qr/qr-default-icon.svg';
import MainTabNavbar from 'components/organisms/common/MainTabNavbar';
import QRPageLayout from 'layouts/page/qr/QRPageLayout';
import PageLayout from 'layouts/common/PageLayout';
import Button from 'components/organisms/common/Button';
import { useNavigate } from 'react-router-dom';
import QRList from 'components/organisms/qr/QRList';
import useAccountList from 'hooks/useAccountList';

function QRPage() {
	const navigate = useNavigate();
	const accountList = useAccountList();

	const handleCreateQRButtonClick = () => {
		if (accountList.length === 0) {
			alert('등록된 계좌가 없습니다. 계좌 등록 화면으로 이동합니다.');
			navigate('/account/register');

			return;
		}
		navigate('/qr/create');
	};

	return (
		<PageLayout>
			<QRPageLayout
				Navbar={<MainTabNavbar tabName="내 송금 QR" />}
				CreateQRButton={
					<Button handleClick={handleCreateQRButtonClick} text="송금QR 만들기" type="Primary" icon={<QR />} />
				}
				QRList={<QRList />}
			/>
		</PageLayout>
	);
}

export default QRPage;
